package cn.crawin.thread.coll;

import java.util.concurrent.DelayQueue;

public class WangBa implements Runnable{

    private DelayQueue<WangMin> queue = new DelayQueue<WangMin>();

    public boolean yinye = true;

    public void shangJi(String name, String id, int money){
        WangMin wm = new WangMin(name, id, 1000*money);
        System.out.println("网名："+wm.getName()+", 身份证：" + wm.getId() + ", 交钱：" + money + "块,开始上机...");
        this.queue.add(wm);
    }

    public void xiaJi(WangMin man){
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"时间到下机...");
    }

    @Override
    public void run() {
        while (yinye){
            try {
                WangMin wm = queue.take();
                xiaJi(wm);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("网吧开始营业");
        WangBa wb = new WangBa();
        Thread sw = new Thread(wb);
        sw.start();

        wb.shangJi("路人甲", "123", 1);
        wb.shangJi("路人乙", "234", 10);
        wb.shangJi("路人丙", "345", 5);
    }
}
