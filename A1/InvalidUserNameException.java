/*
Baeldung, “Create a custom exception in Java,” Baeldung, 02-May-2021. [Online].
Available: https://www.baeldung.com/java-new-custom-exception.
[Accessed: 20-Sep-2022].
 */

public class InvalidUserNameException extends Exception
{
    public InvalidUserNameException(String message) {
        super(message);
    }
}
