package RMI.Client.src;

import RMI.TableI;
import java.io.IOException;
import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Client extends Thread{
    private TableI table;
    private int port;
    private InetAddress address;
    private Philosophe philosophe;

    public Client(String ip, int port, Philosophe p){
        if(ip.equals("localhost") || ip.equals("127.0.0.1")){
            try {
                this.address = InetAddress.getByName("localhost");
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                this.address = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }

        this.port = port;
        this.philosophe = p;
         // Donner le nom du service demandé
        final String TABLE_SERVICE = "TableService";
        // Obtenir une référence vers le remiregistry distant
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry(ip, port);
        
            // Obtenir une référence vers l’objet distant (via le stub local)
            this.table = (TableI)registry.lookup(TABLE_SERVICE);
        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run(){
        Random random = new Random();
        try {
            while (!Thread.currentThread().isInterrupted()){
                
                System.out.println(philosophe.getName() + " try eat!");
                if(!table.tryToEat(this.philosophe.getPos())) {
                    System.out.println("Philosophe " + philosophe.getPos() + " failed, back to think...");
                    while (!table.tryToEat(this.philosophe.getPos())) {
                        //sleep(random.nextInt(256 + 1));
                    }
                }
                System.out.println(philosophe.getName() + " got forks!");
                table.eat(this.philosophe.getPos());

                System.out.println(philosophe.getName() + " has eaten! slurp");

                philosophe.addEat();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Philosophe "+ philosophe.getPos()+" has eaten "+philosophe.getEatenCount());
        }
    }
}
