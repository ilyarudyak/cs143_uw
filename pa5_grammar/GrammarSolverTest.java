import org.junit.Test; 
import org.junit.Assert;
import java.util.*;
import java.io.*;


public class GrammarSolverTest {

    public static final String FILE_NAME = "sentence.txt";



    @Test 
    public void grammar() throws IOException {

        // open grammar file and read grammar
        Scanner input = new Scanner(new File(FILE_NAME));
        List<String> grammar = new ArrayList<String>();
        while (input.hasNextLine()) {
            String next = input.nextLine().trim();
            if (next.length() > 0)
                grammar.add(next);
        }

        // create GrammarSolver
        GrammarSolver solver = new GrammarSolver(grammar);
        //System.out.println(solver);
        //System.out.println(solver.getSymbols());
        String[] rule = {"the", "a"};
        Assert.assertArrayEquals(rule, solver.getRules("<dp>"));

        
        System.out.println(Arrays.toString(solver.generate("<dp>",10)));

    }


}

