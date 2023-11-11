package philosophers_RMI.client;

import philosophers_RMI.Settings;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        for(int i = 0; i < Settings.NUM_PHILOSOPHERS; i++){
            String lf_serv = Settings.SERVICE_NAME + i;
            String rf_serv = Settings.SERVICE_NAME + ((i+1)%Settings.NUM_PHILOSOPHERS);
            Philosopher p = new Philosopher(i, lf_serv, rf_serv);
            p.start();
        }
    }
}
