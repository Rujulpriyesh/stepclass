import java.util.Scanner;
import java.util.Arrays;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        for (char ch : input.toCharArray()) {
            System.out.print("Char: " + ch + ", ASCII: " + (int)ch);
            String type = classifyCharacter(ch);
            System.out.print(", Type: " + type);
            if (Character.isLetter(ch)) {
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);
                System.out.print(", Upper: " + upper + " (" + (int)upper + ")");
                System.out.print(", Lower: " + lower + " (" + (int)lower + ")");
                System.out.print(", Diff: " + Math.abs((int)upper - (int)lower));
            }
            System.out.println();
        }
        System.out.println("ASCII Art:");
        for (char ch : input.toCharArray()) {
            System.out.print((char)((int)ch + 1));
        }
        System.out.println();
        System.out.print("Enter shift for Caesar cipher: ");
        int shift = scanner.nextInt();
        scanner.nextLine();
        String ciphered = caesarCipher(input, shift);
        System.out.println("Caesar Cipher: " + ciphered);
        System.out.println("ASCII Table (32-127):");
        displayASCIITable(32, 127);
        int[] asciiArr = stringToASCII(input);
        System.out.println("ASCII Array: " + Arrays.toString(asciiArr));
        String fromAscii = asciiToString(asciiArr);
        System.out.println("String from ASCII: " + fromAscii);
        scanner.close();
    }

    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        if (Character.isLowerCase(ch)) return "Lowercase Letter";
        if (Character.isDigit(ch)) return "Digit";
        return "Special Character";
    }

    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) return (char)(ch + 32);
        if (Character.isLowerCase(ch)) return (char)(ch - 32);
        return ch;
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                sb.append((char)((ch - base + shift) % 26 + base));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + ": " + (char)i);
        }
    }

    public static int[] stringToASCII(String text) {
        int[] arr = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            arr[i] = (int)text.charAt(i);
        }
        return arr;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char)val);
        }
        return sb.toString();
    }
}
