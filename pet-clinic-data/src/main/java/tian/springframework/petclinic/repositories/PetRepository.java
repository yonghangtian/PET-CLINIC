package tian.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tian.springframework.petclinic.model.Pet;

/**
 * created by tianyh on 7/8/19 1:16 PM
 */
public interface PetRepository extends CrudRepository<Pet, Long> {
}
