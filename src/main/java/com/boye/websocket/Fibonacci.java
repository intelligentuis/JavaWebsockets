package com.boye.websocket;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Fibonacci extends UnicastRemoteObject implements IFibo {
    protected Fibonacci() throws RemoteException {
        super();
    }

    @Override
    public long fibonacci(int rank) throws RemoteException {
        long f = 1, ft = 1, tmp;
        for (int i = 2; i <= rank; i++) {
            tmp = f;
            f = ft;
            ft = ft + tmp;
        }
        return ft;
    }
}
