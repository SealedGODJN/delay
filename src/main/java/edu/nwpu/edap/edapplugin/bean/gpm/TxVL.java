package edu.nwpu.edap.edapplugin.bean.gpm;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TxVL")
public class TxVL {

    @XmlAttribute(name = "ID")
    private int ID;

    @XmlAttribute(name = "BAG")
    private double BAG;

    @XmlAttribute(name = "MaxLatency")
    private double MaxLatency;

    @XmlAttribute(name = "MinLatency")
    private double MinLatency;

    @XmlAttribute(name = "MaxJitter")
    private double MaxJitter;

    @XmlAttribute(name = "BAGDelay")
    private double BAGDelay;

    @XmlAttribute(name = "NetCalMaxLatency")
    private double NetCalMaxLatency;

    @XmlAttribute(name = "NetCalMaxJitter")
    private double NetCalMaxJitter;

    @XmlElement(name = "TxComPort")
    private List<TxComPort> TxComPorts;

	public int getID() {
		return ID;
	}

	public double getBAG() {
		return BAG;
	}

	public double getMaxLatency() {
		return MaxLatency;
	}

	public double getMinLatency() {
		return MinLatency;
	}

	public double getMaxJitter() {
		return MaxJitter;
	}

	public double getBAGDelay() {
		return BAGDelay;
	}

	public double getNetCalMaxLatency() {
		return NetCalMaxLatency;
	}

	public double getNetCalMaxJitter() {
		return NetCalMaxJitter;
	}

	public List<TxComPort> getTxComPorts() {
		return TxComPorts;
	}

	@Override
	public String toString() {
		return "TxVL [ID=" + ID + ", BAG=" + BAG + ", MaxLatency=" + MaxLatency + ", MinLatency=" + MinLatency
				+ ", MaxJitter=" + MaxJitter + ", BAGDelay=" + BAGDelay + ", NetCalMaxLatency=" + NetCalMaxLatency
				+ ", NetCalMaxJitter=" + NetCalMaxJitter + ", TxComPorts=" + TxComPorts + "]";
	}

}
