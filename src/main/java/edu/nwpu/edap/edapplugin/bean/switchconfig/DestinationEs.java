package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DestinationEs")
public class DestinationEs {

    @XmlAttribute(name = "Name")
    private String Name;

    @XmlElement(name = "VLPath")
    private List<ExVLPath> VLPaths;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<ExVLPath> getVLPaths() {
		return VLPaths;
	}

	public void setVLPaths(List<ExVLPath> vLPaths) {
		VLPaths = vLPaths;
	}

	@Override
	public String toString() {
		return "DestinationEs [Name=" + Name + ", VLPaths=" + VLPaths + "]";
	}

}
