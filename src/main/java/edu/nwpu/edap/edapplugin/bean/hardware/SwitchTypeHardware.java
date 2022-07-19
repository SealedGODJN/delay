package edu.nwpu.edap.edapplugin.bean.hardware;

import java.math.BigDecimal;

public class SwitchTypeHardware extends Hardware{

	protected BigDecimal eSTechLatencyRx;
	
	protected BigDecimal eSTechLatencyTx;

	public BigDecimal getESTechLatencyRx() {
		return eSTechLatencyRx;
	}

	public void setESTechLatencyRx(BigDecimal eSTechLatencyRx) {
		this.eSTechLatencyRx = eSTechLatencyRx;
	}

	public BigDecimal getESTechLatencyTx() {
		return eSTechLatencyTx;
	}

	public void setESTechLatencyTx(BigDecimal eSTechLatencyTx) {
		this.eSTechLatencyTx = eSTechLatencyTx;
	}

	@Override
	public String toString() {
		return "SwitchTypeHardware [ESTechLatencyRx=" + eSTechLatencyRx + ", ESTechLatencyTx=" + eSTechLatencyTx
				+ ", type=" + type + ", name=" + name + ", guid=" + guid + "]";
	}
	
	
}
