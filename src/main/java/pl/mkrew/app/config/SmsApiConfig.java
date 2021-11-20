package pl.mkrew.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.exception.ClientException;
import pl.smsapi.proxy.ProxyNative;

@Configuration
public class SmsApiConfig {

    private static String oauthToken = "tutaj dodać token z smsapi";

    @Bean
    public SmsFactory createSmsFactory() throws ClientException {
        OAuthClient client = new OAuthClient(oauthToken);
        ProxyNative proxy = new ProxyNative("https://api.smsapi.pl/");
        return new SmsFactory(client, proxy);
    }
}
