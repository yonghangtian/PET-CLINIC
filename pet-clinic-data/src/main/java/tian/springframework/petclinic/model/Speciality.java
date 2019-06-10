package tian.springframework.petclinic.model;

/**
 * @author tianyh
 * created by tianyh on 6/10/19 7:35 PM
 */
public class Speciality extends BaseEntity {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
