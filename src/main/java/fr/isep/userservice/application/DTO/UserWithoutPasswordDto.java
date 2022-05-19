package fr.isep.userservice.application.DTO;

import fr.isep.userservice.domain.model.enums.LanguageEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
public class UserWithoutPasswordDto implements Serializable {
    @NotNull
    @NotEmpty
    private String username;
    private Integer student_id;
    private String keycloak_id;

    @Email
    @NotNull
    @NotEmpty
    private String email;
    private String first_name;
    private String last_name;
    private String profile_picture;
    private String device_token;
    private LanguageEnum language;
}
