package com.boye.websocket;


// import java.io.File;
// import javax.servlet.ServletException;
// import org.apache.catalina.LifecycleException;
// import org.apache.catalina.startup.Tomcat;

// public class Main {

//     public static void main(String[] args) throws ServletException, LifecycleException {
//         Tomcat tomcat = new Tomcat();
//         // The port that we should run on can be set into an environment variable
//         // Look for that variable and default to 8080 if it isn't there.
//         String webPort = System.getenv("PORT");
//         if (webPort == null || webPort.isEmpty()) {
//             webPort = "8080";
//         }
//         tomcat.setPort(Integer.valueOf(webPort));
//         String webappDirLocation = "src/main/webapp/";
//         tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
//         tomcat.start();
//         tomcat.getServer().await();
//     }
// }

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



private  class Fibonacci extends UnicastRemoteObject  {
    public Fibonacci() throws RemoteException {
    }

    public long fibonacci(int rank) throws RemoteException {

        return 2020;
    }
}

public class Main {


    public static void main(String[] a) {
        try {
            Fibonacci f = new Fibonacci();
            LocateRegistry.createRegistry(java.lang.Integer.parseInt(System.getenv("PORT")));
            Naming.rebind("oktest" , f);
            System.out.println("Server Ready...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
