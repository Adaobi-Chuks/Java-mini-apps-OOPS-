package src.main.model.account;

public class Loan extends Account {

    private static final double interestRate = 0.02;
    private static final double MAX_DEBT = 10000;

    public Loan(String id, String name, double balance) {
        super(id, name, balance);
    }

    public Loan(Loan source) {
        super(source);
    }

    @Override
    public void deposit(double amount) {
        setBalance(round(getBalance() - amount));
    }

    @Override
    public boolean withdraw(double amount) {
        if(round(getBalance() + (amount + (interestRate * amount))) < MAX_DEBT) {
            setBalance(round(getBalance() + (amount + (interestRate * amount))));
            return true;
        } return false;
    }

    @Override
    public Account clone() {
        return new Loan(this);
    }
}
