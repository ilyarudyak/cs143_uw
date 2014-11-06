package anagram_solver;

import java.io.*;
import java.util.*;

public class AnagramMain {
    public static void main(String[] args) throws FileNotFoundException {
    	
    	// create new anagram solver
    	AnagramSolver as = new AnagramSolver(0);
    	
        //System.out.println("Welcome to the cse143 anagram solver.");
        //System.out.println();

        // open the dictionary file
        //Scanner console = new Scanner(System.in);
        //System.out.print("What is the name of the dictionary file? ");
        Scanner input = new Scanner(new File("dict/dict0.txt"));

        // read dictionary into an ArrayList
        List<String> dictionary = new ArrayList<String>();
        while (input.hasNextLine())
            dictionary.add(input.nextLine());
        AnagramSolver.setDictionary(dictionary); // set dictionary
        
        // ask phrase
        //System.out.println();
        //System.out.print("phrase to scramble (return to quit)? ");
        //String phrase = console.nextLine();
        AnagramSolver.setPhrase("George BUSH"); // set phrase

        // ask max
        //System.out.print("Max words to include (0 for no max)? ");
    	//int max = console.nextInt();
    	AnagramSolver.setMax(4); // set max
    	// to skip newline
        //console.nextLine();  
        
        // solve it
        solve(as);
        
        // close all resources
        //console.close();
        input.close();
        
    }// end of main
    
    public static void solve(AnagramSolver sol){
       int exam = sol.examine();
       System.out.println(sol + " " + exam);
       if (exam == AnagramSolver.ACCEPT)
          System.out.println(sol); 
       else if (exam == AnagramSolver.CONTINUE){
          for (AnagramSolver as : sol.extend())
             solve(as);
       }
    }// end of solve
    
    
    
    
    
}// end of class
