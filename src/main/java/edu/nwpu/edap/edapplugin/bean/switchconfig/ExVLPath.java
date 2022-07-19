package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "VLPath")
public class ExVLPath {

    @XmlAttribute(name = "PathName")
    private String PathName;

    @XmlAttribute(name = "Guid")
    private String Guid;

    @XmlAttribute(name = "ExtGuid")
    private String ExtGuid;

    @XmlAttribute(name = "MaxPhysLatency")
    private double MaxPhysLatency;

    @XmlAttribute(name = "MaxPhysJitter")
    private double MaxPhysJitter;

    @XmlAttribute(name = "MaxSkew")
    private double MaxSkew;

    @XmlAttribute(name = "PhysJitter")
    private double PhysJitter;

    @XmlAttribute(name = "PhysLatency")
    private double PhysLatency;

    @XmlElementWrapper(name = "Hops")
    @XmlElement(name = "Hop")
    private List<Hop> Hops;

	public String getPathName() {
		return PathName;
	}

	public void setPathName(String pathName) {
		PathName = pathName;
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

	public double getMaxPhysLatency() {
		return MaxPhysLatency;
	}

	public void setMaxPhysLatency(double maxPhysLatency) {
		MaxPhysLatency = maxPhysLatency;
	}

	public double getMaxPhysJitter() {
		return MaxPhysJitter;
	}

	public void setMaxPhysJitter(double maxPhysJitter) {
		MaxPhysJitter = maxPhysJitter;
	}

	public double getMaxSkew() {
		return MaxSkew;
	}

	public void setMaxSkew(double maxSkew) {
		MaxSkew = maxSkew;
	}

	public double getPhysJitter() {
		return PhysJitter;
	}

	public void setPhysJitter(double physJitter) {
		PhysJitter = physJitter;
	}

	public double getPhysLatency() {
		return PhysLatency;
	}

	public void setPhysLatency(double physLatency) {
		PhysLatency = physLatency;
	}

	public List<Hop> getHops() {
		return Hops;
	}

	public void setHops(List<Hop> hops) {
		Hops = hops;
	}

	@Override
	public String toString() {
		return "ExVLPath [PathName=" + PathName + ", Guid=" + Guid + ", ExtGuid=" + ExtGuid + ", MaxPhysLatency="
				+ MaxPhysLatency + ", MaxPhysJitter=" + MaxPhysJitter + ", MaxSkew=" + MaxSkew + ", PhysJitter="
				+ PhysJitter + ", PhysLatency=" + PhysLatency + ", Hops=" + Hops + "]";
	}

    
}
