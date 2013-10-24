package models;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderView implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private int OrderId;
	private BigInteger Percentage;
	public OrderView(){}
	public OrderView(int OrderId,BigInteger Percentage)
	{
		this.setOrderId(OrderId);
		this.setPercentage(Percentage);
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public BigInteger getPercentage() {
		return Percentage;
	}
	public void setPercentage(BigInteger percentage) {
		Percentage = percentage;
	}
	
}
