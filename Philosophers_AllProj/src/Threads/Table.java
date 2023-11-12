package Threads;

import java.util.Arrays;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Table{
    private int nplaces = 5;
    private boolean[] forks = new boolean[5];
    Random random = new Random();

    public Table(){
        Arrays.fill(forks, true);
    }

    public synchronized void tryToEat(int pos){
        while (!forks[pos] || !forks[(pos+1)%nplaces]) {
            System.out.println("Philosophe "+pos+" thinks...");
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        forks[pos] = forks[(pos+1)%nplaces] = false;
    }

    public synchronized void eat(int pos) {
        try{
            System.out.println("Philosophe "+pos+" is eating!");
            sleep(random.nextInt(256+1));
        } catch (InterruptedException e) {

        }
        System.out.println("Philosophe "+pos+" finished eating!...go back thinking");
        forks[pos] = forks[(pos+1)%nplaces] = true;
        notifyAll();

    }
}