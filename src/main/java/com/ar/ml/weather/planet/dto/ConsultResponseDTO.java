package com.ar.ml.weather.planet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultResponseDTO {
	
	@JsonProperty("dia")
	private int dia;
	@JsonProperty("clima")
	private String clima;
	
	public ConsultResponseDTO (int dia, String clima) {
		this.dia = dia;
		this.clima = clima;
	}
	
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	
	
}
