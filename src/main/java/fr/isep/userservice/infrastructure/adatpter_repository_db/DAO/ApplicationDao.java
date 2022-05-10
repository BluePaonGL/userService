package fr.isep.userservice.infrastructure.adatpter_repository_db.DAO;

import lombok.*;
import org.hibernate.Hibernate;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String applicationId;

    private String status;
    private String object;
    private String motivations;
    private String resume;
    private String contact;

    @ManyToOne(targetEntity = UserDao.class)
    private fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.UserDao user;

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
