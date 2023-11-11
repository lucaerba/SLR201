package philosophers_thread;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread{
    private String name;
    private Fork[] my_forks;

    public Philosopher(String name, Fork lf, Fork rf){
        this.name = name;
        my_forks = new Fork[]{lf, rf};
    }

    public void run(){
        try{
            int counter = 0;
            while(true){
                synchronized(this.my_forks[0]) {
                    synchronized(this.my_forks[1]){
                        System.out.println("Philosopher " + this.name + " is eating.");
                        this.my_forks[0].eat();
                        this.my_forks[1].eat();
                        System.out.println("Philosopher " + this.name + " ate " + ++counter + " times.\n");
                    }
                }
                // Think for a random duration of milliseconds
                long tts = ThreadLocalRandom.current().nextLong(Settings.THINKING_TIME);
                Thread.sleep(tts);
            }
        } catch (Exception ignored) { ignored.printStackTrace();}
    }
    
}
