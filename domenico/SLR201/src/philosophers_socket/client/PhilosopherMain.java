package philosophers_socket.client;

import philosophers_socket.Settings;
import philosophers_socket.server.Fork;

public class PhilosopherMain {
    public static void main(String[] args){
        Fork[] forks = new Fork[Settings.NUM_PHILOSOPHERS];
        for(int i=0; i < Settings.NUM_PHILOSOPHERS; i++)
            forks[i] = new Fork(i);
        Philosopher[] philosophers = new Philosopher[Settings.NUM_PHILOSOPHERS];

        for(int i=0; i<Settings.NUM_PHILOSOPHERS; i++){
            philosophers[i] = new Philosopher(i, forks[i], forks[(i+1)%Settings.NUM_PHILOSOPHERS], Settings.HOST, Settings.PORT + i);
            philosophers[i].start();
        }

    }
}
