package tian.springframework.petclinic.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tian.springframework.petclinic.model.*;
import tian.springframework.petclinic.services.*;

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
    private final SpecialityService specialityService;
    private final VisitService visitService;
    private final PriceService priceService;
    private final PetService petService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService, PriceService priceService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
        this.priceService = priceService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = this.petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = this.petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

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
        owner1.setCity("CN");
        owner1.setTelephone("756346534");

        Pet fionas = new Pet();
        fionas.setName("justCat");
        fionas.setOwner(owner2);
        fionas.setBirthDate(LocalDate.now());
        fionas.setPetType(savedCatPetType);
        owner2.getPets().add(fionas);
        this.ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Price samPrice = new Price();
        samPrice.setHourlyPay(500);
        samPrice.setCurrency("HKD");
        Price savedSamPrice = this.priceService.save(samPrice);

        Price jesPrice = new Price();
        jesPrice.setHourlyPay(1000);
        jesPrice.setCurrency("HKD");
        Price savedJesPrice = this.priceService.save(jesPrice);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);
        vet1.setPrice(savedSamPrice);
        Vet savedVet1 = this.vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);
        vet2.getSpecialities().add(savedDentistry);
        vet2.setPrice(savedJesPrice);
        Vet savedVet2 = this.vetService.save(vet2);

        Visit dogVisit = new Visit();
        dogVisit.setPet(mikesPet);
        dogVisit.setVet(savedVet1);
        dogVisit.setDate(LocalDate.now());
        dogVisit.setDuration(2);
        dogVisit.setDescription("Dog visit");
        this.visitService.save(dogVisit);

        Visit catVisit = new Visit();
        catVisit.setPet(fionas);
        catVisit.setVet(savedVet2);
        catVisit.setDate(LocalDate.now());
        catVisit.setDuration(1);
        catVisit.setDescription("Sneezy Kitty");
        this.visitService.save(catVisit);

        System.out.println("Loaded Vets....");
    }
}
