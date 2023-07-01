import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private double balance;
    private String customerName;
    private String customerPIN;
    private Map<String, Double> transactionHistory;
    private Scanner scanner;
    
    public ATM() {
        balance = 0.0;
        customerName = " Vignesh Lagishetti";
        customerPIN = "8149";
        transactionHistory = new HashMap<>();
        scanner = new Scanner(System.in);
    }
    
    private void displayWelcomeMessage() {
        System.out.println("Welcome, " + customerName + "!");
    }
    
    private boolean verifyPIN() {
        System.out.print("Enter your PIN: ");
        String enteredPIN = scanner.nextLine();
        return enteredPIN.equals(customerPIN);
    }
    
    private void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Transaction History");
        System.out.println("5. Exit");
    }
    
    private void deposit() {
        System.out.print("Enter the amount to deposit: Rupees ");
        double amount = scanner.nextDouble();
        
        if (amount <= 0) {
            System.out.println("Invalid amount. Deposit failed.");
        } else {
            balance += amount;
            transactionHistory.put("Deposit", amount);
            System.out.println("Deposit successful. New balance: Rupees " + balance);
        }
    }
    
    private void withdraw() {
        System.out.print("Enter the amount to withdraw: Rupees");
        double amount = scanner.nextDouble();
        
        if (amount <= 0 || amount > balance) {
            System.out.println("Invalid amount or insufficient funds. Withdrawal failed.");
        } else {
            balance -= amount;
            transactionHistory.put("Withdrawal", -amount);
            System.out.println("Withdrawal successful. New balance: Rupees  " + balance);
        }
    }
    
    private void checkBalance() {
        System.out.println("Current balance: Rupees " + balance);
    }
    
    private void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (Map.Entry<String, Double> entry : transactionHistory.entrySet()) {
            String transactionType = entry.getKey();
            double amount = entry.getValue();
            String sign = (amount >= 0) ? "+" : "-";
            System.out.println(transactionType + ": Rupees " + Math.abs(amount) + " (" + sign + ")");
        }
    }
    
    public void start() {
        System.out.println("Welcome to the ATM!");
        
        if (!verifyPIN()) {
            System.out.println("Invalid PIN. Access denied.");
            return;
        }
        
        displayWelcomeMessage();
        
        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    displayTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
