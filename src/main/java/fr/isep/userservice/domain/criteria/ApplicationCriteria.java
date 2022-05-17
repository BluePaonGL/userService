package fr.isep.userservice.domain.criteria;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApplicationCriteria {
    private String status;
    private String userId;
    private Integer pageNumber;
    private Integer pageSize;
}
