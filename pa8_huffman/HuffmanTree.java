import java.util.*;

public class HuffmanTree {

    // array of freq for each character
    // represented by array index (mCount[97] - freq of 'a')
    private int[] mCount;
    private Node mRoot;
    private PriorityQueue<Node> q;

    public int[] getCount(){
        return mCount;
    }

    public PriorityQueue<Node> getQueue(){
        return q;
    }

    // initialize mCount and copy freq from count
    public HuffmanTree(int[] count){
        mCount = new int[MakeCode.CHAR_MAX];
        for (int i=0; i<mCount.length; i++)
            mCount[i] = count[i];
        
        // build tree
        q = new PriorityQueue<Node>();      
        buildQueue();
        q.add(new Node((char) 255, 1));
        buildHuffmanTree();
        mRoot = q.remove();

    } 

    public String toString(){
        return toString(mRoot, 0);
    }   

    private static String toString(Node n, int level){
        if (n != null) 
            return toString(n.right, level+1) + 
                   makeSpace(level) + n.toString() + "\n" +
                   toString(n.left, level+1);
        else return "";
    }

    private static String makeSpace(int n){
        String s = "";
        for(int i=0; i<n*4; i++)
            s += " ";
        return s;
    }


    // if freq != 0 create leaf and put it to PriorityQueue
    private void buildQueue(){
        for (int i=0; i<mCount.length; i++){
            if (mCount[i] != 0)
                q.add(new Node((char)i, mCount[i]));
        }
    }

    //build HuffmanTree
    private void buildHuffmanTree(){
        while(q.size() > 1)
            combineTwoTrees();
    }

    // combine first two treew in a queue
    private void combineTwoTrees(){

        // construct new Node
        Node l = q.remove();
        Node r = q.remove();
        Node combine = new Node((char)0, l.freq + r.freq);
        combine.left = l;
        combine.right = r;

        // put it into queue
        q.add(combine);
    }

    class Node implements Comparable<Node> {

        private char ch;
        private int freq;
        private Node left;
        private Node right;

        public Node(char c, int f){
            ch = c;
            freq = f;
            left = null;
            right = null;
        }

        public int compareTo(Node that){
            return freq - that.freq;            
        }

        public String toString(){
            if (ch == 0) 
                return Integer.toString(freq);
            else if (ch == 10)
                return "(" + "NL" + ", " +
                    Integer.toString(freq) + ")";
            else if (ch == 13)
                return "(" + "CR" + ", " +
                    Integer.toString(freq) + ")";
            else if (ch == 32)
                return "(" + "[]" + ", " +
                    Integer.toString(freq) + ")";
            else if (ch == 255)
                return "(" + "EOF" + ", " +
                    Integer.toString(freq) + ")";
            else
                return "(" + Character.toString(ch) + ", " +
                    Integer.toString(freq) + ")";
        }



        
    }// end of class Node



}// end of class
