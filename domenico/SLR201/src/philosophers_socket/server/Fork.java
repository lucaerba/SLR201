package philosophers_socket.server;

public class Fork {
    private final int id;

    public Fork(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    /**
     * Philosophers should use this method to eat.
     * It does nothing and is used only for completeness.
    */
    public void eat(){
        // Consume
    }
}
