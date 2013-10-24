package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="Orders")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int Id;
	private Date TransactionDate;
	private Status Status;
	@ManyToOne
	@JoinColumn(name="PersonnelId",unique=true)
	private Personnel Personnel;
	private Date EstimatedPickUpDate;
	private Date RealPickUpDate;
	@ManyToOne
	@JoinColumn(name="customerid", unique=true)
	private Customer Customer;
	@OneToMany
	@JoinTable(name="orderitems",joinColumns={@JoinColumn(name="orderid")},inverseJoinColumns={@JoinColumn(name="itemid")})
	private List<Item> Items;
	public Order(){}
	public Order(Date transactionDate, models.Status status,
			models.Personnel personnel, Date estimatedPickUpDate,
			Date realPickUpDate, models.Customer customer, List<Item> items) {
		TransactionDate = transactionDate;
		Status = status;
		Personnel = personnel;
		EstimatedPickUpDate = estimatedPickUpDate;
		RealPickUpDate = realPickUpDate;
		Customer = customer;
		Items = items;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		TransactionDate = transactionDate;
	}
	public Status getStatus() {
		return Status;
	}
	public void setStatus(Status status) {
		Status = status;
	}
	public Personnel getPersonnel() {
		return Personnel;
	}
	public void setPersonnel(Personnel personnel) {
		Personnel = personnel;
	}
	public Date getEstimatedPickUpDate() {
		return EstimatedPickUpDate;
	}
	public void setEstimatedPickUpDate(Date estimatedPickUpDate) {
		EstimatedPickUpDate = estimatedPickUpDate;
	}
	public Date getRealPickUpDate() {
		return RealPickUpDate;
	}
	public void setRealPickUpDate(Date realPickUpDate) {
		RealPickUpDate = realPickUpDate;
	}
	public Customer getCustomer() {
		return Customer;
	}
	public void setCustomer(Customer customer) {
		Customer = customer;
	}
	public List<Item> getItems() {
		return Items;
	}
	public void setItems(List<Item> items) {
		Items = items;
	}
	
}
