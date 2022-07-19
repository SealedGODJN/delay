package edu.nwpu.edap.edapplugin.bean.device;

import java.math.BigDecimal;

public abstract class BaseDeviceNode extends BaseDeviceAndSwitchNode{
	
	//应用名称
	protected String name;
	
	protected String guid;
	
	//采样周期
	protected int samplePeriod;
	
	//刷新周期
	protected BigDecimal refreshPeriod;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSamplePeriod() {
		return samplePeriod;
	}

	public void setSamplePeriod(int samplePeriod) {
		this.samplePeriod = samplePeriod;
	}

	public BigDecimal getRefreshPeriod() {
		return refreshPeriod;
	}	

	public void setRefreshPeriod(BigDecimal refreshPeriod) {
		this.refreshPeriod = refreshPeriod;
	}
	
}
