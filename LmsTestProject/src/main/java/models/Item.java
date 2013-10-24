package models;
import java.io.Serializable;
 
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	private int Id;
	private Type Type;
	private Material Material;
	private Boolean Color;
	private double Weight;
	private Phase Location;
	private int WashedCount;
	
	public Item(){}
	public Item(Type type, models.Material material,
			Boolean color, double weight, Phase location, int washedCount) {
		Type = type;
		Material = material;
		Color = color;
		Weight = weight;
		Location = location;
		WashedCount = washedCount;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Type getType() {
		return Type;
	}
	public void setType(Type type) {
		Type = type;
	}
	public Material getMaterial() {
		return Material;
	}
	public void setMaterial(Material material) {
		Material = material;
	}
	public Boolean getColor() {
		return Color;
	}
	public void setColor(Boolean color) {
		Color = color;
	}
	public double getWeight() {
		return Weight;
	}
	public void setWeight(double weight) {
		Weight = weight;
	}
	public Phase getLocation() {
		return Location;
	}
	public void setLocation(Phase location) {
		Location = location;
	}
	public int getWashedCount() {
		return WashedCount;
	}
	public void setWashedCount(int washedCount) {
		WashedCount = washedCount;
	}
}
