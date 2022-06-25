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

public class SellTests {
    
    Account[] accounts;

    @Before
    public void setup() {
        accounts = new Account[] {new Personal(4000), new TFSA(4000)};
        for (int i = 0; i < accounts.length; i++) {
            accounts[i].executeTrade(new Trade(Stock.AAPL, Type.MARKET_BUY, 10, 15.649286));
        }
    }

    @Test
    public void pSharesDecrease() {
        accounts[0].executeTrade(new Trade(Stock.AAPL, Type.MARKET_SELL, 2, 15.649286));
        assertEquals(8, accounts[0].getShares(Stock.AAPL));
    }

    @Test
    public void tSharesDecrease() {
        accounts[1].executeTrade(new Trade(Stock.AAPL, Type.MARKET_SELL, 2, 15.649286));
        assertEquals(8, accounts[1].getShares(Stock.AAPL));
    }

    @Test
    public void insufficientFundsSellTest() {
        accounts[0].executeTrade(new Trade(Stock.AAPL, Type.MARKET_SELL, 12, 15.649286));
        assertEquals(3843.51, accounts[0].getFunds());
    }

    @Test
    public void pFundsIncrease() {
        accounts[0].executeTrade(new Trade(Stock.AAPL, Type.MARKET_SELL, 10, 15.649286));
        assertEquals(3992.18, accounts[0].getFunds());
    }

    @Test
    public void tFundsIncrease() {
        accounts[1].executeTrade(new Trade(Stock.AAPL, Type.MARKET_SELL, 10, 15.649286));
        assertEquals(3996.87, accounts[1].getFunds());
    }
}
