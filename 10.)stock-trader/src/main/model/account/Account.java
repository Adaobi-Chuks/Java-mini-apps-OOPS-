package src.main.model.account;

import java.text.DecimalFormat;
import java.util.HashMap;
import src.main.model.Trade;
import src.main.model.Trade.Stock;
import src.main.model.Trade.Type;
import src.main.utils.Color;

public abstract class Account {

    private HashMap<Stock, Integer> portfolio;
    private double funds;

    public Account(double funds) {
        if(funds < 0) {
            throw new IllegalArgumentException("Funds can't be less than zero");
        }
        this.funds = funds;
        this.portfolio = new HashMap<Stock, Integer>();
        portfolio.put(Stock.AAPL, 0);
        portfolio.put(Stock.FB, 0);
        portfolio.put(Stock.GOOG, 0);
        portfolio.put(Stock.TSLA, 0);
    }

    public Account(Account source) {
        this.portfolio = source.portfolio;
        this.funds = source.funds;
    }

    public double getFunds() {
        return this.funds;
    }

    public void setFunds(double funds) {
        if(funds < 0) {
            throw new IllegalArgumentException("Funds can't be less than zero");
        }
        this.funds = funds;
    }

    public int getShares(Stock stock) {
        return this.portfolio.get(stock);
    }

    public void setShares(Stock stock, int shares) {
        if(shares < 0) {
            throw new IllegalArgumentException("Shares can't be less than zero");
        }
        this.portfolio.put(stock, shares);
    }

    public abstract Account clone();
    public abstract void executeTrade(Trade trade);
    public abstract String successOrNot (Trade trade);

    public int calculateShares(Trade trade) {
        return (trade.getType().equals(Type.MARKET_BUY)) ? this.portfolio.get(trade.getStock()) + trade.getShares() :  this.portfolio.get(trade.getStock()) - trade.getShares();
    }

    public double calculateFunds(Trade trade, double fee) {
        double cost = trade.getPrice() * trade.getShares();
        double fees = cost * fee;
        return (trade.getType().equals(Type.MARKET_BUY)) ? round(getFunds() - (cost + fees)) : round(getFunds() + (cost - fees));
    }
    
    private String displayPortofolio() {
        return "  " + Stock.AAPL + "\t\t" + portfolio.get(Trade.Stock.AAPL) + "\n"
            + "  " + Stock.FB + "\t\t" + portfolio.get(Trade.Stock.FB) + "\n"
            + "  " + Stock.GOOG + "\t\t" + portfolio.get(Trade.Stock.GOOG) + "\n"
            + "  " + Stock.TSLA + "\t\t" + portfolio.get(Trade.Stock.TSLA) + "\n";
    }
    
    private double round(double funds) {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.parseDouble(formatter.format(funds));
    }
    
    public String toString() {
        return "\n  Stock\t\t"  + Color.RESET + "Shares" +
        "\n\n" + displayPortofolio() + Color.RESET +
        "\n  Funds Left\t" + Color.GREEN + "$" + round(this.getFunds()) + Color.RESET;
    }

}
