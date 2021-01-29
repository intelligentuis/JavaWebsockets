import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iFindPlayer extends Remote {
    long fibonacci(int rank) throws RemoteException;
}
