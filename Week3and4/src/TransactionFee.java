import java.util.*;

public class TransactionFee {
    String id;
    double fee;
    String timestamp;

    TransactionFee(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    // Bubble Sort
    public static void bubbleSort(ArrayList<TransactionFee> arr) {
        int n = arr.size();
        int swaps = 0, passes = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j).fee > arr.get(j + 1).fee) {
                    TransactionFee temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort Result:");
        for (TransactionFee t : arr)
            System.out.print(t.id + ":" + t.fee + " ");

        System.out.println("\nPasses: " + passes + ", Swaps: " + swaps + "\n");
    }

    // Insertion Sort
    public static void insertionSort(ArrayList<TransactionFee> arr) {
        for (int i = 1; i < arr.size(); i++) {
            TransactionFee key = arr.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (arr.get(j).fee > key.fee ||
                            (arr.get(j).fee == key.fee &&
                                    arr.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                arr.set(j + 1, arr.get(j));
                j--;
            }

            arr.set(j + 1, key);
        }

        System.out.println("Insertion Sort Result:");
        for (TransactionFee t : arr)
            System.out.print(t.id + ":" + t.fee + "@" + t.timestamp + " ");

        System.out.println("\n");
    }

    // Outliers
    public static void findOutliers(ArrayList<TransactionFee> arr) {
        System.out.println("High-fee outliers:");
        boolean found = false;

        for (TransactionFee t : arr) {
            if (t.fee > 50) {
                System.out.print(t.id + ":" + t.fee + " ");
                found = true;
            }
        }

        if (!found) System.out.print("none");
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<TransactionFee> transactions = new ArrayList<>();

        transactions.add(new TransactionFee("id1", 10.5, "10:00"));
        transactions.add(new TransactionFee("id2", 25.0, "09:30"));
        transactions.add(new TransactionFee("id3", 5.0, "10:15"));

        ArrayList<TransactionFee> smallBatch = new ArrayList<>(transactions);
        ArrayList<TransactionFee> mediumBatch = new ArrayList<>(transactions);

        bubbleSort(smallBatch);
        insertionSort(mediumBatch);
        findOutliers(transactions);
    }
}