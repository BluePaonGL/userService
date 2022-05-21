package fr.isep.userservice.domain.criteria;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class UserCriteria {
    private String username;
    private Integer pageNumber;
    private Integer pageSize;
}
