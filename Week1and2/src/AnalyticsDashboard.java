import java.util.*;

public class AnalyticsDashboard {

    static Map<String, Integer> pageViews = new HashMap<>();
    static Map<String, Set<String>> uniqueUsers = new HashMap<>();
    static Map<String, Integer> sources = new HashMap<>();

    public static void processEvent(String url, String user, String source) {
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueUsers.putIfAbsent(url, new HashSet<>());
        uniqueUsers.get(url).add(user);

        sources.put(source, sources.getOrDefault(source, 0) + 1);
    }

    public static void showDashboard() {
        System.out.println("Top Pages:");
        pageViews.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(3)
                .forEach(e -> {
                    String url = e.getKey();
                    System.out.println(url + " - " + e.getValue() +
                            " views (" + uniqueUsers.get(url).size() + " unique)");
                });

        System.out.println("\nTraffic Sources:");
        sources.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public static void main(String[] args) {
        processEvent("/news", "u1", "google");
        processEvent("/news", "u2", "facebook");
        processEvent("/sports", "u1", "google");

        showDashboard();
    }
}