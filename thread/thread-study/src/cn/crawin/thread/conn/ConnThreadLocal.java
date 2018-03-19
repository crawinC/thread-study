package cn.crawin.thread.conn;

public class ConnThreadLocal {

    /**
     * 线程独有，为每个线程单独存储变量，各个线程间互不干扰
     */
    public static ThreadLocal<String> th = new ThreadLocal<String>();

    public void setTh(String value){
        th.set(value);
    }

    public void getTh(){
        System.out.println(Thread.currentThread().getName() + " : " + this.th.get());
    }

    public static void main(String[] args) {

        final ConnThreadLocal ct = new ConnThreadLocal();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ct.setTh("test1");
                ct.getTh();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    ct.getTh();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
