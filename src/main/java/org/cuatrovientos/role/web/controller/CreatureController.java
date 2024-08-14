package org.cuatrovientos.role.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.cuatrovientos.role.persistence.model.Creature;
import org.cuatrovientos.role.service.ICreatureService;
import org.cuatrovientos.role.web.dto.CreatureDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/creatures")
public class CreatureController {
	
	private ICreatureService creatureSercice;
	
	public CreatureController(ICreatureService creatureSercice) {
		this.creatureSercice = creatureSercice;
	}
	
    @GetMapping
    public String getCreatures(Model model) {
        Iterable<Creature> Creatures = creatureSercice.findAll();
        List<CreatureDto> CreatureDtos = new ArrayList<>();
        Creatures.forEach(p -> CreatureDtos.add(convertCreatureToDto(p)));
        model.addAttribute("creatures", CreatureDtos);
        return "creatures";
    }

    @GetMapping("/new")
    public String newCreature(Model model) {
        model.addAttribute("creature", new CreatureDto());
        return "new-creature";
    }

    @GetMapping("/details/{id}")
    public String getCreature(@PathVariable Long id, Model model) {
        Creature Creature = creatureSercice.findById(id)
            .get();
        model.addAttribute("creature", convertCreatureToDto(Creature));
        return "creature";
    }
    @GetMapping("/edit/{id}")
    public String editCreature(@PathVariable Long id, Model model) {
        Creature Creature = creatureSercice.findById(id)
            .get();
        model.addAttribute("creature", convertCreatureToDto(Creature));
        return "edit-creature";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCreature(@ModelAttribute("Creature") CreatureDto Creature, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "creatures";
        }

        creatureSercice.delete(convertCreatureToEntity(Creature));
        return "redirect:/creatures";
    }
    
    @PostMapping("/edit/{id}")
    public String modifyCreature(@ModelAttribute("Creature") CreatureDto Creature, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-creature";
        }

        Creature newCreature = creatureSercice.save(convertCreatureToEntity(Creature));
        return "redirect:/creatures";
    }	
	

    @PostMapping
    public String addCreature(@ModelAttribute("Creature") CreatureDto Creature, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-creature";
        }

        Creature newCreature = creatureSercice.save(convertCreatureToEntity(Creature));
        return "redirect:/creatures";
    }	
	
	///
	
	// Data Transfer Object conversion

	protected CreatureDto convertCreatureToDto(Creature entity) {
		CreatureDto dto = new CreatureDto(entity.getId(), entity.getName(), entity.getAbilities(), entity.getAvatar());
		return dto;
	}

	protected Creature convertCreatureToEntity(CreatureDto dto) {
		Creature Creature = new Creature(dto.getId(), dto.getName(), dto.getAbilities(), dto.getAvatar());
		if (!StringUtils.isEmpty(dto.getId())) {
			Creature.setId(dto.getId());
		}
		return Creature;
	}
}
