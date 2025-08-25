import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence with mixed formatting: ");
        String input = scanner.nextLine();

        String trimmed = input.trim();
        String replacedSpaces = trimmed.replace(" ", "_");
        String noDigits = trimmed.replaceAll("\\d", "");
        String[] words = trimmed.split("\\s+");
        String joined = String.join(" | ", words);
        String noPunct = removePunctuation(trimmed);
        String capitalized = capitalizeWords(trimmed);
        String reversed = reverseWordOrder(trimmed);

        System.out.println("Trimmed: " + trimmed);
        System.out.println("Spaces replaced: " + replacedSpaces);
        System.out.println("Digits removed: " + noDigits);
        System.out.println("Words array: " + Arrays.toString(words));
        System.out.println("Joined: " + joined);
        System.out.println("No punctuation: " + noPunct);
        System.out.println("Capitalized: " + capitalized);
        System.out.println("Reversed: " + reversed);
        System.out.print("Word frequency: ");
        countWordFrequency(trimmed);
        scanner.close();
    }

    public static String removePunctuation(String text) {
        return text.replaceAll("[\\p{Punct}]", "");
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) sb.append(word.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }

    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            if (word.isEmpty()) continue;
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
        }
        System.out.println();
    }
}
