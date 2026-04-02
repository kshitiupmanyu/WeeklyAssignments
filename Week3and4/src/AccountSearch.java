import java.util.*;

public class AccountSearch {

    // ---------------- LINEAR SEARCH ----------------
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear Search:");
        System.out.println("First index: " + first + ", Last index: " + last);
        System.out.println("Comparisons: " + comparisons + "\n");
    }

    // ---------------- BINARY SEARCH (FIRST OCCURRENCE) ----------------
    public static int binaryFirst(String[] arr, String target, int[] comp) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ---------------- BINARY SEARCH (LAST OCCURRENCE) ----------------
    public static int binaryLast(String[] arr, String target, int[] comp) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // Linear Search
        linearSearch(logs, "accB");

        // Sort for Binary Search
        Arrays.sort(logs);

        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        int[] comp = {0};

        int first = binaryFirst(logs, "accB", comp);
        int last = binaryLast(logs, "accB", comp);

        int count = (first == -1) ? 0 : (last - first + 1);

        System.out.println("\nBinary Search:");
        System.out.println("First index: " + first);
        System.out.println("Last index: " + last);
        System.out.println("Count: " + count);
        System.out.println("Comparisons: " + comp[0]);

        // Time Complexity
        System.out.println("\nTime Complexity:");
        System.out.println("Linear Search: O(n)");
        System.out.println("Binary Search: O(log n)");
    }
}