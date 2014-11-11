package com.auret.appwowweekend.classes;

import com.auret.appwowweekend.enums.EdadesEnum;


public class Edad {
	private EdadesEnum edad;
	private Boolean checked;
	
	public Edad (EdadesEnum edad, Boolean checked) {
		this.edad = edad;
		this.checked = checked;
	}

	public EdadesEnum getEdad() {
		return edad;
	}

	public void setEdades(EdadesEnum edad) {
		this.edad = edad;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
