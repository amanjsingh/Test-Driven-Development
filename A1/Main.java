import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static UserRepository userRepository = new UserRepositoryMock();
    private static TelecomRepository telecomRepository = new TelecomRepositoryMock();
    private static BankingService banking = new BankingServiceImpl();
    private static BankingServiceHelper helper = new BankingServiceHelper();
    private static BillingService billing = new BillingServiceTelecomImpl();
    private static Scanner scanner;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("test")) {
            MainTest test = new MainTest();
            test.runTests();
            return;
        }

        scanner = new Scanner(System.in);

        System.out.println("\nCurrently Registered Users in Bank\n");
        printAllUsers();

        while (true) {
            System.out.println("\nBanking Operations");
            System.out.println("1. Register for banking service                  2. View bank account");
            System.out.println("3. Deposit money                                 4. Withdraw money\n");

            System.out.println("Telecom Operations");
            System.out.println("5. Register for telecom service                  6. Check due amount");
            System.out.println("7. Pay bill                                      8. Exit\n");

            int userSelection = 9;

            try{
                System.out.println("Enter Operation\n");
                userSelection = scanner.nextInt();
            }
            catch (InputMismatchException e){
                userSelection = 9;
            }

            scanner.nextLine();

            switch (userSelection) {
                case 1:
                    addBankUser();
                    break;
                case 2:
                    viewBankAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    addBillingUser();
                    break;
                case 6:
                    checkPendingBill();
                    break;
                case 7:
                    payBill();
                    break;
                case 8:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid Selection - Please select an available option\n");
            }
        }
    }

    private static void payBill() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        User user;
        Bill bill;
        try {
            user = userRepository.fetchUser(username);
            bill = telecomRepository.fetchBill(username);
            billing.payBill(user, banking);
        } catch (UserDoesNotExistException | InsufficientBalanceException | InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkPendingBill() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        User user;
        Bill bill;
        try {
            user = userRepository.fetchUser(username);
            bill = telecomRepository.fetchBill(username);
            System.out.println(billing.dueAmount(user));
        } catch (UserDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addBillingUser() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        User user;
        try {
            user = userRepository.fetchUser(username);
            System.out.println("Select annual plan : 600 | 1200 | 1800");
            double currentPlan = 600;
            scanner.nextLine();
            System.out.println("Do you have a referral? (Y/N)");
            String referral = scanner.nextLine();
            boolean isReferred = referral.equalsIgnoreCase("Y") ? true : false;

            Bill bill = new Bill(currentPlan, isReferred);
            user.setBill(bill);
            billing.addUser(user, telecomRepository);

            System.out.println("Account Balance: " + user.getAccount().getBalance());
            System.out.println("Pending Bill: " + bill.getPendingAmt());
        } catch (UserDoesNotExistException | UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Invalid Input\n");
        }
    }

    private static void withdraw() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        User user;
        try {
            user = userRepository.fetchUser(username);
            System.out.println("Balance: " + user.getAccount().getBalance());
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            banking.withdraw(user, amount);
            System.out.println("Balance: " + user.getAccount().getBalance());

        } catch (UserDoesNotExistException | InsufficientBalanceException | InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
        catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Invalid Input\n");
        }
    }

    private static void deposit() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        User user;
        try {
            user = userRepository.fetchUser(username);
            System.out.println("Balance: " + user.getAccount().getBalance());
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            banking.deposit(user, amount);
            System.out.println("Balance: " + user.getAccount().getBalance());
        } catch (UserDoesNotExistException | InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
        catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Invalid Input\n");
        }
    }

    private static void viewBankAccount() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        User user;
        try {
            user = userRepository.fetchUser(username);
            System.out.println(user);
        } catch (UserDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addBankUser() {
        System.out.println("Enter User Details");
        String name = "";
        String username = "";
        String password = "";
        String acctNum = "";
        Double balance = 0.0;

        while (true) {
            System.out.print("Name(Cannot be empty): ");
            name = scanner.nextLine();
            try {
                helper.nameValidation(name);
                break;
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter name again\n");
            }
        }

        while (true) {
            System.out.print("Username(Minimum 5 characters): ");
            username = scanner.nextLine();
            try {
                helper.userNameValidation(username);
                break;
            } catch (InvalidUserNameException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter username again\n");
            }
        }

        while (true) {
            System.out.print("Password(Minimum length 5): ");
            password = scanner.nextLine();
            try {
                helper.passwordValidation(password);
                break;
            } catch (InvalidPasswordException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter password again\n");
            }
        }

        while (true) {
            System.out.print("Account Number(Length 9): ");
            acctNum = scanner.nextLine();
            try {
                helper.acctNumValidation(acctNum);
                break;
            } catch (InvalidAccoutNumberException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter account number again");
            }
        }

        while (true) {
            System.out.print("Initial deposit amount: ");
            balance = scanner.nextDouble();
            scanner.nextLine();
            try {
                helper.acctBalanceValidation(balance);
                break;
            } catch (InvalidAccountBalanceException e) {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Invalid Input\n");
            }
        }

        User user = new User(name, username, password, new Account(acctNum, balance));
        try {
            banking.setupNewUser(user, userRepository);
        } catch (InvalidNameException | InvalidPasswordException | InvalidUserNameException |
                 UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to add user\n");
        }
        catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Invalid Input\n");
        }
    }

    private static void printAllUsers() {
        Map<String, User> userMap = userRepository.fetchAllUsers();
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println();
    }
}
