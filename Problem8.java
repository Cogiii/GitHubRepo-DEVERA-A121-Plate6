// Write a Java program that checks whether two graphs are isomorphic or not, given a set of vertices.
import java.util.*;

public class Problem8 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-GRAPH ISOMORPHISM CHECKER-\nby Laurence Kharl Devera");

        System.out.print("Enter the number of vertices: ");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println("Enter edges for Graph 1 (e.g., \"ab\"). (Type 'end' to finish):");
        int[][] adjMatrix1 = new int[n][n];
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }
            if (input.length() != 2) {
                System.out.println("Invalid input. Please enter edges in the format \"ab\".");
                continue;
            }
            int u = input.charAt(0) - 'a';
            int v = input.charAt(1) - 'a';
            adjMatrix1[u][v]++;
            adjMatrix1[v][u]++;
        }

        System.out.println("Enter edges for Graph 2 (e.g., \"ab\"). (Type 'end' to finish):");
        int[][] adjMatrix2 = new int[n][n];
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }
            if (input.length() != 2) {
                System.out.println("Invalid input. Please enter edges in the format \"ab\".");
                continue;
            }
            int u = input.charAt(0) - 'a';
            int v = input.charAt(1) - 'a';
            adjMatrix2[u][v]++;
            adjMatrix2[v][u]++;
        }

        if (areIsomorphic(adjMatrix1, adjMatrix2, n)) {
            System.out.println("The graphs are isomorphic.");
        } else {
            System.out.println("The graphs are not isomorphic.");
        }

        sc.close();
    }

    private static boolean areIsomorphic(int[][] adjMatrix1, int[][] adjMatrix2, int n) {
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }

        do {
            if (checkPermutation(adjMatrix1, adjMatrix2, perm, n)) {
                return true;
            }
        } while (nextPermutation(perm));

        return false;
    }

    private static boolean checkPermutation(int[][] adjMatrix1, int[][] adjMatrix2, int[] perm, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix1[i][j] != adjMatrix2[perm[i]][perm[j]]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean nextPermutation(int[] perm) {
        int n = perm.length;
        int i = n - 2;
        while (i >= 0 && perm[i] >= perm[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = n - 1;
        while (perm[j] <= perm[i]) {
            j--;
        }
        swap(perm, i, j);
        reverse(perm, i + 1, n - 1);
        return true;
    }

    private static void swap(int[] perm, int i, int j) {
        int temp = perm[i];
        perm[i] = perm[j];
        perm[j] = temp;
    }

    private static void reverse(int[] perm, int start, int end) {
        while (start < end) {
            swap(perm, start, end);
            start++;
            end--;
        }
    }
}
