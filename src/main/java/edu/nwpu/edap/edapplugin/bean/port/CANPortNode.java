package edu.nwpu.edap.edapplugin.bean.port;

public class CANPortNode extends NonA664TypePortNode{
	
	//端口的CANBiteRate
	protected String canBiteRate;
	
	//端口消息的协议
	protected String canMessageProtocolType;

	public CANPortNode(String type, String name, String guid) {
		super(type, name, guid);
	}
	
	public String getCanBiteRate() {
		return canBiteRate;
	}

	public void setCanBiteRate(String canBiteRate) {
		this.canBiteRate = canBiteRate;
	}

	public String getCanMessageProtocolType() {
		return canMessageProtocolType;
	}

	public void setCanMessageProtocolType(String canMessageProtocolType) {
		this.canMessageProtocolType = canMessageProtocolType;
	}

	@Override
	public String toString() {
		return "CANPortNode [canBiteRate=" + canBiteRate + ", canMessageProtocolType=" + canMessageProtocolType
				+ ", physPortName=" + physPortName + ", type=" + type + ", name=" + name + ", guid=" + guid + "]";
	}

}
