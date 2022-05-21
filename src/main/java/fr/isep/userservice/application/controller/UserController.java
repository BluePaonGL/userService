package fr.isep.userservice.application.controller;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.application.DTO.UserWithoutPasswordDto;
import fr.isep.userservice.domain.criteria.UserCriteria;
import fr.isep.userservice.domain.model.User;
import fr.isep.userservice.application.port.UserServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

    private final UserServicePort userServicePort;
    private final ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userServicePort.saveUser(userDto));
    }

    @GetMapping()
    public ResponseEntity<Page<User>> pageUser(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) String username
    ) {
        UserCriteria userCriteria = UserCriteria.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .username(username)
                .build();

        return new ResponseEntity<>(this.userServicePort.pageUsers(userCriteria), HttpStatus.OK);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(this.userServicePort.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        return new ResponseEntity<>(this.userServicePort.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getListOfUserByListOfId(@RequestBody @NotNull List<String> idList) {
        return new ResponseEntity<>(this.userServicePort.getListOfUsersById(idList), HttpStatus.OK);
    }




}
