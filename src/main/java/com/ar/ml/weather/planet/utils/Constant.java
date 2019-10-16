package com.ar.ml.weather.planet.utils;

public class Constant {
	public static final String TYPE_WEATHER_RAIN = "R";
	public static final String TYPE_WEATHER_DROUGHT= "D";
	public static final String TYPE_WEATHER_PERFECT = "P";
	public static final String TYPE_WEATHER_NO_DATA = "S";
	
	public static final String TYPE_WEATHER_RAIN_DESCRIPTION = "lluvia";
	public static final String TYPE_WEATHER_DROUGHT_DESCRIPTION = "sequia";
	public static final String TYPE_WEATHER_PERFECT_DESCRIPTION = "clima perfecto";
	public static final String TYPE_WEATHER_NO_DATA_DESCRIPTION = "no tenemos informacion";	
	
	public static final String PLANET_VULCANO = "VULCANO";
	public static final String PLANET_FEREGI = "FEREGI";
	public static final String PLANET_BETOSOIDE = "BETOSOIDE";
	
	public static final int ANGULE_INITIAL_VULCANO = 0;
	public static final int ANGULE_INITIAL_FEREGI = 0;
	public static final int ANGULE_INITIAL_BETOSOIDE = 0;
	
	public static final int MOVEMENT_FOR_DAY_VULCANO = 5;
	public static final int MOVEMENT_FOR_DAYL_FEREGI = 1;
	public static final int MOVEMENT_FOR_DAY_BETOSOIDE = 3;
	
	public static final int RADIO_VULCANO = 1000;
	public static final int RADIO_FEREGI = 500;
	public static final int RADIO_BETOSOIDE = 2000;
	
	public static final int TYPE_MOVENT_POS = 1;
	public static final int TYPE_MOVENT_NEG = -1;
	
	public static final String ERROR_MESSAGE_BAD_REQUEST = "Datos incorrectos, por favor validar su solicitud.";
	public static final String ERROR_MESSAGE_UNAUTHORIZED = "Civilizacion no autorizada.";
	public static final String MESSAGE_OK_RAIN = "Dias de maxima precipitacion: ";
	public static final String NO_MESSAGE_OK_RAIN = "No tenemos dias de precipitacion";
}
