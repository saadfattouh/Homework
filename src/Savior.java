import data.FileUtils;

import java.io.IOException;

public class Savior extends Thread{

    private BlockingQueue results;

    //for breaking the loop when finished
    int counter = 0;

    public Savior(BlockingQueue BQ){
        this.results = BQ;
    }

    public void setbq(BlockingQueue BQ){
        this.results = BQ;
    }
    public BlockingQueue getbq(){
        return this.results;
    }


    @Override
    public void run(){


        String fileinfo = "";
        String archive = "";

        while (true){

            if(counter >= 99){
                System.out.println(this.getName());
                break;
            }

            try {
                if(results.isEmpty())
                    continue;
                //I couldn't read it as a string so i made an object variable instead
                //and converted it to its string value by using toString() method
                else {
                    Object o = results.pop();
                    fileinfo = o.toString();
                    counter++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            archive += "\n" + fileinfo +"\n";



        }
        try {
            FileUtils.appendStringToFile("src/data/results.txt", "file name "+"    "+"#letters"+"   "+"#numbers"+"   "+"#rest");
            FileUtils.appendStringToFile("src/data/results.txt", archive);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }


    }

}
