import java.util.*;

public class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    // Bubble Sort (ascending riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort (Ascending):");
        for (Client c : arr)
            System.out.print(c.name + ":" + c.riskScore + " ");
        System.out.println("\nSwaps: " + swaps + "\n");
    }

    // Insertion Sort (DESC riskScore + accountBalance)
    public static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort (Descending):");
        for (Client c : arr)
            System.out.print(c.name + ":" + c.riskScore + " ");
        System.out.println("\n");
    }

    // Top 10 highest risk clients
    public static void topClients(Client[] arr) {
        System.out.println("Top High Risk Clients:");
        int limit = Math.min(10, arr.length);

        for (int i = 0; i < limit; i++) {
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        Client[] arr1 = clients.clone();
        Client[] arr2 = clients.clone();

        bubbleSort(arr1);
        insertionSort(arr2);
        topClients(arr2);
    }
}