package tian.springframework.petclinic.services.map;

import org.springframework.stereotype.Service;
import tian.springframework.petclinic.model.PetType;
import tian.springframework.petclinic.services.PetTypeService;

import java.util.Set;

/**
 * @author tianyh
 * created by tianyh on 6/10/19 8:01 PM
 */
@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findall();
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
