package cn.crawin.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UseManyCondition {

    private ReentrantLock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public void m1(){
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m1等待..");
            c1.await();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m1继续..");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2(){
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m2等待..");
            c1.await();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m2继续..");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m3(){
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m3等待..");
            c2.await();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m3继续..");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m4(){
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m4等待..");
            c1.signalAll();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m4继续..");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m5(){
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m5等待..");
            c2.signal();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m5继续..");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final UseManyCondition umc = new UseManyCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m2();
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m3();
            }
        }, "t3");

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m4();
            }
        }, "t4");

        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m5();
            }
        }, "t5");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t4.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t5.start();
    }
}
