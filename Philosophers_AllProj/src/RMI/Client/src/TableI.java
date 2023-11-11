package RMI.Client.src;

public interface TableI extends java.rmi.Remote{
    boolean tryToEat(int pos) throws java.rmi.RemoteException;
    public void eat(int pos) throws java.rmi.RemoteException;
}