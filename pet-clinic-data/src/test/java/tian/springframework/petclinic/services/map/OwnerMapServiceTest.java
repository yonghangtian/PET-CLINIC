package tian.springframework.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tian.springframework.petclinic.model.Owner;
import tian.springframework.petclinic.services.PetService;
import tian.springframework.petclinic.services.PetTypeService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * created by tianyh on 7/11/19 2:38 PM
 */
class OwnerMapServiceTest {

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    OwnerMapService ownerMapService;

    final Long ownerId = 2L;
    final String lastName = "tian";

    @BeforeEach
    public void setUp() {
        ownerMapService = new OwnerMapService(petService, petTypeService);

        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void finByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);

        assertEquals(lastName,owner.getLastName());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());

    }

    @Test
    void saveOwnerWithId() {
        Long id = 3L;

        Owner savedOwner = ownerMapService.save(Owner.builder().id(3L).build());

        assertEquals(id,savedOwner.getId());
    }

    @Test
    void saveOwnerWithOutId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }
}