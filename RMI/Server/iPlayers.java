import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iPlayers extends Remote {
    long fibonacci(int rank) throws RemoteException;
}
