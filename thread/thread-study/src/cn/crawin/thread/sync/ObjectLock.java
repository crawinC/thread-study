package cn.crawin.thread.sync;

/**
 * 使用synchronized代码块加锁，比较灵活
 */
public class ObjectLock {

    public void method1(){
        // 对象锁
        synchronized (this) {

            try {
                System.out.println("do method1 ...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2(){
        // 类锁
        synchronized (ObjectLock.class){
            try {
                System.out.println("do method2 ...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Object ol = new Object();
    public void method3(){
        synchronized (ol){
            try {
                System.out.println("do method3 ...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ObjectLock obl = new ObjectLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                obl.method1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                obl.method2();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                obl.method3();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
