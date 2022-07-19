package edu.nwpu.edap.edapplugin.bean.port;

public class A429PortNode extends NonA664TypePortNode{
	
	//端口比特速率
	protected String biteRate;	

	public A429PortNode(String type, String name, String guid) {
		super(type, name, guid);
	}

	public String getBiteRate() {
		return biteRate;
	}

	public void setBiteRate(String biteRate) {
		this.biteRate = biteRate;
	}

	@Override
	public String toString() {
		return "A429PortNode [biteRate=" + biteRate + ", physPortName=" + physPortName + ", type=" + type + ", name="
				+ name + ", guid=" + guid + "]";
	}
	
}
