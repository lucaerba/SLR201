package Sockets.server;

import java.net.Socket;

public class Main {

    public static void main(String[] args){
        int nServers = 5;
        Server[] servers = new Server[nServers];
        Table t = new Table();

        for(int i=0; i<nServers; i++){
            servers[i] = new Server(Sockets.client.Main.port +i, t);
        }

        for(int i=0; i<nServers; i++){
            servers[i].start();
        }
    }
}
