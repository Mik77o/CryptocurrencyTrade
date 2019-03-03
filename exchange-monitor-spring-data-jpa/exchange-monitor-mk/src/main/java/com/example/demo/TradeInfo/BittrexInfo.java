package com.example.demo.TradeInfo;

import com.example.demo.CryptocurrencyInfo.CryptocurrencyTrade;
import org.json.JSONObject;
import org.apache.log4j.Logger;

import static com.example.demo.GetJson.JsonGetter.getJSON;

public class BittrexInfo implements CryptocurrencyTrade {
    final static Logger logger = Logger.getLogger(BittrexInfo.class);

    @Override
    public Double cryptocurrencyInfo() {
        String dataBittrex = null;
        try {
            dataBittrex = getJSON("https://bittrex.com/api/v1.1/public/getticker?market=btc-eth");
        } catch (Exception e) {
            logger.error("Getting JSON data (Bittrex) failed: " + e);
        }
        JSONObject objB = new JSONObject(dataBittrex);
        Double lastPrice = objB.getJSONObject("result").getDouble("Last");
        return lastPrice;
    }
}
