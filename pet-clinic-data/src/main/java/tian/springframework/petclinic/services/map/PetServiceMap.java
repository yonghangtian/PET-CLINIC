package tian.springframework.petclinic.services.map;

import tian.springframework.petclinic.model.Pet;
import tian.springframework.petclinic.services.CrudService;

import java.util.Set;

/**
 * @author tianyh
 * created by tianyh on 6/5/19 2:56 PM
 */
public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {
    @Override
    public Set<Pet> findAll() {
        return super.findall();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
