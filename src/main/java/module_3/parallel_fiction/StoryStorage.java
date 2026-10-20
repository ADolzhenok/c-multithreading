package module_3.parallel_fiction;

import utils.ThreadSleepUtil;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class StoryStorage {
    private ConcurrentHashMap<Long, StoryResult> map = new ConcurrentHashMap<>();
    private final int saveTimeMillis;

    public StoryStorage (int saveTimeMillis){
        if (saveTimeMillis <= 0){
            throw new IllegalArgumentException();
        }

        this.saveTimeMillis = saveTimeMillis;
    }

    public void save(StoryResult result){
        ThreadSleepUtil.safeSleepWithoutThrow(saveTimeMillis);
        if (Thread.currentThread().isInterrupted()){
            throw new IllegalStateException("Task was interrupted");
        }
        map.put(result.taskId(), result);
    }

    public Optional<StoryResult> get(long taskId){
        return Optional.ofNullable(map.get(taskId));
    }

    public int size(){
        return map.size();
    }

    public Map<Long, StoryResult> snapshot(){
        return Map.copyOf(map);
    }

    public void clear() {
        map.clear();
    }

}
