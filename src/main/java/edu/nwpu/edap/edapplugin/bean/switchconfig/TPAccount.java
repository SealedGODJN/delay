package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TPAccount")
public class TPAccount {

    @XmlAttribute(name = "TPAName")
    private String TPAName;

    @XmlAttribute(name = "Guid")
    private String Guid;

    @XmlAttribute(name = "ExtGuid")
    private String ExtGuid;

    @XmlAttribute(name = "MinBAG")
    private double MinBAG;

    @XmlAttribute(name = "MaxLineSize")
    private int MaxLineSize;

    @XmlElementWrapper(name = "VLCollection")
    @XmlElement(name = "VLRef")
    private List<VLRef> VLCollection;

    @XmlElement(name = "TPAMaxjitter")
    private List<TPAMaxjitter> TPAMaxjitters;

	public String getTPAName() {
		return TPAName;
	}

	public void setTPAName(String tPAName) {
		TPAName = tPAName;
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

	public double getMinBAG() {
		return MinBAG;
	}

	public void setMinBAG(double minBAG) {
		MinBAG = minBAG;
	}

	public int getMaxLineSize() {
		return MaxLineSize;
	}

	public void setMaxLineSize(int maxLineSize) {
		MaxLineSize = maxLineSize;
	}

	public List<VLRef> getVLCollection() {
		return VLCollection;
	}

	public void setVLCollection(List<VLRef> vLCollection) {
		VLCollection = vLCollection;
	}

	public List<TPAMaxjitter> getTPAMaxjitters() {
		return TPAMaxjitters;
	}

	public void setTPAMaxjitters(List<TPAMaxjitter> tPAMaxjitters) {
		TPAMaxjitters = tPAMaxjitters;
	}

	@Override
	public String toString() {
		return "TPAccount [TPAName=" + TPAName + ", Guid=" + Guid + ", ExtGuid=" + ExtGuid + ", MinBAG=" + MinBAG
				+ ", MaxLineSize=" + MaxLineSize + ", VLCollection=" + VLCollection + ", TPAMaxjitters=" + TPAMaxjitters
				+ "]";
	}
}
