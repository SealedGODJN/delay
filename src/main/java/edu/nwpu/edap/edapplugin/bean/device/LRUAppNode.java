package edu.nwpu.edap.edapplugin.bean.device;

public class LRUAppNode extends EndAppNode {

	@Override
	public String toString() {
		return "LRUAppNode [ata=" + ata + ", port=" + port + ", portDef=" + portDef + ", name=" + name + ", guid="
				+ guid + ", samplePeriod=" + samplePeriod + ", refreshPeriod=" + refreshPeriod + ", type=" + type
				+ ", hardware=" + hardware + "]";
	}

	
}
