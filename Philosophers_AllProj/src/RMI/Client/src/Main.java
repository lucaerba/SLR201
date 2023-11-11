package RMI.Client.src;

import java.util.Timer;
import java.util.TimerTask;


public class Main {
    public static String IP_ADDRESS = "192.168.1.51";
    public static int rmiPort = 49153;
    public static void main(String[] args) {
        int nclients = 5;
        Client[] clients = new Client[nclients];

        for (int i = 0; i < nclients; i++) {
            Philosophe p = new Philosophe("Philosophe " + i, i);
            clients[i] = new Client(IP_ADDRESS, rmiPort, p);
        }

        for (int i = 0; i < nclients; i++) {
            clients[i].start();
        }

        // Schedule a task to stop the clients after 7 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        @Override
        public void run() {
            for (int i = 0; i < nclients; i++) {
                clients[i].interrupt(); // Interrupt the thread
            }
            timer.cancel(); // Stop the timer
        }
        }, 7000L); 
    }
}
