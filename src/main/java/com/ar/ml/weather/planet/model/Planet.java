package com.ar.ml.weather.planet.model;

import com.ar.ml.weather.planet.utils.DecimalUtils;

public class Planet {
	public static final int ANGULE_TOTAL = 360;
	
	private final String namePlanet;
	private final int anguleForday;
	private final int typeAngule;
	private final int radio;
	
	//var for calculation
	private double x;
	private double y;
	private int angule;	
	
	public Planet(String namePlanet, int anguleForday, int typeAngule, int radio) {
		this.namePlanet = namePlanet;
		this.anguleForday = anguleForday;
		this.typeAngule = typeAngule;
		this.radio = radio;
	}
	
	public String getNamePlanet() {
		return namePlanet;
	}
	public int getAnguleForday() {
		return anguleForday;
	}
	public int getTypeAngule() {
		return typeAngule;
	}
	public int getRadio() {
		return radio;
	}
	public double getX() {
		double value = this.radio*Math.cos((angule*(2*Math.PI))/360);
		x = DecimalUtils.decimal(value, DecimalUtils.TWO_DECIMAL);
		return x == -0 ? 0 : x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		double value = this.radio*Math.sin((angule*(2*Math.PI))/360);
		y = DecimalUtils.decimal(value, DecimalUtils.TWO_DECIMAL);
		return y == -0 ? 0 : y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public int getAngule(int days) {
		this.angule = (((days*this.anguleForday)%this.ANGULE_TOTAL)*this.typeAngule) + (this.typeAngule < 0 ? ANGULE_TOTAL : 0);	
		return angule;
	}
	
	public int getAngule() {
		return angule;
	}

	public void setAngule(int angule) {
		this.angule = angule;
	}
}
