package com.ar.ml.weather.planet.dto;

import java.util.List;

import com.ar.ml.weather.planet.model.SummaryDay;

public class SummaryDTO {
	private int codError;
	private String descpError;
	private SummaryDay maxDayRain;
	private String msg;
	private List<SummaryDay> itemRain;
	private List<SummaryDay> itemDrought;
	private List<SummaryDay> itemPerfect;
	
	public SummaryDTO(int codError, String descpError) {
		this.codError = codError;
		this.descpError = descpError;
	}
	
	public SummaryDTO() {}
	
	public int getCodError() {
		return codError;
	}
	public void setCodError(int codError) {
		this.codError = codError;
	}
	public String getDescpError() {
		return descpError;
	}
	public void setDescpError(String descpError) {
		this.descpError = descpError;
	}
	
	public List<SummaryDay> getItemRain() {
		return itemRain;
	}

	public void setItemRain(List<SummaryDay> itemRain) {
		this.itemRain = itemRain;
	}

	public List<SummaryDay> getItemDrought() {
		return itemDrought;
	}

	public void setItemDrought(List<SummaryDay> itemDrought) {
		this.itemDrought = itemDrought;
	}

	public List<SummaryDay> getItemPerfect() {
		return itemPerfect;
	}

	public void setItemPerfect(List<SummaryDay> itemPerfect) {
		this.itemPerfect = itemPerfect;
	}

	public SummaryDay getMaxDayRain() {
		return maxDayRain;
	}

	public void setMaxDayRain(SummaryDay maxDayRain) {
		this.maxDayRain = maxDayRain;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
