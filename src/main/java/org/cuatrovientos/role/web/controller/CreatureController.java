package org.cuatrovientos.role.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.cuatrovientos.role.persistence.model.Creature;
import org.cuatrovientos.role.service.ICreatureService;
import org.cuatrovientos.role.web.dto.CreatureDto;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/creatures")
public class CreatureController {
	
	private ICreatureService creatureSercice;
	
	public CreatureController(ICreatureService creatureSercice) {
		this.creatureSercice = creatureSercice;
	}
	
    @GetMapping
    public List<CreatureDto> getCreatures() {
        Iterable<Creature> creatures = creatureSercice.findAll();
        List<CreatureDto> creatureDtos = new ArrayList<>();
        creatures.forEach(p -> creatureDtos.add(convertCreatureToDto(p)));
        return creatureDtos;
    }

    @GetMapping("/details/{id}")
    public CreatureDto getCreature(@PathVariable Long id) {
        Creature creature = creatureSercice.findById(id)
            .get();
        return convertCreatureToDto(creature);
    }
    @GetMapping("/edit/{id}")
    public CreatureDto editCreature(@PathVariable Long id) {
        Creature creature = creatureSercice.findById(id)
            .get();
        return convertCreatureToDto(creature);
    }
    
    @GetMapping("/delete/{id}")
    public Creature deleteCreature(@ModelAttribute("Creature") CreatureDto creature, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Creature();
        }

        return convertCreatureToEntity(creature);
    }
    
    @PostMapping("/edit/{id}")
    public Creature modifyCreature(@ModelAttribute("Creature") CreatureDto creature, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Creature();
        }

        Creature newCreature = creatureSercice.save(convertCreatureToEntity(creature));
        return newCreature;
    }	
	

    @PostMapping
    public Creature addCreature(@ModelAttribute("Creature") CreatureDto Creature, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Creature();
        }

        Creature newCreature = creatureSercice.save(convertCreatureToEntity(Creature));
        return newCreature;
    }	
	
	///
	
	// Data Transfer Object conversion

	protected CreatureDto convertCreatureToDto(Creature entity) {
		CreatureDto dto = new CreatureDto(entity.getId(), entity.getName(), entity.getAbilities(), entity.getAvatar());
		return dto;
	}

	protected Creature convertCreatureToEntity(CreatureDto dto) {
		Creature creature = new Creature(dto.getId(), dto.getName(), dto.getAbilities(), dto.getAvatar());
		if (!StringUtils.isEmpty(dto.getId())) {
			creature.setId(dto.getId());
		}
		return creature;
	}
}
