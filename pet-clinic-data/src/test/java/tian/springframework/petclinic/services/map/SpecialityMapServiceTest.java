package tian.springframework.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tian.springframework.petclinic.model.Speciality;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * created by tianyh on 7/11/19 3:26 PM
 */
class SpecialityMapServiceTest {
    SpecialityMapService specialityMapService;

    final Long specialityId = 2L;
    final String specialityDescription = "this is a speciality";

    @BeforeEach
    public void setUp() {
        specialityMapService = new SpecialityMapService();

        specialityMapService.save(Speciality.builder().id(specialityId).description(specialityDescription).build());
    }

    @Test
    void findAll() {
        Set<Speciality> specialitys = specialityMapService.findAll();

        assertEquals(1, specialitys.size());
    }

    @Test
    void findById() {
        Speciality speciality = specialityMapService.findById(specialityId);

        assertEquals(specialityId, speciality.getId());
    }

    @Test
    void saveSpecialityWithId() {
        Long id = 4L;
        Speciality savedSpeciality = specialityMapService.save(Speciality.builder().id(id).build());

        assertEquals(id, savedSpeciality.getId());
    }

    @Test
    void saveSpecialityWithOutId() {
        Speciality savedSpeciality = specialityMapService.save(Speciality.builder().build());

        assertNotNull(savedSpeciality);
        assertNotNull(savedSpeciality.getId());
    }

    @Test
    void delete() {
        specialityMapService.delete(specialityMapService.findById(specialityId));

        assertEquals(0, specialityMapService.findAll().size());
    }

    @Test
    void deleteById() {
        specialityMapService.deleteById(specialityId);

        assertEquals(0, specialityMapService.findAll().size());
    }
}