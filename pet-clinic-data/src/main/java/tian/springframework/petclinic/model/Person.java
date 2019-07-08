package tian.springframework.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author tianyh
 * @since 5/28/19 4:28 PM
 */
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
