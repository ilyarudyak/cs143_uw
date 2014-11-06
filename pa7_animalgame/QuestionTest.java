import java.io.*;
import java.util.*;


public class QuestionTest{

    public static final String FILE = "2.txt";
    public static final String FILE2 = "3.txt";

    public static void main(String[] args) 
        throws FileNotFoundException {

        Scanner in = new Scanner(new File(FILE));
        PrintWriter out = new PrintWriter(FILE2);

        QuestionTree tree = new QuestionTree();
        tree.read(in);
        tree.write(out);

        in.close();
        out.close();









    } // end of main
} // end of class QuestionTest
