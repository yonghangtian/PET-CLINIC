package tian.springframework.petclinic.services.map;

import org.springframework.stereotype.Service;
import tian.springframework.petclinic.model.Speciality;
import tian.springframework.petclinic.services.SpecialityService;

import java.util.Set;

/**
 * @author tianyh
 * created by tianyh on 6/11/19 9:35 AM
 */
@Service
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {
    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
