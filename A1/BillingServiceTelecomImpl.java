public class BillingServiceTelecomImpl implements BillingService {
    @Override
    public void addUser(User user, TelecomRepository telecomRepository) throws UserAlreadyExistsException {
        telecomRepository.addUser(user);
    }

    @Override
    public boolean isExistingUser(User user) {
        return user.hasBill();
    }

    @Override
    public double dueAmount(User user) {
        if (user.hasBill()) {
            return user.getBill().getPendingAmt();
        }
        return 0;
    }

    @Override
    public void payBill(User user, BankingService bankingService) throws InsufficientBalanceException, InvalidAmountException {
        Bill bill = user.getBill();
        double amountDue = dueAmount(user);
        bankingService.withdraw(user, amountDue);
        bill.setLastPaidYear();
    }
}
