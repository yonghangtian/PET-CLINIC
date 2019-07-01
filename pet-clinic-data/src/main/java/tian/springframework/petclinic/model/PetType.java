package tian.springframework.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author tianyh
 * @since 5/28/19 5:37 PM
 */
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
    @Column(name = "name")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
