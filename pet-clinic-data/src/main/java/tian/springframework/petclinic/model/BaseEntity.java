package tian.springframework.petclinic.model;

import java.io.Serializable;

/**
 * @author tianyh
 * @since 6/3/19 10:26 PM
 */
public class BaseEntity implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
