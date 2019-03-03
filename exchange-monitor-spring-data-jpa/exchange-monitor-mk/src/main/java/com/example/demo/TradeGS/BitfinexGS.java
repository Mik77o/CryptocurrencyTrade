package com.example.demo.TradeGS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BitfinexGS {
    private String timestamp;
    private String volume;
    private Double last_price;
    private String high;
    private String mid;
    private String low;
    private String ask;
    private String bid;
}
