package philosophers_RMI.server;


import philosophers_RMI.AbstractFork;
import philosophers_RMI.Settings;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(Settings.RMI_REGISTRY_PORT);
        Fork[] forks = new Fork[Settings.NUM_PHILOSOPHERS];

        for(int i=0; i < Settings.NUM_PHILOSOPHERS; i++){
            forks[i] = new Fork(i);
        }
        
        for(int i=0; i < Settings.NUM_PHILOSOPHERS; i++){
            forks[i].setLeftFork(forks[(Settings.NUM_PHILOSOPHERS+i-1)%Settings.NUM_PHILOSOPHERS]);
            forks[i].setRightFork(forks[(i+1)%Settings.NUM_PHILOSOPHERS]);

            int port = Settings.PORT + i;
            AbstractFork stub = (AbstractFork) UnicastRemoteObject.exportObject(forks[i], port);
            registry.rebind(Settings.SERVICE_NAME+i, stub);
        }
    }
}
