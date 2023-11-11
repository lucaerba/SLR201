package philosophers_RMI.client;

import philosophers_RMI.AbstractFork;
import philosophers_RMI.Settings;
import philosophers_RMI.States;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread{
    private final int id;
    private States state;
    private final AbstractFork[] my_forks;
    private int counter = 0;

    public Philosopher(int id,
                       String... fork_names)
            throws RemoteException, NotBoundException {
        this.id = id;
        this.state = States.RETRY;
        Registry registry = LocateRegistry.getRegistry(Settings.HOST, Settings.RMI_REGISTRY_PORT);
        AbstractFork lf = (AbstractFork) registry.lookup(fork_names[0]);
        AbstractFork rf = (AbstractFork) registry.lookup(fork_names[1]);
        this.my_forks = new AbstractFork[]{lf, rf};
    }


    public void run(){
        try{
            while(true){
                this.state = this.my_forks[0].request_fork(this.id, this.state);
                this.state = this.my_forks[1].request_fork(this.id, this.state);

                if(this.state == States.COMPLETE){
                    System.out.println("Philosopher P" + this.id + " is eating.");
                    System.out.println("Philosopher P" + this.id + " ate " + ++this.counter + " times.\n");
                    this.state = States.RETRY;

                    // Think for a random duration of milliseconds
                    long tts = ThreadLocalRandom.current().nextLong(Settings.THINKING_TIME);
                    Thread.sleep(tts);
                }
            }
        } catch (Exception ignored) {}
    }
}
