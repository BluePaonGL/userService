package fr.isep.userservice.application.DTO;

import fr.isep.userservice.domain.model.enums.LanguageEnum;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Setter(value = AccessLevel.NONE)
public class UserDto implements Serializable {
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    private Integer studentId;

    @Email
    @NotNull
    @NotEmpty
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String deviceToken;
    private LanguageEnum language;
}
