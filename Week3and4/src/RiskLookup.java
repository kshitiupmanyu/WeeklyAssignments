import java.util.*;

public class RiskLookup {

    // ---------------- LINEAR SEARCH ----------------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Found at index: " + i);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Not found");

        System.out.println("Comparisons: " + comparisons + "\n");
    }

    // ---------------- BINARY FLOOR ----------------
    public static int floor(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int floor = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor;
    }

    // ---------------- BINARY CEILING ----------------
    public static int ceil(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int ceil = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            if (arr[mid] > target) {
                ceil = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceil;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int target = 30;

        System.out.println("Linear Search:");
        linearSearch(risks, target);

        int[] comp = {0};

        int floorVal = floor(risks, target, comp);
        int ceilVal = ceil(risks, target, comp);

        System.out.println("Binary Search:");
        System.out.println("Floor(" + target + "): " + floorVal);
        System.out.println("Ceiling(" + target + "): " + ceilVal);
        System.out.println("Comparisons: " + comp[0]);
    }
}