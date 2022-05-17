package fr.isep.userservice.infrastructure.adatpter_repository_db.adapter;

import fr.isep.userservice.domain.criteria.ApplicationCriteria;
import fr.isep.userservice.domain.model.Application;
import fr.isep.userservice.domain.port.ApplicationRepositoryPort;
import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.ApplicationDao;
import fr.isep.userservice.infrastructure.adatpter_repository_db.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static fr.isep.userservice.infrastructure.adatpter_repository_db.helper.ApplicationRepositorySpecification.statusEquals;
import static fr.isep.userservice.infrastructure.adatpter_repository_db.helper.ApplicationRepositorySpecification.userIdEquals;
import static org.springframework.data.jpa.domain.Specification.where;

@Component
@AllArgsConstructor
public class ApplicationRepositoryAdapter implements ApplicationRepositoryPort {

    private ApplicationRepository applicationRepository;
    private ModelMapper modelMapper;

    @Override
    public Application findById(String applicationId) {
        return modelMapper.map(this.applicationRepository.findByApplicationId(applicationId), Application.class);
    }

    @Override
    public Page<Application> pageApplication(ApplicationCriteria applicationCriteria) {
        Pageable paging = PageRequest.of(applicationCriteria.getPageNumber(), applicationCriteria.getPageSize());
        Specification<ApplicationDao> specification = where(userIdEquals(applicationCriteria.getUserId()))
                .and(statusEquals(applicationCriteria.getStatus()));
        Page<ApplicationDao> applicationDaoPage = this.applicationRepository.findAll(specification, paging);
        return applicationDaoPage.map(applicationDao -> modelMapper.map(applicationDao, Application.class));
    }

    @Override
    public Application saveApplication(Application application) {
        ApplicationDao applicationDao = modelMapper.map(application, ApplicationDao.class);
        return modelMapper.map(this.applicationRepository.save(applicationDao), Application.class);
    }

    @Override
    public Application updateApplication(String applicationId, Application application) {
        return null;
    }
}
