package SpringPracticeCode.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    @Schema(description = "User full name", example = "Jagadish Pannala")
    private String name;

    @Schema(description = "User email address", example = "jagadish@gmail.com")
    private String email;

    @Schema(description = "User city name", example = "Mumbai")
    private String city;

    @Schema(description = "User Password", example = "Jagadish@123")
    private String password;

}