package fr.isep.userservice.application.controller;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.domain.model.User;
import fr.isep.userservice.application.port.UserServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

    private final UserServicePort userServicePort;

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userServicePort.saveUser(userDto));
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(
            @PathVariable String userId
    ) {
        return new ResponseEntity<>(this.userServicePort.getUserById(userId), HttpStatus.OK);
    }

    @RolesAllowed("ADMIN")
    @GetMapping()
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(this.userServicePort.getUsers(), HttpStatus.OK);
    }




}
