package com.example.demo.TradeInfo;

import com.example.demo.CryptocurrencyInfo.CryptocurrencyTrade;
import org.json.JSONObject;
import org.apache.log4j.Logger;

import static com.example.demo.GetJson.JsonGetter.getJSON;

public class WexInfo implements CryptocurrencyTrade {
    final static Logger logger = Logger.getLogger(WexInfo.class);

    @Override
    public Double cryptocurrencyInfo() {
        String daneWex = null;
        try {
            daneWex = getJSON("https://wex.nz/api/3/ticker/eth_btc");
        } catch (Exception e) {
            logger.error("Getting JSON data (Wex) failed: " + e);
        }
        JSONObject objB = new JSONObject(daneWex);
        Double lastPrice = objB.getJSONObject("eth_btc").getDouble("last");

        return lastPrice;
    }
}

