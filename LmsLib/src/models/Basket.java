package models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="basket")
public class Basket implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="Id")
	private int Id;
	@Column(name="Type")
	private BasketType Type;
	private Date EntryTime;
	private Date FinishTime;
	private Phase Step;
	@OneToMany
	@JoinTable(name="basketitems",joinColumns={@JoinColumn(name="basketId")},inverseJoinColumns={@JoinColumn(name="itemid")})
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<Item> Items;
	
	public Basket(){}
	public Basket(int id)
	{
		this.Id = id;
	}
	public Basket(models.BasketType type, Date entryTime, Date finisTime,
			Set<Item> items,Phase s) {
		Type = type;
		EntryTime = entryTime;
		FinishTime = finisTime;
		Items = items;
		Step = s;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public BasketType getType() {
		return Type;
	}
	public void setType(BasketType type) {
		Type = type;
	}
	public Date getEntryTime() {
		return EntryTime;
	}
	public void setEntryTime(Date entryTime) {
		EntryTime = entryTime;
	}
	public Date getFinishTime() {
		return FinishTime;
	}
	public void setFinishTime(Date finisTime) {
		FinishTime = finisTime;
	}
	public Set<Item> getItems() {
		return Items;
	}
	public void setItems(Set<Item> items) {
		Items = items;
	}
	public Phase getStep() {
		return Step;
	}
	public void setStep(Phase step) {
		Step = step;
	}
	
}
