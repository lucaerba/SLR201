package philosophers_socket.server;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import philosophers_socket.Settings;

public class Tournament extends Thread{
    private final Integer[] ports;
    public Byte[] winning_participants;

    public Tournament(Integer[] ports){
        this.ports = ports;
        this.winning_participants = new Byte[Settings.NUM_PHILOSOPHERS];
        Arrays.fill(this.winning_participants, (byte)0);
    }

    public void run(){
        try {
            while(true){
                this.acquire_participants(Settings.NUM_PORTS - 1);
                long tts = ThreadLocalRandom.current().nextLong(Settings.TOURNAMENT_WAIT_TIME);
                Thread.sleep(tts);
                }
        } catch (InterruptedException ignored) { }
    }

    /**
     * Acquire the locks on all the ports one by one until all participants remain blocked and they need to wait for 
     * the new tournament champions to be established.
    */
    private void acquire_participants(int level){
        synchronized (this.ports[level]){
            if(level == 0){
                this.assign();
            } else acquire_participants(level-1);
        }
    }

    private void assign(){
        int j = -1;
        // The total number of philosophers that can eat concurrently in a round table with N seats is floor(N/2)
        for(int i=0; i<Math.floor(Settings.NUM_PHILOSOPHERS / 2); i++){
            // Since a sequence of 3 consecutive losing participants is useless we are sure that we want to take
            // a winner among the next two following positions
            int index = ThreadLocalRandom.current().nextInt(1, 3);
            this.winning_participants[(j+index)%Settings.NUM_PHILOSOPHERS] = 1;
            this.winning_participants[(Settings.NUM_PHILOSOPHERS+j+index-1)%Settings.NUM_PHILOSOPHERS] = 0;
            this.winning_participants[(j+index+1)%Settings.NUM_PHILOSOPHERS] = 0;

            j = (j+index+1)%Settings.NUM_PHILOSOPHERS;
        }
    }
}
