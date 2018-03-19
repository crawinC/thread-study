package cn.crawin.thread.coll;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UseConcurrentMap {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");
        chm.put("k4", "v4");
        chm.putIfAbsent("k5", "v5");

        System.out.println(chm.get("k2"));
        System.out.println(chm.size());

        for (Map.Entry<String, Object> m : chm.entrySet()){
            System.out.println("key: " + m.getKey() + ", value: " + m.getValue());
        }

    }
}
