package fr.isep.userservice.domain.port;

import fr.isep.userservice.domain.criteria.UserCriteria;
import fr.isep.userservice.domain.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserRepositoryPort {
    User findById(String userId);
    User findByUsername(String username);
    Page<User> pageUser(UserCriteria userCriteria);
    User save(User user);
    List<User> findAll();

    void delete(String userId);
}
