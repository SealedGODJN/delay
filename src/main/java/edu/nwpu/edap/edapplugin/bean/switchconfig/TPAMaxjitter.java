package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TPAMaxjitter")
public class TPAMaxjitter {

    @XmlAttribute(name = "SwitchName")
    private String SwitchName;

    @XmlAttribute(name = "MaxjitterValue")
    private double MaxjitterValue;

	public String getSwitchName() {
		return SwitchName;
	}

	public void setSwitchName(String switchName) {
		SwitchName = switchName;
	}

	public double getMaxjitterValue() {
		return MaxjitterValue;
	}

	public void setMaxjitterValue(double maxjitterValue) {
		MaxjitterValue = maxjitterValue;
	}

	@Override
	public String toString() {
		return "TPAMaxjitter [SwitchName=" + SwitchName + ", MaxjitterValue=" + MaxjitterValue + "]";
	}

    
}
