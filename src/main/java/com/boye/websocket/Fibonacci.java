package com.boye.websocket;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public  class Fibonacci extends UnicastRemoteObject  {
    public Fibonacci() throws RemoteException {
    }

    public long fibonacci(int rank)  throws RemoteException {

        return 2020;
    }
}
