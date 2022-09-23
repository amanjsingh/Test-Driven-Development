public class BankingServiceHelper {
    public void userNameValidation(String userName) throws InvalidUserNameException{
        if(userName == null || userName.length()<5){
            throw new InvalidUserNameException("Username length must be greater than 5");
        }
    }

    public void nameValidation(String name) throws InvalidNameException{
        if (name == null || name.equals("")) {
            throw new InvalidNameException("Name cannot be empty");
        }
    }

    public void passwordValidation(String password) throws InvalidPasswordException{
        if(password == null || password.length()<5){
            throw new InvalidPasswordException("Password length must be greater than 5");
        }
    }

    public void acctNumValidation(String acctNum) throws  InvalidAccoutNumberException {
        if(acctNum == null || acctNum.length()<9){
            throw new InvalidAccoutNumberException("Account Number must be 9 digits long");
        }
    }

    public void acctBalanceValidation(Double balance) throws InvalidAccountBalanceException {
        if(balance.toString().isEmpty() || balance == 0){
            throw new InvalidAccountBalanceException("Enter a valid amount");
        }
    }
}
