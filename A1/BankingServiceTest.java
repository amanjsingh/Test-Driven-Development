public class BankingServiceTest {

    private UserRepository repository;
    private BankingService banking;

    public BankingServiceTest() {
        this.repository = new UserRepositoryMock();
        this.banking = new BankingServiceImpl();
    }

    public void setupAlreadyExistingUserTest() {
        User user = new User("Virat Kohli", "RunMachine", "Century71", new Account("543216789", 90000));
        try {
            banking.setupNewUser(user, repository);
            System.out.println("\nUser added with already existing username - Test FAILURE\n");
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("New user profile for existing user not created - Test PASSED!\n");
        } catch (InvalidNameException | InvalidPasswordException | InvalidUserNameException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(Add user with existing username) - Test FAILURE\n");
        }
    }

    public void setupNewUserWithEmptyNameTest() {

        User user = new User("", "emptyName", "Testing123", new Account("123654987", 3000));
        try {
            banking.setupNewUser(user, repository);
            System.out.println("User added with empty name - Test FAILURE\n");
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
            System.out.println("New User with empty name - Test PASSED!\n");
        } catch (InvalidPasswordException | InvalidUserNameException | UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(Empty Name Test) - Test FAILURE\n");
        }
    }

    public void setupNewUserWithInvalidNameTest() {

        User user = new User("AB", "emptyName", "Testing123", new Account("123654987", 3000));
        try {
            banking.setupNewUser(user, repository);
            System.out.println("User added with invalid name - Test FAILURE\n");
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
            System.out.println("New User with invalid name - Test PASSED!\n");
        } catch (InvalidPasswordException | InvalidUserNameException | UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(Invalid Name Test) - Test FAILURE\n");
        }
    }

    public void setupNewUserWithEmptyUsernameTest() {

        User user = new User("Test Name", "", "Testing123", new Account("123654987", 3000));
        try {
            banking.setupNewUser(user, repository);
            System.out.println("User added with empty username - Test FAILURE\n");
        } catch (InvalidUserNameException e) {
            System.out.println(e.getMessage());
            System.out.println("New User with empty username - Test PASSED!\n");
        } catch (InvalidPasswordException | InvalidNameException | UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(Empty UserName Test) - Test FAILURE\n");
        }
    }

    public void setupNewUserWithInvalidUsernameTest() {

        User user = new User("Test Name", "am", "Testing123", new Account("123654987", 3000));
        try {
            banking.setupNewUser(user, repository);
            System.out.println("User added with invalid username - Test FAILURE\n");
        } catch (InvalidUserNameException e) {
            System.out.println(e.getMessage());
            System.out.println("New User with invalid username - Test PASSED!\n");
        } catch (InvalidPasswordException | InvalidNameException | UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(Invalid UserName Test) - Test FAILURE\n");
        }
    }

    public void setupNewUserWithInvalidPasswordTest() {

        User user = new User("Test Name", "testuser", "abc", new Account("123654987", 3000));
        try {
            banking.setupNewUser(user, repository);
            System.out.println("User added with invalid password - Test FAILURE\n");
        } catch (InvalidPasswordException e) {
            System.out.println(e.getMessage());
            System.out.println("New User with invalid password - Test PASSED!\n");
        } catch (InvalidUserNameException | InvalidNameException | UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(Invalid Password Test) - Test FAILURE\n");
        }
    }

    public void setupNewUserTest() {

        User user = new User("Test Name", "test123456", "Testing123", new Account("123654987", 3000));
        try {
            banking.setupNewUser(user, repository);
            System.out.println("New User - Test PASSED!\n");
        } catch (InvalidNameException | InvalidPasswordException | InvalidUserNameException |
                 UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("User not added - Test FAILURE\n");
        }
    }

    public void isExistingUserPositiveTest() {
        boolean isRegisteredUser = banking.isExistingUser("Batman", repository);
        if (isRegisteredUser) {
            System.out.println("Existing User Verified - Test PASSED!\n");
        } else {
            System.out.println("Unable to verify existing user - Test FAILURE\n");
        }
    }

    public void depositTest() {

        User user;
        try {
            user = new User("Batman", repository);
            Account account = user.getAccount();
            double startingBalance = account.getBalance();

            banking.deposit(user, 100);
            if (account.getBalance() - startingBalance == 100) {
                System.out.println("Deposit - Test PASSED!\n");
            } else {
                System.out.println("Deposit not processed - Test FAILURE\n");
            }
        } catch (UserDoesNotExistException | InvalidAmountException e) {
            System.out.println("Deposit not processed - Test FAILURE");
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void depositNegativeAmountTest() {

        User user;
        try {
            user = new User("Batman", repository);
            Account account = user.getAccount();
            double startingBalance = account.getBalance();

            banking.deposit(user, -10);
            System.out.println("Deposit - Test FAILURE\n");
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
            System.out.println("Deposit negative amount - Test PASSED!\n");
        } catch (UserDoesNotExistException e) {
            System.out.println("Something went wrong @(depositNegativeAmountTest) - Test FAILURE\n");
        }
    }

    public void withdrawTest() {

        try {
            User user = new User("SpiderMan", repository);
            Account account = user.getAccount();
            double startingBalance = account.getBalance();
            banking.withdraw(user, 50);
            System.out.println("Withdraw - Test PASSED!\n");
        } catch (InsufficientBalanceException | UserDoesNotExistException | InvalidAmountException e) {
            System.out.println("Withdrawal not processed - Test FAILURE");
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void withdrawNegativeAmountTest() {

        User user;
        try {
            user = new User("Batman", repository);
            Account account = user.getAccount();
            double startingBalance = account.getBalance();

            banking.withdraw(user, -10);
            System.out.println("Withdraw negative amount - Test FAILURE\n");
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
            System.out.println("Withdraw negative amount - Test PASSED!\n");
        } catch (UserDoesNotExistException | InsufficientBalanceException e) {
            System.out.println("Something went wrong @(withdrawNegativeAmountTest) - Test FAILURE\n");
        }
    }
}
