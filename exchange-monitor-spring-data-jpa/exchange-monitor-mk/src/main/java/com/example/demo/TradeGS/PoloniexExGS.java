package com.example.demo.TradeGS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoloniexExGS {
    private Double last;
    private Double lowestAsk;
    private Double highestBid;
    private Double percentChange;
    private Double baseVolume;
    private Double quoteVolume;
}
