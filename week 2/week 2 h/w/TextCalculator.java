import java.util.*;
public class TextCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expressions (type 'end' to finish):");
        while (true) {
            String expr = scanner.nextLine();
            if (expr.equalsIgnoreCase("end")) break;
            if (!validate(expr)) {
                System.out.println("Invalid expression: " + expr);
                continue;
            }
            int result = evaluate(expr);
            System.out.println("Expression: " + expr);
            System.out.println("Result: " + result);
        }
        scanner.close();
    }
    public static boolean validate(String expr) {
        int paren = 0;
        for (char c : expr.toCharArray()) {
            if (c == '(') paren++;
            if (c == ')') paren--;
            if (!(Character.isDigit(c) || "+-*/() ".indexOf(c) != -1)) return false;
        }
        return paren == 0;
    }
    public static int evaluate(String expr) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            if (Character.isDigit(expr.charAt(i))) {
                int j = i;
                while (j < expr.length() && Character.isDigit(expr.charAt(j))) j++;
                nums.add(Integer.parseInt(expr.substring(i, j)));
                i = j;
            } else if ("+-*/".indexOf(expr.charAt(i)) != -1) {
                ops.add(expr.charAt(i));
                i++;
            } else i++;
        }
        for (int k = 0; k < ops.size(); k++) {
            if (ops.get(k) == '*' || ops.get(k) == '/') {
                int a = nums.get(k), b = nums.get(k + 1);
                int res = ops.get(k) == '*' ? a * b : a / b;
                nums.set(k, res); nums.remove(k + 1); ops.remove(k); k--;
            }
        }
        int result = nums.get(0);
        for (int k = 0; k < ops.size(); k++) {
            if (ops.get(k) == '+') result += nums.get(k + 1);
            else result -= nums.get(k + 1);
        }
        return result;
    }
}
