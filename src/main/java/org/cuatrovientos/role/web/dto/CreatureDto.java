package org.cuatrovientos.role.web.dto;

public class CreatureDto {
	
	Long id;	
	String name;	
	String abilities;	
	String avatar;
	
	public CreatureDto() {}
	
	public CreatureDto(Long id, String name, String abilities, String avatar) {
		super();
		this.id = id;
		this.name = name;
		this.abilities = abilities;
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbilities() {
		return abilities;
	}

	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	

}
