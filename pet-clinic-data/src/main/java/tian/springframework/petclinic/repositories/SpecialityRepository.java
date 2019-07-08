package tian.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import tian.springframework.petclinic.model.Speciality;

/**
 * created by tianyh on 7/8/19 1:18 PM
 */
public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
