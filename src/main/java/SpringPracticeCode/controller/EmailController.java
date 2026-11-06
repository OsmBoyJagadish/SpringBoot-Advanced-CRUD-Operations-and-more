package SpringPracticeCode.controller;

import SpringPracticeCode.dto.EmailWithoutAttachment;
import SpringPracticeCode.response.ApiResponse;
import SpringPracticeCode.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import SpringPracticeCode.dto.EmailWithAttachment;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<ApiResponse<EmailWithoutAttachment>> sendEmail(@RequestBody EmailWithoutAttachment emailWithoutAttachment){
        ApiResponse<EmailWithoutAttachment> response = emailService.sendEmail(emailWithoutAttachment);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/sendEmailWithAttachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<String>> sendEmailWithAttachment(@Valid @ModelAttribute EmailWithAttachment emailWithAttachment, @RequestParam("attachment") MultipartFile attachment) {
        emailService.sendEmailWithAttachment(emailWithAttachment, attachment);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Email sent successfully")
                .status(HttpStatus.OK)
                .data("Email sent to " + emailWithAttachment.getRecipient())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
