package fr.isep.userservice.infrastructure.adatpter_repository_db.helper;

import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.UserDao;
import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.UserDao_;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


@NoArgsConstructor
public class UserRepositorySpecification {
    public static Specification<UserDao> usernameEquals(String username) {
        return (root, query, criteriaBuilder) -> {
            if(username != null) {
                return criteriaBuilder.equal(root.get(UserDao_.USERNAME), username);
            } else {
                return criteriaBuilder.conjunction();
            }

        };
    }
}
