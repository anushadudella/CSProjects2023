import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    static Node root = null;
    private static boolean flag = false;
    private static String path = "";
    private static String pathFinal = "";

    public static void main(String[] args) {

        // Combining all the lines of the
        // input file into one string
        // called "allLines"
        String allLines = "" , line = "";
        try{
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null){
                allLines += line;
            }
            countChars(allLines);
        } catch (Exception e ) {
            System.out.println(e.toString());
        }
    }

    // @param: takes all lines of the input file and creates
    // a hash-map of letters and their frequencies
    // also calls the method "completeSet()" that builds the nodes and tree
    // and calls the method "printCompressedBitPattern()" to write
    // the final bit representation of the input text.
    private static void countChars(String allLines) {
        HashMap<String, Integer> countsOfLetters = new HashMap<String, Integer>();
        for (String letter : allLines.split("")) {
            if (countsOfLetters.containsKey(letter)) {
                countsOfLetters.put(letter, countsOfLetters.get(letter) + 1);
            } else {
                countsOfLetters.put(letter, 1);
            }
        }
        System.out.println("CHARACTER COUNT: ");
        System.out.println(countsOfLetters);
        HashMap<String, String> hmFinal = completeSet(countsOfLetters);
        System.out.println("\nCHARACTER ENCODING: ");
        System.out.println(hmFinal);

        printCompressedBitPattern(allLines, hmFinal);

    }

    // @param takes all lines of input text and the final mapping between
    // nodes and their bit pattern representation to print out the
    // final encoded pattern

    private static void printCompressedBitPattern(String allLines, HashMap<String,String> hmFinal) {

        String codedLetters = "";
        for(String letter: allLines.split("")) {
            if(hmFinal.containsKey(letter)) {
                codedLetters = codedLetters + hmFinal.get(letter) ;
            }
        }
        System.out.println("\nCOMPRESSED BIT PATTERN: ");
        System.out.println(codedLetters);

    }

    // @param: takes the letters their frequencies hashmap and creates the additional
    // set of nodes and their frequencies
    // calls the method "buildTree()" to build the tree from the final set of nodes
    private static HashMap<String, String> completeSet(HashMap<String,Integer> subset) {

        int counter = subset.size();
        int setsize = counter;

        HashMap<String, String> hmFinal = new HashMap<String, String>();
        HashMap<String, Integer> hm = sortByValue(subset);
        Set<String> keySet = hm.keySet();
        ArrayList<String> listOfKeysCopy = new ArrayList<String>(keySet);
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);

        Collection<Integer> values = hm.values();
        ArrayList<Integer> listOfValues = new ArrayList<>(values);
        int i = 0;

        // Iterates through the set called "subset" that keeps getting added of new nodes
        // A new key value pair is added by taking the lowest 2 frequency and making a
        // new node with sum as the frequency of new node
        while(counter != ((setsize * 2) - 1)) {
            subset.put(listOfKeys.get(i) + listOfKeys.get(i + 1),listOfValues.get(i) + listOfValues.get(i + 1));
            hm = sortByValue(subset);
            keySet = hm.keySet();
            listOfKeys = new ArrayList<String>(keySet);
            values = hm.values();
            listOfValues = new ArrayList<>(values);
            counter = subset.size();
            i = i + 2;
        }

        // creates the final tree structure with the new key value pair
        // that got added with the logic mentioned right above.
        buildTree(listOfKeys,listOfValues, values.size());


        // for each node in the original text find its location in the tree
        // by calling method "searchNode()" and trace its path and store it
        for(String s : listOfKeysCopy) {
            pathFinal = "";
            path = "";
            flag = false;
            searchNode(root, s);
            hmFinal.put(s,pathFinal);
        }
        return hmFinal;
    }

    // @param: takes letters (listofKeys) and their frequencies (listOfValues)
    // the tree building starts at the highest frequency and iterates through the
    // list in descending order of frequencies of letters
    public static void buildTree(ArrayList<String> listOfKeys, ArrayList<Integer> listOfValues, int  valuesSize) {

        int value1, value2 = 0;
        String key1 = null;
        String key2 = null;

        // Get the last element as the root of the tree to be
        // constructed
        int rootvalue = listOfValues.get(valuesSize-1);
        String rootnode = listOfKeys.get(valuesSize-1);

        root = new Node(rootvalue, rootnode);
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(root);

        // iterate through all the nodes in the queue starting from
        // the second to last element as the last element is the root
        // taking 2 elements at a time, we come up with the parent and search
        // for this parent in the queue so that the 2 elements we picked
        // can be the left and right children of this parent
        for(int j = valuesSize-2 ; j > 0; j = j -2  ) {
            value1 = listOfValues.get(j);
            value2 = listOfValues.get(j-1);
            key1 = listOfKeys.get(j);
            key2 = listOfKeys.get(j-1);

            Node parentNode = searchQueue(queue,value1 + value2);
            Node node1 = new Node(value1,key1);
            Node node2 = new Node(value2,key2);
            queue.add(node1);
            queue.add(node2);

            if (value1 >= value2) {
                parentNode.right = node1;
                parentNode.left = node2;
            } else {
                parentNode.left = node1;
                parentNode.right = node2;
            }
        }


    }

    // @param: takes root node and the letter to search for
    // recursively loops through the tree and traces
    // from root to the letter and formulates the path in
    // the variable "path"
    // referred to: https://www.geeksforgeeks.org/search-a-node-in-binary-tree/
    public static void searchNode(Node temp,String searchtag){

        //Check whether tree is empty
        if(root == null){
            System.out.println("Tree is empty");
        }
        else{
            if(temp.tag.equals(searchtag)){
                flag = true;
                pathFinal = path;
                return;
            }
            if (flag == false && temp.left != null) {
                path = path + "1";
                searchNode(temp.left, searchtag);
            }
            if (flag == false && temp.right != null) {
                path = path + "0";
                searchNode(temp.right, searchtag);
            }
            path = path.substring(0,path.length()-1);
        }
    }

    // @param: takes a queue of nodes and search key
    // searching for the node which has the key
    private static Node searchQueue(LinkedList<Node> queue, int key) {
        for (Iterator i = queue.iterator(); i.hasNext();) {
            Node n = (Node) i.next();
            if(n.key == key )
                return n;
        }
        return null;
    }

    // A standard Node definition that represents in our case
    // the letter and its frequency of occurrence
    static class Node
    {
        String tag;
        int key;
        Node left, right;
        Node(int key, String tag)
        {
            this.key = key;
            this.tag = tag;
            left = right = null;
        }
    };

    // @param: takes the hash-map of letters and frequencies
    // and sorts the hash-map as per the frequency of that letter
    // referred to: https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
