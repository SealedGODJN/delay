package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Switch")
public class ExSwitch {

    @XmlAttribute(name = "Name")
    private String Name;

    @XmlAttribute(name = "Guid")
    private String Guid;

    @XmlAttribute(name = "ExtGuid")
    private String ExtGuid;

    @XmlAttribute(name = "Network")
    private String Network;

    @XmlAttribute(name = "IpAddress")
    private String IpAddress;

    @XmlElementWrapper(name = "AswPhysPorts")
    @XmlElement(name = "AswPhysPort")
    private List<ExAswPhysPort> AswPhysPorts;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

	public String getNetwork() {
		return Network;
	}

	public void setNetwork(String network) {
		Network = network;
	}

	public String getIpAddress() {
		return IpAddress;
	}

	public void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}

	public List<ExAswPhysPort> getAswPhysPorts() {
		return AswPhysPorts;
	}

	public void setAswPhysPorts(List<ExAswPhysPort> aswPhysPorts) {
		AswPhysPorts = aswPhysPorts;
	}

	@Override
	public String toString() {
		return "ExSwitch [Name=" + Name + ", Guid=" + Guid + ", ExtGuid=" + ExtGuid + ", Network=" + Network
				+ ", IpAddress=" + IpAddress + ", AswPhysPorts=" + AswPhysPorts + "]";
	}

    
}
