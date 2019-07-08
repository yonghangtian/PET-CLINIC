package tian.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tian.springframework.petclinic.model.Vet;

/**
 * created by tianyh on 7/8/19 1:18 PM
 */
public interface VetRepository extends CrudRepository<Vet, Long> {
}
