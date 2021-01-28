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
import java.sql.*;
import java.util.*; 
import org.json.*;

@ServerEndpoint("/game-endpoint2")
public class GameEndpoint2 {

    static Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>()); // idPlayer:Session
    static Map<String, String> levels = Collections.synchronizedMap(new HashMap<String, String>()); // level:idPLayer


    String idPlayer2, idPlayer,idGame,idLevel;
    ResultSet rs;


    @OnOpen
    public void onOpen(Session session) {

        idPlayer = session.getId();

        System.out.println("Open session " + idPlayer);
        sessions.put(session.getId(), session);
    }

    @OnMessage
    public void onMessage(String message, final Session session) {

        // Get The Message
        System.out.println("Session " + session.getId() + " message: " + message);


        JSONObject json = new JSONObject(message);


        // Replying ( There are 3 sinarios)
        // 1s : Find Player
        String option = json.getString("option");

        if(option.equals("startGame"))
        {

            idLevel = json.getString("idLevel");

            try {

                    if(levels.containsKey(idLevel))// SO I AM PLAYER 2
                    {

                        idGame = "@"+UUID.randomUUID();

                        System.out.println("I AM PLAYER2");

                        idPlayer2 = levels.get(idLevel);
                        sessions.remove(idLevel);

                        // SEND TO PLAYER 2

                        JSONObject obj1 = new JSONObject();
                        obj1.put("option","GameBegins");
                        obj1.put("idPlayer2",idPlayer2);

                        session.getBasicRemote().sendText(obj1.toString());

                        // SEND TO PLAYER 1 

                        JSONObject obj2 = new JSONObject();
                        obj2.put("option","GameBegins");
                        obj2.put("idPlayer2",idPlayer);
                        System.out.println(obj2.toString());
                        sessions.get(idPlayer2).getBasicRemote().sendText(obj2.toString());

                    }else // SO I AM PLAYER 1
                    {
                        System.out.println("I AM PLAYER1");

                        idLevel = json.getString("idLevel");
                        idPlayer = session.getId();

                        levels.put(idLevel,idPlayer);

                    }

               

                    
            } catch (Exception e) {
                System.out.println("There was an error: " + e.getMessage());
        
            } 


        }

        // Send And Get Update
        else if(option.equals("update"))
        {


            try {


                sessions.get(json.getString("idPlayer2")).getBasicRemote().sendText(message);
                  

                    
            } catch (Exception e) {
                System.out.println("There was an error: " + e.getMessage());
        
            } 

        }
       

    }

    @OnClose
    public void onClose(Session session) {
        sessions.put(session.getId(), session);
        System.out.println("Session " + session.getId() + " is closed.");
    }
}
