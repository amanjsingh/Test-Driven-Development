public class User {
    private String name;
    private String userName;
    private String password;

    private Level level;
    private Account account;

    private Bill bill;
    private boolean hasBill;

    public User(String name, String userName, String password, Account account) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.account = account;
        setLevel(this.account);
    }

    public User(String name, String userName, String password, Account account, Bill bill) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.account = account;
        setLevel(this.account);
        setBill(bill);
    }

    public User(String userName, UserRepository repository) throws UserDoesNotExistException {
        User user = repository.fetchUser(userName);
        this.name = user.name;
        this.userName = user.userName;
        this.password = user.password;
        this.account = user.account;
        setLevel(this.account);
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Level getLevel() {
        return level;
    }

    private void setLevel(Account account) {
        if(account.getBalance() > 100000){
            this.level = Level.PLATINUM;
        }
        else if(account.getBalance() > 75000){
            this.level = Level.GOLD;
        }
        else{
            this.level = Level.SILVER;
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
        this.hasBill = true;
    }

    public boolean hasBill() {
        return hasBill;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", level=" + level + '\'' +
                ", acctNum=" + account.getNumber() + '\'' +
                ", acctBal=" + account.getBalance() + '\'' +
                ", Telecom Service=" + hasBill() + '\'' +
                '}';
    }
}
