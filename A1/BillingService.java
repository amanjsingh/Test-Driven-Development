public interface BillingService {
    public abstract void addUser(User user, TelecomRepository telecomRepository) throws UserAlreadyExistsException;
    public abstract boolean isExistingUser(User user);
    public abstract double dueAmount(User user);
    public abstract void payBill(User user, BankingService bankingService) throws InsufficientBalanceException, InvalidAmountException;
}
