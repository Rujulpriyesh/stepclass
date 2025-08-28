import java.util.*;
public class FileOrganizer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> files = new ArrayList<>();
        System.out.println("Enter file names (type 'end' to finish):");
        while (true) {
            String file = scanner.nextLine();
            if (file.equalsIgnoreCase("end")) break;
            files.add(file);
        }
        Map<String, Integer> categoryCount = new HashMap<>();
        System.out.printf("%-25s %-15s %-25s\n", "Original Name", "Category", "Suggested Name");
        for (String file : files) {
            String ext = extractExtension(file);
            String category = categorize(ext);
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
            String newName = generateName(file, category);
            System.out.printf("%-25s %-15s %-25s\n", file, category, newName);
        }
        System.out.println("\nCategory counts:");
        for (String cat : categoryCount.keySet()) {
            System.out.println(cat + ": " + categoryCount.get(cat));
        }
        scanner.close();
    }
    public static String extractExtension(String file) {
        int dot = file.lastIndexOf('.');
        return dot != -1 ? file.substring(dot + 1).toLowerCase() : "";
    }
    public static String categorize(String ext) {
        if (ext.equals("txt") || ext.equals("doc")) return "Document";
        if (ext.equals("jpg") || ext.equals("png")) return "Image";
        if (ext.equals("mp3") || ext.equals("wav")) return "Audio";
        if (ext.equals("mp4") || ext.equals("avi")) return "Video";
        return "Unknown";
    }
    public static String generateName(String file, String category) {
        String base = file.replaceAll("[^a-zA-Z0-9]", "_");
        String date = String.valueOf(System.currentTimeMillis());
        return category + "_" + date + "_" + base;
    }
}
