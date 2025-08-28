import java.util.*;
public class SpellChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter dictionary words (comma separated): ");
        String[] dict = scanner.nextLine().split(",");
        for (int i = 0; i < dict.length; i++) dict[i] = dict[i].trim();
        System.out.print("Enter sentence to check: ");
        String sentence = scanner.nextLine();
        String[] words = splitWords(sentence);
        System.out.printf("%-15s %-20s %-10s %-10s\n", "Word", "Suggestion", "Distance", "Status");
        for (String word : words) {
            String suggestion = word;
            int minDist = Integer.MAX_VALUE;
            for (String d : dict) {
                int dist = stringDistance(word, d);
                if (dist < minDist) { minDist = dist; suggestion = d; }
            }
            boolean correct = minDist == 0;
            boolean suggest = minDist <= 2 && !correct;
            System.out.printf("%-15s %-20s %-10d %-10s\n", word, suggest ? suggestion : "-", minDist, correct ? "Correct" : "Misspelled");
        }
        scanner.close();
    }
    public static String[] splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || !Character.isLetterOrDigit(text.charAt(i))) {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        return words.toArray(new String[0]);
    }
    public static int stringDistance(String a, String b) {
        int dist = Math.abs(a.length() - b.length());
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) != b.charAt(i)) dist++;
        }
        return dist;
    }
}
