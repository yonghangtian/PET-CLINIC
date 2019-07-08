package tian.springframework.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author tianyh
 * created by tianyh on 6/10/19 7:35 PM
 */
@Entity
@Table(name = "specialties")
public class Speciality extends BaseEntity {

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
