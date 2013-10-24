package models;

import java.io.Serializable;
 
public class Water implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	private int Id;
	private double Temperature;
	private double PH;
	private double Hardness;
	private int BleachId;
	private int FabricSoftenerId;
	private int DetergantId;
	
	private double BleachAmount;
	
	private double FabricSoftenerAmount;
	private double DetergantAmount; 
	private Detergant Detergant; 
	private Bleach Bleach; 
	public FabricSoftener FabricSoftener;
	public Water(){}
	public Water(int id){this.Id = id;}
	public Water(double temperature, double pH, double hardness, int bleachId,
			int fabricSoftenerId, int detergantId, double bleachAmount,
			double fabricSoftenerAmount, double detergantAmount) {
		Temperature = temperature;
		PH = pH;
		Hardness = hardness;
		BleachId = bleachId;
		FabricSoftenerId = fabricSoftenerId;
		DetergantId = detergantId;
		BleachAmount = bleachAmount;
		FabricSoftenerAmount = fabricSoftenerAmount;
		DetergantAmount = detergantAmount;
	}

	public int getBleachId() {
		return BleachId;
	}

	public void setBleachId(int bleachId) {
		BleachId = bleachId;
	}

	public int getFabricSoftenerId() {
		return FabricSoftenerId;
	}

	public void setFabricSoftenerId(int fabricSoftenerId) {
		FabricSoftenerId = fabricSoftenerId;
	}

	public int getDetergantId() {
		return DetergantId;
	}

	public void setDetergantId(int detergantId) {
		DetergantId = detergantId;
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
