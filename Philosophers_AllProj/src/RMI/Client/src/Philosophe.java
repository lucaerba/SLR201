package RMI.Client.src;

public class Philosophe{
    private String name = null;
    private int pos = -1;
    private int eatenCount = 0;

    public Philosophe(String name, int pos){
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
        //System.out.println(name + " has eaten "+eatenCount+" times");
    }
    
    public int getEatenCount(){
        return eatenCount;
    }
}