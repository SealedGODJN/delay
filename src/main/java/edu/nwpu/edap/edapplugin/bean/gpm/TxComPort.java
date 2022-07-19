package edu.nwpu.edap.edapplugin.bean.gpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TxComPort")
public class TxComPort {

    @XmlAttribute(name = "PortID")
    private int PortID;

    @XmlAttribute(name = "SubVLID")
    private int SubVLID;

    @XmlAttribute(name = "TransComportBacklog")
    private int TransComportBacklog;

    @XmlAttribute(name = "MaxMessageLatency")
    private double MaxMessageLatency;

    @XmlAttribute(name = "NetCalMaxMessageLatency")
    private double NetCalMaxMessageLatency;

	public int getPortID() {
		return PortID;
	}

	public int getSubVLID() {
		return SubVLID;
	}

	public int getTransComportBacklog() {
		return TransComportBacklog;
	}

	public double getMaxMessageLatency() {
		return MaxMessageLatency;
	}

	public double getNetCalMaxMessageLatency() {
		return NetCalMaxMessageLatency;
	}

	@Override
	public String toString() {
		return "TxComPort [PortID=" + PortID + ", SubVLID=" + SubVLID + ", TransComportBacklog=" + TransComportBacklog
				+ ", MaxMessageLatency=" + MaxMessageLatency + ", NetCalMaxMessageLatency=" + NetCalMaxMessageLatency
				+ "]";
	}

    
}
