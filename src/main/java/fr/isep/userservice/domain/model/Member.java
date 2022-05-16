package fr.isep.userservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Member {
    private String memberId;
    private String profile_picture;
    private LocalDate entry_date;
    private String description;
}
