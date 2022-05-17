package fr.isep.userservice.domain.port;

import fr.isep.userservice.domain.criteria.ApplicationCriteria;
import fr.isep.userservice.domain.model.Application;
import org.springframework.data.domain.Page;

public interface ApplicationRepositoryPort {
    Application findById(String applicationId);
    Page<Application> pageApplication(ApplicationCriteria applicationCriteria);

    Application saveApplication(Application application);

    Application updateApplication(String applicationId, Application application);
}
