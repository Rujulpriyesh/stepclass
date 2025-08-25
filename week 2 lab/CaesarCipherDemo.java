import java.util.*;
public class CaesarCipherDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to encrypt: ");
        String text = scanner.nextLine();
        System.out.print("Enter shift value: ");
        int shift = scanner.nextInt();
        scanner.nextLine();
        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);
        System.out.println("Original text: " + text);
        displayAsciiValues(text);
        System.out.println("Encrypted text: " + encrypted);
        displayAsciiValues(encrypted);
        System.out.println("Decrypted text: " + decrypted);
        displayAsciiValues(decrypted);
        System.out.println("Decryption valid: " + text.equals(decrypted));
        scanner.close();
    }

    public static String encrypt(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') sb.append((char)('A' + (c - 'A' + shift + 26) % 26));
            else if (c >= 'a' && c <= 'z') sb.append((char)('a' + (c - 'a' + shift + 26) % 26));
            else sb.append(c);
        }
        return sb.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift);
    }

    public static void displayAsciiValues(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c + "(" + (int)c + ") ");
        }
        System.out.println();
    }
}
