package org.jianzhao.java.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;
import javax.jms.Session;

public class SessionHolder {

    public static Session getSession() throws JMSException {
        var factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        var connection = factory.createConnection();
        connection.start();
        return connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
    }
}
