package anagram_solver;

import java.util.Arrays;
import java.util.List;

public class AnagramSolver {
	
	private String[] anagram;
	
	private static List<String> dictionary;
	private static int max;
	private static String phrase;
	
	
	public static final int ACCEPT = 1;
	public static final int ABANDON = 2;
	public static final int CONTINUE = 3;
	
	public AnagramSolver(int size){
		anagram = new String[size];
	}
	
	public void setAnagram(String[] a){
		anagram = a;
	}
	
	public static void setDictionary(List<String> dictionary) {
		AnagramSolver.dictionary = dictionary;
	}

	public static void setMax(int max) {
		AnagramSolver.max = max;
	}

	public static void setPhrase(String phrase) {
		AnagramSolver.phrase = phrase;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(anagram);
	}
	
	/**
    Examines a partial solution.
	@return one of ACCEPT, ABANDON, CONTINUE
	*/
	public int examine(){
		
		if(isAnagram(Arrays.toString(anagram), phrase))
			return ACCEPT;
		
		else if(anagram.length >= max)
			return ABANDON;
		
		else 
			return CONTINUE;
	}
	
	/**
    Yields all extensions of this partial solution.
    @return an array of partial solutions that extend this solution.
	*/
	public AnagramSolver[] extend(){
		
		AnagramSolver[] result = new AnagramSolver[dictionary.size()];
		
		for (int i = 0; i < result.length; i++){
			int size = anagram.length;

	        // The new solution has one more row than this one
	        result[i] = new AnagramSolver(size + 1);
	
	        // Copy this solution into the new one
	        for (int j = 0; j < size; j++)
	        	result[i].anagram[j] = anagram[j];
	
	        // Append the new word
	        result[i].anagram[size] = dictionary.get(i);
	      }
		return result;
	}
	
	
	
	
	//------------------- helper methods --------------------------
	
	boolean isAnagram(String s1, String s2){	
		return toStandardForm(s1).equals(toStandardForm(s2));
	}
	
	// to lower case; only letters; in sorted order
	String toStandardForm(String s){
		String s1 = "";
		char[] characters = s.toLowerCase().toCharArray();
		Arrays.sort(characters);
		for (char ch: characters){
			if(Character.isLetter(ch))
				s1 += Character.toString(ch);
		}
		return s1;		
	}
	
	
	
	

}// end of class





















