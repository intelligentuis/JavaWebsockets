import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFibo extends Remote {
    long fibonacci(int rank) throws RemoteException;
}
