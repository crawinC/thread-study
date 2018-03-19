package cn.crawin.thread.sync;

/**
 * 对象锁的同步和异步问题
 */
public class Sync3 {

    public synchronized void method1(){

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2(){

        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        final Sync3 sy = new Sync3();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sy.method1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                sy.method2();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
