package fr.isep.userservice.infrastructure.adatpter_repository_db.repository;

import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.ApplicationDao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationRepository extends PagingAndSortingRepository<ApplicationDao, Long>, JpaSpecificationExecutor<ApplicationDao> {


}
