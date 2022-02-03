/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package options;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author eiker
 */
public class StockOption {    
    private String symbol;    
    private String contractSymbol;
    private BigDecimal strike;
    private String currency;
    private BigDecimal lastPrice;
    private BigDecimal change;
    private BigDecimal percentChange;
    private long volume;
    private long openInterest;
    private BigDecimal bid;
    private BigDecimal ask;
    private String contractSize;
    private Calendar expiration;
    private Calendar lastTradeDate;
    private BigDecimal impliedVolatility;
    private boolean inTheMoney;
    
    public StockOption() {}

    public StockOption( 
            String symbol,    
            String contractSymbol,
            BigDecimal strike,
            String currency,
            BigDecimal lastPrice,
            BigDecimal change,
            BigDecimal percentChange,
            long volume,
            long openInterest,
            BigDecimal bid,
            BigDecimal ask,
            String contractSize,
            Calendar expiration,
            Calendar lastTradeDate,
            BigDecimal impliedVolatility,
            boolean inTheMoney) {
        this.symbol = symbol;
        this.contractSymbol = contractSymbol;
        this.strike = strike;
        this.currency = currency;
        this.lastPrice = lastPrice;
        this.change = change;
        this.percentChange = percentChange;
        this.volume = volume;
        this.openInterest = openInterest;
        this.bid = bid;
        this.ask = ask;
        this.contractSize = contractSize;
        this.expiration = expiration;
        this.lastTradeDate = lastTradeDate;
        this.impliedVolatility = impliedVolatility;
        this.inTheMoney = inTheMoney;
    }

    public String getSymbol() {
        return symbol;
    }
    
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    
    public String getContractSymbol(){
        return contractSymbol;
    }    
    
    public void setContractSymbol(String contractSymbol){
        this.contractSymbol = contractSymbol;
    }
    
    public BigDecimal getStrike(){
        return strike;
    }    
    
    public void setStrike(BigDecimal strike){
        this.strike = strike;
    }
    
    public String getCurrency(){
        return currency;
    }    
    
    public void setCurrency(String currency){
        this.currency = currency;
    }
    
    public BigDecimal getLastPrice(){
        return lastPrice;
    }    
    
    public void setLastPrice(BigDecimal lastPrice){
        this.lastPrice = lastPrice;
    }
    
    public BigDecimal getChange(){
        return change;
    }    
    
    public void setChange(BigDecimal change){
        this.change = change;
    }
    
    public BigDecimal getPercentChange(){
        return percentChange;
    }    
    
    public void setPercentChange(BigDecimal percentChange){
        this.percentChange = percentChange;
    }
    
    public Long getVolume(){
        return volume;
    }    
    
    public void setVolume(Long volume){
        this.volume = volume;
    }
    
    public Long getOpenInterest(){
        return openInterest;
    }    
    
    public void setOpenInterest(Long openInterest){
        this.openInterest = openInterest;
    }
    
    public BigDecimal getBid(){
        return bid;
    }    
    
    public void setBid(BigDecimal bid){
        this.bid = bid;
    }
    
    public BigDecimal getAsk(){
        return ask;
    }    
    
    public void setAsk(BigDecimal ask){
        this.ask = ask;
    }
    
    public String getContractSize(){
        return contractSize;
    }    
    
    public void setContractSize(String contractSize){
        this.contractSize = contractSize;
    }
    
    public Calendar getExpiration(){
        return expiration;
    }    
    
    public void setExpiration(Calendar expiration){
        this.expiration = expiration;
    }
    
    public Calendar getLastTradeDate(){
        return lastTradeDate;
    }    
    
    public void setLastTradeDate(Calendar lastTradeDate){
        this.lastTradeDate = lastTradeDate;
    }
    
    public BigDecimal getImpliedVolatility(){
        return impliedVolatility;
    }    
    
    public void setimpliedVolatility(BigDecimal impliedVolatility){
        this.impliedVolatility = impliedVolatility;
    }
    
    public Boolean getIsInTheMoney(){
        return inTheMoney;
    }    
    
    public void setIsInTheMoney(Boolean inTheMoney){
        this.inTheMoney = inTheMoney;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String lastDate = dateFormat.format(this.lastTradeDate.getTime());
        String expDate = dateFormat.format(this.expiration.getTime());
        return this.symbol + "@" + this.contractSymbol + ": S@" + this.strike + ", A@" + this.ask + ", B@" + 
                this.bid + " <- " + this.currency + " (Last: " + this.lastPrice + ", Change: " + this.change + "|" + 
                this.percentChange + "%), IV: " + this.impliedVolatility + ", IM: " +
                this.inTheMoney + " | " + this.openInterest + "/" + this.volume + " [" + lastDate + "|" + expDate;
    }
}
