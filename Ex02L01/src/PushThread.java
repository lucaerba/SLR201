public class PushThread extends Thread{
    private CommandsBuffer buffer = null;
    private int nItx = 100;

    public PushThread(CommandsBuffer buffer){
        this.buffer = buffer;
    }

    public void run(){
        for(int i = 0; i<nItx; i++){
            String command = "Command "+i;
            buffer.push(command);
            System.out.println("Write: "+command);
            try {
                Thread.sleep(50);   
            } catch (Exception e) {
            }
        }    
    }

}
