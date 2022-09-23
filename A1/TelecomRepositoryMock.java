import java.util.HashMap;
import java.util.Map;

public class TelecomRepositoryMock implements TelecomRepository {

    private static Map<String, Bill> userMap = new HashMap<>();

    static {
        userMap.put("Batman", new Bill(600, false, 2020, 1200, 2020));
        userMap.put("RunMachine", new Bill(1200, false, 2012, 3600, 2019));
        userMap.put("SpiderMan", new Bill(1800, false, 2022, 1790, 2022));
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        if (isExistingUser(user.getUserName())) {
            throw new UserAlreadyExistsException("User already enrolled in telecom plan");
        }
        userMap.put(user.getUserName(), user.getBill());
    }

    @Override
    public boolean isExistingUser(String username) {
        return userMap.containsKey(username);
    }

    @Override
    public Bill fetchBill(String userName) throws UserDoesNotExistException {
        if (!isExistingUser(userName)) {
            throw new UserDoesNotExistException("User is not enrolled in telecom plan - Please register");
        }
        return userMap.get(userName);
    }
}
