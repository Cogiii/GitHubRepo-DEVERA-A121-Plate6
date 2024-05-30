//Write a java program that accepts an adjacency matrix of a graph. The program should list the edges of this graph and give the number of times each edge appears
import java.util.*;

public class Problem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-COUNT NUMBER OF EDGES APPEARS-\nby Laurence Kharl Devera");

        System.out.print("Enter the number of vertices: ");
        int vertices = sc.nextInt();

        // get the vertex name starting from 'a'
        char[] vertexNames = new char[vertices];
        for (int i = 0; i < vertices; i++) {
            vertexNames[i] = (char) ('a' + i);
        }

        boolean done = false;
        int[][] adjMatrix = new int[vertices][vertices];
        while (!done) {
            System.out.println("Enter the adjacency matrix:");

            sc.nextLine();
            // get user input by row 
            for (int i = 0; i < vertices; i++) {
                String[] row = sc.nextLine().split(" ");

                if (row.length != vertices){
                    System.out.println("Invalid Input, Please enter the matrix again with "+vertices+" columns.");
                    break;
                }
                for (int j = 0; j < vertices; j++) 
                    adjMatrix[i][j] = Integer.parseInt(row[j]);
                
                if(i == vertices-1)
                    done = true;
            }
        }
        
        Map<String, Integer> edgeCount = new HashMap<>();

        // check if matrix_ij is greater than 0 then proceed to get the list of an edge and add 1 (for the number of times appears).
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (adjMatrix[i][j] > 0) {
                    char u = vertexNames[i];
                    char v = vertexNames[j];
                    String edge = (u < v) ? u + "" + v : v + "" + u;
                    if (!edgeCount.containsKey(edge))
                        edgeCount.put(edge, edgeCount.getOrDefault(edge, 0) + adjMatrix[i][j]);
                }
            }
        }

        System.out.println("Edges List and Number Times Appears:");
        edgeCount.forEach((edge, times) -> {
            System.out.println("Edge " + edge + ": " + times + " times");
        });
        sc.close();
    }
}
