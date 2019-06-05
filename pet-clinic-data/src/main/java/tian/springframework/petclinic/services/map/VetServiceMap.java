package tian.springframework.petclinic.services.map;

import tian.springframework.petclinic.model.Vet;
import tian.springframework.petclinic.services.CrudService;

import java.util.Set;

/**
 * @author tianyh
 * created by tianyh on 6/5/19 2:56 PM
 */
public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {
    @Override
    public Set<Vet> findAll() {
        return super.findall();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
