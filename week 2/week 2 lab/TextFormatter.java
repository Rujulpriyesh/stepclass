import java.util.*;
public class TextFormatter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to format: ");
        String text = scanner.nextLine();
        System.out.print("Enter desired line width: ");
        int width = scanner.nextInt();
        scanner.nextLine();
        String[] words = splitWords(text);
        List<String> justified = justifyText(words, width);
        List<String> centered = centerAlignText(justified, width);
        PerformanceResult perf = comparePerformance(words, width);
        System.out.println("Original text:");
        System.out.println(text);
        System.out.println("\nLeft-justified:");
        displayFormatted(justified);
        System.out.println("\nCenter-aligned:");
        displayFormatted(centered);
        System.out.println("\nPerformance comparison:");
        System.out.printf("%-20s %-20s\n", "Method", "Time (ns)");
        System.out.printf("%-20s %-20d\n", "StringBuilder", perf.builderTime);
        System.out.printf("%-20s %-20d\n", "StringConcat", perf.concatTime);
        scanner.close();
    }

    public static String[] splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || text.charAt(i) == ' ') {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        return words.toArray(new String[0]);
    }

    public static List<String> justifyText(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            int gaps = j - i - 1;
            StringBuilder sb = new StringBuilder();
            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(' ');
                }
                for (int k = sb.length(); k < width; k++) sb.append(' ');
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int spacePerGap = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap; s++) sb.append(' ');
                        if (extra > 0) { sb.append(' '); extra--; }
                    }
                }
            }
            lines.add(sb.toString());
            i = j;
        }
        return lines;
    }

    public static List<String> centerAlignText(List<String> lines, int width) {
        List<String> centered = new ArrayList<>();
        for (String line : lines) {
            int pad = (width - line.trim().length()) / 2;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pad; i++) sb.append(' ');
            sb.append(line.trim());
            while (sb.length() < width) sb.append(' ');
            centered.add(sb.toString());
        }
        return centered;
    }

    public static PerformanceResult comparePerformance(String[] words, int width) {
        long start = System.nanoTime();
        justifyText(words, width);
        long builderTime = System.nanoTime() - start;
        start = System.nanoTime();
        justifyTextConcat(words, width);
        long concatTime = System.nanoTime() - start;
        return new PerformanceResult(builderTime, concatTime);
    }

    public static List<String> justifyTextConcat(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            int gaps = j - i - 1;
            String line = "";
            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line += words[k];
                    if (k < j - 1) line += ' ';
                }
                while (line.length() < width) line += ' ';
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int spacePerGap = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    line += words[k];
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap; s++) line += ' ';
                        if (extra > 0) { line += ' '; extra--; }
                    }
                }
            }
            lines.add(line);
            i = j;
        }
        return lines;
    }

    public static void displayFormatted(List<String> lines) {
        int lineNum = 1;
        for (String line : lines) {
            System.out.printf("Line %2d (%2d chars): %s\n", lineNum++, line.length(), line);
        }
    }

    static class PerformanceResult {
        long builderTime, concatTime;
        PerformanceResult(long b, long c) { builderTime = b; concatTime = c; }
    }
}
