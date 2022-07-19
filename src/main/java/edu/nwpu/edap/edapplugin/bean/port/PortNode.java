package edu.nwpu.edap.edapplugin.bean.port;

public abstract class PortNode {
	
	//端口类型
	protected String type;
	
	//端口名称
	protected String name;
	
	//端口Guid
	protected String guid;

	public PortNode(String type, String name, String guid) {
		super();
		this.type = type;
		this.name = name;
		this.guid = guid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
