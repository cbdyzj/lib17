package org.jianzhao.java.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

import static org.jianzhao.sugar.Sugar.println;

public class AioDemo {

    public static void main(String[] args) throws IOException {
        new AioDemo().start(8910);
        System.in.read();
    }

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    public void handle(String data, AsynchronousSocketChannel channel) {
        println(data);
        this.send(data, channel);
    }

    public Future<Integer> send(String data, AsynchronousSocketChannel channel) {
        ByteBuffer buffer = ByteBuffer.wrap(data.getBytes(StandardCharsets.UTF_8));
        return channel.write(buffer);
    }

    public void start(int port) throws IOException {
        var server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress("localhost", port));
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel channel, Void v) {
                ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
                channel.read(buffer, null, new CompletionHandler<Integer, Void>() {
                    @Override
                    public void completed(Integer size, Void v) {
                        if (size < 0) {
                            try {
                                channel.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        buffer.flip();
                        String data = new String(buffer.array(), 0, size);
                        buffer.clear();
                        AioDemo.this.handle(data, channel);
                        channel.read(buffer, null, this);
                    }

                    @Override
                    public void failed(Throwable throwable, Void v) {
                        throwable.printStackTrace();
                        try {
                            channel.shutdownInput();
                            channel.shutdownOutput();
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                server.accept(null, this);
            }

            @Override
            public void failed(Throwable throwable, Void v) {
                throwable.printStackTrace();
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
