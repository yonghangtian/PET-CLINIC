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
 * @since 5/28/19 5:37 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PetType)) {
            return false;
        }
        PetType petType = (PetType) o;
        return Objects.equals(getName(), petType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
