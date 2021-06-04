package org.jianzhao.boot.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;

@Slf4j
@Component
@ServerEndpoint("/ws")
public class WsServerEndpoint {

    /**
     * 在ws握手成功之后触发open事件
     *
     * @see HandshakeInterceptor
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) {
        log.info("ws open: {}", session.getId());
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        log.info("ws close: {}, reason: {}", session.getId(), reason.getReasonPhrase());
    }

    @OnMessage
    public void onMessage(Session session, String text) throws IOException {
        log.info("ws message: {}, message: {}", session.getId(), text);
        session.getBasicRemote().sendText("reply: " + text);
    }

    @OnMessage
    public void onMessage(Session session, ByteBuffer buff) {
    }

    @OnMessage
    public void onMessage(Session session, PongMessage message) {
    }

    @OnError
    public void onError(Session session, Throwable t) {
        log.info("ws error: {}, cause: {}", session.getId(), t.getMessage());
    }
}
