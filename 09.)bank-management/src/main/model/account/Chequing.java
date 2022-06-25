package src.main.model.account;

import src.main.model.account.impl.Taxable;

public class Chequing extends Account implements Taxable{

    private static final double OVERDRAFT_FEE = 5.50;
    private static final double TAX = 0.15;
    private static final double OVERDRAFT_LIMIT = -200;
    private static final double TAXABLE = 3000;

    public Chequing(String id, String name, double balance) {
        super(id, name, balance);
    }

    public Chequing(Chequing savings) {
        super(savings);
    }

    @Override
    public void deposit(double amount) {
        setBalance(round(getBalance() + amount));
    }

    @Override
    public boolean withdraw(double amount) {
        if(amount > getBalance()) {
            amount += OVERDRAFT_FEE;
        }
        if((getBalance() - amount) > OVERDRAFT_LIMIT) {
            setBalance(round(getBalance() - amount));
            return true;
        }
        return false;
    }

    @Override
    public void tax(double income) {
        if(income > TAXABLE) {
            setBalance(round(getBalance() - ((income - TAXABLE) * TAX)));
        }
    }

    @Override
    public Account clone() {
        return new Chequing(this);
    }

}
