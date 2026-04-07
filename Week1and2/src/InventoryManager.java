import java.util.*;

public class InventoryManager {

    static Map<String, Integer> stock = new HashMap<>();
    static Map<String, Queue<Integer>> waitingList = new HashMap<>();

    public static synchronized String purchaseItem(String product, int userId) {
        int currentStock = stock.getOrDefault(product, 0);

        if (currentStock > 0) {
            stock.put(product, currentStock - 1);
            return "Success, remaining: " + (currentStock - 1);
        } else {
            waitingList.putIfAbsent(product, new LinkedList<>());
            waitingList.get(product).add(userId);
            return "Added to waiting list, position: " + waitingList.get(product).size();
        }
    }

    public static int checkStock(String product) {
        return stock.getOrDefault(product, 0);
    }

    public static void main(String[] args) {
        stock.put("IPHONE15", 2);

        System.out.println(purchaseItem("IPHONE15", 1));
        System.out.println(purchaseItem("IPHONE15", 2));
        System.out.println(purchaseItem("IPHONE15", 3));
    }
}
