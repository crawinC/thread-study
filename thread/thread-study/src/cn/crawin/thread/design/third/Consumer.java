package cn.crawin.thread.design.third;

import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable{

    private BlockingDeque<Data> queue;

    public Consumer(BlockingDeque queue){
        this.queue = queue;
    }

    // 随机对象
    private static Random r = new Random();

    @Override
    public void run() {
        while (true){
            // 获取数据
            try {
                Data data = this.queue.take();
                // 进行数据处理  模拟耗时
                Thread.sleep(r.nextInt(1000));
                System.out.println("当前消费消费线程：" + Thread.currentThread().getName() +
                        ", 消费成功，消费数据为id：" + data.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
