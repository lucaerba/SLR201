public class Main {
    public static void main(String[] args){
        IOUtilities IO = null;
        System.out.println(args[0]);

        if(args[0].equals("-f")){
            IO = IOUtilities.forFile("output.txt");
        }else if (args[0].equals("-c")) {
            IO = IOUtilities.forKeyboard(

            );
        }else if (args[0].equals("-s")){
            IO = IOUtilities.forFile("output.txt");
        }

        Table t = new Table(IO);

        Philophe p0 = new Philophe("Philosophe 0", t, 0, IO);
        Philophe p1 = new Philophe("Philosophe 1", t, 1, IO);
        Philophe p2 = new Philophe("Philosophe 2", t, 2, IO);
        Philophe p3 = new Philophe("Philosophe 3", t, 3, IO);
        Philophe p4 = new Philophe("Philosophe 4", t, 4, IO);

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

            IO.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
