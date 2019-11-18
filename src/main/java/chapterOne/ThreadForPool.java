package chapterOne;

/**
 * @author xilun
 * @create 2019-11-07 9:50
 */
public class ThreadForPool implements Runnable {
    private int i;
    public ThreadForPool(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始处理"+ i + "线程！！");
            Thread.sleep(1000L);
            System.out.println("我的线程标识是：" + this.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
