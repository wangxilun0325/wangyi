package chapterOne;

/**
 * @author xilun
 * @create 2019-11-06 15:54
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException{
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(500);
//        thread.stop();
        thread.interrupt();
        while(thread.isAlive()){}
        thread.print();
    }
}
