package models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="Water")
public class Water implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int Id;
	private double Temperature;
	private double PH;
	private double Hardness;
	
	private double BleachAmount;
	
	private double FabricSoftenerAmount;
	private double DetergantAmount;
	@ManyToOne
	@JoinColumn(name="detergantid")
	private Detergant Detergant;
	@ManyToOne
	@JoinColumn(name="bleachid")
	private Bleach Bleach;
	@ManyToOne
	@JoinColumn(name="fabricsoftenerid")
	public FabricSoftener FabricSoftener;
	public Water(){}
	public Water(int id){this.Id = id;}
	public Water(int id,double temperature, double pH, double hardness, int bleachId,
			int fabricSoftenerId, int detergantId, double bleachAmount,
			double fabricSoftenerAmount, double detergantAmount) {
		setId(id);
		Temperature = temperature;
		PH = pH;
		Hardness = hardness;
		if(this.Bleach == null)
			this.Bleach = new Bleach();
		Bleach.setId(bleachId);
		if(this.FabricSoftener == null)
			this.FabricSoftener = new FabricSoftener();
		FabricSoftener.setId(fabricSoftenerId);
		if(this.Detergant == null)
			this.Detergant = new Detergant();
		this.Detergant.setId(detergantId);
		BleachAmount = bleachAmount;
		FabricSoftenerAmount = fabricSoftenerAmount;
		DetergantAmount = detergantAmount;
	}


	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public double getTemperature() {
		return Temperature;
	}
	public void setTemperature(double temperature) {
		Temperature = temperature;
	}
	public double getPH() {
		return PH;
	}
	public void setPH(double pH) {
		PH = pH;
	}
	public double getHardness() {
		return Hardness;
	}
	public void setHardness(double hardness) {
		Hardness = hardness;
	}
	public double getBleachAmount() {
		return BleachAmount;
	}
	public void setBleachAmount(double bleachAmount) {
		BleachAmount = bleachAmount;
	}
	public double getFabricSoftenerAmount() {
		return FabricSoftenerAmount;
	}
	public void setFabricSoftenerAmount(double fabricSoftenerAmount) {
		FabricSoftenerAmount = fabricSoftenerAmount;
	}
	public double getDetergantAmount() {
		return DetergantAmount;
	}
	public void setDetergantAmount(double detergantAmount) {
		DetergantAmount = detergantAmount;
	}
	public Detergant getDetergant() {
		return Detergant;
	}
	public void setDetergant(Detergant detergant) {
		Detergant = detergant;
	}
	public Bleach getBleach() {
		return Bleach;
	}
	public void setBleach(Bleach bleach) {
		Bleach = bleach;
	}
	public FabricSoftener getFabricSoftener() {
		return FabricSoftener;
	}
	public void setFabricSoftener(FabricSoftener fabricSoftener) {
		FabricSoftener = fabricSoftener;
	}
}
