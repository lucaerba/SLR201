public class Main {
    public static void main(String[] args){
        Client[] clients = new Client[5];
        Server[] servers = new Server[5];

        for(int i=0; i<5; i++){
            clients[i] = new Client("localhost", 49153+i);
            servers[i] = new Server(49153+i);
        }

        for(int i=0; i<5; i++){
            servers[i].start();
        }

        for(int i=0; i<5; i++){
            clients[i].start();
        }
    }
}
