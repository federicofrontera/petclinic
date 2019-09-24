package fede.springframework.petclinic.repositories;

import fede.springframework.petclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends CrudRepository<Specialty,Long> {
}
