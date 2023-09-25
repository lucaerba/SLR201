public class Philosophe{
    private String name = null;
    private Table table = null;
    private int pos = -1;
    private boolean hasEaten = false;
    private int eatenCount = 0;

    public Philosophe(String name, Table table, int pos){
        this.table = table;
        this.name = name;
        this.pos = pos;
    }

    public String getName(){
        return name;
    }
    public int getPos(){
        return pos;
    }
    public void addEat(){
        eatenCount++;
        System.out.println(name + " has eaten "+eatenCount+" times");
    }
}