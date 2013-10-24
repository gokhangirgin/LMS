package te;

public class Shirt {
	private int count;
	private double temperature;
	private String color;
	private String status;
	public void setColor(String color) {
		this.color = color;
	}
	public Shirt(){ this.count= 3; this.color = "WHITE"; this.temperature=10; this.setStatus("Dirty");}
	public Shirt(int count, double temperature, String color,String status) {
		this.count = count;
		this.temperature = temperature;
		this.color = color;
		this.status = status;
	}
	public String getColor() {
		return color;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public String toString()
	{
		return "Status of Shirt : " + this.getStatus() + " ## washed : "+ this.getCount() + " times before. Heat of washing was " + this.getTemperature();
	}
}