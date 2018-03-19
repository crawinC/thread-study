package cn.crawin.thread.sync;

/**
 * 关键字synchronized取得的锁都是对象锁，而不是把一段代码(方法)当做锁
 * 所以在代码中哪个线程执行synchronized关键字的方法，哪个线程就持有该方法所属对象的锁(Lock)
 *
 * 在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁(独占.class类)
 */
public class Sync2 {

    private int num = 0;

    public synchronized void printNum(String tag){

        try {
            if ("a".equals(tag)){
                num = 100;
                System.out.println("tag a, set num over!");
                Thread.sleep(1000);
            }else {
                num = 200;
                System.out.println("tag b, set num over!");
            }
            System.out.println("tag " + tag + ", num=" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        final Sync2 s1 = new Sync2();
        final Sync2 s2 = new Sync2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                s1.printNum("a");
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                s2.printNum("b");
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
