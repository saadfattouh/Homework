import java.io.IOException;

public class Main {

    public static void main(String args[]) throws InterruptedException, IOException {
        BlockingQueue Directories = new BlockingQueue(8);
        BlockingQueue fileInfo = new BlockingQueue(1);

        //generator object
        Generator g = new Generator(Directories);

        //extractor objects
        InfoExtractor ex1 = new InfoExtractor(Directories, fileInfo);
        InfoExtractor ex2 = new InfoExtractor(Directories, fileInfo);
        InfoExtractor ex3 = new InfoExtractor(Directories, fileInfo);
        InfoExtractor ex4 = new InfoExtractor(Directories, fileInfo);
        InfoExtractor ex5 = new InfoExtractor(Directories, fileInfo);
        InfoExtractor ex6 = new InfoExtractor(Directories, fileInfo);
        InfoExtractor ex7 = new InfoExtractor(Directories, fileInfo);
        InfoExtractor ex8 = new InfoExtractor(Directories, fileInfo);

        Savior s = new Savior(fileInfo);

        //run forever
        g.start();
        ex1.start();
        ex2.start();
        ex3.start();
        ex4.start();
        ex5.start();
        ex6.start();
        ex7.start();
        ex8.start();
        s.start();



    }
}
