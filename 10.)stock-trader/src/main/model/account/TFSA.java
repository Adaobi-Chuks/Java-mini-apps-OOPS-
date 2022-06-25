package src.main.model.account;

import src.main.model.Trade;

public class TFSA extends Account {
    
    private static final double FEE = 0.01;

    public TFSA(double funds) {
        super(funds);
    }

    public TFSA(TFSA source) {
        super(source);
    }

    @Override
    public Account clone() {
        return new TFSA(this);
    }

    @Override
    public void executeTrade(Trade trade) {
        if(successOrNot(trade).equals("successful")) {
            setShares(trade.getStock(), calculateShares(trade));
            this.setFunds(calculateFunds(trade, FEE));
        }
    }

    @Override
    public String successOrNot(Trade trade) {
        if(trade.getType().equals(Trade.Type.MARKET_BUY)) {
            if(calculateFunds(trade, FEE) < 0) {
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
