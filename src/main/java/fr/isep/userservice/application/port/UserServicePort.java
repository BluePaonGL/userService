package fr.isep.userservice.application.port;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.domain.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserServicePort {
    User saveUser(UserDto userDto);
    User getUser(String username);


    Page<User> getUsers();
}