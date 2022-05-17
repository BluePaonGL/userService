package fr.isep.userservice.domain.model;

import fr.isep.userservice.domain.model.enums.ApplicationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    private String applicationId;
    private ApplicationStatusEnum status;
    private String object;
    private String motivations;
    private String resume;
    private String contact;

    private User user;
}
