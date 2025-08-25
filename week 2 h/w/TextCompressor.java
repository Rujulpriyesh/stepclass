import java.util.*;
public class TextCompressor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to compress: ");
        String text = scanner.nextLine();
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int unique = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int idx = -1;
            for (int j = 0; j < unique; j++) if (chars[j] == c) idx = j;
            if (idx == -1) { chars[unique] = c; freq[unique] = 1; unique++; }
            else freq[idx]++;
        }
        System.out.println("Char Frequency Table:");
        for (int i = 0; i < unique; i++) System.out.println(chars[i] + ": " + freq[i]);
        String[] codes = new String[unique];
        for (int i = 0; i < unique; i++) codes[i] = String.valueOf(i);
        System.out.println("Compression Mapping:");
        for (int i = 0; i < unique; i++) System.out.println(chars[i] + " -> " + codes[i]);
        StringBuilder compressed = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (int j = 0; j < unique; j++) if (chars[j] == c) compressed.append(codes[j]);
        }
        System.out.println("Compressed text: " + compressed);
        StringBuilder decompressed = new StringBuilder();
        for (int i = 0; i < compressed.length(); i++) {
            int idx = compressed.charAt(i) - '0';
            decompressed.append(chars[idx]);
        }
        System.out.println("Decompressed text: " + decompressed);
        double ratio = (double)compressed.length() / text.length();
        System.out.println("Compression efficiency: " + (100.0 * (1 - ratio)) + "%");
        scanner.close();
    }
}
