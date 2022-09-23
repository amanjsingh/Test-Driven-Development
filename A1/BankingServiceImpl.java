public class BankingServiceImpl implements BankingService {

    private final BankingServiceHelper helper = new BankingServiceHelper();

    @Override
    public void setupNewUser(User user, UserRepository repository) throws InvalidNameException, InvalidPasswordException, InvalidUserNameException, UserAlreadyExistsException {
        helper.userNameValidation(user.getUserName());
        helper.nameValidation(user.getName());
        helper.passwordValidation(user.getPassword());
        repository.addUser(user);
    }

    @Override
    public boolean isExistingUser(String username, UserRepository repository) {
        try {
            repository.fetchUser(username);
            return true;
        } catch (UserDoesNotExistException e) {
            return false;
        }
    }

    @Override
    public void deposit(User user, double amount) throws InvalidAmountException {
        if(amount <=0){
            throw new InvalidAmountException("Please enter amount greater than 0");
        }
        Account account = user.getAccount();
        double balance = account.getBalance();
        balance += amount;
        account.setBalance(balance);
    }

    @Override
    public void withdraw(User user, double amount) throws InsufficientBalanceException, InvalidAmountException {
        if(amount<=0){
            throw new InvalidAmountException("Please enter amount greater than 0");
        }
        Account account = user.getAccount();
        double balance = account.getBalance();
        if (amount > balance) {
            throw new InsufficientBalanceException("Unable to process transaction - Insufficient Balance");
        }
        balance -= amount;
        account.setBalance(balance);
    }
}
