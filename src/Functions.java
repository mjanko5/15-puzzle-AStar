/**
 * Matt Jankowski
 * AI (CS 411) Hw 6
 * 15 puzzle A* - Functions Class
 * To God be the Glory
 */


import java.util.PriorityQueue;

public class Functions {

    public static void printArray(String name, int[] array) {
        System.out.print(name + " = [");
        for (int i = 0; i < 16; i++) {
            if (i == 15) { //if last character, print without space
                System.out.print(array[i]);
            } else {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println("]");
    }

    public static void printBoard(int[] array) {
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 3) { //if last character
                System.out.print("\t" + array[i] + "\n");
            } else {
                System.out.print("\t" + array[i]);
            }
        }
    }

    //Example: print Frontier list
    public static void printNodeList(String name, PriorityQueue<Node> priorityQueue) {
        System.out.print(name + " = [ ");
        for (Node node : priorityQueue) {
            System.out.print(node.getNodeID() + " ");
        }
        System.out.println("]");

        //also print getF():
//        System.out.print("--------" + " = [ ");
//        for (Node node : priorityQueue) {
//            System.out.print(node.getF() + " ");
//        }
//        System.out.println("]");
    }

}
