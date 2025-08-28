public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        System.out.println("String concatenation time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        endTime = System.nanoTime();
        System.out.println("StringBuilder concatenation time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        endTime = System.nanoTime();
        System.out.println("StringBuffer concatenation time: " + (endTime - startTime) + " ns");

        long t1 = System.nanoTime();
    String s = "";
    for (int i = 0; i < 1000; i++) s += i;
    System.out.println("s length: " + s.length());
        long t2 = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append(i);
        long t3 = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 1000; i++) sbf.append(i);
        long t4 = System.nanoTime();
        System.out.println("String: " + (t2-t1) + " ns");
        System.out.println("StringBuilder: " + (t3-t2) + " ns");
        System.out.println("StringBuffer: " + (t4-t3) + " ns");
    }

    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java " + i + " ";
        }
        return result;
    }

    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello World");
        sb.append("!");
        sb.insert(5, " Java");
        sb.delete(0, 6);
        sb.deleteCharAt(2);
        sb.reverse();
        sb.replace(0, 4, "Test");
        sb.setCharAt(0, 'X');
        int cap = sb.capacity();
        sb.ensureCapacity(50);
        sb.trimToSize();
        System.out.println("StringBuilder result: " + sb.toString());
        System.out.println("Capacity: " + cap);
    }

    public static void demonstrateThreadSafety() {
        StringBuffer sb = new StringBuffer();
        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                sb.append("A");
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("StringBuffer after threads: " + sb.length());
    }

    public static void compareStringComparisonMethods() {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1 == str3: " + (str1 == str3));
        System.out.println("str1.equals(str3): " + str1.equals(str3));
        System.out.println("str1.equalsIgnoreCase(str3): " + str1.equalsIgnoreCase(str3));
        System.out.println("str1.compareTo(str3): " + str1.compareTo(str3));
        System.out.println("str1.compareToIgnoreCase(str3): " + str1.compareToIgnoreCase(str3));
    }

    public static void demonstrateMemoryEfficiency() {
        Runtime runtime = Runtime.getRuntime();
        long before = runtime.totalMemory() - runtime.freeMemory();
        String s = "";
        for (int i = 0; i < 1000; i++) {
            s += "A";
        }
        System.out.println("String result: " + s.substring(0, Math.min(50, s.length())) + "...");
        long after = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("String memory usage: " + (after - before));
        before = runtime.totalMemory() - runtime.freeMemory();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("A");
        }
        after = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("StringBuilder memory usage: " + (after - before));
        System.out.println("String pool: " + ("Hello" == "Hello"));
        System.out.println("StringBuilder capacity: " + sb.capacity());
    }
}
