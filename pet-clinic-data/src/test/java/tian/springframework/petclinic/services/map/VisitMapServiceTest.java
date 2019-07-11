package tian.springframework.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tian.springframework.petclinic.model.Owner;
import tian.springframework.petclinic.model.Pet;
import tian.springframework.petclinic.model.Visit;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * created by tianyh on 7/11/19 3:26 PM
 */
class VisitMapServiceTest {

    VisitMapService visitMapService;

    final Long visitId = 2L;
    final LocalDate date = LocalDate.now();
    final Owner owner = Owner.builder().id(visitId).build();
    final Pet cat = Pet.builder().id(visitId).owner(owner).build();

    @BeforeEach
    public void setUp() {
        visitMapService = new VisitMapService();

        visitMapService.save(Visit.builder().id(visitId).date(date).pet(cat).build());
    }

    @Test
    void findAll() {
        Set<Visit> visits = visitMapService.findAll();

        assertEquals(1, visits.size());
    }

    @Test
    void findById() {
        Visit visit = visitMapService.findById(visitId);

        assertEquals(visitId, visit.getId());
    }

    @Test
    void saveNormalVisit() {
        Long id = 4L;
        Visit savedVisit = visitMapService.save(Visit.builder().id(id).date(date).pet(cat).build());

        assertEquals(id, savedVisit.getId());
    }

    @Test
    void saveLostInfoVisit() {
        try{
            Long id = 4L;
            Visit savedVisit = visitMapService.save(Visit.builder().id(id).build());
        }catch (Exception ex) {
            assertEquals("Invalid Visit", ex.getMessage());
        }
    }
    @Test
    void delete() {
        visitMapService.delete(visitMapService.findById(visitId));

        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void deleteById() {
        visitMapService.deleteById(visitId);

        assertEquals(0, visitMapService.findAll().size());
    }
}