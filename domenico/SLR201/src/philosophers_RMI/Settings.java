package philosophers_RMI;

public interface Settings {
    public final static int PORT = 8888;
    public final static int RMI_REGISTRY_PORT = 9999;
    public final static String HOST = "localhost";
    public final static int NUM_PHILOSOPHERS = 10;
    public final static String SERVICE_NAME = "FORK N#";
    public final static int THINKING_TIME = 256;
}
