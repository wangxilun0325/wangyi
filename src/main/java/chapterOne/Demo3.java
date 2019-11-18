package chapterOne;

/**
 * @author xilun
 * @create 2019-11-06 16:08
 */
public class Demo3 extends Thread {
    public volatile static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                while (flag) { // 判断是否运行
                    System.out.println("运行中");
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 3秒之后，将状态标志改为False，代表不继续运行
        Thread.sleep(3000L);
        flag = false;
        System.out.println("程序运行结束");
    }
}
