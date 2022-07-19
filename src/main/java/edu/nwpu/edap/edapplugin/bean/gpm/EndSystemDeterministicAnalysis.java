package edu.nwpu.edap.edapplugin.bean.gpm;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EndSystemDeterministicAnalysis")
public class EndSystemDeterministicAnalysis {

    @XmlElement(name = "RxVLs")
    private RxVLs RxVls;

    @XmlElement(name = "TxVLs")
    private TxVLs TxVls;

	public RxVLs getRxVls() {
		return RxVls;
	}

	public TxVLs getTxVls() {
		return TxVls;
	}

	@Override
	public String toString() {
		return "EndSystemDeterministricAnalysis [RxVls=" + RxVls + ", TxVls=" + TxVls + "]";
	}

}
