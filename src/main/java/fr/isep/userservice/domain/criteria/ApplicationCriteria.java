package fr.isep.userservice.domain.criteria;

import fr.isep.userservice.domain.model.enums.ApplicationStatusEnum;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApplicationCriteria {
    private ApplicationStatusEnum status;
    private String userId;
    private Integer pageNumber;
    private Integer pageSize;
}
