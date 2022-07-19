package edu.nwpu.edap.edapplugin.bean.switchconfig;

public class VLInfo {
	
	private double physJitter;
	
	private double physLatency;

	public VLInfo(double physJitter, double physLatency) {
		super();
		this.physJitter = physJitter;
		this.physLatency = physLatency;
	}

	public double getPhysJitter() {
		return physJitter;
	}

	public void setPhysJitter(double physJitter) {
		this.physJitter = physJitter;
	}

	public double getPhysLatency() {
		return physLatency;
	}

	public void setPhysLatency(double physLatency) {
		this.physLatency = physLatency;
	}

	@Override
	public String toString() {
		return "VLInfo [physJitter=" + physJitter + ", physLatency=" + physLatency + "]";
	}
	
	
}
