package cn.crawin.thread.sync;

public class Sync1 extends Thread{

    private int count = 5;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(this.currentThread().getName() + " count=" + count);
    }

    public static void main(String[] args) {
        Sync1 sync = new Sync1();
        Thread t1 = new Thread(sync, "t1");
        Thread t2 = new Thread(sync, "t2");
        Thread t3 = new Thread(sync, "t3");
        Thread t4 = new Thread(sync, "t4");
        Thread t5 = new Thread(sync, "t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
