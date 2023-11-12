package Threads;

public class Philophe extends Thread{
    public boolean finished = false;
    private String name = null;
    private Table table = null;
    private int pos = -1;
    private int eatenCount = 0;

    public Philophe(String name, Table table, int pos){
        this.table = table;
        this.name = name;
        this.pos = pos;
    }

    public void run(){
        while(!finished){
            System.out.println(name + " try eat!");
            if(!table.tryToEat(pos)){
                System.out.println("Philosophe "+pos+" thinks...");
                while(!table.tryToEat(pos)){

                };
                System.out.println("Philosophe "+pos+" finally...");
            }
            System.out.println(name + " got forks!");
            table.eat(pos);
            eatenCount++;
        }
        System.out.println(name + " has eaten "+ eatenCount+ " times.");
    }
}
