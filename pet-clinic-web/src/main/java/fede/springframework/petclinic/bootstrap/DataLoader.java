package fede.springframework.petclinic.bootstrap;

import fede.springframework.petclinic.model.Owner;
import fede.springframework.petclinic.model.Pet;
import fede.springframework.petclinic.model.PetType;
import fede.springframework.petclinic.model.Vet;
import fede.springframework.petclinic.services.OwnerService;
import fede.springframework.petclinic.services.PetTypeService;
import fede.springframework.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Mister");
        owner1.setLastName("T");
        owner1.setAddress("Fake Street 123");
        owner1.setCity("New York");
        owner1.setTelephone("221-456-6549");

        Pet tiger = new Pet();
        tiger.setPetType(savedDogPetType);
        tiger.setOwner(owner1);
        tiger.setBirthdate(LocalDate.now());
        tiger.setName("Tiger");
        owner1.getPets().add(tiger);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("John");
        owner2.setLastName("Smith");
        owner2.setAddress("Hello Street 23123");
        owner2.setCity("Dubai");
        owner2.setTelephone("999-8899");

        Pet wolf = new Pet();
        wolf.setPetType(savedDogPetType);
        wolf.setOwner(owner2);
        wolf.setBirthdate(LocalDate.now());
        wolf.setName("Wolf");
        owner2.getPets().add(wolf);

        ownerService.save(owner2);



        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("First");
        vet1.setLastName("Vet");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Second");
        vet2.setLastName("Veterinary");
        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
