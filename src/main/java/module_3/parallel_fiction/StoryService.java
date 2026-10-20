package module_3.parallel_fiction;

import module_3.utils.BusyCpuUtil;
import utils.ThreadSleepUtil;

import java.util.concurrent.ThreadLocalRandom;

public final class StoryService {
    private final int fetchDraftMs;
    private final int editTextMs;
    private final int finalizeMs;
    private final double chanceOfEditingError;

    public StoryService(int fetchDraftMs, int editTextMs, int finalizeMs, double chanceOfEditingError){
        validMs(fetchDraftMs);
        validMs(editTextMs);
        validMs(finalizeMs);

        if (chanceOfEditingError < 0 || chanceOfEditingError > 1){
            throw new IllegalArgumentException("chanceOfEditingError is out of bound (0,1)");
        }

        this.fetchDraftMs = fetchDraftMs;
        this.editTextMs = editTextMs;
        this.finalizeMs = finalizeMs;
        this.chanceOfEditingError = chanceOfEditingError;
    }

    private void validMs(int ms){
        if (ms < 0){
            throw new IllegalArgumentException("ms must be more than 0");
        }
    }

    public String fetchDraft(StoryTask task){
        ThreadSleepUtil.safeSleepWithJitter(fetchDraftMs);
        if (Thread.currentThread().isInterrupted()){
            throw new IllegalStateException("Task was interrupted");
        }
        return "draft %d by %s".formatted(task.id(), task.author());
    }

    public String editText(String draft) {
        if (Thread.currentThread().isInterrupted()) {
            throw new IllegalStateException("Task was interrupted before editing");
        }

        if (ThreadLocalRandom.current().nextDouble() < chanceOfEditingError){
            throw new IllegalArgumentException("Error occurred while editing");
        }
        BusyCpuUtil.spinOnCpuMillisWithJitter(editTextMs);

        if (Thread.currentThread().isInterrupted()) {
            throw new IllegalStateException("Task was interrupted during editing");
        }
        return "edit : " + draft;
    }

    public StoryResult finalizeStory(StoryTask task, String editedText){
        ThreadSleepUtil.safeSleepWithoutThrow(finalizeMs);
        if (Thread.currentThread().isInterrupted()){
            throw new IllegalStateException("Task was interrupted");
        }
        return new StoryResult(task.id(), editedText);
    }

}
