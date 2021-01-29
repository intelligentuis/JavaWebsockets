package com.boye.websocket;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class Main {



    static Fibonacci f ;
    public static void main(String[] a) {

        try
        {

        f= new Fibonacci();
        }catch (RemoteException e)
        {
            
        }

        try {
            // 
            LocateRegistry.createRegistry(java.lang.Integer.parseInt(System.getenv("PORT")));
            Naming.rebind("ok" , f);
            System.out.println("Server Ready...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
