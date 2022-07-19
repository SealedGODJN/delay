package edu.nwpu.edap.edapplugin.bean.device;

public class GPMAppNode extends EndAppNode {

	// 延迟
	protected double latency;

	// 抖动
	protected double jitter;

	// 延迟上限
	protected double latencyThreshold;

	// 抖动上限
	protected double JitterThreshold;
	
	//最大消息延迟，区段接口文件中获得
	protected double maxMessageLatency;
	
	//是否参与延迟计算
	protected boolean count;

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

	public double getMaxMessageLatency() {
		return maxMessageLatency;
	}

	public void setMaxMessageLatency(double maxMessageLatency) {
		this.maxMessageLatency = maxMessageLatency;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "GPMAppNode [latency=" + latency + ", jitter=" + jitter + ", latencyThreshold=" + latencyThreshold
				+ ", JitterThreshold=" + JitterThreshold + ", maxMessageLatency=" + maxMessageLatency + ", ata=" + ata
				+ ", port=" + port + ", portDef=" + portDef + ", name=" + name + ", guid=" + guid + ", samplePeriod="
				+ samplePeriod + ", refreshPeriod=" + refreshPeriod + ", count=" + count + ", type=" + type
				+ ", hardware=" + hardware + "]";
	}
	
}
