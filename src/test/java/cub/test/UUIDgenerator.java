package cub.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UUIDgenerator {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        long num = 1000000033L;
        PrimerTask task = new PrimerTask(num);
        Future<Boolean> future = executorService.submit(task);
        executorService.shutdown();
//        boolean result = future.get();
//        System.out.format("%d 是否为素数？ %b\n", num, result);
        Thread.sleep(1000);
        future.cancel(true);
        System.out.println("end");
    }

    private static final class PrimerTask implements Callable<Boolean> {

        private final long num;

        public PrimerTask(long num) {
            this.num = num;
        }

        @Override
        public Boolean call() throws Exception {
            // i < num 让任务有足够的运行时间
            double begin = System.currentTimeMillis();
            for (long i = 2; i < num; i++) {
//                if (Thread.currentThread().isInterrupted()) { // 任务被取消
//                    System.out.println("PrimerTask.call： 你取消我干啥？");
//                    double end = System.currentTimeMillis();
//                    double time = (end - begin);
//                    System.out.format("任务运行时间: %.3f s\n", time);
//                    return false;
//                }
                if (num % i == 0) {
                    return false;
                }
            }
            double end = System.currentTimeMillis();
            double time = (end - begin);
            System.out.format("任务运行时间: %.3f s\n", time);
            return true;
        }

    }

}
