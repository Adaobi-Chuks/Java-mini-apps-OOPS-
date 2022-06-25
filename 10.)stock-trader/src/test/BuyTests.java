package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import src.main.model.Trade;
import src.main.model.Trade.Stock;
import src.main.model.Trade.Type;
import src.main.model.account.Account;
import src.main.model.account.Personal;
import src.main.model.account.TFSA;

public class BuyTests {
    
    Account[] accounts;

    @Before
    public void setup() {
        accounts = new Account[] {new Personal(4000), new TFSA(4000)};
    }

    @Test
    public void pSharesIncrease() {
        accounts[0].executeTrade(new Trade(Stock.AAPL, Type.MARKET_BUY, 10, 15.649286));
        assertEquals(10, accounts[0].getShares(Stock.AAPL));
    }

    @Test
    public void tSharesIncrease() {
        accounts[1].executeTrade(new Trade(Stock.AAPL, Type.MARKET_BUY, 10, 15.649286));
        assertEquals(10, accounts[1].getShares(Stock.AAPL));
    }

    @Test
    public void insufficientFundsBuyTest() {
        accounts[0].executeTrade(new Trade(Stock.AAPL, Type.MARKET_BUY, 10000, 15.649286));
        assertEquals(0, accounts[0].getShares(Stock.AAPL));
    }

    @Test
    public void pFundsDecrease() {
        accounts[0].executeTrade(new Trade(Stock.AAPL, Type.MARKET_BUY, 10, 15.649286));
        assertEquals(3843.51, accounts[0].getFunds());
    }

    @Test
    public void tFundsInDecrease() {
        accounts[1].executeTrade(new Trade(Stock.AAPL, Type.MARKET_BUY, 10, 15.649286));
        assertEquals(3841.94, accounts[1].getFunds());
    }

}
