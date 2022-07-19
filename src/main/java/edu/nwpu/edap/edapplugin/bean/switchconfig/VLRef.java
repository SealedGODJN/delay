package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "VLRef")
public class VLRef {

    @XmlAttribute(name = "VLID")
    private int VLID;

	public int getVLID() {
		return VLID;
	}

	public void setVLID(int vLID) {
		VLID = vLID;
	}

	@Override
	public String toString() {
		return "VLRef [VLID=" + VLID + "]";
	}
}
