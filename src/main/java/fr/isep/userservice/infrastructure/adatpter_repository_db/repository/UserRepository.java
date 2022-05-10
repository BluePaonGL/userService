package fr.isep.userservice.infrastructure.adatpter_repository_db.repository;

import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDao, Long> {
    UserDao findByUsername(String username);
}
