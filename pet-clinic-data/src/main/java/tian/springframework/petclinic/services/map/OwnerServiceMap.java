package tian.springframework.petclinic.services.map;

import tian.springframework.petclinic.model.Owner;
import tian.springframework.petclinic.services.OwnerService;

import java.util.Set;

/**
 * @author tianyh
 * created by tianyh on 6/5/19 2:35 PM
 */
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Set<Owner> findAll() {
        return super.findall();
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
