package philosophers_socket;

public interface Settings {
    /**
     * The system works for values of NUM_PHILOSOPHERS and NUM_PORTS 
     * other than 5. The strict requirement is to have NUM_PORTS >= NUM_PHILOSOPHERS
     */
    public final static int PORT = 8888;
    public final static int NUM_PORTS = 8;
    public final static String HOST = "localhost";
    public final static int NUM_PHILOSOPHERS = 7;
    public final static int THINKING_TIME = 256;
    public final static int TOURNAMENT_WAIT_TIME = 256;
}
