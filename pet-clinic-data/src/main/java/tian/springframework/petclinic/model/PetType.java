package tian.springframework.petclinic.model;

/**
 * @author tianyh
 * @since 5/28/19 5:37 PM
 */
public class PetType extends BaseEntity {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
