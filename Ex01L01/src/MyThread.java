import javax.lang.model.element.Name;

public class MyThread extends Thread{
    private String name ;
    private int nIterations = 100;
    public MyThread(String name){
        this.name = name;
    }

    public void run(){
        for(int i=0; i<nIterations; i++){
            System.out.println(name + " " +i );
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("END");
    }
}