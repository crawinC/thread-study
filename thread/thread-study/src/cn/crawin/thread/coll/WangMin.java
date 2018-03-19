package cn.crawin.thread.coll;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class WangMin implements Delayed{

    private String name;
    private String id;
    private long endTime;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public WangMin(String name, String id, long endTime){
        this.name = name;
        this.id = id;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    /**
     * 用来判断是否到了截止时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        WangMin w = (WangMin)o;
        return this.getDelay(this.timeUnit) - w.getDelay(this.timeUnit) > 0 ? 1 : 0;
    }
}
