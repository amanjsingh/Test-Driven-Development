public interface BankingService {
    public abstract void setupNewUser(User user, UserRepository repository) throws InvalidNameException, InvalidPasswordException, InvalidUserNameException, UserAlreadyExistsException;
    public abstract boolean isExistingUser(String username, UserRepository repository);
    public abstract void deposit(User user, double amount) throws InvalidAmountException;
    public abstract void withdraw(User user, double amount) throws InsufficientBalanceException, InvalidAmountException;
}
