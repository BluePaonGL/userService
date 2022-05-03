package fr.isep.userservice.domain.model;

import fr.isep.userservice.domain.model.enums.ApplicationStatusEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long applicationId;

    private String status;
    private String object;
    private String motivations;
    private String resume;
    private String contact;

    @ManyToOne(targetEntity = User.class)
    private User user;
}
