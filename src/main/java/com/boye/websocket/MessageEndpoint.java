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

import java.util.*; 

@ServerEndpoint("/test-endpoint")
public class MessageEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open session " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, final Session session) {
        System.out.println("Session " + session.getId() + " message: " + message);
        // if ("GET".equals(message)) {
           
            try {
                String ok = "false";
                for (Session session : user.getOpenSessions()) {
                    if (id.equals(session.getId())) {
                        // return session;
                        ok= "true";
                    }
                }

                String msg = "Message " + ok;
                System.out.println(msg);
                session.getBasicRemote().sendText(msg);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
          
        // } 
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session " + session.getId() + " is closed.");
    }
}


