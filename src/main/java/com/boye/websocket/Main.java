package com.boye.websocket;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



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
            //java.lang.Integer.parseInt(System.getenv("PORT"))
            LocateRegistry.createRegistry(8080);
            Naming.rebind("ok" , f);
            System.out.println("Server Ready...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
