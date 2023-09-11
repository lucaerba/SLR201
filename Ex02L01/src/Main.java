public class Main {
    public static void main(String[] args) {
        CommandsBuffer buffer = new CommandsBuffer();
        PushThread pushThread = new PushThread(buffer);
        PullThread pullThread = new PullThread(buffer);

        pushThread.start();
        pullThread.start();

        try {
            pushThread.join();
            pullThread.join();
        }catch (Exception e){}

    }
}