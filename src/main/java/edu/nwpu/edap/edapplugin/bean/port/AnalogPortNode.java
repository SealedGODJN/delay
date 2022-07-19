package edu.nwpu.edap.edapplugin.bean.port;

public class AnalogPortNode extends NonA664TypePortNode{

	public AnalogPortNode(String type, String name, String guid) {
		super(type, name, guid);
	}

	@Override
	public String toString() {
		return "AnalogPortNode [physPortName=" + physPortName + ", type=" + type + ", name=" + name + ", guid=" + guid
				+ "]";
	}
	
	
}
