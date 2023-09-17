public class Main {
    public static void main(String[] args){
        Table t = new Table();

        Philophe p0 = new Philophe("Philosophe 0", t, 0);
        Philophe p1 = new Philophe("Philosophe 1", t, 1);
        Philophe p2 = new Philophe("Philosophe 2", t, 2);
        Philophe p3 = new Philophe("Philosophe 3", t, 3);
        Philophe p4 = new Philophe("Philosophe 4", t, 4);

        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();

        try {
            p0.join();
            p1.join();
            p2.join();
            p3.join();
            p4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
