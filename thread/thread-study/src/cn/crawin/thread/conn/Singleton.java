package cn.crawin.thread.conn;

/**
 * 内部类
 */
public class Singleton {

    private static class InnerSingleton{
        private static Singleton single = new Singleton();
    }

    public static Singleton getInstance(){
        return InnerSingleton.single;
    }

}
