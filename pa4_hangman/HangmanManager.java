import java.util.*;
import java.io.*;


public class HangmanManager {

    private List<String> dictionary;
    private int length;
    private int guessesLeft;

    private SortedSet<String> words;
    private SortedSet<Character> guesses;
    private String pattern;
  


    public HangmanManager(List<String> dictionary, 
            int length, int max){
        this.dictionary = dictionary;
        this.length = length;
        this.guessesLeft = max;

        this.words = this.words();
        this.guesses = new TreeSet<Character>();

        this.pattern = "";
        for (int i=0; i<this.length; i++){
            this.pattern += "-";
        }
    }

    public SortedSet<String> getWords(){
       return words; 
    }

    /**build set of words with given length 
     from dictionary*/
    public SortedSet<String> words(){
        SortedSet<String> set = new TreeSet<String>();
        for (String word : dictionary){
            if (word.length() == length)
                set.add(word);
        }
        return set;
    }

    /** how many guesses the player has left */
    public int guessesLeft(){
        return guessesLeft;
    }

    /** find out the current set of letters 
      that have been guessed by the user. */
    public SortedSet<Character> guesses(){
        return guesses;
    }

    /** return the current pattern to be displayed 
      for the hangman game taking into account 
      guesses that have been made*/
    public String pattern(){
        return pattern;
    }

    /** */
    public int record(char guess){

        int r = 0;

        // check if guessesLeft > 0
        if (guessesLeft == 0)
            throw new IllegalStateException();

        // check if guess is not in guesses
        if (guesses.contains(guess))
            throw new IllegalArgumentException();
    
        Map<String, SortedSet<String>> map = new TreeMap<>();

        // build map
        for (String word : words){
            String p = buildPattern(guess, word);
            if (map.containsKey(p))
                map.get(p).add(word);
            else{ 
                SortedSet<String> set = new TreeSet<String>();
                set.add(word);
                map.put(p, set);
            }
        }
        
        // get key with max set
        String key = chooseMaxSet(map);
        
        // if pattern the same - wrong guess
        if (pattern.equals(key))
            guessesLeft--;
            
        else
            r = difference(key, pattern); 
        


        // update pattern, words and guesses
        pattern = key;
        words = map.get(key);
        guesses.add(guess);


        return r;
    }

    private int difference(String word, String word2){

        int diff = 0;
        for (int i=0; i<word.length(); i++){
            if (word.charAt(i) != word2.charAt(i))
                diff++;
        } 
        return diff;
    }

    private String buildPattern(char guess, String word){
        
        String p = "";
        for (int i=0; i<word.length(); i++){
            if ( guess == word.charAt(i))
               p += Character.toString(guess);
            else
                p += pattern.substring(i, i+1);
        }
        return p;
    }

    private String chooseMaxSet(Map<String, SortedSet<String>> map){

        int max = 0;
        String p = "";
        for (String key : map.keySet()){
            if (map.get(key).size() > max){
                max = map.get(key).size();
                p = key;
            }
        }
        return p;
    }

























}
