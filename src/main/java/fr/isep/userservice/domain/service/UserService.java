package fr.isep.userservice.domain.service;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.domain.model.User;
import fr.isep.userservice.domain.port.UserRepositoryPort;
import fr.isep.userservice.application.port.UserServicePort;
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
@Slf4j
public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;
    private final Keycloak keycloak;
    private final ModelMapper modelMapper;

    @Value("${keycloak.realm}")
    private String REALM;

    @Override
    public fr.isep.userservice.domain.model.User saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        CredentialRepresentation password = preparePasswordRepresentation(userDto.getPassword());
        UserRepresentation userRepresentation = prepareUserRepresentation(user, password);


        try (Response response = keycloak
                .realm(REALM)
                .users()
                .create(userRepresentation)) {
            String location = (String) response.getHeaders().get("Location").get(0);
            String keycloakId = location.split("users/")[1];

            user.setKeycloak_id(keycloakId);
            this.userRepositoryPort.save(user);
        } catch (NullPointerException e) {
            log.info("This user already exist in Keycloak");
        }
        return user;
    }

    @Override
    public User getUser(String username) {
        return userRepositoryPort.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return this.userRepositoryPort.findAll();
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
