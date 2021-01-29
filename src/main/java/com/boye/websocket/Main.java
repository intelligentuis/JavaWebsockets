package com.boye.websocket;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import java.io.*;  
import java.net.*;  

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
            System.out.println("ERRRRRRRRRRRRRRRRROOOOOOOOOOORRRR");
        }

        try {
            String port = System.getenv("PORT");
            System.out.print("Port "+port +"#");
            Registry registry = LocateRegistry.createRegistry(java.lang.Integer.parseInt(port));
           
            // "rmi://localhost:"+port+"/rmi"
            // String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/ok";
            registry.rebind("url" , f);


            System.out.println("Server Ready...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
