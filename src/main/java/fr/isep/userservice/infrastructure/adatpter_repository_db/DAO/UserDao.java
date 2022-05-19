package fr.isep.userservice.infrastructure.adatpter_repository_db.DAO;

import fr.isep.userservice.domain.model.enums.LanguageEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="`user`")
public class UserDao {
    @Id
    private String userId;

    @Column(unique = true)
    private String username;
    private Integer student_id;

    @Column(unique = true)
    private String email;
    private String first_name;
    private String last_name;
    private String profile_picture;
    private String device_token;
    private LanguageEnum language;

    @OneToMany(targetEntity = fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.ApplicationDao.class)
    @ToString.Exclude
    private List<fr.isep.userservice.infrastructure.adatpter_repository_db.DAO.ApplicationDao> applications;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserDao user = (UserDao) o;
        return userId != null && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
