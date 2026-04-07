import java.util.*;

public class DNSCache {

    static class Entry {
        String ip;
        long expiry;

        Entry(String ip, long ttl) {
            this.ip = ip;
            this.expiry = System.currentTimeMillis() + ttl;
        }
    }

    static Map<String, Entry> cache = new HashMap<>();
    static int hits = 0, misses = 0;

    public static String resolve(String domain) {
        if (cache.containsKey(domain)) {
            Entry e = cache.get(domain);
            if (System.currentTimeMillis() < e.expiry) {
                hits++;
                return "HIT: " + e.ip;
            }
        }

        misses++;
        String newIP = "192.168.1." + new Random().nextInt(255);
        cache.put(domain, new Entry(newIP, 5000)); // 5 sec TTL
        return "MISS: " + newIP;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(resolve("google.com"));
        System.out.println(resolve("google.com"));
    }
}
