//Write a java program that will determine if a graph has a cycle or not

import java.util.*;

public class Problem3 {

    /*Check if the directed graph has a cycle through recursion. Traverse the edges starting from the first node, if we go back to the node, return true.*/
    private static boolean hasCycle(String node, Map<String, List<String>> adjList, Set<String> visited, Set<String> recStack) {
        if (recStack.contains(node)) 
            return true;

        visited.add(node);
        recStack.add(node);

        for (String neighbor : adjList.get(node)) 
            if (hasCycle(neighbor, adjList, visited, recStack)) 
                return true;

        recStack.remove(node);
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-GRAPH CYCLE CHECKER-\nby Laurence Kharl Devera");

        System.out.print("Is the graph directed? (yes/no): ");
        String graphType = sc.next();
        boolean isDirected = graphType.equalsIgnoreCase("yes");

        Map<String, List<String>> adjList = new HashMap<>();

        System.out.println("Enter the edges (e.g. \"ab\") (type 'end' to finish):");

        sc.nextLine();

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

            adjList.putIfAbsent(u, new ArrayList<>());
            adjList.putIfAbsent(v, new ArrayList<>());

            adjList.get(u).add(v);
            if (!isDirected) {
                adjList.get(v).add(u);
            }
        }

        // check all the vertex and the neighbor itself to traverse all the edges (call the hasCycle function)
        boolean hasCycle = false;
        Set<String> visited = new HashSet<>();
        Set<String> recStack = new HashSet<>();
        for (String node : adjList.keySet()) {
            if (!visited.contains(node) && hasCycle(node, adjList, visited, recStack)) {
                hasCycle = true;
                break;
            }
        }

        if (hasCycle) {
            System.out.println("The graph has a cycle.");
        } else {
            System.out.println("The graph does not have a cycle.");
        }

        sc.close();
    }
}
