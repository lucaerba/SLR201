package RMI.Server.src;

import static java.lang.Thread.sleep;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Random;
import RMI.TableI;
public class TableServer implements TableI {

    private int nplaces = 5;
    private boolean[] forks = new boolean[5];
    Random random = new Random();

    public TableServer(){
        Arrays.fill(forks, true);
    }

    public synchronized boolean tryToEat(int pos){
        while(!forks[pos] || !forks[(pos+1)%nplaces]) {
            System.out.println("Philosophe "+pos+" failed to get forks, go back thinking...");
            return false;
        }
        forks[pos] = forks[(pos+1)%nplaces] = false;
        System.out.println("Philosophe "+pos+" got forks!!");
        return true;
    }

    public void eat(int pos){
        try {
            System.out.println("Philosophe "+pos+" is eating!");
            sleep(random.nextInt(256+1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        forks[pos] = forks[(pos+1)%nplaces] = true;
        System.out.println("Philosophe "+pos+" finished eating!...go back thinking");
        
    }
}
