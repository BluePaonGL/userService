package fr.isep.userservice.domain.model;

import fr.isep.userservice.domain.model.enums.LanguageEnum;
import lombok.Data;

@Data
public class User {
    private String userId;
    private String password;
    private String username;
    private Integer student_id;
    private String email;
    private String first_name;
    private String last_name;
    private String profile_picture;
    private String device_token;
    private LanguageEnum language;
}
