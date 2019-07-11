package tian.springframework.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tian.springframework.petclinic.model.PetType;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * created by tianyh on 7/11/19 3:25 PM
 */
class PetTypeMapServiceTest {
    PetTypeMapService petTypeMapService;

    final Long petTypeId = 2L;
    final String petTypeName = "cat";

    @BeforeEach
    public void setUp() {
        petTypeMapService = new PetTypeMapService();

        petTypeMapService.save(PetType.builder().id(petTypeId).name(petTypeName).build());
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeMapService.findAll();

        assertEquals(1,petTypes.size());
    }

    @Test
    void findById() {
        PetType petType = petTypeMapService.findById(petTypeId);

        assertEquals(petTypeId, petType.getId());
    }

    @Test
    void savePetWithId() {
        Long id = 4L;
        PetType savedPetType = petTypeMapService.save(PetType.builder().id(id).build());

        assertEquals(id, savedPetType.getId());
    }

    @Test
    void savePetWithOutId() {
        PetType savedPetType = petTypeMapService.save(PetType.builder().build());

        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
    }

    @Test
    void delete() {
        petTypeMapService.delete(petTypeMapService.findById(petTypeId));

        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(petTypeId);

        assertEquals(0, petTypeMapService.findAll().size());
    }
}