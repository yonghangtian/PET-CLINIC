package tian.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tian.springframework.petclinic.model.Price;

/**
 * @author tian
 */
public interface PriceRepository extends CrudRepository<Price, Long> {
}
