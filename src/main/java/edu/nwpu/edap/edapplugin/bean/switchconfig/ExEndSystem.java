package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EndSystem")
public class ExEndSystem {

    @XmlAttribute(name = "Name")
    private String Name;

    @XmlAttribute(name = "Guid")
    private String Guid;

    @XmlAttribute(name = "ExtGuid")
    private String ExtGuid;

    @XmlElementWrapper(name = "AsePhysPorts")
    @XmlElement(name = "AesPhyPort")
    private List<ExAesPhysPort> AesPhysPorts;

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

	public List<ExAesPhysPort> getAesPhysPorts() {
		return AesPhysPorts;
	}

	public void setAesPhysPorts(List<ExAesPhysPort> aesPhysPorts) {
		AesPhysPorts = aesPhysPorts;
	}

	@Override
	public String toString() {
		return "ExEndSystem [Name=" + Name + ", Guid=" + Guid + ", ExtGuid=" + ExtGuid + ", AesPhysPorts="
				+ AesPhysPorts + "]";
	}

    
}
