package fr.isep.userservice.application.controller;

import fr.isep.userservice.application.DTO.UserDto;
import fr.isep.userservice.application.port.ApplicationServicePort;
import fr.isep.userservice.domain.criteria.ApplicationCriteria;
import fr.isep.userservice.domain.model.Application;
import fr.isep.userservice.domain.model.User;
import fr.isep.userservice.application.port.UserServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    private final ApplicationServicePort applicationServicePort;

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userServicePort.saveUser(userDto));
    }

    @RolesAllowed("ADMIN")
    @GetMapping()
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(this.userServicePort.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/application")
    public ResponseEntity<Page<Application>> pageApplicationByUser(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String userId,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        ApplicationCriteria applicationCriteria = ApplicationCriteria.builder()
                .status(status)
                .userId(userId)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
        return new ResponseEntity<>(this.applicationServicePort.pageApplication(applicationCriteria),
        HttpStatus.OK);
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<Application> getApplicationById() {
        return null;
    }

    @PostMapping("/application")
    public ResponseEntity<Application> createApplication() {
        return null;
    }

    @PatchMapping("/application/status")
    public ResponseEntity<Application> changeApplicationStatus() {
        return null;
    }

    @PatchMapping("/application/review")
    public ResponseEntity<Application> postApplicationReview() {
        return null;
    }




}
