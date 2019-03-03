package com.example.demo.Message;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MessageSender {

    public void sendMessage(String mess, String nameOfTrade) {
        final String ACCOUNT_SID = "ACd72e7ee5454f031ce3e88d4369ada950";
        final String AUTH_TOKEN = "3351a7e5e2e5166e3a77caeb007c044b";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("+48537780840"),
                new PhoneNumber("+48732483436"), nameOfTrade + "! " + mess).create();
    }
}
