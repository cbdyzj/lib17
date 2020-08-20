package draft.activemq;

import io.vavr.control.Try;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.JMSException;

import static org.jianzhao.sugar.Sugar.println;

public class Consumer {

    public static void main(String... args) throws JMSException {
        var session = SessionHolder.getSession();
        var destination = session.createQueue("test");

        var consumer = session.createConsumer(destination);
        consumer.setMessageListener(message -> Try.run(() -> {
            println(((ActiveMQTextMessage) message).getText());
            message.acknowledge();
        }));

    }
}
