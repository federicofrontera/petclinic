package fede.springframework.petclinic.repositories;

import fede.springframework.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository  extends CrudRepository<Vet,Long> {
}
