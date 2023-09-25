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
                IOUtilities reader = IOUtilities.forInputStream(socket.getInputStream());
                IOUtilities writer = IOUtilities.forOutputStream(socket.getOutputStream());

                String input = reader.read();
                System.out.println(input);

                writer.write("Hello " + input + "!\n");
                writer.close();
                reader.close();
            }
        }catch (Exception e){

        }
    }


}
