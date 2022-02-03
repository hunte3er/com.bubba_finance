/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubba.finance.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import yahoofinance.histquotes.HistQuotesRequest;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.csv.StockQuotesData;
import yahoofinance.quotes.csv.StockQuotesRequest;
import yahoofinance.quotes.query1v7.StockQuotesQuery1V7Request;

/**
 *
 * @author eiker
 */
public class YahooFinance extends yahoofinance.YahooFinance{    
    public static final String OPTIONS_REQUESTV7_ENABLED = System.getProperty("yahoofinance.optionsrequestv7.enabled", "true");
    public static final String OPTIONS_QUERY1V7_BASE_URL = System.getProperty("yahoooptions.baseurl.quotes", "https://query1.finance.yahoo.com/v7/finance/options/");

    public static Stock get(String symbol) throws IOException {
        return YahooFinance.get(symbol, false);
    }
    
    /**
     * Same as the <code>get(String)</code> method, but with the option to include
     * historical stock quote data. Including historical data will cause the {@link Stock}
     * object's member field {@link yahoofinance.histquotes.HistoricalQuote} to be filled in
     * with the default past year term at monthly intervals.
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol                the symbol of the stock for which you want to retrieve information
     * @param includeHistorical     indicates if the historical quotes should be included.
     * @return                      a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, boolean includeHistorical) throws IOException {
        Map<String, Stock> result = YahooFinance.getQuotes(symbol, includeHistorical);
        return result.get(symbol.toUpperCase());
    }
    
    /**
     * Sends a request with the historical quotes included
     * at the specified interval (DAILY, WEEKLY, MONTHLY).
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param interval      the interval of the included historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Interval interval) throws IOException {
        return YahooFinance.get(symbol, HistQuotesRequest.DEFAULT_FROM, HistQuotesRequest.DEFAULT_TO, interval);
    }
    
    /**
     * Sends a request with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * at the default interval (monthly).
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param from          start date of the historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Calendar from) throws IOException {
        return YahooFinance.get(symbol, from, HistQuotesRequest.DEFAULT_TO, HistQuotesRequest.DEFAULT_INTERVAL);
    }
    
    /**
     * Sends a request with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * at the specified interval.
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param from          start date of the historical data
     * @param interval      the interval of the included historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Calendar from, Interval interval) throws IOException {
        return YahooFinance.get(symbol, from, HistQuotesRequest.DEFAULT_TO, interval);
    }
    
    /**
     * Sends a request with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * until the specified Calendar date (to) 
     * at the default interval (monthly).
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param from          start date of the historical data
     * @param to            end date of the historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Calendar from, Calendar to) throws IOException {
        return YahooFinance.get(symbol, from, to, HistQuotesRequest.DEFAULT_INTERVAL);
    }
    
    /**
     * Sends a request with the historical quotes included
     * starting from the specified {@link Calendar} date 
     * until the specified Calendar date (to) 
     * at the specified interval.
     * Returns null if the data can't be retrieved from Yahoo Finance.
     * 
     * @param symbol        the symbol of the stock for which you want to retrieve information
     * @param from          start date of the historical data
     * @param to            end date of the historical data
     * @param interval      the interval of the included historical data
     * @return              a {@link Stock} object containing the requested information
     * @throws java.io.IOException when there's a connection problem
     */
    public static Stock get(String symbol, Calendar from, Calendar to, Interval interval) throws IOException {
        Map<String, Stock> result = YahooFinance.getQuotes(symbol, from, to, interval);
        return result.get(symbol.toUpperCase());
    }
//    
//    /**
//    * Sends a basic quotes request to Yahoo Finance. This will return a {@link Map} object
//    * that links the symbols to their respective {@link Stock} objects.
//    * The Stock objects have their {@link yahoofinance.quotes.stock.StockQuote}, {@link yahoofinance.quotes.stock.StockStats}
//    * and {@link yahoofinance.quotes.stock.StockDividend} member fields
//    * filled in with the available data.
//    * <p>
//    * All the information is retrieved in a single request to Yahoo Finance.
//    * The returned Map only includes the Stocks that could 
//    * successfully be retrieved from Yahoo Finance.
//    * 
//    * @param symbols    the symbols of the stocks for which you want to retrieve information
//    * @return           a Map that links the symbols to their respective Stock objects
//     * @throws java.io.IOException when there's a connection problem
//    */
//    public static Map<String, Stock> get(String[] symbols) throws IOException {
//        return YahooFinance.get(symbols, false);
//    }
//    
//    /**
//     * Same as the <code>get(String[])</code> method, but with the option to include
//     * historical stock quote data. Including historical data will cause the {@link Stock}
//     * objects their member field {@link yahoofinance.histquotes.HistoricalQuote} to be filled in
//     * with the default past year term at monthly intervals.
//     * <p>
//     * The latest quotes will be retrieved in a single request to Yahoo Finance.
//     * For the historical quotes (if includeHistorical), 
//     * a separate request will be sent for each requested stock.
//     * The returned Map only includes the Stocks that could 
//     * successfully be retrieved from Yahoo Finance.
//     * 
//     * @param symbols               the symbols of the stocks for which you want to retrieve information
//     * @param includeHistorical     indicates if the historical quotes should be included
//     * @return                      a Map that links the symbols to their respective Stock objects
//     * @throws java.io.IOException when there's a connection problem
//     */
//    public static Map<String, Stock> get(String[] symbols, boolean includeHistorical) throws IOException {
//        return YahooFinance.getQuotes(Utils.join(symbols, ","), includeHistorical);
//    }
//    
//    /**
//     * Sends a request for multiple stocks with the historical quotes included
//     * from the past year, 
//     * at the specified interval. (DAILY, WEEKLY, MONTHLY)
//     * <p>
//     * The latest quotes will be retrieved in a single request to Yahoo Finance.
//     * For the historical quotes, a separate request will be sent for each requested stock.
//     * The returned Map only includes the Stocks that could 
//     * successfully be retrieved from Yahoo Finance.
//     * 
//     * @param symbols               the symbols of the stocks for which you want to retrieve information
//     * @param interval              the interval of the included historical data
//     * @return                      a Map that links the symbols to their respective Stock objects.
//     * @throws java.io.IOException when there's a connection problem
//     */
//    public static Map<String, Stock> get(String[] symbols, Interval interval) throws IOException {
//        return YahooFinance.getQuotes(Utils.join(symbols, ","), HistQuotesRequest.DEFAULT_FROM, HistQuotesRequest.DEFAULT_TO, interval);
//    }
//    
//    /**
//     * Sends a request for multiple stocks with the historical quotes included
//     * starting from the specified {@link Calendar} date until today, 
//     * at the default interval (monthly).
//     * <p>
//     * The latest quotes will be retrieved in a single request to Yahoo Finance.
//     * For the historical quotes, a separate request will be sent for each requested stock.
//     * The returned Map only includes the Stocks that could 
//     * successfully be retrieved from Yahoo Finance.
//     * 
//     * @param symbols               the symbols of the stocks for which you want to retrieve information
//     * @param from                  start date of the historical data
//     * @return                      a Map that links the symbols to their respective Stock objects.
//     * @throws java.io.IOException when there's a connection problem
//     */
//    public static Map<String, Stock> get(String[] symbols, Calendar from) throws IOException {
//        return YahooFinance.getQuotes(Utils.join(symbols, ","), from, HistQuotesRequest.DEFAULT_TO, HistQuotesRequest.DEFAULT_INTERVAL);
//    }
//    
//    /**
//     * Sends a request for multiple stocks with the historical quotes included
//     * starting from the specified {@link Calendar} date until today, 
//     * at the specified interval.
//     * <p>
//     * The latest quotes will be retrieved in a single request to Yahoo Finance.
//     * For the historical quotes, a separate request will be sent for each requested stock.
//     * The returned Map only includes the Stocks that could 
//     * successfully be retrieved from Yahoo Finance.
//     * 
//     * @param symbols               the symbols of the stocks for which you want to retrieve information
//     * @param from                  start date of the historical data
//     * @param interval              the interval of the included historical data
//     * @return                      a Map that links the symbols to their respective Stock objects.
//     * @throws java.io.IOException when there's a connection problem
//     */
//    public static Map<String, Stock> get(String[] symbols, Calendar from, Interval interval) throws IOException {
//        return YahooFinance.getQuotes(Utils.join(symbols, ","), from, HistQuotesRequest.DEFAULT_TO, interval);
//    }
//    
//    /**
//     * Sends a request for multiple stocks with the historical quotes included
//     * starting from the specified {@link Calendar} date 
//     * until the specified Calendar date (to) 
//     * at the default interval (monthly).
//     * <p>
//     * The latest quotes will be retrieved in a single request to Yahoo Finance.
//     * For the historical quotes, a separate request will be sent for each requested stock.
//     * The returned Map only includes the Stocks that could 
//     * successfully be retrieved from Yahoo Finance.
//     * 
//     * @param symbols               the symbols of the stocks for which you want to retrieve information
//     * @param from                  start date of the historical data
//     * @param to                    end date of the historical data
//     * @return                      a Map that links the symbols to their respective Stock objects.
//     * @throws java.io.IOException when there's a connection problem
//     */
//    public static Map<String, Stock> get(String[] symbols, Calendar from, Calendar to) throws IOException {
//        return YahooFinance.getQuotes(Utils.join(symbols, ","), from, to, HistQuotesRequest.DEFAULT_INTERVAL);
//    }
//    
//    /**
//     * Sends a request for multiple stocks with the historical quotes included
//     * starting from the specified {@link Calendar} date 
//     * until the specified Calendar date (to) 
//     * at the specified interval.
//     * <p>
//     * The latest quotes will be retrieved in a single request to Yahoo Finance.
//     * For the historical quotes, a separate request will be sent for each requested stock.
//     * The returned Map only includes the Stocks that could 
//     * successfully be retrieved from Yahoo Finance.
//     * 
//     * @param symbols               the symbols of the stocks for which you want to retrieve information
//     * @param from                  start date of the historical data
//     * @param to                    end date of the historical data
//     * @param interval              the interval of the included historical data
//     * @return                      a Map that links the symbols to their respective Stock objects.
//     * @throws java.io.IOException when there's a connection problem
//     */
//    public static Map<String, Stock> get(String[] symbols, Calendar from, Calendar to, Interval interval) throws IOException {
//        return YahooFinance.getQuotes(Utils.join(symbols, ","), from, to, interval);
//    }
    
    private static Map<String, Stock> getQuotes(String query, boolean includeHistorical) throws IOException {
        Map<String, Stock> result = new HashMap<String, Stock>();
        if(YahooFinance.QUOTES_QUERY1V7_ENABLED.equalsIgnoreCase("true")) {
            StockQuotesQuery1V7Request request = new StockQuotesQuery1V7Request(query);
            List<Stock> stocks = request.getResult();
            for(Stock stock : stocks) {
                result.put(stock.getSymbol(), stock);
            }
        } else {
            StockQuotesRequest request = new StockQuotesRequest(query);
            List<StockQuotesData> quotes = request.getResult();
            for(StockQuotesData data : quotes) {
                Stock s = data.getStock();
                result.put(s.getSymbol(), s);
            }
        }

        if(includeHistorical) {
            for(Stock s : result.values()) {
                s.getHistory();
            }
        }

        return result;
    }
    
    private static Map<String, Stock> getQuotes(String query, Calendar from, Calendar to, Interval interval) throws IOException {
        Map<String, Stock> stocks = YahooFinance.getQuotes(query, false);
        stocks = YahooFinance.fetchHistoricalQuotes(stocks, from, to, interval);
        return stocks;
    }
    
    private static Map<String, Stock> fetchHistoricalQuotes(Map<String, Stock> stocks, Calendar from, Calendar to, Interval interval) throws IOException {
        for(Stock s : stocks.values()) {
            s.getHistory(from, to, interval);
        }
        return stocks;
    }
}
