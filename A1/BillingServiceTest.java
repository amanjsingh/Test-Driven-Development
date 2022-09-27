public class BillingServiceTest {

    private UserRepository userRepository;
    private TelecomRepository telecomRepository;
    private BillingService billingService;
    private BankingService bankingService;

    public BillingServiceTest() {
        userRepository = new UserRepositoryMock();
        telecomRepository = new TelecomRepositoryMock();
        billingService = new BillingServiceTelecomImpl();
    }

    public void addUserTest() {
        try {
            User user = userRepository.fetchUser("Checkmybalance");
            Bill bill = new Bill(600, false);
            user.setBill(bill);
            billingService.addUser(user, telecomRepository);
            System.out.println("User added to telecom service - Test PASSED!\n");
        } catch (UserDoesNotExistException | UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to add user to telecom service - Test FAILURE\n");
        }
    }

    public void addEnrolledUserTest() {
        try {
            User user = userRepository.fetchUser("Checkmybalance");
            Bill bill = new Bill(600, false);
            user.setBill(bill);
            billingService.addUser(user, telecomRepository);
            System.out.println("Already enrolled user added to telecom service - Test FAILURE!\n");
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("User has already enrolled in telecom service - Test PASSED\n");
        } catch (UserDoesNotExistException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(Add enrolled user) - Test FAILURE\n");
        }
    }

    public void enrollWithInvalidUser() {
        try {
            User user = userRepository.fetchUser("InvalidUser");
            Bill bill = new Bill(600, false);
            user.setBill(bill);
            billingService.addUser(user, telecomRepository);
            System.out.println("Non-Banking user enrolled in Telecom service - Test FAILURE!\n");
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(enrollWithInvalidUser) - Test FAILURE\n");
        } catch (UserDoesNotExistException e) {
            System.out.println(e.getMessage());
            System.out.println("Non-Banking users not allowed to enroll in Telecom service - Test PASSED\n");
        }
    }

    public void dueAmountTest() {
        try {
            User user = userRepository.fetchUser("Batman");
            double dueAmt = billingService.dueAmount(user);
            if(dueAmt == 2400){
                System.out.println("Due Amount - Test PASSED!\n");
            }
        } catch (UserDoesNotExistException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(dueAmountTest) - Test FAILURE\n");
        }
    }

    public void payBillTest(){
        try {
            bankingService = new BankingServiceImpl();
            User user = userRepository.fetchUser("RunMachine");
            double accountBalanceBefore = user.getAccount().getBalance();
            double pendingAmountBefore = user.getBill().getPendingAmt();

            billingService.payBill(user, bankingService);

            double accountBalanceAfter = user.getAccount().getBalance();
            double pendingAmountAfter = user.getBill().getPendingAmt();

            if(accountBalanceBefore-accountBalanceAfter == pendingAmountBefore){
                System.out.println("Pay bill - Test PASSED!\n");
            }

        } catch (UserDoesNotExistException | InsufficientBalanceException | InvalidAmountException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(payBillTest) - Test FAILURE\n");
        }
    }

    public void payBillLowBalanceTest(){
        try {
            bankingService = new BankingServiceImpl();
            User user = userRepository.fetchUser("Checkmybalance");

            billingService.payBill(user, bankingService);

        } catch ( InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            System.out.println("Pay bill with Insufficient Balance - Test PASSED!\n");
        } catch (UserDoesNotExistException | InvalidAmountException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong @(payBillLowBalanceTest) - Test FAILURE\n");
        }
    }
}
