import java.io.IOException;
import java.net.*;

public class Client extends Thread{
    private Socket socket;
    private InetAddress address;
    private SocketAddress socketAddress;

    public Client(String ip, int port){
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

        this.socketAddress = new InetSocketAddress(address, port);

    }

    public void run(){
        while (true){
            try {
                socket = new Socket();
                socket.connect(socketAddress);
                String
                socket.getOutputStream().write();
                System.out.println(socket.getInputStream().read());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
