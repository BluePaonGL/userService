package fr.isep.userservice.application.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Setter(value = AccessLevel.NONE)
public class ApplicationDto implements Serializable {
    @NotNull
    @NotEmpty
    private final String object;
    @NotNull
    @NotEmpty
    private final String motivations;
    @NotNull
    @NotEmpty
    private final String resume;
    @NotNull
    @NotEmpty
    private final String contact;


}
