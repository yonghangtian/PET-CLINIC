package tian.springframework.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tian.springframework.petclinic.model.Speciality;
import tian.springframework.petclinic.model.Vet;
import tian.springframework.petclinic.services.SpecialityService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * created by tianyh on 7/11/19 3:26 PM
 */
class VetMapServiceTest {

    @Mock
    SpecialityService specialityService;

    @InjectMocks
    VetMapService vetMapService;

    final Long id = 3L;

    @BeforeEach
    public void setUp() {
        vetMapService = new VetMapService(specialityService);
        Vet vet = Vet.builder().id(id).build();
        vetMapService.save(vet);
    }

    @Test
    void findAll() {
        Set<Vet> vets = vetMapService.findAll();

        assertEquals(1, vets.size());
    }

    @Test
    void findById() {
        Vet vet = vetMapService.findById(id);

        assertEquals(id, vet.getId());
    }

    @Test
    void saveVetWithId() {
        Long newId = 5L;
        Vet savedVet = vetMapService.save(Vet.builder().id(newId).build());

        assertEquals(newId, savedVet.getId());
    }

    @Test
    void saveVetWithOutId() {
        Vet savedVet = vetMapService.save(Vet.builder().build());

        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
    }

/*    have some problem doing this.
* */
    @Test
    void saveVetWithSpecialities() {
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(Speciality.builder().id(2L).description("cat").build());

        Vet savedVet = vetMapService.save(Vet.builder().specialities(specialities).build());

        assertEquals(specialities, savedVet.getSpecialities());
    }

    @Test
    void delete() {
        vetMapService.delete(vetMapService.findById(id));

        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void deleteById() {
        vetMapService.deleteById(id);

        assertEquals(0, vetMapService.findAll().size());
    }
}