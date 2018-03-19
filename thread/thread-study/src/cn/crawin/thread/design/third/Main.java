package cn.crawin.thread.design.third;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    public static void main(String[] args) {
        // 内存缓冲区
        BlockingDeque<Data> queue = new LinkedBlockingDeque<>(10);

        // 生产者
        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);

        // 消费者
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        ExecutorService cachePool = Executors.newCachedThreadPool();
        cachePool.execute(p1);
        cachePool.execute(p2);
        cachePool.execute(p3);
        cachePool.execute(c1);
        cachePool.execute(c2);
        cachePool.execute(c3);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        p1.stop();
        p2.stop();
        p3.stop();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
