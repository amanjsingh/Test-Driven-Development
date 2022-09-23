import java.util.Map;

public interface UserRepository {
    public abstract void addUser(User user) throws UserAlreadyExistsException;
    public abstract boolean isExistingUser(String userName);
    public abstract User fetchUser(String userName) throws UserDoesNotExistException;
    public abstract Map<String, User> fetchAllUsers();
}
