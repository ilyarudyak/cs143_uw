// Stuart Reges
// 5/23/05
//
// MakeCode prompts the user for an input file name and a code file name.  It
// examines the input file for the frequencies of characters and then uses a
// Huffman tree to generate codes to use for each character so as to compress
// the original file.  This program does not the actual encoding or decoding.

import java.io.*;
import java.util.*;

public class MakeCode {
    public static final int CHAR_MAX = 256;  // max char value to be encoded

    private int[] count;

    public int[] getCount(){
        return count;
    }

    public MakeCode(String inFile){

        try {
            // open input file and count character frequencies
            FileInputStream input = new FileInputStream(inFile);
            count = new int[CHAR_MAX];
            int n = input.read();
            while (n != -1) {
                count[n]++;
                n = input.read();
            }
        }catch (IOException e){
            System.out.println("error" + e);
        }

    }// end of constructor

    public String toString(){
        String s = "";
        for (int i=0; i<count.length; i++){
            if (count[i] != 0){
                if (i == 10) 
                    s += "NL" + " " + count[i] + "\n";
                else if (i == 13)
                    s += "CR" + " " + count[i] + "\n";
                else if (i == 32)
                    s += "[]" + " " + count[i] + "\n";
                else
                    s += (char) i + " " + count[i] + "\n";
            }
        }
        return s;
    }

}// end of class
