package fr.isep.userservice.application.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Setter(value = AccessLevel.NONE)
public class ApplicationDto implements Serializable {
    @NotNull
    @NotEmpty
    private String object;
    @NotNull
    @NotEmpty
    private String motivations;
    @NotNull
    @NotEmpty
    private String resume;
    @NotNull
    @NotEmpty
    private String contact;


}
