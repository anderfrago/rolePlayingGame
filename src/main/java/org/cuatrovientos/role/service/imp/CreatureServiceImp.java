package org.cuatrovientos.role.service.imp;

import java.time.LocalDate;
import java.util.Optional;

import org.cuatrovientos.role.persistence.model.Creature;
import org.cuatrovientos.role.persistence.repository.ICreatureRepository;
import org.cuatrovientos.role.service.ICreatureService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CreatureServiceImp implements ICreatureService {
	
	private ICreatureRepository creatureRepository;	

	public CreatureServiceImp(ICreatureRepository creatureRepository) {
		this.creatureRepository = creatureRepository;
	}

	@Override
    public Optional<Creature> findById(Long id) {
        return creatureRepository.findById(id);
    }

    @Override
    public Creature save(Creature creature) {
        if (StringUtils.isEmpty(creature.getId())) {
        	creature.setDateCreated(LocalDate.now());
        }
        return creatureRepository.save(creature);
    }

    @Override
    public Iterable<Creature> findAll() {
        return creatureRepository.findAll();
    }

	@Override
	public void delete(Creature creature) {
		creatureRepository.delete(creature);
	}

  
    
}
