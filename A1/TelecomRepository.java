public interface TelecomRepository {
    public abstract void addUser(User user) throws UserAlreadyExistsException;
    public abstract boolean isExistingUser(String username);
    public abstract Bill fetchBill(String userName) throws UserDoesNotExistException;
}
