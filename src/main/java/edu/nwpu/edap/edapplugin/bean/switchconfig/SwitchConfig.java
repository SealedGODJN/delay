package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SwitchConfig")
public class SwitchConfig {

    @XmlAttribute(name = "XsdVersion")
    private String XsdVersion;

    @XmlAttribute(name = "InputCRC32")
    private String InputCRC32;

    @XmlAttribute(name = "OutputCRC32")
    private String OutputCRC32;

    @XmlAttribute(name = "SwitchLoadPN")
    private String SwitchLoadPN;

    @XmlElementWrapper(name = "Switches")
    @XmlElement(name = "Switch")
    private List<ExSwitch> Switches;

    @XmlElementWrapper(name = "EndSystems")
    @XmlElement(name = "EndSystem")
    private List<ExEndSystem> EndSystems;

    @XmlElementWrapper(name = "VLs")
    @XmlElement(name = "VL")
    private List<ExVL> VLs;

    @XmlElementWrapper(name = "TrafficPolicingAccounts")
    @XmlElement(name = "TPAccount")
    private List<TPAccount> TrafficPolicingAccounts;

	public String getXsdVersion() {
		return XsdVersion;
	}

	public void setXsdVersion(String xsdVersion) {
		XsdVersion = xsdVersion;
	}

	public String getInputCRC32() {
		return InputCRC32;
	}

	public void setInputCRC32(String inputCRC32) {
		InputCRC32 = inputCRC32;
	}

	public String getOutputCRC32() {
		return OutputCRC32;
	}

	public void setOutputCRC32(String outputCRC32) {
		OutputCRC32 = outputCRC32;
	}

	public String getSwitchLoadPN() {
		return SwitchLoadPN;
	}

	public void setSwitchLoadPN(String switchLoadPN) {
		SwitchLoadPN = switchLoadPN;
	}

	public List<ExSwitch> getSwitches() {
		return Switches;
	}

	public void setSwitches(List<ExSwitch> switches) {
		Switches = switches;
	}

	public List<ExEndSystem> getEndSystems() {
		return EndSystems;
	}

	public void setEndSystems(List<ExEndSystem> endSystems) {
		EndSystems = endSystems;
	}

	public List<ExVL> getVLs() {
		return VLs;
	}

	public void setVLs(List<ExVL> vLs) {
		VLs = vLs;
	}

	public List<TPAccount> getTrafficPolicingAccounts() {
		return TrafficPolicingAccounts;
	}

	public void setTrafficPolicingAccounts(List<TPAccount> trafficPolicingAccounts) {
		TrafficPolicingAccounts = trafficPolicingAccounts;
	}

	@Override
	public String toString() {
		return "SwitchConfig [XsdVersion=" + XsdVersion + ", InputCRC32=" + InputCRC32 + ", OutputCRC32=" + OutputCRC32
				+ ", SwitchLoadPN=" + SwitchLoadPN + ", Switches=" + Switches + ", EndSystems=" + EndSystems + ", VLs="
				+ VLs + ", TrafficPolicingAccounts=" + TrafficPolicingAccounts + "]";
	}
    
}
