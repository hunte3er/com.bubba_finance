/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubba.finance.test;

import com.yahoofinance.quotes.query1v7.StockOptionsQuery1V7Request;

/**
 *
 * @author eiker
 */
public class Test {
    
    public static void main(String[] args) throws Exception{
        //Stock stock = YahooFinance.get("AAPL");
        
//        Map<String, Stock> stocks = YahooFinance.get((new String[]{"AAPL", "AA", "ABB"}));
 
//        BigDecimal price = stock.getQuote().getPrice();
//        BigDecimal change = stock.getQuote().getChangeInPercent();
//        BigDecimal peg = stock.getStats().getPeg();
//        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
//        
//        FxQuote usdeur = YahooFinance.getFx(FxSymbols.USDGBP);

        //stock.print();
        
//        Iterator<Map.Entry<String, Stock>> iterator = stocks.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Stock> entry = iterator.next();
//            entry.getValue().print();
//        }
//        System.out.println(usdeur);
        StockOptionsQuery1V7Request req = new StockOptionsQuery1V7Request("AAPL");
        System.out.println(req.getResult());
    }
}
