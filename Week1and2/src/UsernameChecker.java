import java.util.*;

public class UsernameChecker {

    static Map<String, Integer> users = new HashMap<>();
    static Map<String, Integer> attempts = new HashMap<>();

    public static boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public static List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            suggestions.add(username + i);
        }
        suggestions.add(username.replace("_", "."));
        return suggestions;
    }

    public static String getMostAttempted() {
        return Collections.max(attempts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) {
        users.put("john_doe", 1);

        System.out.println(checkAvailability("john_doe")); // false
        System.out.println(checkAvailability("jane_smith")); // true
        System.out.println(suggestAlternatives("john_doe"));
    }
}
