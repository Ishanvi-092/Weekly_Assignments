import java.util.*;

public class PlagiarismDetector {

    public static Set<String> getNGrams(String text, int n) {
        String[] words = text.split(" ");
        Set<String> grams = new HashSet<>();

        for (int i = 0; i <= words.length - n; i++) {
            String gram = "";
            for (int j = 0; j < n; j++) {
                gram += words[i + j] + " ";
            }
            grams.add(gram.trim());
        }
        return grams;
    }

    public static double similarity(String doc1, String doc2) {
        Set<String> g1 = getNGrams(doc1, 3);
        Set<String> g2 = getNGrams(doc2, 3);

        int match = 0;
        for (String s : g1) {
            if (g2.contains(s)) match++;
        }

        return (match * 100.0) / g1.size();
    }

    public static void main(String[] args) {
        String d1 = "this is a simple plagiarism test";
        String d2 = "this is a plagiarism test example";

        System.out.println("Similarity: " + similarity(d1, d2) + "%");
    }
}
