package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "VL")
public class ExVL {

    @XmlAttribute(name = "VLName")
    private String VLName;

    @XmlAttribute(name = "Guid")
    private String Guid;

    @XmlAttribute(name = "ExtGuid")
    private String ExtGuid;

    @XmlAttribute(name = "VLID")
    private int VLID;

    @XmlAttribute(name = "BAG")
    private double BAG;

    @XmlAttribute(name = "MaxFrameSize")
    private int MaxFrameSize;

    @XmlAttribute(name = "MinFrameSize")
    private int MinFrameSize;

    @XmlAttribute(name = "Priority")
    private int Priority;

    @XmlAttribute(name = "SourceEsName")
    private String SourceEsName;

    @XmlAttribute(name = "AesJitter")
    private double AesJitter;

    @XmlElementWrapper(name = "DestinationEsCollection")
    @XmlElement(name = "DestinationEs")
    private List<DestinationEs> DestinationEsCollection;

	public String getVLName() {
		return VLName;
	}

	public void setVLName(String vLName) {
		VLName = vLName;
	}

	public String getGuid() {
		return Guid;
	}

	public void setGuid(String guid) {
		Guid = guid;
	}

	public String getExtGuid() {
		return ExtGuid;
	}

	public void setExtGuid(String extGuid) {
		ExtGuid = extGuid;
	}

	public int getVLID() {
		return VLID;
	}

	public void setVLID(int vLID) {
		VLID = vLID;
	}

	public double getBAG() {
		return BAG;
	}

	public void setBAG(double bAG) {
		BAG = bAG;
	}

	public int getMaxFrameSize() {
		return MaxFrameSize;
	}

	public void setMaxFrameSize(int maxFrameSize) {
		MaxFrameSize = maxFrameSize;
	}

	public int getMinFrameSize() {
		return MinFrameSize;
	}

	public void setMinFrameSize(int minFrameSize) {
		MinFrameSize = minFrameSize;
	}

	public int getPriority() {
		return Priority;
	}

	public void setPriority(int priority) {
		Priority = priority;
	}

	public String getSourceEsName() {
		return SourceEsName;
	}

	public void setSourceEsName(String sourceEsName) {
		SourceEsName = sourceEsName;
	}

	public double getAesJitter() {
		return AesJitter;
	}

	public void setAesJitter(double aesJitter) {
		AesJitter = aesJitter;
	}

	public List<DestinationEs> getDestinationEsCollection() {
		return DestinationEsCollection;
	}

	public void setDestinationEsCollection(List<DestinationEs> destinationEsCollection) {
		DestinationEsCollection = destinationEsCollection;
	}

	@Override
	public String toString() {
		return "ExVL [VLName=" + VLName + ", Guid=" + Guid + ", ExtGuid=" + ExtGuid + ", VLID=" + VLID + ", BAG=" + BAG
				+ ", MaxFrameSize=" + MaxFrameSize + ", MinFrameSize=" + MinFrameSize + ", Priority=" + Priority
				+ ", SourceEsName=" + SourceEsName + ", AesJitter=" + AesJitter + ", DestinationEsCollection="
				+ DestinationEsCollection + "]";
	}

    
}
