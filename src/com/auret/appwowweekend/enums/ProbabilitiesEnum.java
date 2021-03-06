package com.auret.appwowweekend.enums;


public enum ProbabilitiesEnum {

	HIGHEST (100, "Highest probability"), 
	MIDDLE  (50, "Middle probability"),
	LOWEST  (0, "Lowest probability");
	
	private Integer value;
	private String description;
	
	private ProbabilitiesEnum(Integer value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public Integer getValue() {
		return this.value;
	}
	public String getDescription() {
		return this.description;
	}
	
	public static final ProbabilitiesEnum getFromValue(Integer value) {
		for(ProbabilitiesEnum c : values()) {
			if(c.getValue().equals(value))
				return c;
		}
		return null;
	}


}
