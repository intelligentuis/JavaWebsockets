package com.boye.websocket;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public  class Fibonacci extends UnicastRemoteObject  {
    public Fibonacci(){
    }

    public long fibonacci(int rank)  {

        return 2020;
    }
}
