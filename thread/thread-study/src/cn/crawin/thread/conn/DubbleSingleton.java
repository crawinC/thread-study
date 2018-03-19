package cn.crawin.thread.conn;

public class DubbleSingleton {

    public static DubbleSingleton single;

    public static DubbleSingleton getSingle() {
        // 校验
        if (single == null){
            try {
                // 模拟初始化对象准备时间
                Thread.sleep(3000);
//                single = new DubbleSingleton();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (DubbleSingleton.class){
                if (single == null)
                    single = new DubbleSingleton();
            }
        }
        return single;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getSingle().hashCode());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getSingle().hashCode());
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getSingle().hashCode());
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}

