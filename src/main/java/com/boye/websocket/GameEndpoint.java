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

@ServerEndpoint("/game-endpoint")
public class GameEndpoint {


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open session " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, final Session session) {

        // Get The Message 
        System.out.println("Session " + session.getId() + " message: " + message);

        // Split The message into map keys:vales
        String[] arrOfStr = message.split(",", 3); 

        Map<String, String> map = new HashMap<String, String>();

        for (String a : arrOfStr) 
        {
            String[] tmp = a.split(":", 2); 
            map.put(tmp[0],tmp[1]);
        }

        // Replying ( There are 3 sinarios)
        // 1s : Find Player
        if(map.get("message").equals("startGame"))
        {

            // ++++
            Connection connection = null;
            try {

                    String dbUrl = System.getenv("JDBC_DATABASE_URL");
                    connection= DriverManager.getConnection(dbUrl);

                    // Check waiting Player TODO


                    // Set Infos in SQL in ordreWating Player
                    PreparedStatement st = connection.prepareStatement("INSERT INTO QueuePlayers (idPlayer,idLevel)  VALUES (?,?)");
                    st.setString(1, map.get("idPlayer"));
                    st.setString(2, map.get("idLevel"));
                    ResultSet rs = st.executeQuery();


                    // String out ;
                    // while (!rs.next()) {
                    //     out = "Read from DB: " + rs.get("idPlayer") + "\n";
                    // }

                    session.getBasicRemote().sendText("ok");
            } catch (Exception e) {
                System.out.println("There was an error: " + e.getMessage());
        
            } finally {
                if (connection != null) try{connection.close();} catch(SQLException e){}
            }


        }
        // Send And Get Update
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
        // 
        else if(map.get("message").equals("coinEated"))
        {

        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session " + session.getId() + " is closed.");
    }
}
