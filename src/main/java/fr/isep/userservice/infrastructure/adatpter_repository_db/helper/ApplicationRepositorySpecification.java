package fr.isep.userservice.infrastructure.adatpter_repository_db.helper;

import fr.isep.userservice.domain.model.enums.ApplicationStatusEnum;
import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.ApplicationDao;
import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.ApplicationDao_;
import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.UserDao;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ApplicationRepositorySpecification {

    private ApplicationRepositorySpecification() {

    }

    public static Specification<ApplicationDao> userIdEquals(String userId) {
        return (root, query, criteriaBuilder) -> {
            if(userId != null) {
                Join<ApplicationDao, UserDao> userDaoJoin = root.join("user");
                return criteriaBuilder.equal(userDaoJoin.<String> get("userId"), userId);
            } else {
                return criteriaBuilder.conjunction();
            }

        };
    }

    public static Specification<ApplicationDao> statusEquals(ApplicationStatusEnum status) {

        return (root, query, criteriaBuilder) -> {
            if(status != null) {
                return criteriaBuilder.equal(root.get(ApplicationDao_.STATUS), status);
            } else {
                return criteriaBuilder.conjunction();
            }

        };
    }
}
