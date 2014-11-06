import java.io.*;
import java.util.*;

class AssassinManager{

    private List<String> killRing;
    private List<String> graveyard;

    public AssassinManager(List<String> names){

        if (names == null)
            throw new IllegalArgumentException();

        graveyard = new LinkedList<String>();
        killRing = new LinkedList<String>(names);
    }

    void printKillRing(){
        if (!gameOver()){
            for (String person : killRing)
                System.out.print(person + " --> ");
        }
        System.out.print(killRing.get(0) + "\n");

    }

    // ok
    void printGraveyard(){
        System.out.println(graveyard);
    }

    // ok
    boolean killRingContains(String name){
        return killRing.contains(name.toLowerCase());
    }

    // ok
    boolean graveyardContains(String name){
        return graveyard.contains(name.toLowerCase());
    }

    // ok
    boolean gameOver(){
        if(killRing.size() == 1)
            return true;
        return false;
    }

    // ok
    String winner(){
        if (!gameOver())
            return null;
        else
            return killRing.get(0);
    }
    // ok
    void kill(String name){
        name = name.toLowerCase();
        if (!killRing.contains(name)){
            throw new IllegalArgumentException();
        }
        
        else if (gameOver()){
            throw new IllegalStateException();
        }
        else{
            killRing.remove(name);
            graveyard.add(name);
        }
            
    }

}
