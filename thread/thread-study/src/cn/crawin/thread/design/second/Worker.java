package cn.crawin.thread.design.second;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true){
            Task input = this.workQueue.poll();
            if (input == null)
                break;

            Object output = handle(input);
            this.resultMap.put(Integer.toString(input.getId()), output);
        }
    }

    public Object handle(Task input) {
        Object output = null;

        // 处理任务的耗时，模拟
        try {
            Thread.sleep(500);
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return output;
    }
}
