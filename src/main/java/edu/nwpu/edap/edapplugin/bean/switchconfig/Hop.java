package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Hop")
public class Hop {

    @XmlAttribute(name = "SwitchName")
    private String SwitchName;

    @XmlAttribute(name = "IngressPort")
    private String IngressPort;

    @XmlAttribute(name = "EgressPort")
    private String EgressPort;

	public String getSwitchName() {
		return SwitchName;
	}

	public void setSwitchName(String switchName) {
		SwitchName = switchName;
	}

	public String getIngressPort() {
		return IngressPort;
	}

	public void setIngressPort(String ingressPort) {
		IngressPort = ingressPort;
	}

	public String getEgressPort() {
		return EgressPort;
	}

	public void setEgressPort(String egressPort) {
		EgressPort = egressPort;
	}

	@Override
	public String toString() {
		return "Hop [SwitchName=" + SwitchName + ", IngressPort=" + IngressPort + ", EgressPort=" + EgressPort + "]";
	}

    
}
