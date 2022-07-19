package edu.nwpu.edap.edapplugin.bean.device;

import edu.nwpu.edap.edapplugin.bean.port.PortNode;

public class RIUAppNode extends BaseDeviceNode {

	//RDIU的类型
	protected String RIUType;
	
	//RDIU的发出端口
	protected PortNode pubPort;
	
	//RDIU的接收端口
	protected PortNode subPort;
	
	//延迟
	protected double latency;
	
	//抖动
	protected double jitter;
	
	//延迟上限
	protected double latencyThreshold;
	
	//抖动上限
	protected double JitterThreshold;
	
	//是否参与延迟计算
	protected boolean count;

	public String getRIUType() {
		return RIUType;
	}

	public void setRIUType(String rIUType) {
		RIUType = rIUType;
	}

	public PortNode getPubPort() {
		return pubPort;
	}

	public void setPubPort(PortNode pubPort) {
		this.pubPort = pubPort;
	}

	public PortNode getSubPort() {
		return subPort;
	}

	public void setSubPort(PortNode subPort) {
		this.subPort = subPort;
	}

	public double getLatency() {
		return latency;
	}

	public void setLatency(double latency) {
		this.latency = latency;
	}

	public double getJitter() {
		return jitter;
	}

	public void setJitter(double jitter) {
		this.jitter = jitter;
	}

	public double getLatencyThreshold() {
		return latencyThreshold;
	}

	public void setLatencyThreshold(double latencyThreshold) {
		this.latencyThreshold = latencyThreshold;
	}

	public double getJitterThreshold() {
		return JitterThreshold;
	}

	public void setJitterThreshold(double jitterThreshold) {
		JitterThreshold = jitterThreshold;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "RIUAppNode [RIUType=" + RIUType + ", pubPort=" + pubPort + ", subPort=" + subPort + ", latency="
				+ latency + ", jitter=" + jitter + ", latencyThreshold=" + latencyThreshold + ", JitterThreshold="
				+ JitterThreshold + ", name=" + name + ", guid=" + guid + ", samplePeriod=" + samplePeriod
				+ ", refreshPeriod=" + refreshPeriod + ", count=" + count + ", type=" + type + ", hardware=" + hardware
				+ "]";
	}
	
	
}
