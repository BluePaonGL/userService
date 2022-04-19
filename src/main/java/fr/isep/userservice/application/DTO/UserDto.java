package fr.isep.userservice.application.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Setter(value = AccessLevel.NONE)
public class UserDto implements Serializable {
    @NotNull
    @NotEmpty
    private final String username;
    private final String password;
    private final Integer student_id;

    @Email
    private final String email;
    private final String first_name;
    private final String last_name;
    private final String profile_picture;
    private final String device_token;
}
