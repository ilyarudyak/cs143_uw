import org.junit.Test; 
import org.junit.Assert;
import java.util.*;
import java.io.*;


public class HangmanManagerTest {

    public static final String DICTIONARY_FILE = "dictionary2.txt";



    @Test 
    public void words() throws IOException {

        // open the dictionary file and read dictionary into an ArrayList
        Scanner input = new Scanner(new File(DICTIONARY_FILE));
        List<String> dictionary = new ArrayList<String>();
        while (input.hasNext())
            dictionary.add(input.next().toLowerCase());


        HangmanManager hangman = new HangmanManager(dictionary, 3, 0);
        SortedSet<String> set = hangman.words();
        Assert.assertEquals(0, set.size());

        HangmanManager hangman1 = new HangmanManager(dictionary, 4, 0);
        SortedSet<String> set1 = hangman1.words();
        Assert.assertEquals(9, set1.size());

        Assert.assertEquals("ally", set1.first());
        Assert.assertEquals("ibex", set1.last());

        Assert.assertEquals("----", hangman1.pattern());
    }

    @Test
    public void record() throws IOException{

        // open the dictionary file and read dictionary into an ArrayList
        Scanner input = new Scanner(new File(DICTIONARY_FILE));
        List<String> dictionary = new ArrayList<String>();
        while (input.hasNext())
            dictionary.add(input.next().toLowerCase());


        HangmanManager hangman = new HangmanManager(dictionary, 4, 10);

        // test buildPattern()
        Assert.assertEquals("-e--", hangman.buildPattern('e', "beta"));
        Assert.assertEquals("e--e", hangman.buildPattern('e', "else"));
        Assert.assertEquals("----", hangman.buildPattern('e', "cool"));

        // test record()
        hangman.record('e');
        Assert.assertEquals("----", hangman.pattern());

        hangman.record('o');
        Assert.assertEquals("-oo-", hangman.pattern());

        hangman.record('d');
        Assert.assertEquals("-oo-", hangman.pattern());

        hangman.record('c');
        Assert.assertEquals("coo-", hangman.pattern());

        hangman.record('f');
        Assert.assertEquals("coo-", hangman.pattern());

        hangman.record('l');
        Assert.assertEquals("cool", hangman.pattern());









    }
        

}
