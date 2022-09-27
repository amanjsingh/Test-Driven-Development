import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankingServiceHelper {
    public void userNameValidation(String userName) throws InvalidUserNameException{
        /*
        Java regular expressions. [Online]. Available: https://www.w3schools.com/java/java_regex.asp. [Accessed: 27-Sep-2022].
        */
        Pattern userNameRegex = Pattern.compile("^[A-Za-z][A-Za-z0-9]{4,9}$");
        Matcher userNameValidator = userNameRegex.matcher(userName);
        boolean isUserNameValid = userNameValidator.find();
        if(!isUserNameValid){
            throw new InvalidUserNameException("Username should be 5 to 10 characters long");
        }
    }

    public void nameValidation(String name) throws InvalidNameException{
        Pattern nameRegex = Pattern.compile("^[A-Za-z ]{3,70}$");
        Matcher nameValidator = nameRegex.matcher(name);
        boolean isNameValid = nameValidator.find();
        if (!isNameValid) {
            throw new InvalidNameException("Name cannot be empty\nName should be atleast 3 characters long");
        }
    }

    public void passwordValidation(String password) throws InvalidPasswordException{
        if(password == null || password.length()<5){
            throw new InvalidPasswordException("Password length must be greater than 5");
        }
    }

    public void acctNumValidation(String acctNum) throws  InvalidAccoutNumberException {
        if(acctNum.isEmpty() || acctNum.length()<9 || acctNum.length()>9){
            throw new InvalidAccoutNumberException("Account Number must be 9 digits long");
        }
    }

    public void acctBalanceValidation(Double balance) throws InvalidAccountBalanceException {
        if(balance.toString().isEmpty() || balance <= 0){
            throw new InvalidAccountBalanceException("Enter a valid amount");
        }
    }
}
