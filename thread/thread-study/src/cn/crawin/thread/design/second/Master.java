package cn.crawin.thread.design.second;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    // 盛放任务的容器
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
    // 盛放worker的集合
    private HashMap<String, Thread> workers = new HashMap<String, Thread>();
    // 盛放每一个worker执行任务的结果集合
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

    // 构造方法
    public Master(Worker worker, int workerCount){
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);

        for (int i = 0; i < workerCount; i++){
            this.workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    // 提交任务的方法
    public void submit(Task task){
        this.workQueue.add(task);
    }

    // 执行的方法，启动所有的worker方法去执行任务
    public void execute(){
        for (Map.Entry<String, Thread> m : workers.entrySet()){
            m.getValue().start();
        }
    }

    // 判断是否运行结束的方法
    public boolean isComplete(){
        for (Map.Entry<String, Thread> m : workers.entrySet()){
            if (m.getValue().getState() != Thread.State.TERMINATED)
                return false;
        }
        return true;
    }

    // 计算结果方法
    public int getResult(){
        int priceResult = 0;
        for (Map.Entry<String, Object> m : resultMap.entrySet())
            priceResult += (Integer)m.getValue();

        return priceResult;
    }
}
