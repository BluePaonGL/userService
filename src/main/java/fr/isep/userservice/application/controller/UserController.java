package fr.isep.userservice.application.controller;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.domain.model.User;
import fr.isep.userservice.application.port.UserServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
@Validated
public class UserController {

    private final UserServicePort userServicePort;

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userServicePort.saveUser(userDto));
    }

}
