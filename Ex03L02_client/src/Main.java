import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        Client[] clients = new Client[5];

        for (int i = 0; i < 5; i++) {
            Philosophe p = new Philosophe("Philosophe " + i, i);
            clients[i] = new Client("localhost", 49153 + i, p);
        }

        for (int i = 0; i < 5; i++) {
            clients[i].start();
        }

        // Schedule a task to stop the clients after 7 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                clients[i].interrupt(); // Interrupt the thread
            }
            timer.cancel(); // Stop the timer
        }
        }, 7000L); 
    }
}
