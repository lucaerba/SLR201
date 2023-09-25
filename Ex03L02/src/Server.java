import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server extends Thread{
    private ServerSocket serverSocket;
    private Socket socket;
    private Table table;

    public Server(int port, Table t){
        try {
            serverSocket = new ServerSocket(port);
            this.table = t;
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
                //System.out.println(input);

                Pattern pattern = Pattern.compile("(\\d+)-(\\d+)");
                Matcher matcher = pattern.matcher(input);
                int pos=0, act=0;
                if (matcher.find())
                {
                    pos = Integer.parseInt(matcher.group(1));
                    act = Integer.parseInt(matcher.group(2));
                }
                if(act == 1){
                    table.tryToEat(pos);
                }
                writer.write("OK\n");

                input = reader.read();
                //System.out.println(input);
                matcher = pattern.matcher(input);
                if (matcher.find())
                {
                    pos = Integer.parseInt(matcher.group(1));
                    act = Integer.parseInt(matcher.group(2));
                }
                if(act == 2){
                    table.eat(pos);
                }
                writer.write("OK\n");

                writer.close();
                reader.close();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}

