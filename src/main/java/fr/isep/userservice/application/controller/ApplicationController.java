package fr.isep.userservice.application.controller;

import fr.isep.userservice.application.DTO.ApplicationDto;
import fr.isep.userservice.application.port.ApplicationServicePort;
import fr.isep.userservice.domain.criteria.ApplicationCriteria;
import fr.isep.userservice.domain.model.Application;
import fr.isep.userservice.domain.model.enums.ApplicationStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
@Slf4j
@Validated
public class ApplicationController {

    private final ApplicationServicePort applicationServicePort;

    @PostMapping()
    public ResponseEntity<Page<Application>> pageApplicationByUser(
            @RequestParam(required = false) ApplicationStatusEnum status,
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

    @GetMapping("/{applicationId}")
    public ResponseEntity<Application> getApplicationById(@PathVariable String applicationId) {
        return new ResponseEntity<>(this.applicationServicePort.getApplicationById(applicationId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Application> createApplication(@Valid @RequestBody ApplicationDto createApplicationDTO) {
        return new ResponseEntity<>(this.applicationServicePort.createApplication(createApplicationDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/status")
    public ResponseEntity<Application> changeApplicationStatus() {
        return null;
    }

    @PatchMapping("/review")
    public ResponseEntity<Application> postApplicationReview() {
        return null;
    }
}
