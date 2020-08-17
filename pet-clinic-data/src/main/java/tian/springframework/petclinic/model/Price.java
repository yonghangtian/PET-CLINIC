package tian.springframework.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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
    public Price(Long id, int hourlyPay, String currency) {
        super(id);
        this.hourlyPay = hourlyPay;
        this.currency = currency;
    }

    @Column(name = "hourlyPay")
    private int hourlyPay;

    @Column(name = "currency")
    private String currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Price)) {
            return false;
        }
        Price price = (Price) o;
        return getHourlyPay() == price.getHourlyPay() &&
                Objects.equals(getCurrency(), price.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHourlyPay(), getCurrency());
    }
}
