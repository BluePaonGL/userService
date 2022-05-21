package fr.isep.userservice.domain.model;

import fr.isep.userservice.domain.model.enums.LanguageEnum;
import lombok.Data;

@Data
public class User {
    private String userId;
    private String password;
    private String username;
    private Integer studentId;
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String deviceToken;
    private LanguageEnum language;
}
