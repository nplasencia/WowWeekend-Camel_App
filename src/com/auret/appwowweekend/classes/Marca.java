package com.auret.appwowweekend.classes;

import com.auret.appwowweekend.enums.MarcasEnum;

public class Marca {
	private MarcasEnum marca;
	private Boolean checked;
	
	public Marca (MarcasEnum marca, Boolean checked) {
		this.marca = marca;
		this.checked = checked;
	}

	public MarcasEnum getMarca() {
		return marca;
	}

	public void setMarca(MarcasEnum marca) {
		this.marca = marca;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
