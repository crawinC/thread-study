package cn.crawin.thread.conn;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseCyclicBarrier {

    static class Runner implements Runnable{
        private CyclicBarrier barrier;
        private String name;

        public  Runner(CyclicBarrier barrier, String name){
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000*(new Random()).nextInt(5));
                System.out.println(name + " 准备OK.");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + " Go!!");
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new Thread(new Runner(barrier, "ceshi1")));
        executor.submit(new Thread(new Runner(barrier, "ceshi2")));
        executor.submit(new Thread(new Runner(barrier, "ceshi3")));

        executor.shutdown();
    }
}
