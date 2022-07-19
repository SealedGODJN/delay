package edu.nwpu.edap.edapplugin.bean.device;

import edu.nwpu.edap.edapplugin.bean.port.PortNode;

public class EndAppNode extends BaseDeviceNode {

	//端应用的ata周期
	protected String ata;
	
	//端应用的端口信息
	protected PortNode port;
	
	//端应用的端口定义Guid
	protected String portDef;
	
	//端应用的方向
	protected String direct;

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	public PortNode getPort() {
		return port;
	}

	public void setPort(PortNode port) {
		this.port = port;
	}

	public String getPortDef() {
		return portDef;
	}

	public void setPortDef(String portDef) {
		this.portDef = portDef;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}
	
	
}
