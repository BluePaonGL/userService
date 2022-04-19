package fr.isep.userservice.application.port;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.domain.model.User;

import java.util.List;

public interface UserServicePort {
    User saveUser(UserDto userDto);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);

    //Utiliser Page ici
    List<User> getUsers();
}
