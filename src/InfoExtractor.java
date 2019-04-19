import data.FileUtils;

import java.io.IOException;

public class InfoExtractor extends Thread {

    //class attributes
    private BlockingQueue storedBQ;
    private  BlockingQueue resultBQ ;

    static int counter = 1;

    //constructor
    public InfoExtractor(BlockingQueue storedBQ, BlockingQueue resultBQ) {
        this.storedBQ = storedBQ;
        this.resultBQ = resultBQ;

    }

    //set() N get()
    public BlockingQueue getResultBQ() {
        return this.resultBQ; }
    public BlockingQueue getStoredBQ(){
        return this.resultBQ; }

    @Override
    public void  run(){

        int Letters = 0;
        int Numbers = 0;
        int SpecialChars = 0;

        // storedBQ     files directories BlokingQueue
        // o        the returned object from storedBQ.pop()
        // filename     file name after parsing object "o" to String
        // text     the content of the file "filename"

        String filename = "";
        String text = "";

        //start popping out files and extract information from them
        while (true) {

            //for reaching stage three
            if(counter >= 100)
            {
                System.out.println(counter);
                System.out.println(this.getClass().getName());
                break;
            }

            try {
                if(storedBQ.isEmpty())
                    continue;
                //I couldn't read it as a string so i made an object variable instead
                //and converted it to its string value by using toString() method
                else {
                    Object o = storedBQ.pop();
                    filename = o.toString();
                    counter++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                text = FileUtils.readFileAsString(filename);
            }catch(IOException ioe){
                ioe.printStackTrace();
            }

            //extracting information eg. num of letters, num of numbers...etc
            for (int i = 0; i < text.length(); i++) {
                Character ch = text.charAt(i);

                if ((Integer.valueOf(ch) > 64 && Integer.valueOf(ch) < 91) || ((Integer.valueOf(ch) > 96 && Integer.valueOf(ch) < 123))) {
                    Letters++;
                } else if (Integer.valueOf(ch) > 47 && Integer.valueOf(ch) < 58) {
                    Numbers++;
                } else {
                    SpecialChars++;
                }
            }

            //final result to add to the resultBQ later
            String fileinfo = filename + "    " + String.valueOf(Letters) + "    " + String.valueOf(Numbers) + "    " + String.valueOf(SpecialChars);

            try{
                resultBQ.add(fileinfo);
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }

            //reset counters for the next file
            Letters = 0;
            Numbers = 0;
            SpecialChars = 0;

            }




    }




}
