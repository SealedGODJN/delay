package edu.nwpu.edap.edapplugin.bean.gpm;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TxVLs")
public class TxVLs {

    @XmlAttribute(name = "MaxTxVLMaxLatency")
    private double MaxTxVLMaxLatency;

    @XmlAttribute(name = "MaxTxVLMaxJitter")
    private double MaxTxVLMaxJitter;

    @XmlAttribute(name = "MaxNetCalMaxLatency")
    private double MaxNetCalMaxLatency;

    @XmlAttribute(name = "MaxNetCalMaxJitter")
    private double MaxNetCalMaxJitter;

    @XmlAttribute(name = "TxBandWidth")
    private double TxBandWidth;

    @XmlAttribute(name = "TxBandWidthRatio")
    private double TxBandWidthRatio;

    @XmlAttribute(name = "GlobalMaxJitter")
    private double GlobalMaxJitter;

    @XmlAttribute(name = "SchedulingModel")
    private String SchedulingModel;

    @XmlElement(name = "TxVL")
    private List<TxVL> TxVLs;

	public double getMaxTxVLMaxLatency() {
		return MaxTxVLMaxLatency;
	}

	public double getMaxTxVLMaxJitter() {
		return MaxTxVLMaxJitter;
	}

	public double getMaxNetCalMaxLatency() {
		return MaxNetCalMaxLatency;
	}

	public double getMaxNetCalMaxJitter() {
		return MaxNetCalMaxJitter;
	}

	public double getTxBandWidth() {
		return TxBandWidth;
	}

	public double getTxBandWidthRatio() {
		return TxBandWidthRatio;
	}

	public double getGlobalMaxJitter() {
		return GlobalMaxJitter;
	}

	public String getSchedulingModel() {
		return SchedulingModel;
	}

	public List<TxVL> getTxVLs() {
		return TxVLs;
	}

	@Override
	public String toString() {
		return "TxVLs [MaxTxVLMaxLatency=" + MaxTxVLMaxLatency + ", MaxTxVLMaxJitter=" + MaxTxVLMaxJitter
				+ ", MaxNetCalMaxLatency=" + MaxNetCalMaxLatency + ", MaxNetCalMaxJitter=" + MaxNetCalMaxJitter
				+ ", TxBandWidth=" + TxBandWidth + ", TxBandWidthRatio=" + TxBandWidthRatio + ", GlobalMaxJitter="
				+ GlobalMaxJitter + ", SchedulingModel=" + SchedulingModel + ", TxVLs=" + TxVLs + "]";
	}

    
}
