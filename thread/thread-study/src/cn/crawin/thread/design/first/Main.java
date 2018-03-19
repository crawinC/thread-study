package cn.crawin.thread.design.first;

public class Main {

    public static void main(String[] args) {

        FutureClient fc = new FutureClient();
        Data data = fc.request("请求参数");
        System.out.println("请求发送成功");
        System.out.println("做其它的事情...");

        String result = data.getRequest();
        System.out.println(result);
    }
}
