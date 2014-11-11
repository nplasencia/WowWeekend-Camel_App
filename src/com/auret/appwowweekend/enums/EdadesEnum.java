package com.auret.appwowweekend.enums;

public enum EdadesEnum {
	
	MENOR25  (1, "18-24"),
	MENOR35  (2, "25-34"), 
	MAYOR35  (3, "+35");
	

	private Integer value;
	private String description;
	
	private EdadesEnum(Integer value, String description) {
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
	
	public static final EdadesEnum getFromValue(Integer value) {
		for(EdadesEnum c : values()) {
			if(c.getValue().equals(value))
				return c;
		}
		return null;
	}
	
}
