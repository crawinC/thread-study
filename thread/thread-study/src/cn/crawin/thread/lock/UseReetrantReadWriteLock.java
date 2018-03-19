package cn.crawin.thread.lock;


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseReetrantReadWriteLock {

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    /**
     * 可以并发的访问，线程不互斥
     */
    public void read(){
        try {
            readLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 并发访问下，线程互斥
     */
    public void write(){
        try {
            writeLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        final UseReetrantReadWriteLock urrw = new UseReetrantReadWriteLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t3");

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t4");

//        t1.start();
//        t2.start();

        t3.start();
        t4.start();
    }
}
