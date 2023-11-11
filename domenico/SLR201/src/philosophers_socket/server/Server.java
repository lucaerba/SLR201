package philosophers_socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread{
    private final Integer port;
    private final Fork[] forks;
    private Tournament t;

    public Server(Fork[] forks, Tournament t, int port){
        this.forks = forks;
        this.t = t;
        this.port = port;
    }

    /**
     * The server works independently of the number of philosphers chosen
     */
    public void run(){
        
        while (true) {
            // It locks this port only to have a check on when the tournament is deciding winners
            synchronized(this.port){
                try (ServerSocket ss = new ServerSocket(this.port)) {
                    Socket server = ss.accept();
                    System.out.println("Ciao belo");
                    try (
                        // Data input stream
                        DataInputStream dis = new DataInputStream(server.getInputStream());
                        // Byte output stream
                        DataOutputStream dos = new DataOutputStream(server.getOutputStream())
                        ){
                        byte[] buf = new byte[3];
                        if (dis.read(buf) != 0) {
                            int phil_id = buf[0];
                            int lf = buf[1];
                            int rf = buf[2];
                            if(this.t.winning_participants[phil_id] == 1){
                                Fork[] request_forks = new Fork[]{this.forks[lf], this.forks[rf]};
                                request_forks[0].eat();
                                request_forks[1].eat();
                                dos.write(1);

                            } else dos.write(0);
                        }
                    } catch (Exception ignored) {}
                } catch (IOException ignored) {}
            }
        }
    }
}
