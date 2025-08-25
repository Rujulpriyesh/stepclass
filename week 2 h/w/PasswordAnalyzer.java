import java.util.*;
public class PasswordAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> passwords = new ArrayList<>();
        System.out.println("Enter passwords to analyze (type 'end' to finish):");
        while (true) {
            String pwd = scanner.nextLine();
            if (pwd.equalsIgnoreCase("end")) break;
            passwords.add(pwd);
        }
        System.out.printf("%-20s %-8s %-8s %-8s %-8s %-8s %-8s %-10s\n", "Password", "Len", "Upper", "Lower", "Digit", "Special", "Score", "Strength");
        for (String pwd : passwords) {
            int upper = 0, lower = 0, digit = 0, special = 0;
            for (char c : pwd.toCharArray()) {
                if (c >= 65 && c <= 90) upper++;
                else if (c >= 97 && c <= 122) lower++;
                else if (c >= 48 && c <= 57) digit++;
                else if (c >= 33 && c <= 126) special++;
            }
            int score = (pwd.length() > 8 ? (pwd.length() - 8) * 2 : 0);
            if (upper > 0) score += 10;
            if (lower > 0) score += 10;
            if (digit > 0) score += 10;
            if (special > 0) score += 10;
            String[] patterns = {"123", "abc", "qwerty"};
            for (String pat : patterns) if (pwd.toLowerCase().contains(pat)) score -= 10;
            String strength = score <= 20 ? "Weak" : score <= 50 ? "Medium" : "Strong";
            System.out.printf("%-20s %-8d %-8d %-8d %-8d %-8d %-8d %-10s\n", pwd, pwd.length(), upper, lower, digit, special, score, strength);
        }
        System.out.print("Generate strong password of length: ");
        int len = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Generated: " + generatePassword(len));
        scanner.close();
    }
    public static String generatePassword(int len) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));
        for (int i = 4; i < len; i++) {
            String all = upper + lower + digits + special;
            sb.append(all.charAt(rand.nextInt(all.length())));
        }
        char[] arr = sb.toString().toCharArray();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            char tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }
        return new String(arr);
    }
}
