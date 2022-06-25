package src.main.model.account;

public class Savings extends Account {

    private static final double WITHDRAWAL_FEE = 5.0;

    public Savings(String id, String name, double balance) {
        super(id, name, balance);
    }

    public Savings(Savings source) {
        super(source);
    }

    @Override
    public void deposit(double amount) {
        setBalance(round(getBalance() + amount));
    }

    @Override
    public boolean withdraw(double amount) {
        setBalance(round(getBalance() - (amount + WITHDRAWAL_FEE)));
        return true;
    }

    @Override
    public Account clone() {
        return new Savings(this);
    }    
    
}
