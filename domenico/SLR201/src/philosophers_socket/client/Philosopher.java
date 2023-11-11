package philosophers_socket.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

import philosophers_socket.Settings;
import philosophers_socket.server.Fork;

public class Philosopher extends Thread{
    private final int id;
    private final Fork[] my_forks;
    private long counter = 0;
    private byte[] buf = new byte[1];
    private String address;
    private int port;
    public Philosopher(int id, Fork lf, Fork rf, String address, int port){
        this.id = id;
        this.my_forks = new Fork[]{lf, rf};
        this.address = address;
        this.port = port;
        this.buf = new byte[1];
    }

    public void run(){
        while(true){
            try (Socket conn = new Socket(InetAddress.getByName(address), port);
                 DataInputStream conn_in = new DataInputStream(conn.getInputStream());
                 DataOutputStream conn_out = new DataOutputStream(conn.getOutputStream())){
                if(request_forks(conn_in, conn_out, my_forks[0].getId(), my_forks[1].getId())){

                    System.out.println("Philosopher P" + this.id + " is eating.");
                    System.out.println("Philosopher P" + this.id + " ate " + ++this.counter + " times.\n");

                    // Think for a random duration of milliseconds
                    long tts = ThreadLocalRandom.current().nextLong(Settings.THINKING_TIME);
                    Thread.sleep(tts);
                }
            } catch (Exception ignored) {}
        }
    }

    private boolean request_forks(DataInputStream conn_in, DataOutputStream conn_out, int lid, int rid) throws IOException {
        // Send request for forks by specifying their ids
        // Specify also the id of the sender
        conn_out.write(new byte[]{(byte)this.id, (byte) lid, (byte) rid});
        while(conn_in.read(buf) == 0){} // Busy waiting
        return buf[0] == 1;
    }
}
