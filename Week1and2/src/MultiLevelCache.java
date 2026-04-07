import java.util.*;

public class MultiLevelCache {

    static LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(10, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > 3;
                }
            };

    static Map<String, String> L2 = new HashMap<>();

    public static String get(String key) {

        if (L1.containsKey(key)) {
            return "L1 HIT";
        }

        if (L2.containsKey(key)) {
            L1.put(key, L2.get(key));
            return "L2 HIT → promoted to L1";
        }

        // simulate DB
        String value = "DATA_" + key;
        L2.put(key, value);

        return "L3 HIT → added to L2";
    }

    public static void main(String[] args) {
        System.out.println(get("A"));
        System.out.println(get("A"));
    }
}
