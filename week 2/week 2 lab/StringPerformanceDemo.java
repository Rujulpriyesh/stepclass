import java.util.*;
public class StringPerformanceDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int iterations = scanner.nextInt();
        scanner.nextLine();
        PerformanceResult stringRes = testStringConcat(iterations);
        PerformanceResult builderRes = testStringBuilder(iterations);
        PerformanceResult bufferRes = testStringBuffer(iterations);
        System.out.printf("%-15s %-15s %-15s\n", "Method", "Time (ms)", "Length");
        System.out.printf("%-15s %-15d %-15d\n", "String", stringRes.timeMs, stringRes.length);
        System.out.printf("%-15s %-15d %-15d\n", "StringBuilder", builderRes.timeMs, builderRes.length);
        System.out.printf("%-15s %-15d %-15d\n", "StringBuffer", bufferRes.timeMs, bufferRes.length);
        scanner.close();
    }

    static class PerformanceResult {
        long timeMs;
        int length;
        PerformanceResult(long t, int l) { timeMs = t; length = l; }
    }

    public static PerformanceResult testStringConcat(int iterations) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) s += "A";
        long end = System.currentTimeMillis();
        return new PerformanceResult(end - start, s.length());
    }

    public static PerformanceResult testStringBuilder(int iterations) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) sb.append("A");
        long end = System.currentTimeMillis();
        return new PerformanceResult(end - start, sb.length());
    }

    public static PerformanceResult testStringBuffer(int iterations) {
        long start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) sbf.append("A");
        long end = System.currentTimeMillis();
        return new PerformanceResult(end - start, sbf.length());
    }
}
