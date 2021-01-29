package com.boye.websocket;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


// import java.rmi.server.hostname;


public class Main {


    static Fibonacci f ;
    public static void main(String[] a) {

        System.out.println("HELLLLLLLOOOOOOOO");

        try
        {

        f= new Fibonacci();
        }catch (RemoteException e)
        {
            
        }

        try {
            String port = System.getenv("PORT");

            Registry registry = LocateRegistry.createRegistry(java.lang.Integer.parseInt(System.getenv("PORT")));
           
            // System.setProperty("java.rmi.server.hostname","1.2.3.4");
            // "rmi://localhost:"+port+"/rmi"
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/ok";
            registry.rebind(url , f);


            System.out.println("Server Ready...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
