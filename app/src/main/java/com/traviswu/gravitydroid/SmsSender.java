package com.traviswu.gravitydroid;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public final class SmsSender {
    /* Find your sid and token at twilio.com/user/account */
    public static final String ACCOUNT_SID = "AC185aa7d7f03dad5aa8c1029838be1126";
    public static final String AUTH_TOKEN = "b04cb2fc29d5c63a7432581b0617a3b6";
    public static void send(String x) throws TwilioRestException {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account account = client.getAccount();
        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", "+6139856607")); // Replace with a valid phone number for your account.
        params.add(new BasicNameValuePair("From", "+16137779973")); // Replace with a valid phone number for your account.
        params.add(new BasicNameValuePair("Body", "Where's Wallace?"));
        Message sms = messageFactory.create(params);

    }
}