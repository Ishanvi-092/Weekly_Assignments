import java.util.*;

public class TwoSum {

    static class Txn {
        int id, amount;

        Txn(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }
    }

    public static void findTwoSum(List<Txn> list, int target) {
        Map<Integer, Txn> map = new HashMap<>();

        for (Txn t : list) {
            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                System.out.println("Pair: " + map.get(complement).id + ", " + t.id);
            }

            map.put(t.amount, t);
        }
    }

    public static void main(String[] args) {
        List<Txn> list = Arrays.asList(
                new Txn(1, 500),
                new Txn(2, 300),
                new Txn(3, 200)
        );

        findTwoSum(list, 500);
    }
}
