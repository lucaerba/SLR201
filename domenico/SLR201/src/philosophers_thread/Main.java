package philosophers_thread;


public class Main {
    public static void main(String[] args){

        Fork[] forks = new Fork[Settings.NUM_PHILOSOPHERS];

        for(int i=0; i < Settings.NUM_PHILOSOPHERS; i++){
            forks[i] = new Fork();
        }

        for(int i=0; i < Settings.NUM_PHILOSOPHERS; i++){
            if(i+1 == Settings.NUM_PHILOSOPHERS) {
                Philosopher p = new Philosopher("P"+i, forks[0], forks[i]);
                p.start();
            }
            else {
                Philosopher p = new Philosopher("P"+i, forks[i], forks[i+1]);
                p.start();
            }
        }
    }
}
