package services.map;

import fede.springframework.petclinic.model.Owner;
import fede.springframework.petclinic.services.map.OwnerMapService;
import fede.springframework.petclinic.services.map.PetMapService;
import fede.springframework.petclinic.services.map.PetTypeMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(ownerId, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(Long.valueOf(ownerId), owner.getId());
    }

    @Test
    void saveExistingId() {
        long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(Long.valueOf(id), savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertTrue(ownerMapService.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertTrue(ownerMapService.findAll().isEmpty());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);

        assertEquals(Long.valueOf(ownerId), smith.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner ownerNotFound = ownerMapService.findByLastName("anderson");

        assertNull(ownerNotFound);
    }
}