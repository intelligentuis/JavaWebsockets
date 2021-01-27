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


        //  Player1 wait in the Queue
        //  INSERT INTO Players (idPlayer,idLevel)  VALUES ( 'idPlayer1','l5')

        //  Player1 check coming Player2
        //  SELECT idgame FROM Players WHERE idgame IS NOT  NULL and idPlayer='idPlayer1';


        // UPDATE Players SET xy = ?  WHERE idPlayer = 'idPlayer1';
        // ---------------------

        // Player2: searching idPlayer1 
        // SELECT idplayer FROM Players WHERE idgame IS NULL and idlevel = 'l5';

        // Player2: Set the id of the game in ordre to player1 can find it
        // UPDATE Players SET idGame = '2020f2021' WHERE idPlayer IN ('idPlayer1','idPlayer2');



        // Replying ( There are 3 sinarios)
        // 1s : Find Player
        String option = (String)json.get("message");

        if(option.equals("startGame"))
        {

            idLevel = (String)json.get("idLevel");
            idPlayer = (String)json.get("idPlayer");

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
                        idPlayer2 = (String)json.get("idPlayer");
                        idPlayer1 = rs.getString("idPlayer");

                        idGame = "@"+UUID.randomUUID();

                        // Init Player
                        st = connection.prepareStatement("INSERT INTO Players (idPlayer,idLevel,idSession)  VALUES ( ?,?,?)");
                        st.setString(1, idPlayer2);
                        st.setString(2, idLevel);
                        st.setString(3, session.getId());
                        st.executeUpdate(); 

                        // Set Game ID
                        st = connection.prepareStatement("UPDATE Players SET idGame = ? WHERE idPlayer IN (?,?,?)");
                        st.setString(1, idGame);
                        st.setString(2, idPlayer1);
                        st.setString(3, idPlayer2);
                        st.executeUpdate(); 


                        session.getBasicRemote().sendText(idGame);
                    }else // SO I AM PLAYER 1
                    {
                        idPlayer1 = (String)json.get("idPlayer");
                        idLevel = (String)json.get("idLevel");
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
                        session.getBasicRemote().sendText(idGame);
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


                    st = connection.prepareStatement("UPDATE Players SET x=? ,y = ? WHERE idPlayer = ? and idGame = ?");
                    st.setDouble(1, Double.parseDouble((String)json.get("x")));
                    st.setDouble(2, Double.parseDouble((String)json.get("y")));
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
                    peers.get(rs.getString("idSession")).getBasicRemote().sendText((String)json.get("idCoin"));
               

                    
            } catch (Exception e) {
                System.out.println("There was an error: " + e.getMessage());
        
            } finally {
                if (connection != null) try{connection.close();} catch(SQLException e){}
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        peers.put(session.getId(), session);
        System.out.println("Session " + session.getId() + " is closed.");
    }
}
