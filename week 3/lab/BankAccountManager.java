import java.util.Scanner;

public class BankAccountManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount[] accounts = new BankAccount[2];
        for (int i = 0; i < accounts.length; i++) {
            System.out.print("Enter Account Holder Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Initial Deposit: ");
            double deposit = Double.parseDouble(sc.nextLine());
            accounts[i] = new BankAccount(name, deposit);
        }
        accounts[0].deposit(500);
        accounts[1].withdraw(200);
        for (BankAccount acc : accounts) acc.displayAccountInfo();
        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts());
        sc.close();
    }
}

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int accountCounter = 1;
    public BankAccount(String holderName, double initialDeposit) {
        this.accountHolderName = holderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }
    private static String generateAccountNumber() {
        return "ACC" + String.format("%03d", accountCounter++);
    }
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) balance -= amount;
    }
    public double checkBalance() {
        return balance;
    }
    public void displayAccountInfo() {
        System.out.printf("Account: %s, Holder: %s, Balance: %.2f\n", accountNumber, accountHolderName, balance);
    }
    public static int getTotalAccounts() {
        return totalAccounts;
    }
}
