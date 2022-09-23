import java.util.Map;

public class UserRepositorySQL implements UserRepository{
    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        UserRepositoryMock repository = new UserRepositoryMock();
        repository.addUser(user);
    }

    @Override
    public boolean isExistingUser(String userName) {
        UserRepositoryMock repository = new UserRepositoryMock();
        return repository.isExistingUser(userName);
    }

    @Override
    public User fetchUser(String userName) throws UserDoesNotExistException {
        UserRepositoryMock repository = new UserRepositoryMock();
        return repository.fetchUser(userName);
    }

    @Override
    public Map<String, User> fetchAllUsers() {
        UserRepositoryMock repository = new UserRepositoryMock();
        return repository.fetchAllUsers();
    }
}
