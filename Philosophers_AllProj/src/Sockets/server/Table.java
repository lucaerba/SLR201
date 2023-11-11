package Sockets.server;

import java.util.*;

import static java.lang.Thread.sleep;

public class Table{
    private int nplaces = 5;
    private List<Boolean> forks = Collections.synchronizedList(new ArrayList<>(5));

    //private boolean[] forks = new boolean[5];
    Random random = new Random();

    public Table(){
        for (int i = 0; i < 5; i++) {
            forks.add(false); // Initialize forks as available
        }
    }

    public synchronized boolean tryToEat(int pos) {
        if (!forks.get(pos) || !forks.get((pos + 1) % nplaces)) {
            System.out.println("Philosopher " + pos + " failed to get forks, go back thinking...");
            return false;
        }
        forks.set(pos, false);
        forks.set((pos + 1) % nplaces, false);
        System.out.println("Philosopher " + pos + " got forks!!");
        return true;
    }

    public void eat(int pos) {
        try {
            System.out.println("Philosopher " + pos + " is eating!");
            Thread.sleep(random.nextInt(256 + 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            forks.set(pos, true);
            forks.set((pos + 1) % nplaces, true);
            System.out.println("Philosopher " + pos + " finished eating!...go back thinking");
        }
    }
}