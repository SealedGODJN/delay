package edu.nwpu.edap.edapplugin.bean.hardware;

import java.math.BigDecimal;

public abstract class IncludeA664MsgTypeHardware extends Hardware{

	protected BigDecimal ESTechLatencyRx;
	
	protected BigDecimal ESTechLatencyTx;
	
	protected String ESTransmitPolicy;
	
	protected String ESHighIntegrity;

	public BigDecimal getESTechLatencyRx() {
		return ESTechLatencyRx;
	}

	public void setESTechLatencyRx(BigDecimal eSTechLatencyRx) {
		ESTechLatencyRx = eSTechLatencyRx;
	}

	public BigDecimal getESTechLatencyTx() {
		return ESTechLatencyTx;
	}

	public void setESTechLatencyTx(BigDecimal eSTechLatencyTx) {
		ESTechLatencyTx = eSTechLatencyTx;
	}

	public String getESTransmitPolicy() {
		return ESTransmitPolicy;
	}

	public void setESTransmitPolicy(String eSTransmitPolicy) {
		ESTransmitPolicy = eSTransmitPolicy;
	}

	public String getESHighIntegrity() {
		return ESHighIntegrity;
	}

	public void setESHighIntegrity(String eSHighIntegrity) {
		ESHighIntegrity = eSHighIntegrity;
	}

}
