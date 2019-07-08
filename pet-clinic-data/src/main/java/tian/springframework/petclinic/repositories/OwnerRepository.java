package tian.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tian.springframework.petclinic.model.Owner;

/**
 * created by tianyh on 7/8/19 1:15 PM
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
