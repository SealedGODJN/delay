package edu.nwpu.edap.edapplugin.bean.port;

public abstract class NonA664TypePortNode extends PortNode{
	
	//端口物理名称
	protected String physPortName;

	public NonA664TypePortNode(String type, String name, String guid) {
		super(type, name, guid);
	}

	public String getPhysPortName() {
		return physPortName;
	}

	public void setPhysPortName(String physPortName) {
		this.physPortName = physPortName;
	}
}
