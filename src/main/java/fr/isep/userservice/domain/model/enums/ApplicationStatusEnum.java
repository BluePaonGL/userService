package fr.isep.userservice.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApplicationStatusEnum {

    SUBMITTED("submitted"),
    IN_REVIEW("in_progress");

    @Getter
    private final String value;
}
