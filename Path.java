import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        try {
            
            // Reads from the file graphs.dat
            // the first line being number of testCases
            FileReader file = new FileReader("graphs.dat");
            BufferedReader br = new BufferedReader(file);
            int testCases = Integer.parseInt(br.readLine());
            ArrayList <String> arr = new ArrayList <String>();

            // Reading twice the number of lines as testCases
            // first line being the path to be constructed
            // second line mentions if the path exists
            for (int i = 0; i < testCases * 2; i++) {
                arr.add(br.readLine());
            }
            
            // For each of the path and the given source destinations
            // we find all possible routes that exists
            for (int i = 0; i < arr.size(); i = i + 2) {
                String graph = arr.get(i); 
                String srcDest = arr.get(i + 1);
                if (srcDest != null && graph != null)
                    findAllPaths(graph, srcDest);
            }
        } catch (Exception e) {
            System.out.println("Invalid input." + e.toString());
        }

    }

    // @param: takes the route and source/detination as input
    // Splits the graph into an array of strings separated by a 
    // space and then converts the characters into a hashmap
    //@return: does not return anything as it is a void method
    private static void findAllPaths(String graph, String srcDest){
        String[] edges = graph.split(" ");
        
        // We convert the graph string into corresponding numerical equivalent
        // So a path like AB BC CD will be converted to 01 12 23
        // Do this as the BFS algorithm uses numericals as path 
        // rather than alphabets
        HashMap <String,Integer> numVertices = new HashMap <String, Integer>();

        // Through this for loop, each node of an edge is being assigned 
        // a unique numerical value
        // add the node to the numVertices only if it doesn't exist
        int counter = 0;
        String fchar, schar = "";
        for (int i = 0; i < edges.length; ++i) {
            fchar = edges[i].substring(0, 1);
            schar = edges[i].substring(1, 2);
            if (!numVertices.containsKey(fchar))
                numVertices.put(fchar, counter++);
            if (!numVertices.containsKey(schar))
                numVertices.put(schar, counter++);

        }

        // No of vertices
        int v = numVertices.size();

        // Initializing an ArrayList of Arraylists to perform 
        // the BFS search algorithm
        ArrayList <ArrayList <Integer>> adj =  new ArrayList <ArrayList <Integer>>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList <Integer>());
        }
        
        // Add each edge in the path to the array adj so we can go through for finding the 
        // shortest path, getting the numerical equivalent of the nodes of the edges 
        for (int i = 0; i < edges.length; i++) { 
            addEdge(adj, numVertices.get(edges[i].substring(0, 1)), numVertices.get(edges[i].substring(1, 2)));
        }

        // Get the numerical equal of the source/dest vertices
        // If the path to find is AC it will be converted to 
        // something like 02 assuming A = 0, B = 1, C = 2
        // We do this as the BFS algorithm we use is based on numbers and not alphabets
        String srcStr = srcDest.substring(0, 1);
        String destStr = srcDest.substring(1, 2);

        int source = numVertices.get(srcStr);
        int dest = numVertices.get(destStr);

        System.out.print("Shortest Path between " + srcStr + " and " + destStr + " is ");
        printShortestDistance(adj, source, dest, v);
    }

    //@param: We add all the edges in the path to the nested array list
    // So in a path like AB BC CD which is represented as numerical values
    // of 01, 12, 23
    // we will have 4 arraylists nested in an arraylist
    // in each of those 4 arraylists one for each vertex so we should have 
    // 0 ==> 1, 1 ==> 2, 0 2 ==> 1, 3 3 ==> 2
    //@return: void method 
    private static void addEdge(ArrayList <ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    //@param: Here we use the predecessor array (pre[]) that we populated
    // using the BFS algorithm to trace back from the destination to the source
    //@return: void method 
    private static void printShortestDistance(
        ArrayList <ArrayList <Integer>> adj,
        int source, int dest, int v) {
        // predecessor[i] array stores predecessor of
        // i (marks the path of traversal)
        // and distance array stores distance of i
        // from s
        int pred[] = new int[v];
        int dist[] = new int[v];

        // calls the BFS method which determines the shortest path from 
        // two vertices
        // if the BFS method returns as false which means there is no connection
        if (bfSearch(adj, source, dest, v, pred, dist) == false) {
            System.out.println("no path as they are not connected.");
            return;
        }

        // Print distance
        System.out.println(dist[dest]);
    }

    // bfSearch stores predecessor of each vertex in array predecessor
    // and its distance from source in array distance
    private static boolean bfSearch(ArrayList <ArrayList <Integer>> adjct, int src,
        int dest, int v, int predecessor[], int distance[]) {
            
        // A queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList <Integer> queue = new LinkedList <Integer>();

        // Boolean array visited[] which stores the
        // information a particular vertex is visited or not

        boolean visited[] = new boolean[v];

        // Initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
            predecessor[i] = -1;
        }

        // Now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        distance[src] = 0;
        queue.add(src);

        // bfSearch Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adjct.get(u).size(); i++) {
                if (visited[adjct.get(u).get(i)] == false) {
                    visited[adjct.get(u).get(i)] = true;
                    distance[adjct.get(u).get(i)] = distance[u] + 1;
                    predecessor[adjct.get(u).get(i)] = u;
                    queue.add(adjct.get(u).get(i));

                    // stopping condition (when we find
                    // our destination)
                    if(adjct.get(u).get(i) == dest){
                        return true;

                    }
                }
            }
        }
        return false;
    }
}
