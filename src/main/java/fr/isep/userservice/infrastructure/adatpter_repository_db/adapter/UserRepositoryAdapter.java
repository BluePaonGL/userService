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
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User findById(String userId) {
        UserDao userDaoOptional = this.userRepository.findByUserId(userId);
        try {
            return modelMapper.map(userDaoOptional, User.class);
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
        UserDao userDao = modelMapper.map(user, UserDao.class);
        return modelMapper.map(this.userRepository.save(userDao), User.class);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> getListOfUsersById(List<String> listOfId) {
        List<UserDao> userDaoList = this.userRepository.findUserDaoByUserIdIn(listOfId);
        return userDaoList.stream()
                .map(userDao -> this.modelMapper.map(userDao, User.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String userId) {

    }
}
