package com.boye.websocket;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class RMIMain {



    static Fibonacci f ;
    public static void main(String[] a) throws RemoteException {

        f= new Fibonacci();

        try {
            // java.lang.Integer.parseInt(System.getenv("PORT"))
            LocateRegistry.createRegistry();
            Naming.rebind("ok" , f);
            System.out.println("Server Ready...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
