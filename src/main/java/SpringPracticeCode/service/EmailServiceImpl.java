package SpringPracticeCode.service;

import SpringPracticeCode.dto.EmailWithAttachment;
import SpringPracticeCode.dto.EmailWithoutAttachment;
import SpringPracticeCode.exception.EmailSendingException;
import SpringPracticeCode.exception.InvalidAttachmentException;
import SpringPracticeCode.response.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import static SpringPracticeCode.constant.GeneralMsgConstant.MAX_FILE_SIZE;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public ApiResponse<EmailWithoutAttachment> sendEmail(EmailWithoutAttachment emailWithoutAttachment) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailWithoutAttachment.getTo());
        mailMessage.setSubject(emailWithoutAttachment.getSubject());
        mailMessage.setText(emailWithoutAttachment.getMessage());

        javaMailSender.send(mailMessage);

        return ApiResponse.<EmailWithoutAttachment> builder()
                .message("Email Sent Successfully")
                .status(HttpStatus.OK)
                .data(emailWithoutAttachment)
                .build();
    }

    @Override
    public void sendEmailWithAttachment(EmailWithAttachment emailWithAttachment, MultipartFile attachment) {
        validateAttachment(attachment);
        try{
            logger.info("Preparing email for recipient: {}", emailWithAttachment.getRecipient());
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(username);
            helper.setTo(emailWithAttachment.getRecipient());
            helper.setSubject(emailWithAttachment.getSubject());
            helper.setText(emailWithAttachment.getMessage());
            helper.addAttachment(attachment.getOriginalFilename(),attachment);

            javaMailSender.send(mimeMessage);

            logger.info("Email sent successfully to :{}", emailWithAttachment.getRecipient());

        } catch (MessagingException | RuntimeException ex) {
            logger.error("Failed to send email to :{}", emailWithAttachment.getRecipient(), ex);
            throw new EmailSendingException("Failed to send email", ex);
        }

    }

    private void validateAttachment(MultipartFile attachment){
        if(attachment == null || attachment.isEmpty()){
            throw new InvalidAttachmentException("Attachment is required");
        }
        if (attachment.getSize() > MAX_FILE_SIZE){
            throw new InvalidAttachmentException("Attachment size must not exceed 10 MB");
        }
        String fileName = attachment.getOriginalFilename();
        if(fileName == null || fileName.isBlank()){
            throw new InvalidAttachmentException("Attachment file name is invalid");
        }
    }

}
