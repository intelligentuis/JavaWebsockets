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


@ServerEndpoint("/sql-endpoint")
public class SQLEndpoint {


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open session " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, final Session session) throws IOException {
        System.out.println("Session " + session.getId() + " message: " + message);

        // +++
        
        Connection connection = null;
        try {
                connection = getConnection();

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
                stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
                ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

                String out = "Hello!\n";
                while (rs.next()) {
                    out += "Read from DB: " + rs.getTimestamp("tick") + "\n";
                }

                session.getBasicRemote().sendText(out);
        } catch (Exception e) {
            session.getBasicRemote().sendText("There was an error: " + e.getMessage());
        } finally {
            if (connection != null) try{connection.close();} catch(SQLException e){}
        }


  
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session " + session.getId() + " is closed.");
    }
}


private static Connection getConnection() throws URISyntaxException, SQLException {
    String dbUrl = System.getenv("JDBC_DATABASE_URL");
    return DriverManager.getConnection(dbUrl);
}