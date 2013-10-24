package models;

import java.io.Serializable;
import java.util.Date; 
public class Drying implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	private int Id; 
	private Basket Basket;
	private Date StartTime;
	private Date EndTime;
	private double Temperature;
	private Boolean OpenAir;
	public Drying(){}
	public Drying(models.Basket basket, Date startTime, Date endTime,
			double temperature, Boolean openAir) {
		super();
		Basket = basket;
		StartTime = startTime;
		EndTime = endTime;
		Temperature = temperature;
		OpenAir = openAir;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Basket getBasket() {
		return Basket;
	}
	public void setBasket(Basket basket) {
		Basket = basket;
	}
	public Date getStartTime() {
		return StartTime;
	}
	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}
	public Date getEndTime() {
		return EndTime;
	}
	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}
	public double getTemperature() {
		return Temperature;
	}
	public void setTemperature(double temperature) {
		Temperature = temperature;
	}
	public Boolean getOpenAir() {
		return OpenAir;
	}
	public void setOpenAir(Boolean openAir) {
		OpenAir = openAir;
	}
	
	
	
}
