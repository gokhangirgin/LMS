package models;
import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name="Detergant")
public class Detergant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int Id;
	private String Name;
	private String Odor;
	private Boolean ForColored;
	private double UnitPrice;
	public Detergant(){}
	public Detergant(String name, String odor, Boolean forColored,
			double unitPrice) {
		Name = name;
		Odor = odor;
		ForColored = forColored;
		UnitPrice = unitPrice;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getOdor() {
		return Odor;
	}
	public void setOdor(String odor) {
		Odor = odor;
	}
	public Boolean getForColored() {
		return ForColored;
	}
	public void setForColored(Boolean forColored) {
		ForColored = forColored;
	}
	public double getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}
}
