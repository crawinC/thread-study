package cn.crawin.thread.sync;

/**
 * 使用synchronized代码块减小锁的粒度，提高性能
 */
public class Optmize {

    public void doLongTimeTask(int time){

        try {
            System.out.println("当前线程开始：" + Thread.currentThread().getName() +
                    ", 正在执行一个较长时间的业务操作，其内容不需要同步");
            Thread.sleep(time);

            synchronized (this){
                System.out.println("当前线程：" + Thread.currentThread().getName() +
                        ", 执行同步代码块，对其同步变量进行操作");
                Thread.sleep(1000);
            }

            System.out.println("当前线程结束：" + Thread.currentThread().getName() +
                    ", 执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        final Optmize opt = new Optmize();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                opt.doLongTimeTask(0);
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                opt.doLongTimeTask(1000);
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
