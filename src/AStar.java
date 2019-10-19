/**
 * Matt Jankowski
 * AI (CS 411) Hw 6
 * 15 puzzle A* - AStar Class
 * To God be the Glory
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AStar {
    private int goal[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    PriorityQueue<Node> frontier = new PriorityQueue<Node>();
    private long starting_time;
    public static int nodeCount; //total number of nodes created
    public static int heuristic_static; //static var keeps track of which heuristic to use

    //AStar Constructor:
    public AStar(int[] initial_board, int heuristic) {

        if (heuristic == 1) System.out.println("Starting A* Search with heuristic: MISPLACED TILES\n");
        else System.out.println("Starting A* Search with heuristic: MANHATTAN DISTANCE\n");

        starting_time = System.currentTimeMillis(); //get time at start of run
        nodeCount = 0; //reset
        heuristic_static = heuristic;

        Node root = new Node(initial_board, null, '#');  //create root (direction = '#')
        if (matchesGoal(root)) return; //if matches - done!

        enqueue(root); //put into frontier

        /** A* Search:
         *   frontier size will always be > 0 in our case since the search space is collosal.
         *   However, it will jump out after running for 30 sec.
         */
        while (frontier.size() > 0 && System.currentTimeMillis() - starting_time < 30000) {
            Node u = dequeueMin(); //extract min from frontier

            for (Node v : generateChildren(u)) {
                if (!inFrontier(v)) {  //white (prevents going back to the same node)
                    if (matchesGoal(v)) return; //if found, succeed, & end game
                    enqueue(v); //put into frontier
                }
            }
        }
        //30 sec passed. (There may be a solution but further down the tree)
        System.out.println("Solution not found.");
    }

    //functions:
    //put Node into frontier
    public void enqueue(Node node) {
        frontier.add(node);
        //Functions.printNodeList("frontier", frontier);
    }

    //remove Node from frontier
    public Node dequeueMin() {
        return frontier.poll(); //extract min
    }

    /**
     * TRANSITION FUNCTION (Same as in BFS).
     * generate all children of the parent.
     * blank is in corner -> 2 children    blank is on side -> 3 children   blank within -> 4 children.
     */
    public ArrayList<Node> generateChildren(Node parent) {
        Node U = parent.moveUp();
        Node D = parent.moveDown();
        Node L = parent.moveLeft();
        Node R = parent.moveRight();

        ArrayList<Node> children = new ArrayList<>(4);
        //Add the created nodes to the arrayList (as long as they're not null)
        if (U != null) children.add(U);
        if (D != null) children.add(D);
        if (L != null) children.add(L);
        if (R != null) children.add(R);

        return children;
    }

    //print success messages
    public void success(Node solutionNode) {
        printMoves(solutionNode);
        printNumNodes(solutionNode);
        printTime();
        printMemory();
    }

    //return true if node is in frontier list (by comparing int[])
    public boolean inFrontier(Node node) {
        for (Node n : frontier) {
            if (Arrays.equals(n.getBoard(), node.getBoard())) return true;
        }
        return false;
    }

    //check if the board matches goal, if so, success and return true!
    public boolean matchesGoal(Node v) {
        if (Arrays.equals(v.getBoard(), goal)) {
            success(v);
            return true;
        }
        return false;
    }

    //calculate and print move sequence
    public void printMoves(Node node) {
        String moves = "";
        while (node.getDirection() != '#') { //while not root node
            moves = Character.toString(node.getDirection()).concat(moves); //add direction of node
            node = node.getParent(); //update
        }
        System.out.println("Moves: " + moves);
    }

    //print how many nodes were expanded (created)
    public void printNumNodes(Node node) {
        System.out.println("Number of Nodes expanded: " + nodeCount);
    }

    //calculate and print the time taken to run program
    public void printTime() {
        long ending_time = System.currentTimeMillis(); //record time at end of run
        float total_seconds = (float) (ending_time - starting_time) / 1000; //get difference between start and end.
        System.out.println("Time Taken: " + total_seconds + " s"); //print result
    }

    //calculate and print runtime of program
    //referenced memory usage documentation at: https://www.vogella.com/tutorials/JavaPerformance/article.html
    public void printMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); //garbage collector frees up space
        long memory = runtime.totalMemory() - runtime.freeMemory(); //calculate memory
        System.out.println("Memory Used: " + memory / 1024L + " kB");
    }

}