package fr.isep.userservice.application.port;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.domain.criteria.UserCriteria;
import fr.isep.userservice.domain.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserServicePort {
    User saveUser(UserDto userDto);
    User getUser(String username);
    User getUserById(String userId);

    List<User> getListOfUsersById(List<String> listOfId);
    Page<User> pageUsers(UserCriteria userCriteria);

    List<User> getUsers();
}
