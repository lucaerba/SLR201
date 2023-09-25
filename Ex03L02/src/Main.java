public class Main {
    public static void main(String[] args){
        Client[] clients = new Client[5];
        Server[] servers = new Server[5];
        Table t = new Table();


        for(int i=0; i<5; i++){
            Philosophe p = new Philosophe("Philosophe "+i, t, i);

            clients[i] = new Client("localhost", 49153+i, p);
            servers[i] = new Server(49153+i, t);
        }

        for(int i=0; i<5; i++){
            servers[i].start();
        }

        for(int i=0; i<5; i++){
            clients[i].start();
        }
    }
}
