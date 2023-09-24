import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private ServerSocket serverSocket;
    private Socket socket;

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){
        try {
            while (true){
                socket = serverSocket.accept();

                socket.getOutputStream().write(1015);
                System.out.println(socket.getInputStream().read());
            }

        }catch (Exception e){

        }
    }


}
