package tian.springframework.petclinic.model;

import java.util.Set;

/**
 * @author tianyh
 * @since 5/28/19 5:36 PM
 */
public class Vet extends Person {

    private Set<Speciality> specialities;

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
