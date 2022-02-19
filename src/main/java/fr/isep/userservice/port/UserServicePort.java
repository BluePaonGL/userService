package fr.isep.userservice.port;

import fr.isep.userservice.domain.Role;
import fr.isep.userservice.domain.User;

import java.util.List;

public interface UserServicePort {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);

    //Utiliser Page ici
    List<User> getUsers();
}
