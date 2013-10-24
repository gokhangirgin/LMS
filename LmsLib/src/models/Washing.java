package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity
@Table(name="Washing")
public class Washing implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int Id;
	@ManyToOne
	@JoinColumn(name="basketid")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Basket Basket;
	private Date StartTime;
	private Date EndTime;
	@ManyToOne
	@JoinColumn(name="waterid")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Water Water;
	private int MachineId;
	public Washing(){}
	
	public Washing(models.Basket basket, Date startTime, Date endTime,
			models.Water water, int machineId) {
		Basket = basket;
		StartTime = startTime;
		EndTime = endTime;
		Water = water;
		MachineId = machineId;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Water getWater() {
		return Water;
	}
	public void setWater(Water water) {
		Water = water;
	}
	public int getMachineId() {
		return MachineId;
	}
	public void setMachineId(int machineId) {
		MachineId = machineId;
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
