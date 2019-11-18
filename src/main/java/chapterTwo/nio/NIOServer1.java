package chapterTwo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author xilun
 * @create 2019-11-13 10:03
 */
public class NIOServer1 {
    private static ArrayList<SocketChannel> channels = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功");
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                System.out.println("收到新连接：" + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
            } else {
                Iterator<SocketChannel> iterator = channels.iterator();
                while (iterator.hasNext()) {
                    SocketChannel ch = iterator.next();
                    try {
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                        if (ch.read(requestBuffer) == 0) {
                            continue;
                        }
                        while (ch.isOpen() && ch.read(requestBuffer) != -1) {
                            if (requestBuffer.position() > 0) {
                                break;
                            }
                        }
                        if (requestBuffer.position() == 0) continue;
                        requestBuffer.flip();
                        byte[] content = new byte[requestBuffer.limit()];
                        requestBuffer.get(content);
                        System.out.println(new String(content));
                        System.out.println("收到数据，来自：" + ch.getRemoteAddress());
                        String response = "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: 11\r\n\r\n" +
                                "Hello World";
                        ByteBuffer byteBuffer = ByteBuffer.wrap(response.getBytes());
                        while (byteBuffer.hasRemaining()) {
                            ch.write(byteBuffer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        iterator.remove();
                    }

                }
            }
        }
    }
}
