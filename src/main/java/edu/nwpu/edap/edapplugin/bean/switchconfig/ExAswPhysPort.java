package edu.nwpu.edap.edapplugin.bean.switchconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AswPhyPort")
public class ExAswPhysPort {

    @XmlAttribute(name = "Name")
    private String Name;

    @XmlAttribute(name = "Guid")
    private String Guid;

    @XmlAttribute(name = "ExtGuid")
    private String ExtGuid;

    @XmlAttribute(name = "PortID")
    private int PortID;

    @XmlAttribute(name = "Medium")
    private String Medium;

    @XmlAttribute(name = "Speed")
    private int Speed;

    @XmlAttribute(name = "State")
    private String State;

    @XmlAttribute(name = "DwellTime")
    private int DwellTime;

    @XmlAttribute(name = "LinkedHwName")
    private String LinkedHwName;

    @XmlAttribute(name = "LinkedHwPort")
    private String LinkedHwPort;

    @XmlAttribute(name = "PriorityQueueDepth1")
    private int PriorityQueueDepth1;

    @XmlAttribute(name = "PriorityQueueDepth2")
    private int PriorityQueueDepth2;

    @XmlAttribute(name = "CalculatedWatermark1")
    private int CalculatedWatermark1;

    @XmlAttribute(name = "CalculatedWatermark2")
    private int CalculatedWatermark2;

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

	public int getPortID() {
		return PortID;
	}

	public void setPortID(int portID) {
		PortID = portID;
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

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public int getDwellTime() {
		return DwellTime;
	}

	public void setDwellTime(int dwellTime) {
		DwellTime = dwellTime;
	}

	public String getLinkedHwName() {
		return LinkedHwName;
	}

	public void setLinkedHwName(String linkedHwName) {
		LinkedHwName = linkedHwName;
	}

	public String getLinkedHwPort() {
		return LinkedHwPort;
	}

	public void setLinkedHwPort(String linkedHwPort) {
		LinkedHwPort = linkedHwPort;
	}

	public int getPriorityQueueDepth1() {
		return PriorityQueueDepth1;
	}

	public void setPriorityQueueDepth1(int priorityQueueDepth1) {
		PriorityQueueDepth1 = priorityQueueDepth1;
	}

	public int getPriorityQueueDepth2() {
		return PriorityQueueDepth2;
	}

	public void setPriorityQueueDepth2(int priorityQueueDepth2) {
		PriorityQueueDepth2 = priorityQueueDepth2;
	}

	public int getCalculatedWatermark1() {
		return CalculatedWatermark1;
	}

	public void setCalculatedWatermark1(int calculatedWatermark1) {
		CalculatedWatermark1 = calculatedWatermark1;
	}

	public int getCalculatedWatermark2() {
		return CalculatedWatermark2;
	}

	public void setCalculatedWatermark2(int calculatedWatermark2) {
		CalculatedWatermark2 = calculatedWatermark2;
	}

	@Override
	public String toString() {
		return "ExAswPhysPort [Name=" + Name + ", Guid=" + Guid + ", ExtGuid=" + ExtGuid + ", PortID=" + PortID
				+ ", Medium=" + Medium + ", Speed=" + Speed + ", State=" + State + ", DwellTime=" + DwellTime
				+ ", LinkedHwName=" + LinkedHwName + ", LinkedHwPort=" + LinkedHwPort + ", PriorityQueueDepth1="
				+ PriorityQueueDepth1 + ", PriorityQueueDepth2=" + PriorityQueueDepth2 + ", CalculatedWatermark1="
				+ CalculatedWatermark1 + ", CalculatedWatermark2=" + CalculatedWatermark2 + "]";
	}

    
}
