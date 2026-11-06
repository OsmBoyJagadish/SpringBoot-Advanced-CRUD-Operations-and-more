package SpringPracticeCode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailWithoutAttachment {
    private String to;
    private String subject;
    private String message;
}
