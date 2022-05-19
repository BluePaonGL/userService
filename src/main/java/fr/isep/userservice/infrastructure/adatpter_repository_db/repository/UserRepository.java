package fr.isep.userservice.infrastructure.adatpter_repository_db.repository;

import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDao, Long> {
    UserDao findByUsername(String username);

    UserDao findByUserId(String userId);

    List<UserDao> findUserDaoByUserIdIn(List<String> listOfId);
}
