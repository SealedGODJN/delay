package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AesPhyPort")
public class ExAesPhysPort {

    @XmlAttribute(name = "Name")
    private String Name;

    @XmlAttribute(name = "Guid")
    private String Guid;

    @XmlAttribute(name = "ExtGuid")
    private String ExtGuid;

    @XmlAttribute(name = "Medium")
    private String Medium;

    @XmlAttribute(name = "Speed")
    private int Speed;

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

	public String getMedium() {
		return Medium;
	}

	public void setMedium(String medium) {
		Medium = medium;
	}

	public int getSpeed() {
		return Speed;
	}

	public void setSpeed(int speed) {
		Speed = speed;
	}

	@Override
	public String toString() {
		return "ExAesPhysPort [Name=" + Name + ", Guid=" + Guid + ", ExtGuid=" + ExtGuid + ", Medium=" + Medium
				+ ", Speed=" + Speed + "]";
	}

    
}
