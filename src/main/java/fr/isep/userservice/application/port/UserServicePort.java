package fr.isep.userservice.application.port;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.domain.model.User;

import java.util.List;

public interface UserServicePort {
    fr.isep.userservice.domain.model.User saveUser(UserDto userDto);
    fr.isep.userservice.domain.model.User getUser(String username);


    List<User> getUsers();
}
