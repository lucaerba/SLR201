package Sockets.client;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static String IP_ADDRESS = "172.25.221.177";

    public static void main(String[] args) {
        int nclients = 5;
        Client[] clients = new Client[nclients];

        for (int i = 0; i < nclients; i++) {
            Philosophe p = new Philosophe("Philosophe " + i, i);
            clients[i] = new Client(IP_ADDRESS, 49153 + i, p);
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
