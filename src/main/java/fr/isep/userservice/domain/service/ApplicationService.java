package fr.isep.userservice.domain.service;

import fr.isep.userservice.application.DTO.ApplicationDto;
import fr.isep.userservice.application.port.ApplicationServicePort;
import fr.isep.userservice.domain.criteria.ApplicationCriteria;
import fr.isep.userservice.domain.model.Application;
import fr.isep.userservice.domain.model.User;
import fr.isep.userservice.domain.port.ApplicationRepositoryPort;
import fr.isep.userservice.domain.port.UserRepositoryPort;
import fr.isep.userservice.infrastructure.adatpter_repository_db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.IDToken;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService implements ApplicationServicePort {

    private final ApplicationRepositoryPort applicationRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public Application getApplicationById(String applicationId) {
        return this.applicationRepositoryPort.findById(applicationId);
    }

    @Override
    public Page<Application> pageApplication(ApplicationCriteria criteria) {
        return this.applicationRepositoryPort.pageApplication(criteria);
    }

    @Override
    public Application createApplication(ApplicationDto applicationDTO) {
        Application application = this.modelMapper.map(applicationDTO, Application.class);
        KeycloakPrincipal userDetails = (KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = userDetails.getKeycloakSecurityContext().getToken().getSubject();
        User user = this.userRepositoryPort.findById(id);
        application.setUser(user);
        return this.applicationRepositoryPort.saveApplication(application);
    }

    @Override
    public Application changeApplicationStatus(String applicationId, Application application) {
        return this.applicationRepositoryPort.updateApplication(applicationId, application);
    }

    @Override
    public Application postApplicationReview(String applicationId, Application application) {
        return this.applicationRepositoryPort.updateApplication(applicationId, application);
    }
}
