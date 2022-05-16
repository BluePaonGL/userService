package fr.isep.userservice.application.port;

import fr.isep.userservice.domain.model.Application;

import java.util.List;

public interface ApplicationServicePort {
    Application getApplicationById();
    List<Application> getApplicationByUserId();
}
