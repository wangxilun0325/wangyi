package chapterTwo;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author xilun
 * @create 2019-11-07 9:11
 */
public class BIOClient {
    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) throws Exception{
        Socket s = new Socket("localhost",8080);
        OutputStream out = s.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入");
        String msg = scanner.nextLine();
        out.write(msg.getBytes(charset));//阻塞
        scanner. close();
        s.close();
    }
}
