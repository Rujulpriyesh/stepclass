import java.util.*;
public class CaseConvertor {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter text: ");
		String text = scanner.nextLine();
		String upper = toUpper(text);
		String lower = toLower(text);
		String title = toTitleCase(text);
		System.out.printf("%-15s %-30s\n", "Method", "Result");
		System.out.printf("%-15s %-30s\n", "Manual Upper", upper);
		System.out.printf("%-15s %-30s\n", "Manual Lower", lower);
		System.out.printf("%-15s %-30s\n", "Manual Title", title);
		System.out.printf("%-15s %-30s\n", "Built-in Upper", text.toUpperCase());
		System.out.printf("%-15s %-30s\n", "Built-in Lower", text.toLowerCase());
		System.out.printf("%-15s %-30s\n", "Built-in Title", builtInTitleCase(text));
		scanner.close();
	}

	public static String toUpper(String text) {
		StringBuilder sb = new StringBuilder();
		for (char c : text.toCharArray()) {
			if (c >= 'a' && c <= 'z') sb.append((char)(c - 32));
			else sb.append(c);
		}
		return sb.toString();
	}

	public static String toLower(String text) {
		StringBuilder sb = new StringBuilder();
		for (char c : text.toCharArray()) {
			if (c >= 'A' && c <= 'Z') sb.append((char)(c + 32));
			else sb.append(c);
		}
		return sb.toString();
	}

	public static String toTitleCase(String text) {
		StringBuilder sb = new StringBuilder();
		boolean newWord = true;
		for (char c : text.toCharArray()) {
			if (c == ' ') {
				sb.append(c);
				newWord = true;
			} else if (newWord && c >= 'a' && c <= 'z') {
				sb.append((char)(c - 32));
				newWord = false;
			} else if (!newWord && c >= 'A' && c <= 'Z') {
				sb.append((char)(c + 32));
			} else {
				sb.append(c);
				newWord = false;
			}
		}
		return sb.toString();
	}

	public static String builtInTitleCase(String text) {
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
}
