package com.ar.ml.weather.planet.dto;

import java.util.List;

import com.ar.ml.weather.planet.utils.Constant;

public class InformationDTO {
	private Integer maxDayRain;
	private List<Integer> listDayRain;
	private List<Integer> listDayDrought;
	private List<Integer> listDayPerfect;
	private int countDayRain;
	private int countDayDrought;
	private int countDayPerfect;
	private String msgClient;
	
	
	public Integer getMaxDayRain() {
		return maxDayRain;
	}
	public void setMaxDayRain(Integer maxDayRain) {
		this.maxDayRain = maxDayRain;
	}
	public List<Integer> getListDayRain() {
		return listDayRain;
	}
	public void setListDayRain(List<Integer> listDayRain) {
		this.listDayRain = listDayRain;
	}
	public List<Integer> getListDayDrought() {
		return listDayDrought;
	}
	public void setListDayDrought(List<Integer> listDayDrought) {
		this.listDayDrought = listDayDrought;
	}
	public List<Integer> getListDayPerfect() {
		return listDayPerfect;
	}
	public void setListDayPerfect(List<Integer> listDayPerfect) {
		this.listDayPerfect = listDayPerfect;
	}
	public int getCountDayRain() {
		return this.listDayRain.size();
	}
	public void setCountDayRain(int countDayRain) {
		this.countDayRain = countDayRain;
	}
	public int getCountDayDrought() {
		return this.listDayDrought.size();
	}
	public void setCountDayDrought(int countDayDrought) {
		this.countDayDrought = countDayDrought;
	}
	public int getCountDayPerfect() {
		return this.listDayPerfect.size();
	}
	public void setCountDayPerfect(int countDayPerfect) {
		this.countDayPerfect = countDayPerfect;
	}
	public String getMsgClient() {
		String msg = Constant.NO_MESSAGE_OK_RAIN;
		if(this.maxDayRain == null)
			msg = Constant.MESSAGE_OK_RAIN+this.getMaxDayRain();
		return msg;
	}
	public void setMsgClient(String msgClient) {
		this.msgClient = msgClient;
	}
	
}
