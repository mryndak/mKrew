package pl.mkrew.app.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Async("fixedThreadPool")
    @SneakyThrows // zamienia wyjatek exception na runtime
    public void sendEmail(String recipient, String subject, String message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(recipient);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(message);
        javaMailSender.send(mimeMessage);
    }
}
