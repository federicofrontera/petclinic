package fede.springframework.petclinic.bootstrap;

import fede.springframework.petclinic.model.Owner;
import fede.springframework.petclinic.model.Vet;
import fede.springframework.petclinic.services.OwnerService;
import fede.springframework.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Juan");
        owner1.setLastName("Perez");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("John");
        owner2.setLastName("Smith");

        ownerService.save(owner2);


        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Veterinario");
        vet1.setLastName("Primero");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Segundo");
        vet2.setLastName("Veterinario");

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
