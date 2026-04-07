import java.util.*;

public class RateLimiter {

    static class Bucket {
        int tokens;
        long lastRefill;

        Bucket(int tokens) {
            this.tokens = tokens;
            this.lastRefill = System.currentTimeMillis();
        }
    }

    static Map<String, Bucket> clients = new HashMap<>();
    static int MAX = 1000;

    public static String check(String clientId) {
        clients.putIfAbsent(clientId, new Bucket(MAX));
        Bucket b = clients.get(clientId);

        long now = System.currentTimeMillis();

        // refill every hour
        if (now - b.lastRefill >= 3600000) {
            b.tokens = MAX;
            b.lastRefill = now;
        }

        if (b.tokens > 0) {
            b.tokens--;
            return "Allowed, remaining: " + b.tokens;
        } else {
            return "Denied, limit reached";
        }
    }

    public static void main(String[] args) {
        System.out.println(check("abc"));
    }
}