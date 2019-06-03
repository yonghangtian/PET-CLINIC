package tian.springframework.petclinic.services;

import tian.springframework.petclinic.model.Pet;

import java.util.Set;

/**
 * @author tianyh
 * @since 6/3/19 10:17 PM
 */
public interface PerService {
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
