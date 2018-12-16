package fede.springframework.petclinic.services;

import fede.springframework.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
