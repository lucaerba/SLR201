
public class CommandsBuffer{
    private String name = null ;
    private String[] commands = new String[1024];
    private int len = 0;

    public CommandsBuffer(String name){
        this.name = name;
    }
    public CommandsBuffer(){
     
    }

    public synchronized void push(String command){
        commands[len++] = command;
        try {
            notifyAll();   
        } catch (Exception e) {}
    }

    public synchronized String pop(){
        while(len == 0){ 
            try{
                wait();   
            } catch (Exception e) {}
        }
        return commands[--len];
    }

    public int getLen(){
        return len;
    }
}