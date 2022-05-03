package fr.isep.userservice.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LanguageEnum {
    FRENCH("fr"),
    ENGLISH("en");

    @Getter
    private final String value;
}
