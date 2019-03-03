package com.example.demo.TradeInfo;

import com.example.demo.CryptocurrencyInfo.CryptocurrencyTrade;
import com.example.demo.TradeGS.BitbayGS;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import static com.example.demo.GetJson.JsonGetter.getJSON;

public class BitbayInfo implements CryptocurrencyTrade {
    final static Logger logger = Logger.getLogger(BitbayInfo.class);

    @Override
    public Double cryptocurrencyInfo() {
        String data = null;
        try {
            data = getJSON("https://bitbay.net/API/Public/ETHBTC/ticker.json");
        } catch (Exception e) {
            logger.error("Getting JSON data (Bitbay) failed: " + e);
        }
        Gson gson = new Gson();
        BitbayGS bbTC = gson.fromJson(data, BitbayGS.class);
        return bbTC.getLast();
    }
}
