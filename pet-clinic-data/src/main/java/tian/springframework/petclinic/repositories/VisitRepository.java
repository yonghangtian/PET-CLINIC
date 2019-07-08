package tian.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tian.springframework.petclinic.model.Visit;

/**
 * created by tianyh on 7/8/19 1:18 PM
 */
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
