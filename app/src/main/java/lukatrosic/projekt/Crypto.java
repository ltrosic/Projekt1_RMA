package lukatrosic.projekt;

import java.io.Serializable;

public class Crypto implements Serializable {


    private String symbol;
    private String highPrice;

    public Crypto(String symbol, String highPrice){
        this.symbol = symbol;
        this.highPrice = highPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }
}
