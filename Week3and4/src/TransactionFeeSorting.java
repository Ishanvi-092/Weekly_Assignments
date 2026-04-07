import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // format HH:MM

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionFeeSorting {

    // Bubble Sort (by fee)
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            // Early termination if already sorted
            if (!swapped) break;
        }

        System.out.println("Bubble Sort Result: " + list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort (by fee, then timestamp)
    public static void insertionSort(List<Transaction> list) {
        int n = list.size();
        int shifts = 0;

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Compare by fee, then timestamp
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
                shifts++;
            }

            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort Result: " + list);
        System.out.println("Shifts: " + shifts);
    }

    // Comparator logic: fee first, then timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // Detect high-fee outliers (> 50)
    public static void detectOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        System.out.println("High-fee outliers: " + outliers);
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Clone lists to test both sorts independently
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        List<Transaction> insertionList = new ArrayList<>(transactions);

        // Apply Bubble Sort (small batch)
        bubbleSort(bubbleList);

        // Apply Insertion Sort (medium batch)
        insertionSort(insertionList);

        // Detect outliers
        detectOutliers(transactions);
    }
}
