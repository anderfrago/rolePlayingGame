package org.cuatrovientos.role.service;

import java.util.Optional;

import org.cuatrovientos.role.persistence.model.Creature;
import org.springframework.stereotype.Service;

@Service
public interface ICreatureService {
	
    Optional<Creature> findById(Long id);

    Creature save(Creature creature);
    
    void delete(Creature creature);

    Iterable<Creature> findAll();   
    
}
