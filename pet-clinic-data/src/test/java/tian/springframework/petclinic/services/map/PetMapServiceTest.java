package tian.springframework.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tian.springframework.petclinic.model.Pet;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * created by tianyh on 7/11/19 3:25 PM
 */
class PetMapServiceTest {

    PetMapService petMapService;

    final Long petId = 2L;
    final String petName = "mimi";

    @BeforeEach
    public void setUp() {
        petMapService = new PetMapService();

        petMapService.save(Pet.builder().id(petId).name(petName).build());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();

        assertEquals(1,pets.size());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);

        assertEquals(petId, pet.getId());
    }

    @Test
    void savePetWithId() {
        Long id = 4L;
        Pet savedPet = petMapService.save(Pet.builder().id(id).build());

        assertEquals(id, savedPet.getId());
    }

    @Test
    void savePetWithOutId() {
        Pet savedPet = petMapService.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(petId));

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);

        assertEquals(0, petMapService.findAll().size());
    }
}