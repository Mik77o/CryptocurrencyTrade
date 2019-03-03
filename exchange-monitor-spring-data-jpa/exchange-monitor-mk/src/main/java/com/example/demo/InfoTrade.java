package com.example.demo;

import com.github.artfultom.wexapi.WexClient;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
//@EnableScheduling
public class InfoTrade {

    final static Logger logger = Logger.getLogger(InfoTrade.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(InfoTrade.class, args);

        WexClient client = new WexClient("https://wex.nz");
        try {
            client.check();
        } catch (IOException e) {
            logger.error("Problem to connect (Wex) : " + e);
        }
    }
}
