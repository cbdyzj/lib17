package draft.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("WeakerAccess")
public class MqChannel {

    private final Channel channel;
    private final Connection connection;

    public MqChannel() throws IOException, TimeoutException {
        var factory = new ConnectionFactory();
        factory.setUsername("username");
        factory.setPassword("password");
        factory.setHost("localhost");
        this.connection = factory.newConnection();
        this.channel = this.connection.createChannel();
    }

    public Channel getChannel() {
        return channel;
    }

    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }

}
