public class Main {
    public static void main(String[] args) {
        MyThread m1 = new MyThread("giovanni");
        MyThread m2 = new MyThread("ciro");

        m1.start();
        m2.start();

        try {
            m1.join();
            m2.join();
        }catch (Exception e){}

    }
}