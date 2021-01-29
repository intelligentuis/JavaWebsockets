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

        try
        {

        f= new Fibonacci();
        }catch (RemoteException e)
        {
        }

        try {
            String port = System.getenv("PORT");
            // java.lang.Integer.parseInt(port)

            Registry registry = LocateRegistry.createRegistry(java.lang.Integer.parseInt(port));
           
            registry.rebind("OK" , f);


            System.out.println("Server Ready...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
