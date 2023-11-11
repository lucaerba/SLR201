import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Table{
    private int nplaces = 5;
    private boolean[] forks = new boolean[5];
    public Table(){
        Arrays.fill(forks, true);
    }

    public synchronized void tryToEat(int pos){
        while(!forks[pos] || !forks[(pos+1)%nplaces]) {
            try {
                System.out.println("Philosophe "+pos+" thinks...x");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        forks[pos] = forks[(pos+1)%nplaces] = false;

    }

    public synchronized void eat(int pos) {
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        forks[pos] = forks[(pos+1)%nplaces] = true;
        notifyAll();
    }
}