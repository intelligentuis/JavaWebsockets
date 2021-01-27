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


@ServerEndpoint("/game-endpoint")
public class GameEndpoint {


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open session " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, final Session session) {
        System.out.println("Session " + session.getId() + " message: " + message);

        String[] arrOfStr = message.split(",", 3); 

        Map<String, String> map = new HashMap<String, String>();

        for (String a : arrOfStr) 
        {
            String[] tmp = a.split(":", 2); 
            map.put(tmp[0],tmp[1]);
        }

        if(map.get("message").equals("startGame"))
        {

            try {
                String msg = "Message " + map.get("message");
                System.out.println(msg);
                session.getBasicRemote().sendText(msg);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            
        }
        else if(map.get("message").equals("myPosition"))
        {

             try {
                String msg = "Message " + UUID.randomUUID();
                System.out.println(msg);
                session.getBasicRemote().sendText(msg);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        else if(map.get("message").equals("coinEated"))
        {

        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session " + session.getId() + " is closed.");
    }
}
