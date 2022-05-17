package fr.isep.userservice.infrastructure.adatpter_repository_db.DAO;

import fr.isep.userservice.domain.model.enums.ApplicationStatusEnum;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDao {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String applicationId;

    @Enumerated(value = EnumType.STRING)
    private ApplicationStatusEnum status;
    private String review_comment;

    private String object;
    private String motivations;
    private String resume;
    private String contact;

    @ManyToOne(targetEntity = UserDao.class)
    private UserDao user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ApplicationDao that = (ApplicationDao) o;
        return applicationId != null && Objects.equals(applicationId, that.applicationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
