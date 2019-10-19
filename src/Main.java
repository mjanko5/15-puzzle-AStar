/**
 * Matt Jankowski
 * AI (CS 411) Hw 6
 * 15 puzzle A* - Main Class
 * To God be the Glory
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Starting 15 puzzle.");

        int initialBoard[] = new int[]{
                1, 2, 0, 4, 6, 7, 3, 8, 5, 9, 10, 12, 13, 14, 11, 15
        };

        //    ^^^ REPLACE INPUT HERE

        System.out.println("1 = Misplaced Tiles\n2 = Manhattan Distance");
        System.out.println("Enter heuristic you want to use:");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        if (input == 1)
            new AStar(initialBoard, 1);  //run AStar:  Misplaced tiles
        else if (input == 2)
            new AStar(initialBoard, 2);  //run AStar:  Manhattan distance
        else
            System.out.println("Invalid Input. Please run again.");
    }
}