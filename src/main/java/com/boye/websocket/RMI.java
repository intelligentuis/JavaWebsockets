package com.boye.websocket;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class RMI {



    static Fibonacci f ;
    public static void main(String[] a) throws RemoteException {

        f= new Fibonacci();

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
