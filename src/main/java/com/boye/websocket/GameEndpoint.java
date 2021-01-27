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

@ServerEndpoint("/game-endpoint")
public class GameEndpoint {

    static Map<String, Session> peers = Collections.synchronizedMap(new HashMap<String, Session>());
    // static Map<String, String> players = Collections.synchronizedMap(new HashMap<String, String>());
    
    String idPlayer1,idPlayer2, idPlayer,idGame,idLevel;
    ResultSet rs;


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open session " + session.getId());
        peers.put(session.getId(), session);
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
            idPlayer = "@"+UUID.randomUUID();//json.getString("idPlayer");

            PreparedStatement st;
            Connection connection = null;

            try {

                    String dbUrl = System.getenv("JDBC_DATABASE_URL");
                    connection= DriverManager.getConnection(dbUrl);

                    // Player1 Or Player2

                    st = connection.prepareStatement("SELECT idplayer FROM Players WHERE idgame IS NULL and idlevel = ?");
                    st.setString(1, idLevel);
                    rs = st.executeQuery();

                    if (rs.next()) // SO I AM PLAYER 2
                    {
                        idPlayer2 = "@"+UUID.randomUUID();//json.getString("idPlayer");
                        idPlayer1 = rs.getString("idPlayer");

                        idGame = "@"+UUID.randomUUID();

                        // Init Player
                        st = connection.prepareStatement("INSERT INTO Players (idPlayer,idLevel,idSession)  VALUES ( ?,?,?)");
                        st.setString(1, idPlayer2);
                        st.setString(2, idLevel);
                        st.setString(3, session.getId());
                        st.executeUpdate(); 

                        // Set Game ID
                        st = connection.prepareStatement("UPDATE Players SET idGame = ? WHERE idPlayer IN (?,?)");
                        st.setString(1, idGame);
                        st.setString(2, idPlayer1);
                        st.setString(3, idPlayer2);
                        st.executeUpdate(); 


                        session.getBasicRemote().sendText("GameBegins");
                    }else // SO I AM PLAYER 1
                    {
                        idPlayer1 = "@"+UUID.randomUUID() ;//json.getString("idPlayer");
                        idLevel = json.getString("idLevel");
                        // Init Player
                        st = connection.prepareStatement("INSERT INTO Players (idPlayer,idLevel,idSession)  VALUES ( ?,?,?)");
                        st.setString(1, idPlayer1);
                        st.setString(2, idLevel);
                        st.setString(3, session.getId());
                        st.executeUpdate(); 

                        do
                        {
                            st = connection.prepareStatement("SELECT idgame FROM Players WHERE idgame IS NOT  NULL and idPlayer=?");
                            st.setString(1, idPlayer1);
                            rs = st.executeQuery();

                        }while(!rs.next());

                        idGame =rs.getString("idGame");
                        session.getBasicRemote().sendText("GameBegins");
                    }

               

                    
            } catch (Exception e) {
                System.out.println("There was an error: " + e.getMessage());
        
            } finally {
                if (connection != null) try{connection.close();} catch(SQLException e){}
            }


        }
        // Send And Get Update
        else if(option.equals("update"))
        {

            PreparedStatement st;
            Connection connection = null;

            try {

                    String dbUrl = System.getenv("JDBC_DATABASE_URL");
                    connection= DriverManager.getConnection(dbUrl);

                    System.out.println(">>>>>"+json.getString("x"));

                    st = connection.prepareStatement("UPDATE Players SET x=? ,y = ? WHERE idPlayer = ? and idGame = ?");
                    st.setString(1, json.getString("x"));
                    st.setString(2, json.getString("y"));
                    st.setString(3, idPlayer);
                    st.setString(4, idGame);
                    st.executeUpdate(); 


                    // Get xy of other players

                    st = connection.prepareStatement("SELECT x,y FROM Players WHERE NOT idPlayer = ? and idGame = ?");
                    st.setString(1, idPlayer);
                    st.setString(2, idGame);
                    rs = st.executeQuery();

                    rs.next();

                    session.getBasicRemote().sendText(rs.getString("x")+","+rs.getString("x"));

               

                    
            } catch (Exception e) {
                System.out.println("There was an error: " + e.getMessage());
        
            } finally {
                if (connection != null) try{connection.close();} catch(SQLException e){}
            }

        }
        // 
        else if(option.equals("coinEated"))
        {

            PreparedStatement st;
            Connection connection = null;

            try {

                    String dbUrl = System.getenv("JDBC_DATABASE_URL");
                    connection= DriverManager.getConnection(dbUrl);


                    st = connection.prepareStatement("SELECT idSession FROM Players WHERE idGame=? and NOT idPlayer = ?");
                    st.setString(1, idGame);
                    st.setString(2, idPlayer);
                    rs = st.executeQuery();

                    rs.next();

                    // SELECT idSession FROM Players WHERE idGame=? and NOT idPlayer = ?
                    peers.get(rs.getString("idSession")).getBasicRemote().sendText(json.getString("idCoin"));
               

                    
            } catch (Exception e) {
                System.out.println("There was an error: " + e.getMessage());
        
            } finally {
                if (connection != null) try{connection.close();} catch(SQLException e){}
            }
        }

        else if(option.equals("status"))
        {

            // String status = json.getString("status");
            // if(okera.equals("win"))status = "loss";
            // else status = "win";

            // PreparedStatement st;
            // Connection connection = null;

            // try {

            //         String dbUrl = System.getenv("JDBC_DATABASE_URL");
            //         connection= DriverManager.getConnection(dbUrl);


            //         st = connection.prepareStatement("SELECT idSession FROM Players WHERE idGame=? and NOT idPlayer = ?");
            //         st.setString(1, idGame);
            //         st.setString(2, idPlayer);
            //         rs = st.executeQuery();

            //         rs.next();

            //         // SELECT idSession FROM Players WHERE idGame=? and NOT idPlayer = ?
            //         peers.get(rs.getString("idSession")).getBasicRemote().sendText(json.getString(Okera));
               

                    
            // } catch (Exception e) {
            //     System.out.println("There was an error: " + e.getMessage());
        
            // } finally {
            //     if (connection != null) try{connection.close();} catch(SQLException e){}
            // }
        }


    }

    @OnClose
    public void onClose(Session session) {
        peers.put(session.getId(), session);
        System.out.println("Session " + session.getId() + " is closed.");
    }
}
