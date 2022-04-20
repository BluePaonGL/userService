package fr.isep.userservice.domain.model;

import fr.isep.userservice.domain.model.enums.LanguageEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name="`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String keycloak_id;

    private String username;
    private Integer student_id;
    private String email;
    private String first_name;
    private String last_name;
    private String profile_picture;
    private String device_token;
    private LanguageEnum language;

    @OneToMany(targetEntity = Application.class)
    private List<Application> applications;
}
