import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Table{
    private int nplaces = 5;
    private boolean[] forks = new boolean[5];
    private IOUtilities io = null;

    public Table(IOUtilities io){
        this.io = io;
        Arrays.fill(forks, true);
    }

    public synchronized void tryToEat(int pos){
        while(!forks[pos] || !forks[(pos+1)%nplaces]) {
            try {
                System.out.println("Philosophe "+pos+" thinks...");
                io.write( "Philosophe "+pos+" thinks...\n");
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