//Write a java program that receives a list of edges of a simple graph, the program should determine whether it is connected and find the number of connected components if it is not connected
import java.util.*;

public class Problem1 {
    // traverse all the edges base from the neighbor or adjacent list and if the edges is already visited, stop the recursion.
    private static void searchPath(String node, Map<String, List<String>> adjList, Set<String> visited) {
        visited.add(node);
        for (String neighbor : adjList.get(node)) {
            if (!visited.contains(neighbor)) {
                searchPath(neighbor, adjList, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, List<String>> adjList = new HashMap<>();
        Set<String> vertices = new HashSet<>();

        System.out.println("-CONNECTED GRAPH CHECKER-\nby Laurence Kharl Devera");

        System.out.println("Enter the edges (e.g. \"ab\"). (Type 'end' to finish):");
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }

            if (input.length() != 2) {
                System.out.println("Invalid edge format. Please use \"ab\" format.");
                continue;
            }

            String u = input.substring(0,1);
            String v = input.substring(1);

            vertices.add(u);
            vertices.add(v);

            adjList.putIfAbsent(u, new ArrayList<>());
            adjList.putIfAbsent(v, new ArrayList<>());

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        Set<String> visited = new HashSet<>();
        int componentCount = 0;
        
        // check each vertex of it traverse all the edges at once without disconnecting.
        for (String vertex : vertices) {
            if (!visited.contains(vertex)) {
                searchPath(vertex, adjList, visited);
                componentCount++;
            }
        }

        if (componentCount == 1) {
            System.out.println("The graph is connected.");
        } else {
            System.out.println("The graph is not connected.");
            System.out.println("Number of connected components: " + componentCount);
        }

        sc.close();
    }
}
