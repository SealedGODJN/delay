package edu.nwpu.edap.edapplugin.bean.port;

public class HFQueuingPortNode extends A664QueuingPortNode{

	public HFQueuingPortNode(String type, String name, String guid) {
		super(type, name, guid);
	}

	@Override
	public String toString() {
		return "HFQueuingPortNode [queueLength=" + queueLength + ", type=" + type + ", name=" + name + ", guid=" + guid
				+ "]";
	}

}
