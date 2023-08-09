package pokerface.pokerface.config.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private int authKey; // 인증 번호

    public String sendEmail(String toMail) {
        log.info("=====send Email Called=====");

        makeAuthNumber();

        String title = "POKER FACE의 회원가입을 위한 인증 메일입니다.";
        String text = new StringBuilder()
                .append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://localhost:8080/api/members/signUpConfirm?email=")
                .append(toMail)
                .append("&authKey=")
                .append(authKey)
                .append("' target='_blenk'>이메일 인증 확인</a>")
                .toString();

        log.debug("text : {}", text);

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return Integer.toString(authKey);
    }

    public void makeAuthNumber() {
        Random random = new Random();
        authKey = random.nextInt(888888) + 222222;;
    }
}
