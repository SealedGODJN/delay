package edu.nwpu.edap.edapplugin.bean.port;

public class HFSamplingPortNode extends A664SamplingPortNode{

	public HFSamplingPortNode(String type, String name, String guid) {
		super(type, name, guid);
	}

	@Override
	public String toString() {
		return "HFSamplingPortNode [type=" + type + ", name=" + name + ", guid=" + guid + "]";
	}

}
