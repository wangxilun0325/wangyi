package chapterOne;

/**
 * @author xilun
 * @create 2019-11-06 19:56
 */
public class Demo {
    public static Object baozidian = null;

    public void waitNotifyTest1() throws Exception {
        new Thread(() -> {
            if (baozidian == null) {
                synchronized (this) {
                    try {
                        System.out.println("1 进入等待");
                        System.out.println("lambda的this： " + this);
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2 卖到包子， 回家");
        }).start();
        Thread.sleep(3000L);
        baozidian = new Object();
        synchronized (this) {
            System.out.println("lambda的this： " + this);
            this.notifyAll();
            System.out.println("3 通知消费者");
        }
    }

    public void waitNotifyTest2() throws Exception {
        new Thread(new Runnable() {
            public void run() {
                if (baozidian == null) {
                    synchronized (this) {
                        try {
                            System.out.println("1 进入等待");
                            System.out.println("Runnable的this：" + this);
                            this.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }).start();
        Thread.sleep(3000L);
        baozidian = new Object();
        synchronized (this) {
            System.out.println("Runnable的this：" + this);
            this.notifyAll();
            System.out.println("3 通知消费者");
        }
    }

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        demo.waitNotifyTest1();
        //demo.waitNotifyTest2();
    }
}
