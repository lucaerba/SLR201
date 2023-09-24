
public class Philophe extends Thread{
    private String name = null;
    private Table table = null;
    private int pos = -1;
    private boolean hasEaten = false;
    private IOUtilities io = null;

    public Philophe(String name, Table table, int pos, IOUtilities io){
        this.table = table;
        this.name = name;
        this.pos = pos;
        this.io = io;
    }

    public void run(){
        for(int i=0; i<3; i++){
            System.out.println(name + " try eat!");
            io.write( name + " try eat!\n");
            table.tryToEat(pos);
            System.out.println(name + " got forks!");
            io.write( name + " got forks!\n");
            table.eat(pos);
            System.out.println(name + " has eaten! slurp");
            io.write( name + " has eaten! slurp\n");
        }
    }
}
