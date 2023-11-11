import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {
    public static void main(String[] args){
        try {
            // Name the remote service
            final String TABLE_SERVICE = "TableService";
            // Instantiate the HelloServer Remote Object to be called by clients
            TableServer myServer = new TableServer();
            // Export the ‘myServer’ instance (on an anonymous port - 0)
            // Obtain the Stub of the Remote Object instance (mysrever)
            Table stub;
            stub = (Table)UnicastRemoteObject.exportObject(myServer, 0);
            
            // Obtain a reference to the local RMI Registry
            //(args[0] proivides the listening port of the RMI Registry)
            int rmiPort = 49153;
            Registry registry = LocateRegistry.getRegistry(rmiPort);
            // Register the remote service with RMI Registry under a name
            registry.rebind(TABLE_SERVICE, stub);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
