import java.util.HashMap;
import java.util.Map;

public class UserRepositoryMock implements UserRepository {

    private static Map<String, User> userMap = new HashMap<>();

    static {
        userMap.put("Batman", new User("Bruce Wayne", "Batman", "BatsAreBackup", new Account("123459876", 100000), new Bill(600, false, 2020, 1200, 2020)));
        userMap.put("RunMachine", new User("Virat Kohli", "RunMachine", "Century71", new Account("543216789", 90000), new Bill(1200, false, 2012, 3600, 2019)));
        userMap.put("SpiderMan", new User("Peter Parker", "SpiderMan", "Webguy", new Account("123987654", 50000), new Bill(1800, false, 2022, 1790, 2022)));
        userMap.put("Checkmybalance", new User("Amanjot Singh", "Checkmybalance", "roundpi", new Account("1122334455", 3.14)));
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        if(userMap.containsKey(user.getUserName())){
            throw new UserAlreadyExistsException("User already exists - Try with a different username");
        }
        userMap.put(user.getUserName(), user);
    }

    @Override
    public boolean isExistingUser(String userName) {
        return userMap.containsKey(userName);
    }

    @Override
    public User fetchUser(String userName) throws UserDoesNotExistException {
        if(!userMap.containsKey(userName)){
            throw new UserDoesNotExistException("Unregistered Username - Try creating a new user");
        }
        return userMap.get(userName);
    }

    @Override
    public Map<String, User> fetchAllUsers() {
        return new HashMap<>(userMap);
    }
}
