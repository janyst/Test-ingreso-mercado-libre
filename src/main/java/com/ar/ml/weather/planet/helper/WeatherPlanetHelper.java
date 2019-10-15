package com.ar.ml.weather.planet.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ar.ml.weather.planet.dao.FactoryDao;
import com.ar.ml.weather.planet.dto.SummaryDTO;
import com.ar.ml.weather.planet.model.Planet;
import com.ar.ml.weather.planet.model.SummaryDay;
import com.ar.ml.weather.planet.utils.Constant;
import com.ar.ml.weather.planet.utils.DecimalUtils;

public class WeatherPlanetHelper {
	private static final int X0 = 0;
	private static final int Y0 = 0;
	static Logger logger = Logger.getLogger(WeatherPlanetHelper.class);
	
	private Planet vulcano;
	private Planet feregi;
	private Planet betosoide;
	
	public Planet getVulcano() {
		return vulcano;
	}

	public void setVulcano(Planet vulcano) {
		this.vulcano = vulcano;
	}

	public Planet getFeregi() {
		return feregi;
	}

	public void setFeregi(Planet feregi) {
		this.feregi = feregi;
	}

	public Planet getBetosoide() {
		return betosoide;
	}

	public void setBetosoide(Planet betosoide) {
		this.betosoide = betosoide;
	}

	/**
	* <h1>retorna un objeto SummaryDay</h1>
	* calcula el resumen del dia, si la funcion calculateInfomation 
	* no escupe nada se manda por defecto "no tenemos informacion"
	* @author A704945
	*/
	public SummaryDay typeWeather(int days) {
		SummaryDay summaryDay = null;
		
		this.vulcano.getAngule(days);		
		this.feregi.getAngule(days);
		this.betosoide.getAngule(days);
		
		summaryDay = this.calculateInfomation(days);
		logger.info("el typo de clima es: "+summaryDay);
		if(summaryDay == null) {			
			logger.warn("no hay un clima, mandamos el valor por defecto");
			summaryDay = new SummaryDay(days, Constant.TYPE_WEATHER_NO_DATA, Constant.TYPE_WEATHER_NO_DATA_DESCRIPTION, null);			
		}
		return summaryDay;
	}
	
	/**
	* <h1>retorna un objeto con una lista de dias por tipo de clima</h1>
	* se busca en BD los valores ejecutados por el jobs y el resumen de los dias.
	* @author A704945
	*/
	public SummaryDTO summaryTenYears() {
		FactoryDao factoryDao = null;
		List<SummaryDay> listDao = null;
		SummaryDTO summaryDTO = new SummaryDTO();
		
		factoryDao = new FactoryDao();
		//listDao = factoryDao.getSummaryDay();
		if(listDao != null && !listDao.isEmpty()) {
			logger.info("la lista posee valor");
			summaryDTO.setItemRain(new ArrayList<SummaryDay>());
			summaryDTO.setItemDrought(new ArrayList<SummaryDay>());
			summaryDTO.setItemPerfect(new ArrayList<SummaryDay>());
			summaryDTO.setMaxDayRain(new SummaryDay());
			for(SummaryDay suDay : listDao) {
				if(suDay.getTypeWeather().equalsIgnoreCase(Constant.TYPE_WEATHER_RAIN)) {
					logger.info("tenemos dia de lluvia "+suDay.getDay()+", perimeter "+suDay.getPerimeter());
					summaryDTO.getItemRain().add(suDay);
					if(summaryDTO.getMaxDayRain().getPerimeter() == null || summaryDTO.getMaxDayRain().getPerimeter() < suDay.getPerimeter()) {
						summaryDTO.setMaxDayRain(suDay);
					}
				}else if(suDay.getTypeWeather().equalsIgnoreCase(Constant.TYPE_WEATHER_DROUGHT)) {
					logger.info("tenemos dias de sequia "+suDay.getDay());
					summaryDTO.getItemDrought().add(suDay);
				}else if(suDay.getTypeWeather().equalsIgnoreCase(Constant.TYPE_WEATHER_PERFECT)){
					logger.info("tenemos dias perfector "+suDay.getDay());
					summaryDTO.getItemPerfect().add(suDay);
				}
			}
			summaryDTO.setCodError(0);
		}else {
			summaryDTO = this.getListSummaryWeather(365*10);
		}
		
		return summaryDTO;
	}
	
	/**
	* <h1>retorna SummaryDay</h1>
	* calcula la informacion del dia exacto, en caso de no cumplir las condiciones retorna null
	* @author A704945
	*/
	private SummaryDay calculateInfomation(int days) {
		SummaryDay summaryDay = null;
		
		//lluvia
		summaryDay = this.rainDay(this.getVulcano().getX(), this.getVulcano().getY(), this.getFeregi().getX(), this.getFeregi().getY(),
				this.getBetosoide().getX(), this.getFeregi().getY(), days);
		if(summaryDay == null) {
			//perfecto o sequia
			summaryDay = this.SunnyDay(this.getVulcano().getX(), this.getVulcano().getY(), this.getFeregi().getX(), this.getFeregi().getY(),
					this.getBetosoide().getX(), this.getFeregi().getY(), days);
		}
		return summaryDay;
	}
	
	/**
	* <h1>listado de resumen del dia</h1>
	* este metodo se llama en caso de que la BD nos de time-out
	* @author A704945
	*/
	public SummaryDTO getListSummaryWeather(int years) {
		SummaryDTO summaryDTO = new SummaryDTO();
		
		summaryDTO.setItemRain(new ArrayList<SummaryDay>());
		summaryDTO.setItemPerfect(new ArrayList<SummaryDay>());
		summaryDTO.setItemDrought(new ArrayList<SummaryDay>());
		
		for(int i=1; i <= years; i++) {
			SummaryDay suDay = null;
			suDay = this.typeWeather(i);
			if(suDay != null) {
				if(suDay.getTypeWeather().equalsIgnoreCase(Constant.TYPE_WEATHER_RAIN)) {
					summaryDTO.getItemRain().add(suDay);
					if(summaryDTO.getMaxDayRain() == null || summaryDTO.getMaxDayRain().getPerimeter() < suDay.getPerimeter()) {
						summaryDTO.setMaxDayRain(suDay);
					}
				}else if(suDay.getTypeWeather().equalsIgnoreCase(Constant.TYPE_WEATHER_DROUGHT)) {
					summaryDTO.getItemDrought().add(suDay);
				}else if(suDay.getTypeWeather().equalsIgnoreCase(Constant.TYPE_WEATHER_RAIN)){
					summaryDTO.getItemPerfect().add(suDay);
				}
			}
		}
		return summaryDTO;
	}
	
	public void executeJobs() {
		FactoryDao factoryDao = null;
		int years = 10*365;
		List<SummaryDay> listRegister = null;
		SummaryDTO listPartial = null;
		listPartial = this.getListSummaryWeather(years);
		if(!listPartial.getItemRain().isEmpty() || !listPartial.getItemDrought().isEmpty() 
				|| !listPartial.getItemPerfect().isEmpty()) {
			listRegister = new ArrayList<SummaryDay>();
			listRegister.addAll(listPartial.getItemRain());
			listRegister.addAll(listPartial.getItemDrought());
			listRegister.addAll(listPartial.getItemPerfect());
			
			factoryDao = new FactoryDao();
			//factoryDao.getRegister(listRegister);
		}
	}
	
	
	
	/**
	* <h1>dia de lluvia</h1>
	* recibe los parametro XY de los tres planetas, retorna si el dia es de lluvia,
	* en caso contrario null
	* @author A704945
	*/
	private SummaryDay rainDay(double x1, double y1, double x2, double y2, double x3, double y3, int days) {
		Double perimeter = null;
		SummaryDay summaryDay = null;
		
		perimeter = this.triangle(x1, y1, x2, y2, x3, y3);
		if(perimeter != null) {
			logger.info("rainDay puntoA(XY): "+x1+";"+y1);
			logger.info("rainDay puntoB(XY): "+x2+";"+y2);
			logger.info("rainDay puntoC(XY): "+x3+";"+x3);
			summaryDay = new SummaryDay(days, Constant.TYPE_WEATHER_RAIN, Constant.TYPE_WEATHER_RAIN_DESCRIPTION, perimeter);
		}
			
		return summaryDay;
	}
	
	/**
	* <h1>dia de sequia</h1>
	* recibe los parametro XY de los tres planetas, retorna si el dia es de sequia,
	* en caso contrario null
	* @author A704945
	*/
	private SummaryDay SunnyDay(double x1, double y1, double x2, double y2, double x3, double y3, int days) {
		SummaryDay summaryDay = null;
		String line = null;
		
		line = this.straightLine(x1, y1, x2, y2, x3, y3);
		if(line != null) {
			logger.info("SunnyDay puntoA(XY): "+x1+";"+y1);
			logger.info("SunnyDay puntoB(XY): "+x2+";"+y2);
			logger.info("SunnyDay puntoC(XY): "+x3+";"+x3);
			summaryDay = new SummaryDay(days, line, line.equalsIgnoreCase(Constant.TYPE_WEATHER_PERFECT) ? Constant.TYPE_WEATHER_PERFECT_DESCRIPTION : Constant.TYPE_WEATHER_DROUGHT_DESCRIPTION, null);
		}
			
		return summaryDay;
	}
	
	/**
	* <h1>calcula de lina recta y valida si pasa por el punto 0,0</h1>	* 
	* @author A704945
	*/
	private String straightLine(double x1, double y1, double x2, double y2, double x3, double y3) {
		String typeLine = null;
		if(equationStraight(x1, y1, x2, y2, x3, y3)){
			typeLine = Constant.TYPE_WEATHER_PERFECT;
			logger.info("straightLine("+Constant.TYPE_WEATHER_PERFECT_DESCRIPTION+") puntoA(XY): "+x1+";"+y1);
			logger.info("straightLine("+Constant.TYPE_WEATHER_PERFECT_DESCRIPTION+") puntoB(XY): "+x2+";"+y2);
			logger.info("straightLine("+Constant.TYPE_WEATHER_PERFECT_DESCRIPTION+") puntoC(XY): "+x3+";"+x3);
			
			if(equationStraight(x1, y1, x2, y2, X0, Y0)) {
				typeLine = Constant.TYPE_WEATHER_DROUGHT;
				logger.info("straightLine("+Constant.TYPE_WEATHER_DROUGHT_DESCRIPTION+") puntoA(XY): "+x1+";"+y1);
				logger.info("straightLine("+Constant.TYPE_WEATHER_DROUGHT_DESCRIPTION+") puntoB(XY): "+x2+";"+y2);
				logger.info("straightLine("+Constant.TYPE_WEATHER_DROUGHT_DESCRIPTION+") puntoC(XY): "+x3+";"+x3);
			}			
		}
		return typeLine;				
	}
	
	/**
	* <h1>calcula de lina recta</h1>	* 
	* @author A704945
	*/
	private boolean equationStraight (double x1, double y1, double x2, double y2, double x3, double y3){		
	    //return ((y3 - y2) * (x2 - x1) ==  (y2 - y1) * (x3 - x2)) ;
		return (DecimalUtils.between((y3 - y2) * (x2 - x1), (y2 - y1) * (x3 - x2))) ;
	}
	
	/**
	* <h1>calcula de area de un triangulo</h1>	* 
	* @author A704945
	*/
	private double area(double x1, double y1, double x2, double y2, double x3, double y3) {
       return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0); 
	}
	
	
	/**
	* <h1>validar si es un triangulo y su centro es 0,0</h1>	* 
	* @author A704945
	*/
	private Double triangle(double x1, double y1, double x2, double y2, double x3, double y3){		
		Double perimeter = null; 
		double area = area (x1, y1, x2, y2, x3, y3); //Calculate area of triangle points ABC 
        double a = area (X0, Y0, x2, y2, x3, y3); //Calculate area of triangle points 0BC
        double b = area (x1, y1, X0, Y0, x3, y3); //Calculate area of triangle points 0AC
        double c = area (x1, y1, x2, y2, X0, Y0); //Calculate area of triangle points 0AB
        if(area != 0 && (area == a + b + c)) {
        	perimeter = perimeterTriangle(x1, y1, x2, y2, x3, y3);
        }
        return perimeter;
	}
	
	/**
	* <h1>perimetro de un triangulo</h1>	* 
	* @author A704945
	*/
	private double perimeterTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
		double distanceAB = 0;
		double distanceAC = 0;
		double distanceBC = 0;		
		distanceAB = distanceTwoPoints(x1, y1, x2, y2);
		distanceAC = distanceTwoPoints(x1, y1, x3, y3);
		distanceBC = distanceTwoPoints(x2, y2, x3, y3);
		return distanceAB + distanceAC + distanceBC;		
	}
	
	/**
	* <h1>distancia entre dos puntos</h1>	* 
	* @author A704945
	*/
	private double distanceTwoPoints(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));	 
	}	
}
