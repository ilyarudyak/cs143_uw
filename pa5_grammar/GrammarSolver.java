import java.io.*;
import java.util.*;

public class GrammarSolver{

    private SortedMap<String, String[]> grammar;

    /** This method will be passed a grammar as 
      a List of Strings and store this 
      in a Map  */
    public GrammarSolver(List<String> g){

        // initialize grammar
        grammar = new TreeMap<String, String[]>();

        // build grammar
        for (String s : g){
            String[] parts = s.split("::=");
            grammar.put(parts[0], parts[1].split("[|]"));

        }

    }

    public String[] getRules(String symbol){
        return grammar.get(symbol);
    }

    public String toString(){
        String s = "\n";
        for (String key : grammar.keySet()){
            s += key + "=" + Arrays.toString(grammar.get(key)) + "\n";
        }
        return s;
    }



    /** Returns true if the given symbol 
      is a nonterminal of the grammar; 
      returns false otherwise.*/
    public boolean isSymbol(String symbol){

        if (!symbol.contains(" "))
            return grammar.keySet().contains(symbol);
        else{
            String[] parts = symbol.split(" ");
            return grammar.keySet().contains(parts[0]) &&
                grammar.keySet().contains(parts[1]);
        }
    }

    public boolean isTerminal(String symbol){
        return !isSymbol(symbol);
    }

    /** we use the grammar to randomly generate
      the given number of occurrences of the given symbol */
    public String[] generate(String symbol, int times){

        String[] a = new String[times];
        if (!isSymbol(symbol) || times < 0)
            throw new IllegalArgumentException();

        for (int i=0; i<times; i++){
            
            a[i] = generate(symbol, "").trim();
        }

        return a;

    }

    private String generate(String symbol, String s){
        
        Random rnd = new Random();

        if (isTerminal(symbol))
            return s + symbol + " ";

        // single string like <tv>, not <tv> <np>
        else if (!symbol.contains(" ")){
            String[] rules = grammar.get(symbol);
            int index = rnd.nextInt(rules.length);
            String symbolNew = rules[index];
            return generate(symbolNew, s);
        }

        else {
            String[] parts = symbol.split(" ");
            return generate(parts[0], s) + 
                generate(parts[1], s);
        }
    }

    /** returns non-terminal symbols*/
    public String getSymbols(){
        return grammar.keySet().toString();
    }









}
