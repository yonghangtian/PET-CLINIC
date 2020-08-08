package tian.springframework.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author tianyh
 * created by tianyh on 6/10/19 7:35 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "specialties")
public class Speciality extends BaseEntity {

    @Builder
    public Speciality(Long id, String description) {
        super(id);
        this.description = description;
    }

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Speciality)) {
            return false;
        }
        Speciality that = (Speciality) o;
        return Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription());
    }
}
