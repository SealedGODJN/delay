package edu.nwpu.edap.edapplugin.bean.hardware;

public abstract class Hardware {

	//硬件类型：LRU、GPM、RDIU、ARS、ACS
	protected String type;
	
	//硬件名称
	protected String name;
	
	//硬件GUID
	protected String guid;

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
