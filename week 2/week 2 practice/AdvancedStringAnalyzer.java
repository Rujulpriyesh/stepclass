import java.util.Scanner;

public class AdvancedStringAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ADVANCED STRING ANALYZER ===");
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();
        performAllComparisons(str1, str2);
        System.out.println("Similarity: " + calculateSimilarity(str1, str2) + "%");
        analyzeMemoryUsage(str1, str2);
        String[] arr = {str1, str2};
        System.out.println("Optimized: " + optimizedStringProcessing(arr));
        demonstrateStringIntern();
        scanner.close();
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

    public static void performAllComparisons(String str1, String str2) {
        System.out.println("Reference equality (==): " + (str1 == str2));
        System.out.println("Content equality (equals): " + str1.equals(str2));
        System.out.println("Case-insensitive equality: " + str1.equalsIgnoreCase(str2));
        System.out.println("Lexicographic compareTo: " + str1.compareTo(str2));
        System.out.println("Case-insensitive compareTo: " + str1.compareToIgnoreCase(str2));
    }

    public static void analyzeMemoryUsage(String... strings) {
    Runtime rt = Runtime.getRuntime();
    long before = rt.totalMemory() - rt.freeMemory();
    String s = "";
    for (String str : strings) s += str;
    long after = rt.totalMemory() - rt.freeMemory();
    System.out.println("Approximate memory used: " + (after - before) + " bytes");
    System.out.println("Combined string length: " + s.length());
    }

    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String s : inputs) sb.append(s).append(",");
        return sb.toString();
    }

    public static void demonstrateStringIntern() {
        String a = new String("Java");
        String b = "Java";
        System.out.println("Before intern: " + (a == b));
        a = a.intern();
        System.out.println("After intern: " + (a == b));
    }
}
