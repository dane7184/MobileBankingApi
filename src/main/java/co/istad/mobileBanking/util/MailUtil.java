package co.istad.mobileBanking.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class MailUtil {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Meta<T>{
        private String to;
        private String from;
        private String subject;
        private String templateUrl;
        private T data;
    }

    public void send(Meta<?> meta) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        //TODO : 1. prepare template
        Context context = new Context();
        context.setVariable("data", meta.data);
        String htmlTemplate = templateEngine.process(meta.templateUrl,context);
        helper.setText(htmlTemplate,true);

        //TODO : 2 prepare email information
        helper.setTo(meta.to);
        helper.setFrom(meta.from);
        helper.setSubject(meta.subject);


        //TODO : 3 Send email

        javaMailSender.send(mimeMessage);

    }
}
