package SpringPracticeCode.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiResponse<T> {
    private String message;
    private HttpStatus status;
    private T data;

//    public static <T> ApiResponse<T> createResponseWithData(String message, T data) {
//        return ApiResponse.<T>builder()
//                .message(message)
//                .status(HttpStatus.OK)
//                .data(data)
//                .build();
//    }

}

