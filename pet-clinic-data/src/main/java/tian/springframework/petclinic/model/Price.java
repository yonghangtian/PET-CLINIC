package tian.springframework.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author tian
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prices")
public class Price extends BaseEntity{
    @Builder
    public Price(Long id, int price) {
        super(id);
        this.price = price;
    }

    @Column(name = "price")
    private int price;

}
