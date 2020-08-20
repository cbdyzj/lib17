package draft.activemq;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;

public class Producer {

    public static void main(String... args) throws JMSException {
        var session = SessionHolder.getSession();
        var destination = session.createQueue("test");
        var producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        producer.send(session.createTextMessage("hello!"));

    }
}
