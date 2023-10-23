public class Main {
    public static void main(String[] args){
        Server[] servers = new Server[5];
        Table t = new Table();


        for(int i=0; i<5; i++){
            servers[i] = new Server(49153+i, t);
        }

        for(int i=0; i<5; i++){
            servers[i].start();
        }

    }
}
