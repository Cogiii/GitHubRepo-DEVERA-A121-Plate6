import java.util.*;

public class Problem8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-GRAPH ISOMORPHISM CHECKER-\nby Laurence Kharl Devera");

        System.out.print("Enter the number of vertices in the graphs: ");
        int n = sc.nextInt();
        sc.nextLine();

        int[][] adjMatrix1 = new int[n][n];
        int[][] adjMatrix2 = new int[n][n];

        System.out.println("Enter edges for Graph 1 (e.g., \"ab\"). (Type 'end' to finish):");
        inputEdges(sc, adjMatrix1, n);

        System.out.println("Enter edges for Graph 2 (e.g., \"ab\"). (Type 'end' to finish):");
        inputEdges(sc, adjMatrix2, n);

        if (areIsomorphic(adjMatrix1, adjMatrix2, n)) {
            System.out.println("The graphs are isomorphic.");
        } else {
            System.out.println("The graphs are not isomorphic.");
        }
        sc.close();
    }

    private static void inputEdges(Scanner sc, int[][] adjMatrix, int n) {
        Map<String, Integer> vertexIndexMap = new HashMap<>();
        int index = 0;

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("end")) break;
            if (input.length() != 2) {
                System.out.println("Invalid input. Please enter edges in the format \"ab\".");
                continue;
            }
            String u = input.substring(0, 1), v = input.substring(1);

            if (!vertexIndexMap.containsKey(u)) {
                vertexIndexMap.put(u, index++);
            }
            if (!vertexIndexMap.containsKey(v)) {
                vertexIndexMap.put(v, index++);
            }

            int uIndex = vertexIndexMap.get(u);
            int vIndex = vertexIndexMap.get(v);
            adjMatrix[uIndex][vIndex]++;
            adjMatrix[vIndex][uIndex]++;
        }
    }

    // Check if the 2 graph is isomorphic or not through checking if both matrix are identical to each other through permutations.
    // If both initial matrix are not identical, call nextPermutation function.
    private static boolean areIsomorphic(int[][] adjMatrix1, int[][] adjMatrix2, int n) {
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) perm[i] = i;

        do {
            if (checkPermutation(adjMatrix1, adjMatrix2, perm, n)) return true;
        } while (nextPermutation(perm));

        return false;
    }
    // Check if both matrix are identical
    private static boolean checkPermutation(int[][] adjMatrix1, int[][] adjMatrix2, int[] perm, int n) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (adjMatrix1[i][j] != adjMatrix2[perm[i]][perm[j]])
                    return false;
        return true;
    }
    // Provide another arrangement of permutation until the array is in descending order (means all possible ways rearranging the perm order does not find any identical)
    private static boolean nextPermutation(int[] perm) {
        int n = perm.length, i = n - 2;
        while (i >= 0 && perm[i] >= perm[i + 1]) i--;
        if (i < 0) return false;
        int j = n - 1;
        while (perm[j] <= perm[i]) j--;
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
        while (start < end) swap(perm, start++, end--);
    }
}
