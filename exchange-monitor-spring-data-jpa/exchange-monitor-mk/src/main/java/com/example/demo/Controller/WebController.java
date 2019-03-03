package com.example.demo.Controller;

import com.example.demo.CryptocurrencyInfo.CryptocurrencyTrade;
import com.example.demo.Model.*;
import com.example.demo.Repo.*;
import com.example.demo.TradeInfo.*;
import com.example.demo.Message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@RestController
public class WebController {
    @Autowired
    TradeRepositoryBitbay repositoryBitbay;
    @Autowired
    TradeRepositoryBitfinex repositoryBitfinex;
    @Autowired
    TradeRepositoryBittrex repositoryBittrex;
    @Autowired
    TradeRepositoryPoloniex repositoryPoloniex;
    @Autowired
    TradeRepositoryWex repositoryWex;

    @RequestMapping("/save")
    public String saveToDatabase(@RequestParam(defaultValue = "all") String nameOfExchange) {
        switch (nameOfExchange) {
            case "bitbay":
                CryptocurrencyTrade bitbayInfo = new BitbayInfo();
                Bitbay lastValueBitbay = repositoryBitbay.findTopByOrderByIdDesc();
                if (lastValueBitbay == null) {
                    Double data = bitbayInfo.cryptocurrencyInfo();
                    repositoryBitbay.save(new Bitbay(data, LocalDateTime.now(), 0.0));
                } else {
                    Double data = bitbayInfo.cryptocurrencyInfo();
                    repositoryBitbay.save(new Bitbay(data, LocalDateTime.now(), differenceEthBtc(lastValueBitbay.getExchangeRate(), data)));
                }
                break;
            case "bitfinex":
                CryptocurrencyTrade bitfinexInfo = new BitfinexInfo();
                Bitfinex lastValueBitfinex;
                lastValueBitfinex = repositoryBitfinex.findTopByOrderByIdDesc();
                if (lastValueBitfinex == null) {
                    Double data = bitfinexInfo.cryptocurrencyInfo();
                    repositoryBitfinex.save(new Bitfinex(data, LocalDateTime.now(), 0.0));
                } else {
                    Double data = bitfinexInfo.cryptocurrencyInfo();
                    repositoryBitfinex.save(new Bitfinex(data, LocalDateTime.now(), differenceEthBtc(lastValueBitfinex.getExchangeRate(), data)));
                }
                break;
            case "bittrex":
                CryptocurrencyTrade bittrexInfo = new BittrexInfo();
                Bittrex lastValueBittrex;
                lastValueBittrex = repositoryBittrex.findTopByOrderByIdDesc();
                if (lastValueBittrex == null) {
                    Double data = bittrexInfo.cryptocurrencyInfo();
                    repositoryBittrex.save(new Bittrex(data, LocalDateTime.now(), 0.0));
                } else {
                    Double data = bittrexInfo.cryptocurrencyInfo();
                    repositoryBittrex.save(new Bittrex(data, LocalDateTime.now(), differenceEthBtc(lastValueBittrex.getExchangeRate(), data)));
                }
                break;
            case "poloniex":
                CryptocurrencyTrade poloniexInfo = new PoloniexExInfo();
                Poloniex lastValuePoloniex;
                lastValuePoloniex = repositoryPoloniex.findTopByOrderByIdDesc();
                if (lastValuePoloniex == null) {
                    Double data = poloniexInfo.cryptocurrencyInfo();
                    repositoryPoloniex.save(new Poloniex(data, LocalDateTime.now(), 0.0));
                } else {
                    Double data = poloniexInfo.cryptocurrencyInfo();
                    repositoryPoloniex.save(new Poloniex(data, LocalDateTime.now(), differenceEthBtc(lastValuePoloniex.getExchangeRate(), data)));
                }
                break;
            case "wex":
                CryptocurrencyTrade wexInfo = new WexInfo();
                Wex lastValueWex;
                lastValueWex = repositoryWex.findTopByOrderByIdDesc();
                if (lastValueWex == null) {
                    Double data = wexInfo.cryptocurrencyInfo();
                    repositoryWex.save(new Wex(data, LocalDateTime.now(), 0.0));
                } else {
                    Double data = wexInfo.cryptocurrencyInfo();
                    repositoryWex.save(new Wex(data, LocalDateTime.now(), differenceEthBtc(lastValueWex.getExchangeRate(), data)));
                }
                break;
            case "all":
                CryptocurrencyTrade bitbayIn = new BitbayInfo();
                Bitbay lastValueBitb = repositoryBitbay.findTopByOrderByIdDesc();
                if (lastValueBitb == null) {
                    Double data = bitbayIn.cryptocurrencyInfo();
                    repositoryBitbay.save(new Bitbay(data, LocalDateTime.now(), 0.0));
                } else {
                    Double data = bitbayIn.cryptocurrencyInfo();
                    repositoryBitbay.save(new Bitbay(data, LocalDateTime.now(), differenceEthBtc(lastValueBitb.getExchangeRate(), data)));
                }

                CryptocurrencyTrade bitfinexIn = new BitfinexInfo();
                Bitfinex lastValueBitf;
                lastValueBitf = repositoryBitfinex.findTopByOrderByIdDesc();
                if (lastValueBitf == null) {
                    Double data1 = bitfinexIn.cryptocurrencyInfo();
                    repositoryBitfinex.save(new Bitfinex(data1, LocalDateTime.now(), 0.0));
                } else {
                    Double data1 = bitfinexIn.cryptocurrencyInfo();
                    repositoryBitfinex.save(new Bitfinex(data1, LocalDateTime.now(), differenceEthBtc(lastValueBitf.getExchangeRate(), data1)));
                }

                CryptocurrencyTrade bittrexIn = new BittrexInfo();
                Bittrex lastValueBittr;
                lastValueBittr = repositoryBittrex.findTopByOrderByIdDesc();
                if (lastValueBittr == null) {
                    Double data2 = bittrexIn.cryptocurrencyInfo();
                    repositoryBittrex.save(new Bittrex(data2, LocalDateTime.now(), 0.0));
                } else {
                    Double data2 = bittrexIn.cryptocurrencyInfo();
                    repositoryBittrex.save(new Bittrex(data2, LocalDateTime.now(), differenceEthBtc(lastValueBittr.getExchangeRate(), data2)));
                }
                CryptocurrencyTrade poloniexIn = new PoloniexExInfo();
                Poloniex lastValuePolon;
                lastValuePolon = repositoryPoloniex.findTopByOrderByIdDesc();
                if (lastValuePolon == null) {
                    Double data3 = poloniexIn.cryptocurrencyInfo();
                    repositoryPoloniex.save(new Poloniex(data3, LocalDateTime.now(), 0.0));
                } else {
                    Double data3 = poloniexIn.cryptocurrencyInfo();
                    repositoryPoloniex.save(new Poloniex(data3, LocalDateTime.now(), differenceEthBtc(lastValuePolon.getExchangeRate(), data3)));
                }
                CryptocurrencyTrade wexIn = new WexInfo();
                Wex lastValueWx;
                lastValueWx = repositoryWex.findTopByOrderByIdDesc();
                if (lastValueWx == null) {
                    Double data4 = wexIn.cryptocurrencyInfo();
                    repositoryWex.save(new Wex(data4, LocalDateTime.now(), 0.0));
                } else {
                    Double data4 = wexIn.cryptocurrencyInfo();
                    repositoryWex.save(new Wex(data4, LocalDateTime.now(), differenceEthBtc(lastValueWx.getExchangeRate(), data4)));
                }
                break;
            default:
                return "Wrong parameter or other error";
        }
        return "Done";
    }

    //@Scheduled(fixedRate = 10000)
    @RequestMapping("/saveStillAll")
    public void saveStillAll() throws InterruptedException {
        CryptocurrencyTrade bitbayIn = new BitbayInfo();
        CryptocurrencyTrade bitfinexIn = new BitfinexInfo();
        CryptocurrencyTrade bittrexIn = new BittrexInfo();
        CryptocurrencyTrade poloniexIn = new PoloniexExInfo();
        CryptocurrencyTrade wexIn = new WexInfo();

        while (true) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                System.out.println("got interrupted!");
            }
            //Thread.sleep(10000);

            Bitbay lastValueBitb = repositoryBitbay.findTopByOrderByIdDesc();
            if (lastValueBitb == null) {
                Double data = bitbayIn.cryptocurrencyInfo();
                repositoryBitbay.save(new Bitbay(data, LocalDateTime.now(), 0.0));
            } else {
                Double data = bitbayIn.cryptocurrencyInfo();
                Double diffBitbay = differenceEthBtc(lastValueBitb.getExchangeRate(), data);
                repositoryBitbay.save(new Bitbay(data, LocalDateTime.now(), diffBitbay));
                if (abs(diffBitbay) > 0.2) {
                    MessageSender messSend = new MessageSender();
                    messSend.sendMessage(lastValueBitb.getExchangeRate() + ",  " + lastValueBitb.getDate() + ",Difference:  " + diffBitbay, "BITBAY");
                }
            }
            Bitfinex lastValueBitf;
            lastValueBitf = repositoryBitfinex.findTopByOrderByIdDesc();
            if (lastValueBitf == null) {
                Double data1 = bitfinexIn.cryptocurrencyInfo();
                repositoryBitfinex.save(new Bitfinex(data1, LocalDateTime.now(), 0.0));
            } else {
                Double data1 = bitfinexIn.cryptocurrencyInfo();
                Double diffBitfinex = differenceEthBtc(lastValueBitf.getExchangeRate(), data1);
                repositoryBitfinex.save(new Bitfinex(data1, LocalDateTime.now(), diffBitfinex));
                if (abs(diffBitfinex) > 0.2) {
                    MessageSender messSend1 = new MessageSender();
                    messSend1.sendMessage(lastValueBitf.getExchangeRate() + ", " + lastValueBitf.getDate() + ",Difference:  " + diffBitfinex, "BITFINEX");
                }
            }
            Bittrex lastValueBittr;
            lastValueBittr = repositoryBittrex.findTopByOrderByIdDesc();
            if (lastValueBittr == null) {
                Double data2 = bittrexIn.cryptocurrencyInfo();
                repositoryBittrex.save(new Bittrex(data2, LocalDateTime.now(), 0.0));
            } else {
                Double data2 = bittrexIn.cryptocurrencyInfo();
                Double diffBittrex = differenceEthBtc(lastValueBittr.getExchangeRate(), data2);
                repositoryBittrex.save(new Bittrex(data2, LocalDateTime.now(), diffBittrex));
                if (abs(diffBittrex) > 0.2) {
                    MessageSender messSend2 = new MessageSender();
                    messSend2.sendMessage(lastValueBittr.getExchangeRate() + ", " + lastValueBittr.getDate() + ",Difference:  " + diffBittrex, "BITTREX");
                }
            }

            Poloniex lastValuePolon;
            lastValuePolon = repositoryPoloniex.findTopByOrderByIdDesc();
            if (lastValuePolon == null) {
                Double data3 = poloniexIn.cryptocurrencyInfo();
                repositoryPoloniex.save(new Poloniex(data3, LocalDateTime.now(), 0.0));
            } else {
                Double data3 = poloniexIn.cryptocurrencyInfo();
                Double diffPoloniex = differenceEthBtc(lastValuePolon.getExchangeRate(), data3);
                repositoryPoloniex.save(new Poloniex(data3, LocalDateTime.now(), diffPoloniex));
                if (abs(diffPoloniex) > 0.2) {
                    MessageSender messSend3 = new MessageSender();
                    messSend3.sendMessage(lastValuePolon.getExchangeRate() + ", " + lastValuePolon.getDate() + ",Difference:  " + diffPoloniex, "POLONIEX");
                }
            }
            Wex lastValueWx;
            lastValueWx = repositoryWex.findTopByOrderByIdDesc();
            if (lastValueWx == null) {
                Double data4 = wexIn.cryptocurrencyInfo();
                repositoryWex.save(new Wex(data4, LocalDateTime.now(), 0.0));
            } else {
                Double data4 = wexIn.cryptocurrencyInfo();
                Double diffWex = differenceEthBtc(lastValueWx.getExchangeRate(), data4);
                repositoryWex.save(new Wex(data4, LocalDateTime.now(), diffWex));
                if (abs(diffWex) > 0.2) {
                    MessageSender messSend4 = new MessageSender();
                    messSend4.sendMessage(lastValueWx.getExchangeRate() + ", " + lastValueWx.getDate() + ",Difference:  " + diffWex, "WEX");
                }
            }
        }
    }

    @RequestMapping("/findall")
    public String findAll() {
        List<String> resultAll = new ArrayList<String>();
        for (Bitbay bitb : repositoryBitbay.findAll()) {
            resultAll.add(bitb.toString() + "<br>");
        }
        resultAll.add("<br>");
        for (Bitfinex bitf : repositoryBitfinex.findAll()) {
            resultAll.add(bitf.toString() + "<br>");
        }
        resultAll.add("<br>");
        for (Bittrex bittr : repositoryBittrex.findAll()) {
            resultAll.add(bittr.toString() + "<br>");
        }
        resultAll.add("<br>");
        for (Poloniex polx : repositoryPoloniex.findAll()) {
            resultAll.add(polx.toString() + "<br>");
        }
        resultAll.add("<br>");
        for (Wex wx : repositoryWex.findAll()) {
            resultAll.add(wx.toString() + "<br>");
        }
        return resultAll.toString();
    }

    @RequestMapping("/findallbitbay")
    public String findAllBitbay() {
        List<String> resultBitbay = new ArrayList<String>();
        for (Bitbay bitb : repositoryBitbay.findAll()) {
            resultBitbay.add(bitb.toString() + "<br>");
        }
        return resultBitbay.toString();
    }

    @RequestMapping("/findallbitfinex")
    public String findAllBitfinex() {
        List<String> resultBitfinex = new ArrayList<String>();
        for (Bitfinex bitf : repositoryBitfinex.findAll()) {
            resultBitfinex.add(bitf.toString() + "<br>");
        }
        return resultBitfinex.toString();
    }

    @RequestMapping("/findallpoloniex")
    public String findAllPoloniex() {
        List<String> resultPoloniex = new ArrayList<String>();
        for (Poloniex polx : repositoryPoloniex.findAll()) {
            resultPoloniex.add(polx.toString() + "<br>");
        }
        return resultPoloniex.toString();
    }

    @RequestMapping("/findallwex")
    public String findAllWex() {
        List<String> resultWex = new ArrayList<String>();
        for (Wex wx : repositoryWex.findAll()) {
            resultWex.add(wx.toString() + "<br>");
        }
        return resultWex.toString();
    }

    @RequestMapping("/findallbittrex")
    public String findAllBittrex() {
        List<String> resultBittrex = new ArrayList<String>();
        for (Bittrex bittr : repositoryBittrex.findAll()) {
            resultBittrex.add(bittr.toString() + "<br>");
        }
        return resultBittrex.toString();
    }

    @RequestMapping("/deleteAllRecords")
    public String deleteAll() {
        repositoryBitbay.deleteAll();
        repositoryBitfinex.deleteAll();
        repositoryBittrex.deleteAll();
        repositoryPoloniex.deleteAll();
        repositoryWex.deleteAll();
        return "All records deleted from all tables!";
    }

    @RequestMapping("/max")
    public String max() {
        Double valBitbay, valBitfinex, valBittrex, valPoloniex, valWex;
        int sizeBitbay, sizeBitfinex, sizeBittrex, sizePoloniex, sizeWex;
        List<LocalDateTime> testBitbay = new ArrayList();
        List<LocalDateTime> testBitfinex = new ArrayList();
        List<LocalDateTime> testBittrex = new ArrayList();
        List<LocalDateTime> testPoloniex = new ArrayList();
        List<LocalDateTime> testWex = new ArrayList();
        valBitbay = repositoryBitbay.getMaxValOfExchangeRate();
        testBitbay = repositoryBitbay.getDate();
        sizeBitbay = testBitbay.size();
        valBitfinex = repositoryBitfinex.getMaxValOfExchangeRate();
        testBitfinex = repositoryBitfinex.getDate();
        sizeBitfinex = testBitfinex.size();
        valBittrex = repositoryBittrex.getMaxValOfExchangeRate();
        testBittrex = repositoryBittrex.getDate();
        sizeBittrex = testBittrex.size();
        valPoloniex = repositoryPoloniex.getMaxValOfExchangeRate();
        testPoloniex = repositoryPoloniex.getDate();
        sizePoloniex = testPoloniex.size();
        valWex = repositoryWex.getMaxValOfExchangeRate();
        testWex = repositoryWex.getDate();
        sizeWex = testWex.size();
        Double max[] = {valBitbay, valBitfinex, valBittrex, valPoloniex, valWex};
        Double result = max[0];
        for (int i = 1; i < 5; i++) {
            if (result < max[i]) {
                result = max[i];
            }
        }
        String exchange = "";
        if (result == valBitbay) {
            exchange = "BitbayMaxValue: " + result.toString();
        } else if (result == valBitfinex) {
            exchange = "BitfinexMaxValue: " + result.toString();
        } else if (result == valBittrex) {
            exchange = "BittrexMaxValue: " + result.toString();
        } else if (result == valPoloniex) {
            exchange = "PoloniexMaxValue: " + result.toString();
        } else if (result == valBittrex) {
            exchange = "BittrexMaxValue: " + result.toString();
        } else if (result == valWex) {
            exchange = "WexMaxValue: " + result.toString();
        }
        return "Maximum value from Exchange:" + "<br>" + "BitbayETHBTC: " + valBitbay.toString()
                + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testBitbay.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testBitbay.get(sizeBitbay > 1 ? sizeBitbay - 1 : 0) + "</font>" + "<br>" +
                "BitfinexETHBTC: " + valBitfinex.toString() + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testBitfinex.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testBitfinex.get(sizeBitfinex > 1 ? sizeBitfinex - 1 : 0) + "</font>" + "<br>" + "BittrexETHBTC: " +
                valBittrex.toString() + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testBittrex.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testBittrex.get(sizeBittrex > 1 ? sizeBittrex - 1 : 0) + "</font>" + "<br>" +
                "PoloniexETHBTC: " + valPoloniex.toString() + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testPoloniex.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testPoloniex.get(sizePoloniex > 1 ? sizePoloniex - 1 : 0) + "</font>" + "<br>"
                + "WexETHBTC: " + valWex.toString() + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testWex.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testWex.get(sizeWex > 1 ? sizeWex - 1 : 0) + "</font>" + "<br>" + "<br>" + "<b>" + exchange + "</b>";
    }

    @RequestMapping("/min")
    public String min() {
        Double valBitbay, valBitfinex, valBittrex, valPoloniex, valWex;
        int sizeBitbay, sizeBitfinex, sizeBittrex, sizePoloniex, sizeWex;
        List<LocalDateTime> testBitbay = new ArrayList();
        List<LocalDateTime> testBitfinex = new ArrayList();
        List<LocalDateTime> testBittrex = new ArrayList();
        List<LocalDateTime> testPoloniex = new ArrayList();
        List<LocalDateTime> testWex = new ArrayList();
        valBitbay = repositoryBitbay.getMinValOfExchangeRate();
        testBitbay = repositoryBitbay.getDateMin();
        sizeBitbay = testBitbay.size();
        valBitfinex = repositoryBitfinex.getMinValOfExchangeRate();
        testBitfinex = repositoryBitfinex.getDateMin();
        sizeBitfinex = testBitfinex.size();
        valBittrex = repositoryBittrex.getMinValOfExchangeRate();
        testBittrex = repositoryBittrex.getDateMin();
        sizeBittrex = testBittrex.size();
        valPoloniex = repositoryPoloniex.getMinValOfExchangeRate();
        testPoloniex = repositoryPoloniex.getDateMin();
        sizePoloniex = testPoloniex.size();
        valWex = repositoryWex.getMinValOfExchangeRate();
        testWex = repositoryWex.getDateMin();
        sizeWex = testWex.size();
        Double max[] = {valBitbay, valBitfinex, valBittrex, valPoloniex, valWex};
        Double result = max[0];
        for (int i = 1; i < 5; i++) {
            if (result > max[i]) {
                result = max[i];
            }
        }
        String exchange = "";
        if (result == valBitbay) {
            exchange = "BitbayMinValue: " + result.toString();
        } else if (result == valBitfinex) {
            exchange = "BitfinexMinValue: " + result.toString();
        } else if (result == valBittrex) {
            exchange = "BittrexMinValue: " + result.toString();
        } else if (result == valPoloniex) {
            exchange = "PoloniexMinValue: " + result.toString();
        } else if (result == valBittrex) {
            exchange = "BittrexMinValue: " + result.toString();
        } else if (result == valWex) {
            exchange = "WexMinValue: " + result.toString();
        }
        return "Minimum value from Exchange:" + "<br>" + "BitbayETHBTC: " + valBitbay.toString()
                + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testBitbay.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testBitbay.get(sizeBitbay > 1 ? sizeBitbay - 1 : 0) + "</font>" + "<br>" +
                "BitfinexETHBTC: " + valBitfinex.toString() + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testBitfinex.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testBitfinex.get(sizeBitfinex > 1 ? sizeBitfinex - 1 : 0) + "</font>" + "<br>" + "BittrexETHBTC: " +
                valBittrex.toString() + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testBittrex.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testBittrex.get(sizeBittrex > 1 ? sizeBittrex - 1 : 0) + "</font>" + "<br>" +
                "PoloniexETHBTC: " + valPoloniex.toString() + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testPoloniex.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testPoloniex.get(sizePoloniex > 1 ? sizePoloniex - 1 : 0) + "</font>" + "<br>"
                + "WexETHBTC: " + valWex.toString() + "&nbsp&nbsp&nbsp" + "DateTime: " + "<font color=navy>" + testWex.get(0) + "</font>"
                + "&nbsp&nbsp&nbsp" + "<font color=red>" + testWex.get(sizeWex > 1 ? sizeWex - 1 : 0) + "</font>" + "<br>" + "<br>" + "<b>" + exchange + "</b>";
    }

    public double differenceEthBtc(double lastP, double nowP) {
        Double resultP;
        if (lastP > 0) {
            resultP = (lastP - nowP) / lastP * 100;
            return resultP;
        }
        return 0;
    }

}