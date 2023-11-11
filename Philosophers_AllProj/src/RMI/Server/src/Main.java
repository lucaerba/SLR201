package RMI.Server.src;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {
    static int rmiPort = 49153;
    public static void main(String[] args){
        try {
            // Name the remote service
            final String TABLE_SERVICE = "TableService";
            // Instantiate the HelloServer Remote Object to be called by clients
            TableServer myServer = new TableServer();

            // Obtain a reference to the local RMI Registry
            Registry registry = LocateRegistry.createRegistry(rmiPort);
            // Export the ‘myServer’ instance (on an anonymous port - 0)
            // Obtain the Stub of the Remote Object instance (mysrever)
            TableI stub;
            stub = (TableI) UnicastRemoteObject.exportObject(myServer, 0);

            // Register the remote service with RMI Registry under a name
            registry.rebind(TABLE_SERVICE, stub);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
