package module_3.parallel_fiction;

import module_3.utils.Logger;
import module_3.utils.MeasurementUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class StoryPipelineDemo {
    public static void main(String[] args){
        int POOL_SIZE = 100;
        int IO_MILLIS = 100;
        int CPU_MILLIS = 1;
        double CHANCE_OF_ERROR = 0.2;

        List<StoryTask> storyTaskList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++){
            storyTaskList.add(StoryTask.of(i, "Author-"+i, "Chapter-"+i));
        }
        StoryStorage storage = new StoryStorage(IO_MILLIS);
        StoryService service = new StoryService(IO_MILLIS, CPU_MILLIS, IO_MILLIS, CHANCE_OF_ERROR);

        try(ExecutorService es = Executors.newFixedThreadPool(POOL_SIZE)){
            MeasurementUtils.measure(
                    "Using ThreadPool",
                    () -> runPipeline(storyTaskList, storage, service, es));
        }
        Logger.info("Snap: %s", storage.snapshot().values().stream().limit(3).toList());
        storage.clear();

        try(ExecutorService vt = Executors.newVirtualThreadPerTaskExecutor()){
            MeasurementUtils.measure(
                    "Using VirtualThreads",
                    () -> runPipeline(storyTaskList, storage, service, vt));
        }
        storage.clear();
    }

    private static void runPipeline(
            List<StoryTask> storyTaskList,
            StoryStorage storage,
            StoryService service, ExecutorService es
    ){
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (StoryTask storyTask : storyTaskList){
            CompletableFuture<Void> future = CompletableFuture
                    .supplyAsync(() -> service.fetchDraft(storyTask), es)
                    .thenApplyAsync(service::editText)
                    .thenApplyAsync(editText -> service.finalizeStory(storyTask, editText), es)
                    .thenAccept(storage::save)
                    .exceptionally(ex -> {
                        //Logger.error(ex.getMessage());
                        return null;
                    });
            futures.add(future);
        }
        for (CompletableFuture<Void> future : futures){
            future.join();
        }
        Logger.log("Expected: %d. Actual: %d", storyTaskList.size(), storage.size());
    }


}
