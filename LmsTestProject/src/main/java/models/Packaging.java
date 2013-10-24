package models;

import java.io.Serializable; 
import java.util.Date;
public class Packaging implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int Id; 
	private Basket Basket;
	private Date StartTime;
	private Date EndTime;
	public Packaging(){}
	public Packaging(int id, models.Basket basket, Date startTime, Date endTime) {
		super();
		Id = id;
		Basket = basket;
		StartTime = startTime;
		EndTime = endTime;
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
}
