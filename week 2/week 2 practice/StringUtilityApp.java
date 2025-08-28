import java.util.*;
public class StringUtilityApp {
    private static StringBuilder output = new StringBuilder();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== STRING UTILITY APPLICATION ===");
        while (true) {
            System.out.println("1. Text Analysis\n2. String Transformation\n3. ASCII Operations\n4. Performance Testing\n5. String Comparison Analysis\n6. Custom String Algorithms\n0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) break;
            if (choice == 1) {
                System.out.print("Enter text: ");
                String text = scanner.nextLine();
                performTextAnalysis(text);
            } else if (choice == 2) {
                System.out.print("Enter text: ");
                String text = scanner.nextLine();
                System.out.print("Enter operations (comma separated): ");
                String[] ops = scanner.nextLine().split(",");
                output.append(performTransformations(text, ops)).append("\n");
            } else if (choice == 3) {
                System.out.print("Enter text: ");
                String text = scanner.nextLine();
                performASCIIOperations(text);
            } else if (choice == 4) {
                System.out.print("Enter iterations: ");
                int it = scanner.nextInt();
                scanner.nextLine();
                performPerformanceTest(it);
            } else if (choice == 5) {
                System.out.print("Enter strings (comma separated): ");
                String[] strs = scanner.nextLine().split(",");
                performComparisonAnalysis(strs);
            } else if (choice == 6) {
                System.out.print("Enter text: ");
                String text = scanner.nextLine();
                performCustomAlgorithms(text);
            }
            displayResults();
            output.setLength(0);
        }
        scanner.close();
    }
    public static void performTextAnalysis(String text) {
        output.append("Length: ").append(text.length()).append("\n");
        String[] words = text.trim().split("\\s+");
        output.append("Word count: ").append(words.length).append("\n");
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) freq.put(c, freq.getOrDefault(c,0)+1);
        output.append("Char frequency: ").append(freq).append("\n");
    }
    public static String performTransformations(String text, String[] operations) {
        StringBuilder sb = new StringBuilder(text);
        for (String op : operations) {
            op = op.trim().toLowerCase();
            if (op.equals("trim")) sb = new StringBuilder(sb.toString().trim());
            else if (op.equals("upper")) sb = new StringBuilder(sb.toString().toUpperCase());
            else if (op.equals("lower")) sb = new StringBuilder(sb.toString().toLowerCase());
            else if (op.startsWith("replace:")) {
                String[] parts = op.split(":");
                if (parts.length == 3) sb = new StringBuilder(sb.toString().replace(parts[1], parts[2]));
            }
        }
        return sb.toString();
    }
    public static void performASCIIOperations(String text) {
        for (char c : text.toCharArray()) {
            output.append(c).append(":").append((int)c).append(" ");
        }
        output.append("\n");
        StringBuilder cipher = new StringBuilder();
        for (char c : text.toCharArray()) {
            cipher.append((char)(c+1));
        }
        output.append("Caesar+1: ").append(cipher).append("\n");
    }
    public static void performPerformanceTest(int iterations) {
        long t1 = System.nanoTime();
        String s = "";
        for (int i = 0; i < iterations; i++) s += i;
        long t2 = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) sb.append(i);
        long t3 = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) sbf.append(i);
        long t4 = System.nanoTime();
        output.append("String: ").append(t2-t1).append(" ns\n");
        output.append("StringBuilder: ").append(t3-t2).append(" ns\n");
        output.append("StringBuffer: ").append(t4-t3).append(" ns\n");
        output.append("s length: ").append(s.length()).append("\n");
    }
    public static void performComparisonAnalysis(String[] strings) {
        if (strings.length < 2) return;
        String str1 = strings[0];
        String str2 = strings[1];
        output.append("==: ").append(str1 == str2).append("\n");
        output.append("equals: ").append(str1.equals(str2)).append("\n");
        output.append("equalsIgnoreCase: ").append(str1.equalsIgnoreCase(str2)).append("\n");
        output.append("compareTo: ").append(str1.compareTo(str2)).append("\n");
        output.append("compareToIgnoreCase: ").append(str1.compareToIgnoreCase(str2)).append("\n");
        output.append("Similarity: ").append(calculateSimilarity(str1, str2)).append("%\n");
    }
    public static void performCustomAlgorithms(String text) {
        String rev = new StringBuilder(text).reverse().toString();
        output.append("Palindrome: ").append(text.equals(rev)).append("\n");
        char[] arr1 = text.replaceAll("\\s","").toCharArray();
        Arrays.sort(arr1);
        output.append("Sorted chars: ").append(new String(arr1)).append("\n");
    }
    public static void displayResults() {
        System.out.print(output);
    }
    public static double calculateSimilarity(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int j = 0; j <= len2; j++) dp[0][j] = j;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
            }
        }
        int maxLen = Math.max(len1, len2);
        if (maxLen == 0) return 100.0;
        return (1.0 - (double)dp[len1][len2] / maxLen) * 100.0;
    }
}
