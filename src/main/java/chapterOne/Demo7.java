package chapterOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xilun
 * @create 2019-11-07 9:47
 */
public class Demo7 {
    public void newCachedThreadPoolTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("****************************newCachedThreadPool*******************************");
        for (int i = 0; i < 4; i++) {
            executorService.execute(new ThreadForPool(i));
        }
    }

    public void newFixedThreadPoolTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("****************************newFixedThreadPool*******************************");
        for (int i = 0; i < 4; i++) {
            executorService.execute(new ThreadForPool(i));
        }
    }

    public void newScheduledThreadPoll() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
        System.out.println("****************************newFixedThreadPool*******************************");
        for (int i = 0; i < 4; i++) {
            scheduledThreadPoolExecutor.schedule(new ThreadForPool(i), 3, TimeUnit.SECONDS);
        }
        //scheduleAtFixedRate(commod,initialDelay,period,unit)
        //scheduleWithFixedDelay(commod,initialDelay,delay,unit)
    }
    public void newSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println("****************************newFixedThreadPool*******************************");
        for (int i = 0; i < 4; i++) {
            executorService.execute(new ThreadForPool(i));
        }
    }

    public static void main(String[] args) {
        Demo7 demo7 = new Demo7();
        //demo7.newCachedThreadPoolTest();
        //demo7.newFixedThreadPoolTest();
        //demo7.newScheduledThreadPoll();
        demo7.newSingleThreadExecutor();
    }
}
