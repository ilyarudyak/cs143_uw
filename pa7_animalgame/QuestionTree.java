import java.util.*;
import java.io.*;

/**
*   You are to implement a yes/no guessing game.  The idea is that 
*   you construct a binary tree where each leaf has the name of an 
*   object and each branch node has a yes/no question that 
*   distinguishes between the objects.
*/
public class QuestionTree{

    private Node root;
    private Scanner console;

    /** This method should construct a question tree with one 
     *  leaf node representing the object “computer”.
     */
    public QuestionTree(){
        root = new Node("computer", false);
        console = new Scanner(System.in);
    }

    // -------------------- read & write ----------------------------
    
    /** 
     *  1) Your method will be passed a Scanner that is linked to 
     *  the file and should replace the current tree with a new 
     *  tree using the information in the file. 
     */
    public void read(Scanner in){
        root = read(root, in);
    }

    private Node read(Node node, Scanner in){
        
        String flag = in.nextLine().trim();
        String data = in.nextLine().trim();

        if (node == null)
            node = new Node();

        if (flag.equals("A:"))
            return new Node(data, false);

        node.data = data;
        node.isQuestion = true;
        node.left = read(node.left, in);
        node.right = read(node.right, in);

        return node;
    }

    /** 2) */
    public void write(PrintWriter out){

        printPreorder(root, out);
        out.close();

    }

    private void printPreorder(Node node, PrintWriter out) {
        if (node != null) {
            if (node.isQuestion == true)
                out.println("Q:\n" + node.data);
            else
                out.println("A:\n" + node.data);
            printPreorder(node.left, out);
            printPreorder(node.right, out);
        }
    }


    // -------------------- read & write ----------------------------

    /** 3) */
    public void askQuestions(){

        askQuestions(root);
    }

    private void askQuestions(Node node) {

        if (node.isQuestion == false){

            // we quessed correct and return
            if (yesTo("Would your object happen to be " + node.data)){
                System.out.println("Great, I got it right!");
                return;
            }
            // we quessed incorrect and ask for new question and answer
            // to modify our tree
            else{
                String oldAnswer = node.data;
                System.out.print("What is the name of your object? ");
                String answer = console.nextLine().trim();
                System.out.print("Please give me a yes/no question that " +
                                   "distinguishes between your object " +
                                   "and mine--> ");
                String question = console.nextLine().trim();

                // change node type to question and add data
                node.data = question;
                node.isQuestion = true;

                // if yes go left
                if (yesTo("And what is the answer for your object")) {
                    node.left = new Node(answer, false);
                    node.right = new Node(oldAnswer, false);
                }
                // else go right
                else{
                    node.left = new Node(oldAnswer, false);
                    node.right = new Node(answer, false);
                }
            }
        }// end outer if
        else {
            if (yesTo(node.data))
                askQuestions(node.left);
            else askQuestions(node.right);
        }
    }

    //---------------------------------------------------------------

    /** 
    *   4) post: asks the user a question, forcing an answer of "y" or "n";
    *      returns true if the answer was yes, returns false otherwise
    */
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    


    

    private class Node {
        private String data;
        private boolean isQuestion;
        private Node left;
        private Node right;
                    
        public Node() {
            data = "";
            isQuestion = false;
            left = null;
            right = null;
        }
        
        public Node(String data, boolean isQuestion) {
            this.data = data;
            this.isQuestion = isQuestion;
            this.left = null;
            this.right = null;
        }

        public Node(String data, boolean isQuestion, 
                Node left, Node right) {
            this.data = data;
            this.isQuestion = isQuestion;
            this.left = left;
            this.right = right;
            
        }




    } // end of class Node



















} // end of QuestionTree class
