package edu.nwpu.edap.edapplugin.bean.gpm;

public class RxComPort {

    private int PortID;

    private int ReceiveComportBacklog;

	public int getPortID() {
		return PortID;
	}

	public int getReceiveComportBacklog() {
		return ReceiveComportBacklog;
	}

	@Override
	public String toString() {
		return "RxComPort [PortID=" + PortID + ", ReceiveComportBacklog=" + ReceiveComportBacklog + "]";
	}

    
}
