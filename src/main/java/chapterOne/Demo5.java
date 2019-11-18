package chapterOne;

/**
 * @author xilun
 * @create 2019-11-06 16:24
 */
public class Demo5 {
    public static ThreadLocal<String> value = new ThreadLocal<>();
    public void threadLocalTest() throws Exception {
        value.set("这是主线程设置的123");
        String v =value.get();
        System.out.println("线程1执行之前，主线程取到的值：" + v);
        new Thread(()->{
            String v1 = value.get();
            System.out.println("线程1取到的值：" + v1);
            // 设置 threadLocal
            value.set("这是线程1设置的456");

            v1 = value.get();
            System.out.println("重新设置之后，线程1取到的值：" + v1);
            System.out.println("线程1执行结束");
        }).start();
        Thread.sleep(5000L);
        v = value.get();
        System.out.println("线程1执行之后，主线程取到的值：" + v);
    }

    public static void main(String[] args) throws Exception {
        new Demo5().threadLocalTest();
    }
}
