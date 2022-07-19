package edu.nwpu.edap.edapplugin.bean.gpm;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RxVL")
public class RxVL {

    @XmlAttribute(name = "ID")
    private int ID;

    @XmlAttribute(name = "MaxMessageLatency")
    private double MaxMessageLatency;

    @XmlElement(name = "RxComPort")
    private List<RxComPort> RxComPorts;

	public int getID() {
		return ID;
	}

	public double getMaxMessageLatency() {
		return MaxMessageLatency;
	}

	public List<RxComPort> getRxComPorts() {
		return RxComPorts;
	}

	@Override
	public String toString() {
		return "RxVL [ID=" + ID + ", MaxMessageLatency=" + MaxMessageLatency + ", RxComPorts=" + RxComPorts + "]";
	}

    
}
