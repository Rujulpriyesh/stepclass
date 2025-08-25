import java.util.*;
public class SubstringReplacer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter main text: ");
        String text = scanner.nextLine();
        System.out.print("Enter substring to find: ");
        String find = scanner.nextLine();
        System.out.print("Enter replacement substring: ");
        String replace = scanner.nextLine();
        int[] positions = findAllOccurrences(text, find);
        String manual = manualReplace(text, find, replace, positions);
        String builtin = text.replace(find, replace);
        boolean isSame = manual.equals(builtin);
        System.out.println("Manual result: " + manual);
        System.out.println("Built-in result: " + builtin);
        System.out.println("Results match: " + isSame);
        scanner.close();
    }

    public static int[] findAllOccurrences(String text, String find) {
        List<Integer> pos = new ArrayList<>();
        int idx = 0;
        while ((idx = text.indexOf(find, idx)) != -1) {
            pos.add(idx);
            idx += find.length();
        }
        return pos.stream().mapToInt(i->i).toArray();
    }

    public static String manualReplace(String text, String find, String replace, int[] positions) {
        StringBuilder sb = new StringBuilder();
        int i = 0, posIdx = 0;
        while (i < text.length()) {
            if (posIdx < positions.length && i == positions[posIdx]) {
                sb.append(replace);
                i += find.length();
                posIdx++;
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}
