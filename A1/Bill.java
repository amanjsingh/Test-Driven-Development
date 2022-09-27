import java.time.LocalDate;

public class Bill {
    private int joiningYear;
    private double currentPlan;
    private double pendingAmt;
    private int lastPaidYear;
    private boolean isReferred;
    private double voucher;

    public Bill(double currentPlan, boolean isReferred) {
        this.currentPlan = currentPlan;
        this.isReferred = isReferred;
        this.joiningYear = LocalDate.now().getYear();
        setPendingAmt();
        this.lastPaidYear = LocalDate.now().getYear();
    }

    public Bill( double currentPlan, boolean isReferred, int joiningYear, double pendingAmt, int lastPaidYear) {
        this.joiningYear = joiningYear;
        this.currentPlan = currentPlan;
        this.pendingAmt = pendingAmt;
        this.lastPaidYear = lastPaidYear;
        this.isReferred = isReferred;
    }

    private void setPendingAmt() {
        if(isReferred()){
            System.out.println("Congrats! $10 referral voucher applied");
            this.pendingAmt = currentPlan - 10;
            this.isReferred = false;
            return;
        }
        int currentYear = LocalDate.now().getYear();
        this.pendingAmt += (currentYear-lastPaidYear)*currentPlan;
    }

    public double getPendingAmt() {
        int currentYear = LocalDate.now().getYear();
        double currentBill = this.pendingAmt + (currentYear-this.lastPaidYear)*this.currentPlan;
        return currentBill;
    }

    public void setLastPaidYear() {
        this.lastPaidYear = LocalDate.now().getYear();
        this.pendingAmt = 0;
    }

    private boolean isLoyalCustomer() {
        int currentYear = LocalDate.now().getYear();
        boolean isLoyalCustomer;

        if (currentYear - joiningYear >= 5) {
            isLoyalCustomer = true;
        } else {
            isLoyalCustomer = false;
        }

        return isLoyalCustomer;
    }

    private void setVoucher(double voucher) {
        if(isLoyalCustomer()){
            this.voucher = 5;
            return;
        }
        this.voucher = 0;
    }

    public double getVoucher() {
        return voucher;
    }

    public double getCurrentPlan() {
        return currentPlan;
    }

    public void setCurrentPlan(double currentPlan) {
        this.currentPlan = currentPlan;
    }

    public int getJoiningYear() {
        return joiningYear;
    }

    public int getLastPaidYear() {
        return lastPaidYear;
    }

    public boolean isReferred() {
        return isReferred;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "currentPlan=" + currentPlan +
                ", pendingAmt=" + pendingAmt +
                '}';
    }
}
