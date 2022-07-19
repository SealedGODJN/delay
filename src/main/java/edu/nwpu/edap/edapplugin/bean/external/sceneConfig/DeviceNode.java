package edu.nwpu.edap.edapplugin.bean.external.sceneConfig;

public class DeviceNode {

    /**
     * 单个节点的种类
     */
    private String type;

    /**
     * 单个节点在该场景的位置
     */
    private String pos;
    
    /**
     * 该设备节点的上限延迟，非664消息的LRU不存在
     */
    private double latencyThreshold = -1;
    
    /**
     * 该设备节点的上限抖动，LRU不存在
     */
    private double jitterThreshold = -1;
    
    /**
     * 该设备节点是否参与延迟计算，true为参与
     */
    private boolean count = true;

	public DeviceNode(String type, String pos, double latencyThreshold, double jitterThreshold, boolean count) {
		super();
		this.type = type;
		this.pos = pos;
		this.latencyThreshold = latencyThreshold;
		this.jitterThreshold = jitterThreshold;
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public double getLatencyThreshold() {
		return latencyThreshold;
	}

	public void setLatencyThreshold(double latencyThreshold) {
		this.latencyThreshold = latencyThreshold;
	}

	public double getJitterThreshold() {
		return jitterThreshold;
	}

	public void setJitterThreshold(double jitterThreshold) {
		this.jitterThreshold = jitterThreshold;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "DeviceNode [type=" + type + ", pos=" + pos + ", latencyThreshold=" + latencyThreshold
				+ ", jitterThreshold=" + jitterThreshold + ", count=" + count + "]";
	}
    
}
