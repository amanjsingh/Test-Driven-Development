public class MainTest {
    public void runTests() {
        BankingServiceTest bankingTest = new BankingServiceTest();
        BillingServiceTest billingTest = new BillingServiceTest();

//      User Validation
        bankingTest.setupAlreadyExistingUserTest();
        bankingTest.setupNewUserWithEmptyNameTest();
        bankingTest.setupNewUserWithInvalidUsernameTest();
        bankingTest.setupNewUserWithInvalidPasswordTest();

//      Existing User Verification
        bankingTest.isExistingUserPositiveTest();

//      New User Creation
        bankingTest.setupNewUserTest();

//      Banking Operations
        bankingTest.depositTest();
        bankingTest.depositNegativeAmountTest();
        bankingTest.withdrawTest();
        bankingTest.withdrawNegativeAmountTest();

//      Billing Operations
        billingTest.addUserTest();
        billingTest.addEnrolledUserTest();
        billingTest.enrollWithInvalidUser();

        billingTest.dueAmountTest();
        billingTest.payBillTest();
        billingTest.payBillLowBalanceTest();
    }
}
