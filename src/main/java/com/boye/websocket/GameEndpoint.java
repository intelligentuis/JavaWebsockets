package com.boye.websocket;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/snubby-endpoint")
public class GameEndpoint {

    private Timer timer;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open session " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, final Session session) {
        System.out.println("Session " + session.getId() + " message: " + message);

        try {
            String msg = "Message " + UUID.randomUUID();
            System.out.println(msg);
            session.getBasicRemote().sendText(msg);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
  
    }

    @OnClose
    public void onClose(Session session) {
        timer.cancel();
        System.out.println("Session " + session.getId() + " is closed.");
    }
}
