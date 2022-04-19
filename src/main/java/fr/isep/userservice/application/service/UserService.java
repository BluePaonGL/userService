package fr.isep.userservice.application.service;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.domain.model.User;
import fr.isep.userservice.application.port.UserServicePort;
import fr.isep.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserServicePort {

    private final UserRepository userRepository;
    private final Keycloak keycloak;
    private final ModelMapper modelMapper;

    @Value("${keycloak.realm}")
    private String REALM;

    @Override
    public User saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        CredentialRepresentation password = preparePasswordRepresentation(userDto.getPassword());
        UserRepresentation userRepresentation = prepareUserRepresentation(user, password);

        Response response = keycloak
                .realm(REALM)
                .users()
                .create(userRepresentation);

        try {
            String location = (String) response.getHeaders().get("Location").get(0);
            String keycloakId = location.split("users/")[1];
        } catch (NullPointerException e) {
            log.info("L'utilisateur existe déjà");
        }


        return user;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        //TODO
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    private CredentialRepresentation preparePasswordRepresentation(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        return credentialRepresentation;
    }

    private UserRepresentation prepareUserRepresentation(User user, CredentialRepresentation password) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        List<CredentialRepresentation> credentials = new ArrayList<>();
        credentials.add(password);

        userRepresentation.setCredentials(credentials);
        userRepresentation.setEnabled(true);
        return userRepresentation;
    }
}
