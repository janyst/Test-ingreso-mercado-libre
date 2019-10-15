package com.ar.ml.weather.planet.dao;

import com.mysql.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ar.ml.weather.planet.model.SummaryDay;

public class FactoryDao {
	private final String TRUNCATE_TABLE_RAIN = "TRUNCATE TABLE `SUMMARY-WEATHER`.RAIN_DAY";
	private final String TRUNCATE_TABLE_SUMMARY = "TRUNCATE TABLE `SUMMARY-WEATHER`.SUMMARY_WEATHER";	
	static Logger logger = Logger.getLogger(FactoryDao.class);
	
	private Connection con = null;
	
	/**
	* <h1>registro de informacion</h1>
	* trunca tablas, registra la informacion del clima por dia.
	* @author A704945
	*/
	public void getRegister (List<SummaryDay> lisSummaryDays) {
		PreparedStatement preparedStatement = null;
		String[] arrySql = null;
		
		try{
			arrySql = this.getSQL(lisSummaryDays);
			if(arrySql != null) {
				this.getConnection();
				preparedStatement = con.prepareStatement(TRUNCATE_TABLE_RAIN);//eliminar informacion de la tabla RAIN_DAY			
				preparedStatement.executeUpdate();		
				
				preparedStatement.executeUpdate(TRUNCATE_TABLE_SUMMARY);//eliminar informacion de la tabla SUMMARY_WEATHER			
				preparedStatement.executeUpdate(arrySql[0]); //insert SUMMARY_WEATHER				
				if(arrySql[1] != null) 
					preparedStatement.executeUpdate(arrySql[1]);//insert RAIN_DAY
				logger.info("insert SUMMARY_WEATHER "+arrySql[0]);
				logger.info("insert RAIN_DAY "+arrySql[1]);
				this.getClose();
			}			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("no podimos ejecutar transacciones en BD", e);
		}
	}
	
	/**
	* <h1>retor los registros almacenados en BD</h1>
	* @author A704945
	*/
	public List<SummaryDay> getSummaryDay() {
		ResultSet rs = null;	
		PreparedStatement preparedStatement = null;
		List<SummaryDay> listSummaryDays = new ArrayList<SummaryDay>();
		
		try {
			if(con == null) {
				this.getConnection();					
			}
			preparedStatement = con.prepareStatement(this.getSql());
			rs = preparedStatement.executeQuery(this.getSql());
			logger.info("select "+this.getSql());
			
			while(rs.next()) {
				listSummaryDays.add(new SummaryDay(rs.getInt("NUMBER_DAY"),rs.getString("ID_WEATHER"), rs.getString("DESCRIPTION_WEATHER"), rs.getDouble("PERIMETER")));
			}	
			this.getClose();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("tenemos problemas con BD",e);
		}
		return listSummaryDays;
	}
	
	/**
	* <h1>se crea conexion con BD</h1>
	* @author A704945
	*/
	private void getConnection() throws Exception{	
		try {
			Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://database-weather.cp02rueziard.us-east-1.rds.amazonaws.com:3306/SUMMARY-WEATHER?user=admin&password=admin123");
		}catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
	}
	
	/**
	* <h1>se crea desconexion con BD</h1>
	* @author A704945
	*/
	private void getClose() throws Exception{	
		try {
			this.con.close();			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	* <h1>crea los sql de insert</h1>
	* @author A704945
	*/
	private String[] getSQL(List<SummaryDay> listSummary) {
		StringBuffer insertSummaryWeather = null;
		StringBuffer insertMaxRain = null;
		String[] arrySql = null;
		
		if(!listSummary.isEmpty()) {
			arrySql = new String[3];
			for(SummaryDay summary : listSummary) {			
				if(insertSummaryWeather == null) {
					insertSummaryWeather = new StringBuffer();
					insertSummaryWeather.append("INSERT INTO `SUMMARY-WEATHER`.SUMMARY_WEATHER (ID, ID_TYPE_WEATHER) VALUES ("+summary.getDay()+",'"+summary.getTypeWeather()+"') ");
				}else {
					insertSummaryWeather.append(",("+summary.getDay()+",'"+summary.getTypeWeather()+"') ");
				}	
				
				if(summary.getPerimeter() != null) {
					if(insertMaxRain == null) {
						insertMaxRain = new StringBuffer();
						insertMaxRain.append("INSERT INTO `SUMMARY-WEATHER`.RAIN_DAY (ID, PERIMETER) VALUES ("+summary.getDay()+","+summary.getPerimeter()+") ");
					}else {
						insertMaxRain.append(",("+summary.getDay()+","+summary.getPerimeter()+") ");
					}
				}					
			}
			arrySql[0] = insertSummaryWeather.toString();
			arrySql[1] = insertMaxRain.toString();
			arrySql[2] = this.getSql();
		}
		return arrySql;
	}
	
	/**
	* <h1>crea el select para buscar los registros de BD</h1>
	* @author A704945
	*/
	private String getSql() {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT ");
		sql.append("B.ID ID_WEATHER, ");
		sql.append("B.DESCRIPTION DESCRIPTION_WEATHER, ");
		sql.append("A.ID NUMBER_DAY, ");
		sql.append("C.PERIMETER PERIMETER ");
		sql.append("FROM `SUMMARY-WEATHER`.SUMMARY_WEATHER A ");
		sql.append("INNER JOIN `SUMMARY-WEATHER`.TYPE_WEATHER B ON B.ID = A.ID_TYPE_WEATHER ");
		sql.append("LEFT JOIN `SUMMARY-WEATHER`.RAIN_DAY C ON C.ID = A.ID ");
		return sql.toString();
	}
}
