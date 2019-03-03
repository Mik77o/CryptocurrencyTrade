package com.example.demo.TradeInfo;

import com.cf.client.poloniex.PoloniexExchangeService;
import com.cf.data.model.poloniex.PoloniexTicker;
import com.example.demo.CryptocurrencyInfo.CryptocurrencyTrade;
import com.example.demo.TradeGS.PoloniexExGS;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

public class PoloniexExInfo implements CryptocurrencyTrade {
    final static Logger logger = Logger.getLogger(PoloniexExInfo.class);

    @Override
    public Double cryptocurrencyInfo() {
        String statsBTC = null;
        //Poloniex keys
        String apiKey = "foo";
        String apiSecret = "bar";
        PoloniexExchangeService serviceBTC = new PoloniexExchangeService(apiKey, apiSecret);
        PoloniexTicker btcethTicker = serviceBTC.returnTicker("BTC_ETH");
        try {
            statsBTC = btcethTicker.toString();
        } catch (Exception e) {
            logger.error("Getting Ticker (Poloniex) failed: " + e);
        }
        Gson gson = new Gson();
        PoloniexExGS polBTC = gson.fromJson(statsBTC, PoloniexExGS.class);
        return polBTC.getLast();
    }
}
