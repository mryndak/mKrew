package pl.mkrew.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.UserEntity;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.exception.SmsapiException;

@Service
public class SmsSenderService {

    private final SmsFactory smsFactory;
    private UserEntity userEntity;

    @Autowired
    public SmsSenderService(SmsFactory smsFactory) {
        this.smsFactory = smsFactory;
    }

    public void sendSms(String message) {
        try {
            smsFactory.actionSend()
                    .setTo(userEntity.getPhoneNumber())
                    .setText(message)
                    .execute();
        } catch (SmsapiException e) {
            e.printStackTrace();
        }
    }
}
