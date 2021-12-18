package draft.net;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import static org.jianzhao.sugar.Sugar.println;

public class NioDemo {

    public static void main(String... args) throws Exception {
        var selector = Selector.open();
        var serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(8000), 1024);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        for (var count = Integer.MAX_VALUE; count > 0; count--) {
            selector.select();
            var keys = selector.selectedKeys();
            for (var key : keys) {
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        var ssc = (ServerSocketChannel) key.channel();
                        var sc = ssc.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        var sc = (SocketChannel) key.channel();
                        var buffer = ByteBuffer.allocate(1024);
                        buffer.flip();
                        var readBytes = sc.read(buffer);
                        var bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        println(new String(bytes, StandardCharsets.UTF_8));
                        sc.write(buffer);
                    }
                }
            }
        }
    }
}
