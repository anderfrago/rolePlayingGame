package org.cuatrovientos.role.persistence;

import org.cuatrovientos.role.persistence.model.Creature;
import org.cuatrovientos.role.persistence.repository.ICreatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    private ICreatureRepository creatureRepository;

    @PostConstruct
    private void postConstruct() {
        Creature creature1 = new Creature();
        creature1.setName("Void-Consumed Treants");
        creature1.setAbilities(""
        		+ " Deals Might Damage to an Enemy Stack, decreasing its Melee Might Resistance and rooting it to the spot so that it is unable to escape.\r\n"
        		+ "<ul>\r\n"
        		+ "<li>Melee Might Damage - 40</li>\r\n"
        		+ "<li>Attack Range - 1</li>\r\n"
        		+ "<li>Duration - 2 rounds</li>\r\n"
        		+ "<li>Cooldown - 4 rounds</li>\r\n"
        		+ "<li>Effect: Void Roots</li>\r\n"
        		+ "<li>Movement Range - -100%</li>\r\n"
        		+ "<li>Melee Might Resistance - 20%</li>\r\n"
        		+ "</ul>");
        creature1.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUtlCPFbOPFTlDEVF2cWnoaB8zE9m65SGS_g9T31xz26Mn_KvWkm4Yq3uOwja981GnJoQ&usqp=CAU");
        
        Creature creature2 = new Creature();
        creature2.setName("Army");
        creature2.setAbilities("To make it cost efficient it is best to use basic troops like Archers, Ghouls, etc. However although un-upgraded Elite and Champions are more expensive, it is easier to store them and they contribute more to Battle Power Points.\r\n");
        creature2.setAvatar("https://vignette2.wikia.nocookie.net/wowwiki/images/a/a1/Ner_zhul_%28the_Lich_King%29.jpg");
        
        creatureRepository.save(creature1);     
        creatureRepository.save(creature2); 
    }
}