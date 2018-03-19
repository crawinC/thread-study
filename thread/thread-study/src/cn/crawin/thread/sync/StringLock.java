package cn.crawin.thread.sync;

/**
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 */
public class StringLock {

    public void method(){
        synchronized ("字符串常量"){
            while (true){
                try {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
                    Thread.sleep(1000);
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final StringLock sl = new StringLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sl.method();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                sl.method();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
