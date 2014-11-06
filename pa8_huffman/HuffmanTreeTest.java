import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import java.util.*;


public class HuffmanTreeTest {

	@Test
	public void testConstructor() {

        int[] count = new int[MakeCode.CHAR_MAX];
        count[97] = 2;
        count[98] = 3;
        count[99] = 4;
        
		HuffmanTree tree = new HuffmanTree(count);

        //System.out.println();
        //System.out.println(tree);


	}

    @Test
    public void testMakeCode(){

        MakeCode mk = new MakeCode("short.txt");
        //System.out.println(mk);
    }

    @Test
    public void testConstructorWithMakeCode(){
        MakeCode mk = new MakeCode("hamlet.txt");
        HuffmanTree tree = new HuffmanTree(mk.getCount());
        System.out.println();
        System.out.println(tree);

    }



}// end of class
