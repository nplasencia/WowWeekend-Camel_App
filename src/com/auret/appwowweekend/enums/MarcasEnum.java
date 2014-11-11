package com.auret.appwowweekend.enums;

public enum MarcasEnum {
	
	CAMEL        (1, "Camel"),
	WINSTON      (2, "Winston"), 
	MARLBORO     (3, "Marlboro"),
	LYM          (4, "L&M"),
	CHESTERFIELD (5, "Chesterfield"),
	OTROS        (6, "Otros");
	

	private Integer value;
	private String description;
	
	private MarcasEnum(Integer value, String description) {
		this.setValue(value);
		this.setDescription(description);
	}
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static final MarcasEnum getFromValue(Integer value) {
		for(MarcasEnum c : values()) {
			if(c.getValue().equals(value))
				return c;
		}
		return null;
	}
	
}
