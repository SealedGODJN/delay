package edu.nwpu.edap.edapplugin.bean.device;

import edu.nwpu.edap.edapplugin.bean.hardware.Hardware;

public class BaseDeviceAndSwitchNode {

	//设备节点种类，包括ARS、ACS、A653Application、HostedFunction、RemoteGateway
	protected String type;
	
	//硬件信息
	protected Hardware hardware;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Hardware getHardware() {
		return hardware;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}
	
	
}
