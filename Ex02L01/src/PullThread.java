public class PullThread extends Thread{
    private CommandsBuffer buffer = null;
    private int nItx = 100;

    public PullThread(CommandsBuffer buffer){
        this.buffer = buffer;
    }

    public void run(){
        for(int i = 0; i<nItx; i++){
            String command = buffer.pop();
            System.out.println("Read: " + command);
            try {
                Thread.sleep(50);   
            } catch (Exception e) {
            }
        }    
    }

}
