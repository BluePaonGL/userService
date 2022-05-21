package fr.isep.userservice.domain.service;

import fr.isep.userservice.domain.model.Application;
import fr.isep.userservice.domain.model.enums.ApplicationStatusEnum;
import fr.isep.userservice.infrastructure.adatpter_repository_db.adapter.ApplicationRepositoryAdapter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApplicationServiceTest {

    @Mock
    private ApplicationRepositoryAdapter applicationRepositoryAdapter;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void setMockOutput() {
        when(applicationRepositoryAdapter.findById(not(eq("123")))).thenThrow(IllegalArgumentException.class);
        when(applicationRepositoryAdapter.findById("123")).thenReturn(this.getValidApplication());
    }

    protected Application getValidApplication() {
        return Application.builder()
                .applicationId("123")
                .status(ApplicationStatusEnum.SUBMITTED)
                .object("test")
                .motivations("test")
                .build();
    }

    @Test
    void should_return_valid_application_when_provided_valid_id() {
        assertEquals(this.applicationService.getApplicationById("123"), getValidApplication());
    }

    @Test
    void should_throw_NoSuchElementException_when_provided_with_invalid_id() {
        assertThrows(IllegalArgumentException.class,() -> {
            this.applicationService.getApplicationById("234");
        });
    }
}