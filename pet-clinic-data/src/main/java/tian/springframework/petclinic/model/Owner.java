package tian.springframework.petclinic.model;

import java.util.Set;

/**
 * @author tianyh
 * @since 5/28/19 5:36 PM
 */
public class Owner extends Person {
    private Set<Pet> pets;

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
