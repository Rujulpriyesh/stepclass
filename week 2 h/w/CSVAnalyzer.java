import java.util.*;
public class CSVAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> rows = new ArrayList<>();
        System.out.println("Enter CSV data (type 'end' to finish):");
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("end")) break;
            rows.add(parseCSV(line));
        }
        int cols = rows.get(0).length;
        System.out.printf("%-15s", "Row");
        for (int i = 0; i < cols; i++) System.out.printf("%-15s", "Col" + (i + 1));
        System.out.println();
        for (int r = 0; r < rows.size(); r++) {
            System.out.printf("%-15d", r + 1);
            for (int c = 0; c < cols; c++) System.out.printf("%-15s", rows.get(r)[c]);
            System.out.println();
        }
        scanner.close();
    }
    public static String[] parseCSV(String line) {
        List<String> fields = new ArrayList<>();
        int start = 0;
        boolean inQuotes = false;
        for (int i = 0; i <= line.length(); i++) {
            if (i == line.length() || (!inQuotes && line.charAt(i) == ',')) {
                fields.add(line.substring(start, i).replaceAll("^\s+|\s+$", ""));
                start = i + 1;
            } else if (line.charAt(i) == '"') {
                inQuotes = !inQuotes;
            }
        }
        return fields.toArray(new String[0]);
    }
}
