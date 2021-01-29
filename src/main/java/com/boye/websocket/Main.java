package com.boye.websocket;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import java.net.InetAddress;


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
            LocateRegistry.createRegistry(java.lang.Integer.parseInt(System.getenv("PORT")));
            // "rmi://localhost:"+port+"/rmi"
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI";
            Naming.rebind(url , f);
            System.out.println("Server Ready...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
