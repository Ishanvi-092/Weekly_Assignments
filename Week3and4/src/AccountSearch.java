import java.util.*;

public class AccountSearch {

    // -------- Linear Search (first & last occurrence) --------
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1, comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear first: " + first + ", last: " + last);
        System.out.println("Comparisons: " + comparisons);
    }

    // -------- Binary Search (count occurrences) --------
    public static void binarySearch(String[] arr, String target) {
        int comparisons = 0;

        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) {
            System.out.println("Binary: Not found");
            return;
        }

        int count = last - first + 1;
        System.out.println("Binary index: " + first + ", count: " + count);
    }

    private static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] logs = {"accA", "accB", "accB", "accC"};

        linearSearch(logs, "accB");
        binarySearch(logs, "accB");
    }
}
