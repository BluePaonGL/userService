package fr.isep.userservice.infrastructure.adatpter_repository_db.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.isep.userservice.domain.criteria.UserCriteria;
import fr.isep.userservice.domain.model.User;
import fr.isep.userservice.domain.port.UserRepositoryPort;
import fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.UserDao;
import fr.isep.userservice.infrastructure.adatpter_repository_db.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.exception.DataException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User findById(String userId) {
        Optional<UserDao> userDaoOptional = this.userRepository.findById(Long.valueOf(userId));
        try {
            return modelMapper.map(userDaoOptional.get(), User.class);
        } catch (NoSuchElementException exception) {
            throw new NoSuchElementException("This user does not exist in the database", exception);
        }
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Page<User> pageUser(UserCriteria userCriteria) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void delete(String userId) {

    }
}
