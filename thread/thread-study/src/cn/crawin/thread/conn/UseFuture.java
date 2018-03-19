package cn.crawin.thread.conn;

import java.util.concurrent.*;

public class UseFuture implements Callable<String>{

    private String para;

    public UseFuture(String para){
        this.para = para;
    }

    /**
     * 这里是真实的业务逻辑，其执行可能很慢
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        // 模拟执行耗时
        Thread.sleep(5000);
        String result = this.para + "处理完成";
        return result;
    }

    // 主控制函数
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String queryStr = "query";
        // 构造FutureTask,并且传入需要真正进行业务逻辑处理的类，该类一定是实现了Callable接口的类
        FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));

        FutureTask<String> future2 = new FutureTask<String>(new UseFuture(queryStr));

        // 创建一个固定线程的线程池且线程数为2
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 这里提交任务future，则开始线程执行RealData的call()方法执行
        // submit和execute的区别：
        // 1.submit可以传入实现Callable接口的实例对象
        // 2.submit方法有返回值

        // 单独启动一个线程执行
        Future f1 = executor.submit(future);
        Future f2 = executor.submit(future2);
        System.out.println("请求完毕");

        try {
            // 这里可以做额外的数据操作，也就是主程序执行其它业务逻辑
            System.out.println("处理实际的业务逻辑");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 调用获取数据方法，如果call()没有执行完成，则依然会进行等待
        System.out.println("数据：" + future.get());
        System.out.println("数据：" + future2.get());

        executor.shutdown();

    }
}
