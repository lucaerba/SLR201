import java.io.IOException;
import java.net.*;

public class Client extends Thread{
    private Socket socket;
    private InetAddress address;
    private SocketAddress socketAddress;
    private int port;
    private Philosophe philosophe;

    public Client(String ip, int port, Philosophe p){
        if(ip.equals("localhost") || ip.equals("127.0.0.1")){
            try {
                this.address = InetAddress.getByName("localhost");
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                this.address = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }

        this.port = port;
        this.philosophe = p;
        this.socketAddress = new InetSocketAddress(address, port);
    }

    public void run(){
        try {
            while (!Thread.currentThread().isInterrupted()){
                socket = new Socket();
                socket.connect(socketAddress);
                IOUtilities writer = IOUtilities.forOutputStream(socket.getOutputStream());
                IOUtilities reader = IOUtilities.forInputStream(socket.getInputStream());

                System.out.println(philosophe.getName() + " try eat!");
                writer.write(philosophe.getPos() + "-" + 1 +"\n");
                String s = reader.read();
                if(!s.equals("OK")){
                    while(s.equals("KO")){
                        System.out.println("Philosophe "+philosophe.getPos()+" back to think...");
                        s = reader.read();
                    }
                }
                System.out.println(philosophe.getName() + " got forks!");
                writer.write(philosophe.getPos() + "-" + 2 +"\n");
                s = reader.read();
                if(!s.equals("OK")){
                    continue;
                }

                System.out.println(philosophe.getName() + " has eaten! slurp");

                writer.close();
                reader.close();
                philosophe.addEat();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Philosophe "+ philosophe.getPos()+" has eaten "+philosophe.getEatenCount());
        }
    }
}
