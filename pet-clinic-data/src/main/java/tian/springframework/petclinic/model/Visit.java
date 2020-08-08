package tian.springframework.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author tianyh
 * created by tianyh on 6/10/19 5:57 PM
 * Each visit is limitted to 1 hour.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Builder
    public Visit(Long id, LocalDate date, String description, Pet pet, Vet vet) {
        super(id);
        this.date = date;
        this.description = description;
        this.pet = pet;
        this.vet = vet;
    }

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "duration")
    private int duration;

    @Column(name = "amount")
    private long amount;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    public long getAmount() {
        this.amount = (long) this.duration * this.vet.getPrice().getHourlyPay();

        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Visit)) {
            return false;
        }
        Visit visit = (Visit) o;
        return getDuration() == visit.getDuration() &&
                Objects.equals(getDate(), visit.getDate()) &&
                Objects.equals(getDescription(), visit.getDescription()) &&
                Objects.equals(getPet(), visit.getPet()) &&
                Objects.equals(getVet(), visit.getVet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getDuration(), getDescription(), getPet(), getVet());
    }
}
