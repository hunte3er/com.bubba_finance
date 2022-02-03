/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package options;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author eiker
 */
public class OptionChain {
    private String symbol;
    private List<Calendar> expirationDates;
    private List<BigDecimal> strikes;
    private Boolean hasMiniOptions;
    private String quote;
    private List<StockOption> calls;
    private List<StockOption> puts;
    private String error;
    
    
    public OptionChain(){};
    
    public OptionChain(
            String symbol,
            List<Calendar> expirationDates,
            List<BigDecimal> strikes,
            Boolean hasMiniOptions,
            String quote,
            List<StockOption> calls,
            List<StockOption> puts,
            String error
    ){
        this.symbol = symbol;
        this.expirationDates = expirationDates;
        this.strikes = strikes;
        this.hasMiniOptions = hasMiniOptions;
        this.quote = quote;
        this.calls = calls;
        this.puts = puts;
        this.error = error;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Calendar> getExpirationDates() {
        return expirationDates;
    }

    public void setExpirationDates(List<Calendar> expirationDates) {
        this.expirationDates = expirationDates;
    }

    public List<BigDecimal> getStrikes() {
        return strikes;
    }

    public void setStrikes(List<BigDecimal> strikes) {
        this.strikes = strikes;
    }

    public Boolean getHasMiniOptions() {
        return hasMiniOptions;
    }

    public void setHasMiniOptions(Boolean hasMiniOptions) {
        this.hasMiniOptions = hasMiniOptions;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public List<StockOption> getCalls() {
        return calls;
    }

    public void setCalls(List<StockOption> calls) {
        this.calls = calls;
    }

    public List<StockOption> getPuts() {
        return puts;
    }

    public void setPuts(List<StockOption> puts) {
        this.puts = puts;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
        
    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < calls.size(); i++){
            result += calls.get(i).toString() + "\r\n";
        }
        for(int i = 0; i < puts.size(); i++){
            result += puts.get(i).toString() + "\r\n";
        }
        return result;
    }
}
