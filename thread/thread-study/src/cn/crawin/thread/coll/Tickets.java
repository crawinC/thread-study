package cn.crawin.thread.coll;

import java.util.Vector;

/**
 * 多线程使用Vector或者HashTable的示例(简单线程同步问题)
 */
public class Tickets {

    public static void main(String[] args) {
        // 初始化火车票池并添加火车票：避免线程同步可采用Vector替代ArrayList HashTable替换HashMap
        final Vector<String> tickets = new Vector<String>();

//        Hashtable<String, String> tickets = new Hashtable<String, String>();
        for (int i=1; i<=1000; i++)
            tickets.add("火车票" + i);


        for (int i=1; i<=10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        if (tickets.isEmpty())
                            break;
                        System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                    }
                }
            }, String.valueOf(i)).start();
        }
    }

}
