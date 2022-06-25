package src.main.model;

public class Trade {
    
    public enum Stock {AAPL, FB, GOOG, TSLA};
    public enum Type {MARKET_BUY, MARKET_SELL};

    private Stock stock;
    private Type type;
    private int shares;
    private double price;

    public Trade(Stock stock, Type type, int shares, double price) {
        if(stock == null || type == null || shares < 0 || price < 0) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        this.stock = stock;
        this.type = type;
        this.shares = shares;
        this.price = price;
    }

    public Trade(Trade source) {
        this.stock = source.stock;
        this.type = source.type;
        this.shares = source.shares;
        this.price = source.price;
    }

    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        if(stock == null) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        this.stock = stock;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        if(type == null) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        this.type = type;
    }

    public int getShares() {
        return this.shares;
    }

    public void setShares(int shares) {
        if(shares < 0) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        this.shares = shares;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        if(price < 0) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        this.price = price;
    }

}
