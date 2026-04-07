import java.util.*;

public class Autocomplete {

    static Map<String, Integer> freq = new HashMap<>();

    public static void add(String query) {
        freq.put(query, freq.getOrDefault(query, 0) + 1);
    }

    public static List<String> search(String prefix) {
        List<String> result = new ArrayList<>();

        for (String q : freq.keySet()) {
            if (q.startsWith(prefix)) {
                result.add(q);
            }
        }

        result.sort((a, b) -> freq.get(b) - freq.get(a));
        return result.subList(0, Math.min(3, result.size()));
    }

    public static void main(String[] args) {
        add("java");
        add("javascript");
        add("java tutorial");

        System.out.println(search("jav"));
    }
}
