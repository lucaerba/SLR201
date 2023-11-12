package Threads;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static int nThreads = 5;
    public static void main(String[] args){
        Table t = new Table();
        Philophe[] ps = new Philophe[nThreads];
        for(int i=0; i<nThreads; i++){
            ps[i] = new Philophe("Philosophe "+i, t, i);
        }
        for(int i=0; i<nThreads; i++){
            ps[i].start();
        }
        // Schedule a task to stop the clients after 7 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < nThreads; i++) {
                    ps[i].finished = true;
                }
                timer.cancel(); // Stop the timer
            }
        }, 7000L);
    }
}
