package tian.springframework.petclinic.services;

import tian.springframework.petclinic.model.Owner;

import java.util.Set;

/**
 * @author tianyh
 * @since 6/3/19 10:15 PM
 */
public interface OwnerService {

    Owner findByLastName(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
