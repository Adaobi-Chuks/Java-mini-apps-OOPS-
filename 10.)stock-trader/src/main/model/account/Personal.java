package src.main.model.account;

import src.main.model.Trade;
import src.main.model.Trade.Type;

public class Personal extends Account {
    public static final double SELL_FEE = 0.05;
    
    public Personal(double funds) {
        super(funds);
    }

    public Personal(Personal source) {
        super(source);
    }

    @Override
    public Account clone() {
        return new Personal(this);
    }

    @Override
    public void executeTrade(Trade trade) {
        if(successOrNot(trade).equals("successful")) {
            setShares(trade.getStock(), calculateShares(trade));
            if (trade.getType().equals(Type.MARKET_BUY)) {
                this.setFunds(calculateFunds(trade, 0));
            } else {
                this.setFunds(calculateFunds(trade, SELL_FEE));
            }
        }
    }

    @Override
    public String successOrNot(Trade trade) {
        if(trade.getType().equals(Trade.Type.MARKET_BUY)) {
            if(calculateFunds(trade, 0) < 0) {
                return "unsuccessful";
            }
        } else if(trade.getType().equals(Trade.Type.MARKET_SELL)) {
            if(getShares(trade.getStock()) < trade.getShares()) {
                return "unsuccessful";
            }
        }
        return "successful";
    }

}
