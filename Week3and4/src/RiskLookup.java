public class RiskLookup {

    // -------- Linear Search --------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int val : arr) {
            comparisons++;
            if (val == target) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Linear: threshold=" + target + " → found (" + comparisons + " comps)");
        } else {
            System.out.println("Linear: threshold=" + target + " → not found (" + comparisons + " comps)");
        }
    }

    // -------- Binary Search (Floor & Ceiling) --------
    public static void binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1, ceil = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                floor = ceil = arr[mid];
                break;
            } else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                ceil = arr[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary floor(" + target + "): " + floor +
                ", ceiling: " + ceil + " (" + comparisons + " comps)");
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};

        linearSearch(risks, 30);
        binarySearch(risks, 30);
    }
}
