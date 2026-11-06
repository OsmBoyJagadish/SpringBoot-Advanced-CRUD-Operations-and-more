package SpringPracticeCode.service;

import SpringPracticeCode.dto.EmailWithAttachment;
import SpringPracticeCode.dto.EmailWithoutAttachment;
import SpringPracticeCode.response.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

    public ApiResponse<EmailWithoutAttachment> sendEmail(EmailWithoutAttachment emailEntity);
    public void sendEmailWithAttachment(EmailWithAttachment emailWithAttachment, MultipartFile attachment);

}
