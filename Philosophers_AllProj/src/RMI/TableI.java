package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TableI extends Remote{
    boolean tryToEat(int pos) throws RemoteException;
    public void eat(int pos) throws RemoteException;
}