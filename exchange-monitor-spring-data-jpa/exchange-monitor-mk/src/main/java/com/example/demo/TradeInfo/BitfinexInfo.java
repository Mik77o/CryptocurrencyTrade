package com.example.demo.TradeInfo;

import com.example.demo.CryptocurrencyInfo.CryptocurrencyTrade;
import com.example.demo.TradeGS.BitfinexGS;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import static com.example.demo.GetJson.JsonGetter.getJSON;

public class BitfinexInfo implements CryptocurrencyTrade {
    final static Logger logger = Logger.getLogger(BitfinexInfo.class);

    @Override
    public Double cryptocurrencyInfo() {
        String data = null;
        try {
            data = getJSON("https://api.bitfinex.com/v1/pubticker/ethbtc");
        } catch (Exception e) {
            logger.error("Getting JSON data (Bitfinex) failed: " + e);
        }
        Gson gson = new Gson();
        BitfinexGS btfBTC = gson.fromJson(data, BitfinexGS.class);
        return btfBTC.getLast_price();
    }
}