public class HelloData implements java.io.Serializable{
    private String message;
    private transient String transientMessage;

    public HelloData(){

    }
}