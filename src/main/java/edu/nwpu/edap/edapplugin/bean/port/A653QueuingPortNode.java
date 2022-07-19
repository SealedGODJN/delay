package edu.nwpu.edap.edapplugin.bean.port;

public class A653QueuingPortNode extends A664QueuingPortNode {

	public A653QueuingPortNode(String type, String name, String guid) {
		super(type, name, guid);
	}

	@Override
	public String toString() {
		return "A653QueuingPortNode [queueLength=" + queueLength + ", type=" + type + ", name=" + name + ", guid="
				+ guid + "]";
	}

}
