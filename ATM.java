1  import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private static HashMap<Integer, Integer> accounts = new HashMap<>();
    private static HashMap<Integer, Integer> accountsBalance = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM. Choose an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Login");

            int choice = input.nextInt();

            if (choice == 1) {
                createAccount();
            } else if (choice == 2) {
                int customerId = login();
                if (customerId != 0) {
                    transaction(customerId);
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a customer ID: ");
        int customerId = input.nextInt();

        System.out.println("Enter a PIN: ");
        int pin = input.nextInt();

        accounts.put(customerId, pin);

        System.out.println("Account created successfully.");
    }

    private static int login() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your customer ID: ");
        int customerId = input.nextInt();

        System.out.println("Enter your PIN: ");
        int pin = input.nextInt();

        if (accounts.containsKey(customerId) && accounts.get(customerId) == pin) {
            System.out.println("Login successful.");
            return customerId;
        } else {
            System.out.println("Invalid customer ID or PIN.");
            return 0;
        }
    }

    private static void transaction(int customerId) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Choose a transaction:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Fast Withdraw");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Logout");

            int choice = input.nextInt();

            if (choice == 1) {
                System.out.println("Enter amount to withdraw: ");
                int amount = input.nextInt();

                if (amount > 0) {
                    int balance = 0;
                    if (accountsBalance.containsKey(customerId)) {
                        balance = accountsBalance.get(customerId);
                    }
                    if (balance >= amount) {
                        accountsBalance.put(customerId, balance - amount);
                        System.out.println("Transaction successful. Current balance: " + accountsBalance.get(customerId));
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                } else {
                    System.out.println("Invalid amount.");
                }
            } else if (choice == 2) {
                System.out.println("Enter amount to deposit: ");
                int amount = input.nextInt();

                if (amount > 0) {
                    int balance = 0;
                    if (accountsBalance.containsKey(customerId)) {
                        balance = accountsBalance.get(customerId);
                    }
                    accountsBalance.put(customerId, balance + amount);
                    System.out.println("Transaction successful. Current balance: " + accountsBalance.get(customerId));
                } else {
                    System.out.println("Invalid amount.");
                }
            } else if (choice == 3) {
                int balance = 0;
                if (accountsBalance.containsKey(customerId)) {
                    balance = accountsBalance.get(customerId);
                }
                System.out.println("Your balance is: " + balance);
            } else if (choice == 4) {
                System.out.println("Enter amount to withdraw: ");
                int amount = input.nextInt();

                if (amount > 0) {
                    int balance = 0;
                    if (accountsBalance.containsKey(customerId)) {
                        balance = accountsBalance.get(customerId);
                    }
                    if (balance >= amount + 10) {
                        accountsBalance.put(customerId, balance - amount - 10);
                        System.out.println("Transaction successful. Current balance: " + accountsBalance.get(customerId));
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                } else {
                    System.out.println("Invalid amount.");
                }
            }
 else if (choice == 5) {
                    System.out.println("Enter recipient's customer ID: ");
                    int recipientId = input.nextInt();

                    if (accounts.containsKey(recipientId)) {
                        if (!accountsBalance.containsKey(recipientId)) {
                            accountsBalance.put(recipientId, 0);
                        }
                        System.out.println("Enter amount to transfer: ");
                        int amount = input.nextInt();

                        if (amount > 0) {
                            int balance = 0;
                            if (accountsBalance.containsKey(customerId)) {
                                balance = accountsBalance.get(customerId);
                            }
                            if (balance >= amount) {
                                accountsBalance.put(customerId, balance - amount);
                                accountsBalance.put(recipientId, accountsBalance.get(recipientId) + amount);
                                System.out.println("Transaction successful. Current balance: " + accountsBalance.get(customerId));
                            } else {
                                System.out.println("Insufficient funds.");
                            }
                        } else {
                            System.out.println("Invalid amount.");
                        }
                    } else {
                        System.out.println("Recipient's customer ID not found.");
                    }
                } else if (choice == 6) {
                    System.out.println("Logout successful.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }

    }


