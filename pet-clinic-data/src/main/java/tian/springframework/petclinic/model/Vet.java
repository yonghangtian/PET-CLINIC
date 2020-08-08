package tian.springframework.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author tianyh
 * @since 5/28/19 5:36 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @Builder
    public Vet(Long id, String firstName, String lastName, Price price, Set<Speciality> specialities) {
        super(id, firstName, lastName);
        this.price = price;
        this.specialities = specialities;
    }

    @OneToOne
    @JoinColumn(name = "price_id")
    Price price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vet)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Vet vet = (Vet) o;
        return Objects.equals(getPrice(), vet.getPrice()) &&
                Objects.equals(getSpecialities(), vet.getSpecialities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPrice(), getSpecialities());
    }
}
