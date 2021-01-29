import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class Fibonacci extends UnicastRemoteObject  {
    public Fibonacci() throws RemoteException {
    }

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