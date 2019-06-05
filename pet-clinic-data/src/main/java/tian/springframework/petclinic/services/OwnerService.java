package tian.springframework.petclinic.services;

import tian.springframework.petclinic.model.Owner;

/**
 * @author tianyh
 * @since 6/3/19 10:15 PM
 */
public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
