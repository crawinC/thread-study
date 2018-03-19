package cn.crawin.thread.conn;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

    // 承装元素
    private LinkedList<Object> list = new LinkedList<Object>();

    // 计数器
    private AtomicInteger count = new AtomicInteger(0);

    // 制定上限和下限
    private final int minSize = 0;
    private final int maxSize;

    public MyQueue(int size){
        this.maxSize = size;
    }

    // 初始化一个对象，用于加锁
    private final Object lock = new Object();

    /**
     * put(obj) 把obj加到BlockingQueue里，如果BlockingQueue没有空间
     * 则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续
     * @param obj
     */
    public void put(Object obj){
        synchronized (lock){
            while (count.get() == this.maxSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 加入元素
            list.add(obj);
            // 计数器累加
            count.incrementAndGet();
            // 通知另外一个线程(唤醒)
            lock.notify();
            System.out.println("新加入的元素为：" + obj);
        }
    }

    public Object take(){
        Object ret = null;

        synchronized (lock){
            while (count.get() == this.minSize){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 做移除元素操作
            ret = list.removeFirst();
            // 计数器递减
            count.decrementAndGet();
            // 唤醒另外一个线程
            lock.notify();
        }
        return ret;
    }

    public int getSize(){
        return this.count.get();
    }

    public static void main(String[] args) {

        final MyQueue mq = new MyQueue(5);
        mq.put("a");
        mq.put("b");
        mq.put("c");
        mq.put("d");
        mq.put("e");

        System.out.println("当前容器的长度：" + mq.getSize());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mq.put("f");
                mq.put("g");
            }
        }, "t1");

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 = mq.take();
                System.out.println("移除的元素为：" + o1);
                Object o2 = mq.take();
                System.out.println("移除的元素为：" + o2);
            }
        }, "t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }

}
