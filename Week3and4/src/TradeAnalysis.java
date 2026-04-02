import java.util.*;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }
}

public class TradeAnalysis {

    // ---------------- MERGE SORT (ASCENDING, STABLE) ----------------
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) { // stable
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ---------------- QUICK SORT (DESCENDING) ----------------
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume; // Lomuto
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // DESC
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // ---------------- MERGE TWO SORTED LISTS ----------------
    public static Trade[] mergeTwoLists(Trade[] a, Trade[] b) {
        int n = a.length, m = b.length;
        Trade[] result = new Trade[n + m];

        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (a[i].volume <= b[j].volume)
                result[k++] = a[i++];
            else
                result[k++] = b[j++];
        }

        while (i < n) result[k++] = a[i++];
        while (j < m) result[k++] = b[j++];

        return result;
    }

    // ---------------- TOTAL VOLUME ----------------
    public static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) sum += t.volume;
        return sum;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Merge Sort (ASC)
        Trade[] arr1 = trades.clone();
        mergeSort(arr1, 0, arr1.length - 1);

        System.out.println("Merge Sort (Ascending):");
        for (Trade t : arr1)
            System.out.print(t.id + ":" + t.volume + " ");
        System.out.println();

        // Quick Sort (DESC)
        Trade[] arr2 = trades.clone();
        quickSort(arr2, 0, arr2.length - 1);

        System.out.println("Quick Sort (Descending):");
        for (Trade t : arr2)
            System.out.print(t.id + ":" + t.volume + " ");
        System.out.println();

        // Merge two sorted lists (example same list twice)
        Trade[] merged = mergeTwoLists(arr1, arr1);

        int total = totalVolume(merged);
        System.out.println("Merged total volume: " + total);
    }
}