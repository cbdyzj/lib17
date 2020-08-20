package draft.amqp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.concurrent.TimeoutException;

import static org.jianzhao.sugar.Sugar.println;

public class MqProducer {

    public static void main(String... args) throws IOException, TimeoutException, InterruptedException {
        var channel = new MqChannel();
        int count = 100;
        while (count > 0) {
            var message = Instant.now().toString();
            channel.getChannel().basicPublish(
                    "",
                    "test",
                    null,
                    message.getBytes(StandardCharsets.UTF_8)
            );
            println("发送消息：" + message);
            Thread.sleep(1000);
            count--;
        }
        channel.close();
    }
}
