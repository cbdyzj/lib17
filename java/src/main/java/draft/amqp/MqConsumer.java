package draft.amqp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static org.jianzhao.sugar.Sugar.println;

public class MqConsumer {

    public static void main(String... args) throws IOException, TimeoutException {
        var channel = new MqChannel().getChannel();
        channel.basicConsume(
                "test",
                (consumerTag, message) -> {
                    println("收到消息");
                    println("消费tag: " + consumerTag);
                    println("消息内容: " + new String(message.getBody(), StandardCharsets.UTF_8));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), true);
                },
                consumerTag -> println("取消tag: " + consumerTag));
        println("开始消费消息");
    }
}
