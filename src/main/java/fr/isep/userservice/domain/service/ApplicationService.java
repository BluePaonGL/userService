package fr.isep.userservice.domain.service;

import fr.isep.userservice.application.DTO.ApplicationDto;
import fr.isep.userservice.application.port.ApplicationServicePort;
import fr.isep.userservice.domain.criteria.ApplicationCriteria;
import fr.isep.userservice.domain.model.Application;
import fr.isep.userservice.domain.port.ApplicationRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService implements ApplicationServicePort {

    private final ApplicationRepositoryPort applicationRepositoryPort;
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
