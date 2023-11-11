package philosophers_socket.server;

import philosophers_socket.Settings;

class RequestProcessor implements Runnable {
    private Integer[] available_ports;
    private final Fork[] forks;

    public RequestProcessor(Fork[] forks){
        this.available_ports = new Integer[Settings.NUM_PORTS];
        for(int i=0; i<Settings.NUM_PORTS; i++)
            this.available_ports[i] = Settings.PORT+i;
        this.forks = forks;
    }

    public void run() {
        // The tournament manager decides each turn who has the right to win the competition for eating
        Tournament t = new Tournament(this.available_ports);
        t.start();

        for (int i=0; i < Settings.NUM_PORTS; i++) {
            Server s = new Server(this.forks, t, this.available_ports[i]);
            s.start();
        }
    }


    public static void main(String[] args){
        Fork[] forks = new Fork[Settings.NUM_PHILOSOPHERS];
        for(int i=0; i<Settings.NUM_PHILOSOPHERS; i++)
            forks[i] = new Fork(i);
    
        RequestProcessor rp = new RequestProcessor(forks);
        rp.run();
    }
}
