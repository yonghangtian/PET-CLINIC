package tian.springframework.petclinic.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tian.springframework.petclinic.model.Owner;
import tian.springframework.petclinic.model.Pet;
import tian.springframework.petclinic.model.PetType;
import tian.springframework.petclinic.model.Vet;
import tian.springframework.petclinic.services.OwnerService;
import tian.springframework.petclinic.services.PetTypeService;
import tian.springframework.petclinic.services.VetService;

import java.time.LocalDate;

/**
 * @author tianyh
 * created by tianyh on 6/9/19 9:19 PM
 */
@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = this.petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = this.petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("124 Waterloo Road");
        owner1.setCity("HK");
        owner1.setTelephone("1213123123");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        this.ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner1.setAddress("244 King Road");
        owner1.setCity("BJ");
        owner1.setTelephone("756346534");

        Pet fionas = new Pet();
        fionas.setName("justCat");
        fionas.setOwner(owner2);
        fionas.setBirthDate(LocalDate.now());
        fionas.setPetType(savedCatPetType);
        owner2.getPets().add(fionas);
        this.ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        this.vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        this.vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
