package edu.nwpu.edap.edapplugin.bean.gpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RxVLs")
public class RxVLs {

    @XmlElement(name = "RxVL")
    private List<RxVL> RxVLs;

	public List<RxVL> getRxVLs() {
		return RxVLs;
	}

	@Override
	public String toString() {
		return "RxVLs [RxVLs=" + RxVLs + "]";
	}
}
