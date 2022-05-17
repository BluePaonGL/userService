package fr.isep.userservice.application.port;

import fr.isep.userservice.domain.criteria.ApplicationCriteria;
import fr.isep.userservice.domain.model.Application;
import org.springframework.data.domain.Page;

public interface ApplicationServicePort {
    Application getApplicationById(String applicationId);
    Page<Application> pageApplication(ApplicationCriteria criteria);

    Application createApplication(Application application);

    Application changeApplicationStatus(String applicationId, Application application);
    Application postApplicationReview(String applicationId, Application application);
}
