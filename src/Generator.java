import com.sun.jdi.Value;

public class Generator extends Thread{
    private BlockingQueue Directories;

    public Generator(BlockingQueue BQ){
        this.Directories = BQ;
    }

    public BlockingQueue getbq(){
        return this.Directories;
    }

    @Override
    public void run () {
//        for (int i = 0; i < 100; i++)
//        {
//            String filename = "src/data/f" + String.valueOf(i) + ".txt";
//            try {
//                Directories.add(filename);
//            }catch (InterruptedException e ){
//                e.printStackTrace();
//            }
//        }

        int i = 0;
        while(true){
            String filename = "src/data/f" + String.valueOf(i) + ".txt";
            try {
                Directories.add(filename);
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
            if(i == 99){
                System.out.println(this.getClass().getName());
                this.yield();
                break;
            }
            i++;
        }
    }
}
