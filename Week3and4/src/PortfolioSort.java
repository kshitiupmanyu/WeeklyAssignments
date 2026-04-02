import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }
}

public class PortfolioSort {

    // ---------------- MERGE SORT (ASC, STABLE) ----------------
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) // stable
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ---------------- QUICK SORT (DESC return + ASC volatility) ----------------
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Median-of-3 pivot selection
    public static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low].returnRate > arr[mid].returnRate) swap(arr, low, mid);
        if (arr[low].returnRate > arr[high].returnRate) swap(arr, low, high);
        if (arr[mid].returnRate > arr[high].returnRate) swap(arr, mid, high);

        return mid;
    }

    public static int partition(Asset[] arr, int low, int high) {
        int pivotIndex = medianOfThree(arr, low, high);
        swap(arr, pivotIndex, high);

        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate &&
                            arr[j].volatility < pivot.volatility)) {

                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 7),
                new Asset("GOOG", 15, 4)
        };

        // Merge Sort (ASC)
        Asset[] arr1 = assets.clone();
        mergeSort(arr1, 0, arr1.length - 1);

        System.out.println("Merge Sort (Ascending):");
        for (Asset a : arr1)
            System.out.print(a.name + ":" + a.returnRate + "% ");
        System.out.println();

        // Quick Sort (DESC + volatility ASC)
        Asset[] arr2 = assets.clone();
        quickSort(arr2, 0, arr2.length - 1);

        System.out.println("Quick Sort (Descending):");
        for (Asset a : arr2)
            System.out.print(a.name + ":" + a.returnRate + "% ");
        System.out.println();
    }
}