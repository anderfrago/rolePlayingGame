package org.cuatrovientos.role.persistence.repository;

import org.cuatrovientos.role.persistence.model.Creature;
import org.springframework.data.repository.CrudRepository;

public interface ICreatureRepository extends CrudRepository<Creature, Long> {

}
