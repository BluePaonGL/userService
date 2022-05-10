package fr.isep.userservice.infrastructure.adatpter_repository_db.DAO;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String memberId;

    private String profile_picture;
    private LocalDate entry_date;

    //TODO Externaliser les traductions
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberDao member = (MemberDao) o;
        return memberId != null && Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
