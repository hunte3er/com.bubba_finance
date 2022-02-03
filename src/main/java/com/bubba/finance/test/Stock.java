/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubba.finance.test;

import java.io.IOException;
import options.OptionChain;
import options.OptionsRequestV7;

/**
 *
 * @author eiker
 */
public class Stock extends yahoofinance.Stock{
    private OptionChain optionChain;
    
    public Stock(String symbol){
        super(symbol);
    }
    
    public OptionChain getOptionChain() throws IOException {
        if(this.optionChain != null) {
            return this.optionChain;
        } else {
            if(YahooFinance.OPTIONS_REQUESTV7_ENABLED.equalsIgnoreCase("true")) {
               OptionsRequestV7 opt = new OptionsRequestV7(super.getSymbol());
               this.setOptionChain(opt.getResult());
            } else {
   //            OptionsRequest opt = new OptionsRequest(this.symbol);
   //            this.setHistory(opt.getResult());
            }
            return this.optionChain;
        }
    }
    
    public void setOptionChain(OptionChain optionChain){
        this.optionChain = optionChain;
    }
}
