package tian.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tian.springframework.petclinic.model.PetType;

/**
 * created by tianyh on 7/8/19 1:17 PM
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
