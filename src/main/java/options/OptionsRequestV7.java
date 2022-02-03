/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package options;

import com.bubba.finance.test.YahooFinance;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Utils;
import yahoofinance.util.RedirectableRequest;

/**
 *
 * @author eiker
 */
public class OptionsRequestV7 {
    

    private static final Logger log = LoggerFactory.getLogger(OptionsRequestV7.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    private final String symbol;    
    private final Calendar date;
    
    public static final Calendar DEFAULT_DATE = Calendar.getInstance();
    
    public OptionsRequestV7(String symbol){
        this.symbol = symbol;
        this.date = null;
    }
    
    public OptionsRequestV7(String symbol, Calendar date){
        this.symbol = symbol;
        this.date = date;
    }
    
    public OptionChain getResult() throws IOException {
        String json = getJson();
        JsonNode resultNode = objectMapper.readTree(json).get("optionChain").get("result").get(0);
        JsonNode expirationDates = resultNode.get("expirationDates");
        JsonNode strikes = resultNode.get("strikes");
        JsonNode quote = resultNode.get("quote");
        JsonNode options = resultNode.get("options").get(0);
        JsonNode expirationDate = options.get("expirationDate");
        JsonNode hasMiniOptions = options.get("hasMiniOptions");
        JsonNode calls = options.get("calls");
        JsonNode puts = options.get("puts");
        JsonNode error = resultNode.get("error");
        
        List<Calendar> expDates = new ArrayList<>();
        for(int i = 0; i < expirationDates.size(); i++){
            Calendar expiration = Calendar.getInstance();
            long expirationL = expirationDates.get(i).asLong();
            expiration.setTimeInMillis(expirationL * 1000);
            expDates.add(expiration);
        }
        
        List<BigDecimal> strikesL = new ArrayList<>();
        for(int i = 0; i < strikes.size(); i++){
            strikesL.add(strikes.get(i).decimalValue());
        }
        
        OptionChain result = new OptionChain(
                symbol, 
                expDates, 
                strikesL, 
                hasMiniOptions.asBoolean(),
                quote.toString(),
                getOptions(calls), 
                getOptions(puts),
                error!=null?error.toString():""
        );
        
        return result;
    }
    
    private List<StockOption> getOptions(JsonNode node){
        List<StockOption> result = new ArrayList<>();
        for (int i = 0; i < node.size(); i++){
            String contractSymbol = node.get(i).get("contractSymbol").toString();
            BigDecimal strike = node.get(i).get("strike").decimalValue();
            String currency = node.get(i).get("currency").toString();
            BigDecimal lastPrice = node.get(i).get("lastPrice").decimalValue();
            BigDecimal change = node.get(i).get("change").decimalValue();
            BigDecimal percentChange = node.get(i).get("percentChange").decimalValue();
            long volume = node.get(i).get("volume").asLong();
            long openInterest = node.get(i).get("openInterest").asLong();
            BigDecimal bid = node.get(i).get("bid").decimalValue();
            BigDecimal ask = node.get(i).get("ask").decimalValue();
            String contractSize = node.get(i).get("contractSize").toString();
            Calendar expiration = Calendar.getInstance();
            long expirationL = node.get(i).get("expiration").asLong();
            expiration.setTimeInMillis(expirationL * 1000);
            Calendar lastTradeDate = Calendar.getInstance();
            long lastTradeDateL = node.get(i).get("lastTradeDate").asLong();
            lastTradeDate.setTimeInMillis(lastTradeDateL * 1000);
            BigDecimal impliedVolatility = node.get(i).get("impliedVolatility").decimalValue();
            boolean inTheMoney = node.get(i).get("inTheMoney").booleanValue();
            
            StockOption option = new StockOption(
                symbol,
                contractSymbol,
                strike,
                currency,
                lastPrice,
                change,
                percentChange,
                volume,
                openInterest,
                bid,
                ask,
                contractSize,
                expiration,
                lastTradeDate,
                impliedVolatility,
                inTheMoney);
            result.add(option);
        }
        return result;
    }
    
    public String getJson() throws IOException{
//         if(this.from.after(this.to)) {
//            log.warn("Unable to retrieve historical quotes. "
//                    + "From-date should not be after to-date. From: "
//                    + this.from.getTime() + ", to: " + this.to.getTime());
//            return "";
//        }

        Map<String, String> params = new LinkedHashMap<>();
       // params.put("date", String.valueOf(this.date.getTimeInMillis() / 1000));

        params.put("date", "1610064000");
       
        String url = YahooFinance.OPTIONS_QUERY1V7_BASE_URL + URLEncoder.encode(this.symbol , "UTF-8");
        if(this.date != null)
            url += "?" + Utils.getURLParameters(params);
        System.out.println(url);

        // Get CSV from Yahoo
        log.info("Sending request: " + url);

        URL request = new URL(url);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        URLConnection connection = redirectableRequest.openConnection();

        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(is);
        StringBuilder builder = new StringBuilder();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(line);
        }
        return builder.toString();
    }
}
