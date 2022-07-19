package edu.nwpu.edap.edapplugin.bean.external;

import com.alibaba.fastjson.annotation.JSONField;

public class ExternalRDIU {

    @JSONField(name = "key")
    private int key;

    @JSONField(name = "Subscribing Port SamplePeriod")
    private int subPortSamplePeriod;

    @JSONField(name = "Receive Queue Length")
    private int receiveQueueLength;

    @JSONField(name = "Publishing Port RefreshPeriod")
    private int pubPortRefreshPeriod;

    @JSONField(name = "Transmit Queue Length")
    private int transmitQueueLength;

    @JSONField(name = "PublishedParameterName")
    private String pubParameterName;

    @JSONField(name = "PublishedParameterGuid")
    private String pubParameterGuid;

    @JSONField(name = "SubscribedParameterName")
    private String subParameterName;

    @JSONField(name = "SubscribedParameterGuid")
    private String subParameterGuid;

    private String RDIU_1Name;
    private String RDIU_1Type;
    private String RDIU_1AppName;
    private String RDIU_1AppInputPortName;
    private int RDIU_1AppInputPortSamplePeriod;
    private String RDIU_1AppOutputPortName;
    private int RDIU_1AppOutputPortRefreshPeriod;
    private String RIU_1LatencyCritical;
    private int RDIU_1Latency;
    private int RDIU_1Jitter;

    private String RDIU_2Name;
    private String RDIU_2Type;
    private String RDIU_2AppName;
    private String RDIU_2AppInputPortName;
    private int RDIU_2AppInputPortSamplePeriod;
    private String RDIU_2AppOutputPortName;
    private int RDIU_2AppOutputPortRefreshPeriod;
    private String RIU_2LatencyCritical;
    private int RDIU_2Latency;
    private int RDIU_2Jitter;

    private String RDIU_3Name;
    private String RDIU_3Type;
    private String RDIU_3AppName;
    private String RDIU_3AppInputPortName;
    private int RDIU_3AppInputPortSamplePeriod;
    private String RDIU_3AppOutputPortName;
    private int RDIU_3AppOutputPortRefreshPeriod;
    private String RIU_3LatencyCritical;
    private int RDIU_3Latency;
    private int RDIU_3Jitter;

    private String RDIU_4Name;
    private String RDIU_4Type;
    private String RDIU_4AppName;
    private String RDIU_4AppInputPortName;
    private int RDIU_4AppInputPortSamplePeriod;
    private String RDIU_4AppOutputPortName;
    private int RDIU_4AppOutputPortRefreshPeriod;
    private String RIU_4LatencyCritical;
    private int RDIU_4Latency;
    private int RDIU_4Jitter;

    private String RDIU_5Name;
    private String RDIU_5Type;
    private String RDIU_5AppName;
    private String RDIU_5AppInputPortName;
    private int RDIU_5AppInputPortSamplePeriod;
    private String RDIU_5AppOutputPortName;
    private int RDIU_5AppOutputPortRefreshPeriod;
    private String RIU_5LatencyCritical;
    private int RDIU_5Latency;
    private int RDIU_5Jitter;

    private String RDIU_6Name;
    private String RDIU_6Type;
    private String RDIU_6AppName;
    private String RDIU_6AppInputPortName;
    private int RDIU_6AppInputPortSamplePeriod;
    private String RDIU_6AppOutputPortName;
    private int RDIU_6AppOutputPortRefreshPeriod;
    private String RIU_6LatencyCritical;
    private int RDIU_6Latency;
    private int RDIU_6Jitter;

    @JSONField(name = "A429-CANQueueDelay")
    private String A429_CANQueueDelay;
    
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getSubPortSamplePeriod() {
		return subPortSamplePeriod;
	}

	public void setSubPortSamplePeriod(int subPortSamplePeriod) {
		this.subPortSamplePeriod = subPortSamplePeriod;
	}

	public int getReceiveQueueLength() {
		return receiveQueueLength;
	}

	public void setReceiveQueueLength(int receiveQueueLength) {
		this.receiveQueueLength = receiveQueueLength;
	}

	public int getPubPortRefreshPeriod() {
		return pubPortRefreshPeriod;
	}

	public void setPubPortRefreshPeriod(int pubPortRefreshPeriod) {
		this.pubPortRefreshPeriod = pubPortRefreshPeriod;
	}

	public int getTransmitQueueLength() {
		return transmitQueueLength;
	}

	public void setTransmitQueueLength(int transmitQueueLength) {
		this.transmitQueueLength = transmitQueueLength;
	}

	public String getPubParameterName() {
		return pubParameterName;
	}

	public void setPubParameterName(String pubParameterName) {
		this.pubParameterName = pubParameterName;
	}

	public String getPubParameterGuid() {
		return pubParameterGuid;
	}

	public void setPubParameterGuid(String pubParameterGuid) {
		this.pubParameterGuid = pubParameterGuid;
	}

	public String getSubParameterName() {
		return subParameterName;
	}

	public void setSubParameterName(String subParameterName) {
		this.subParameterName = subParameterName;
	}

	public String getSubParameterGuid() {
		return subParameterGuid;
	}

	public void setSubParameterGuid(String subParameterGuid) {
		this.subParameterGuid = subParameterGuid;
	}

	public String getRDIU_1Name() {
		return RDIU_1Name;
	}

	public void setRDIU_1Name(String rDIU_1Name) {
		RDIU_1Name = rDIU_1Name;
	}

	public String getRDIU_1Type() {
		return RDIU_1Type;
	}

	public void setRDIU_1Type(String rDIU_1Type) {
		RDIU_1Type = rDIU_1Type;
	}

	public String getRDIU_1AppName() {
		return RDIU_1AppName;
	}

	public void setRDIU_1AppName(String rDIU_1AppName) {
		RDIU_1AppName = rDIU_1AppName;
	}

	public String getRDIU_1AppInputPortName() {
		return RDIU_1AppInputPortName;
	}

	public void setRDIU_1AppInputPortName(String rDIU_1AppInputPortName) {
		RDIU_1AppInputPortName = rDIU_1AppInputPortName;
	}

	public int getRDIU_1AppInputPortSamplePeriod() {
		return RDIU_1AppInputPortSamplePeriod;
	}

	public void setRDIU_1AppInputPortSamplePeriod(int rDIU_1AppInputPortSamplePeriod) {
		RDIU_1AppInputPortSamplePeriod = rDIU_1AppInputPortSamplePeriod;
	}

	public String getRDIU_1AppOutputPortName() {
		return RDIU_1AppOutputPortName;
	}

	public void setRDIU_1AppOutputPortName(String rDIU_1AppOutputPortName) {
		RDIU_1AppOutputPortName = rDIU_1AppOutputPortName;
	}

	public int getRDIU_1AppOutputPortRefreshPeriod() {
		return RDIU_1AppOutputPortRefreshPeriod;
	}

	public void setRDIU_1AppOutputPortRefreshPeriod(int rDIU_1AppOutputPortRefreshPeriod) {
		RDIU_1AppOutputPortRefreshPeriod = rDIU_1AppOutputPortRefreshPeriod;
	}

	public String getRIU_1LatencyCritical() {
		return RIU_1LatencyCritical;
	}

	public void setRIU_1LatencyCritical(String rIU_1LatencyCritical) {
		RIU_1LatencyCritical = rIU_1LatencyCritical;
	}

	public int getRDIU_1Latency() {
		return RDIU_1Latency;
	}

	public void setRDIU_1Latency(int rDIU_1Latency) {
		RDIU_1Latency = rDIU_1Latency;
	}

	public int getRDIU_1Jitter() {
		return RDIU_1Jitter;
	}

	public void setRDIU_1Jitter(int rDIU_1Jitter) {
		RDIU_1Jitter = rDIU_1Jitter;
	}

	public String getRDIU_2Name() {
		return RDIU_2Name;
	}

	public void setRDIU_2Name(String rDIU_2Name) {
		RDIU_2Name = rDIU_2Name;
	}

	public String getRDIU_2Type() {
		return RDIU_2Type;
	}

	public void setRDIU_2Type(String rDIU_2Type) {
		RDIU_2Type = rDIU_2Type;
	}

	public String getRDIU_2AppName() {
		return RDIU_2AppName;
	}

	public void setRDIU_2AppName(String rDIU_2AppName) {
		RDIU_2AppName = rDIU_2AppName;
	}

	public String getRDIU_2AppInputPortName() {
		return RDIU_2AppInputPortName;
	}

	public void setRDIU_2AppInputPortName(String rDIU_2AppInputPortName) {
		RDIU_2AppInputPortName = rDIU_2AppInputPortName;
	}

	public int getRDIU_2AppInputPortSamplePeriod() {
		return RDIU_2AppInputPortSamplePeriod;
	}

	public void setRDIU_2AppInputPortSamplePeriod(int rDIU_2AppInputPortSamplePeriod) {
		RDIU_2AppInputPortSamplePeriod = rDIU_2AppInputPortSamplePeriod;
	}

	public String getRDIU_2AppOutputPortName() {
		return RDIU_2AppOutputPortName;
	}

	public void setRDIU_2AppOutputPortName(String rDIU_2AppOutputPortName) {
		RDIU_2AppOutputPortName = rDIU_2AppOutputPortName;
	}

	public int getRDIU_2AppOutputPortRefreshPeriod() {
		return RDIU_2AppOutputPortRefreshPeriod;
	}

	public void setRDIU_2AppOutputPortRefreshPeriod(int rDIU_2AppOutputPortRefreshPeriod) {
		RDIU_2AppOutputPortRefreshPeriod = rDIU_2AppOutputPortRefreshPeriod;
	}

	public String getRIU_2LatencyCritical() {
		return RIU_2LatencyCritical;
	}

	public void setRIU_2LatencyCritical(String rIU_2LatencyCritical) {
		RIU_2LatencyCritical = rIU_2LatencyCritical;
	}

	public int getRDIU_2Latency() {
		return RDIU_2Latency;
	}

	public void setRDIU_2Latency(int rDIU_2Latency) {
		RDIU_2Latency = rDIU_2Latency;
	}

	public int getRDIU_2Jitter() {
		return RDIU_2Jitter;
	}

	public void setRDIU_2Jitter(int rDIU_2Jitter) {
		RDIU_2Jitter = rDIU_2Jitter;
	}

	public String getRDIU_3Name() {
		return RDIU_3Name;
	}

	public void setRDIU_3Name(String rDIU_3Name) {
		RDIU_3Name = rDIU_3Name;
	}

	public String getRDIU_3Type() {
		return RDIU_3Type;
	}

	public void setRDIU_3Type(String rDIU_3Type) {
		RDIU_3Type = rDIU_3Type;
	}

	public String getRDIU_3AppName() {
		return RDIU_3AppName;
	}

	public void setRDIU_3AppName(String rDIU_3AppName) {
		RDIU_3AppName = rDIU_3AppName;
	}

	public String getRDIU_3AppInputPortName() {
		return RDIU_3AppInputPortName;
	}

	public void setRDIU_3AppInputPortName(String rDIU_3AppInputPortName) {
		RDIU_3AppInputPortName = rDIU_3AppInputPortName;
	}

	public int getRDIU_3AppInputPortSamplePeriod() {
		return RDIU_3AppInputPortSamplePeriod;
	}

	public void setRDIU_3AppInputPortSamplePeriod(int rDIU_3AppInputPortSamplePeriod) {
		RDIU_3AppInputPortSamplePeriod = rDIU_3AppInputPortSamplePeriod;
	}

	public String getRDIU_3AppOutputPortName() {
		return RDIU_3AppOutputPortName;
	}

	public void setRDIU_3AppOutputPortName(String rDIU_3AppOutputPortName) {
		RDIU_3AppOutputPortName = rDIU_3AppOutputPortName;
	}

	public int getRDIU_3AppOutputPortRefreshPeriod() {
		return RDIU_3AppOutputPortRefreshPeriod;
	}

	public void setRDIU_3AppOutputPortRefreshPeriod(int rDIU_3AppOutputPortRefreshPeriod) {
		RDIU_3AppOutputPortRefreshPeriod = rDIU_3AppOutputPortRefreshPeriod;
	}

	public String getRIU_3LatencyCritical() {
		return RIU_3LatencyCritical;
	}

	public void setRIU_3LatencyCritical(String rIU_3LatencyCritical) {
		RIU_3LatencyCritical = rIU_3LatencyCritical;
	}

	public int getRDIU_3Latency() {
		return RDIU_3Latency;
	}

	public void setRDIU_3Latency(int rDIU_3Latency) {
		RDIU_3Latency = rDIU_3Latency;
	}

	public int getRDIU_3Jitter() {
		return RDIU_3Jitter;
	}

	public void setRDIU_3Jitter(int rDIU_3Jitter) {
		RDIU_3Jitter = rDIU_3Jitter;
	}

	public String getRDIU_4Name() {
		return RDIU_4Name;
	}

	public void setRDIU_4Name(String rDIU_4Name) {
		RDIU_4Name = rDIU_4Name;
	}

	public String getRDIU_4Type() {
		return RDIU_4Type;
	}

	public void setRDIU_4Type(String rDIU_4Type) {
		RDIU_4Type = rDIU_4Type;
	}

	public String getRDIU_4AppName() {
		return RDIU_4AppName;
	}

	public void setRDIU_4AppName(String rDIU_4AppName) {
		RDIU_4AppName = rDIU_4AppName;
	}

	public String getRDIU_4AppInputPortName() {
		return RDIU_4AppInputPortName;
	}

	public void setRDIU_4AppInputPortName(String rDIU_4AppInputPortName) {
		RDIU_4AppInputPortName = rDIU_4AppInputPortName;
	}

	public int getRDIU_4AppInputPortSamplePeriod() {
		return RDIU_4AppInputPortSamplePeriod;
	}

	public void setRDIU_4AppInputPortSamplePeriod(int rDIU_4AppInputPortSamplePeriod) {
		RDIU_4AppInputPortSamplePeriod = rDIU_4AppInputPortSamplePeriod;
	}

	public String getRDIU_4AppOutputPortName() {
		return RDIU_4AppOutputPortName;
	}

	public void setRDIU_4AppOutputPortName(String rDIU_4AppOutputPortName) {
		RDIU_4AppOutputPortName = rDIU_4AppOutputPortName;
	}

	public int getRDIU_4AppOutputPortRefreshPeriod() {
		return RDIU_4AppOutputPortRefreshPeriod;
	}

	public void setRDIU_4AppOutputPortRefreshPeriod(int rDIU_4AppOutputPortRefreshPeriod) {
		RDIU_4AppOutputPortRefreshPeriod = rDIU_4AppOutputPortRefreshPeriod;
	}

	public String getRIU_4LatencyCritical() {
		return RIU_4LatencyCritical;
	}

	public void setRIU_4LatencyCritical(String rIU_4LatencyCritical) {
		RIU_4LatencyCritical = rIU_4LatencyCritical;
	}

	public int getRDIU_4Latency() {
		return RDIU_4Latency;
	}

	public void setRDIU_4Latency(int rDIU_4Latency) {
		RDIU_4Latency = rDIU_4Latency;
	}

	public int getRDIU_4Jitter() {
		return RDIU_4Jitter;
	}

	public void setRDIU_4Jitter(int rDIU_4Jitter) {
		RDIU_4Jitter = rDIU_4Jitter;
	}

	public String getRDIU_5Name() {
		return RDIU_5Name;
	}

	public void setRDIU_5Name(String rDIU_5Name) {
		RDIU_5Name = rDIU_5Name;
	}

	public String getRDIU_5Type() {
		return RDIU_5Type;
	}

	public void setRDIU_5Type(String rDIU_5Type) {
		RDIU_5Type = rDIU_5Type;
	}

	public String getRDIU_5AppName() {
		return RDIU_5AppName;
	}

	public void setRDIU_5AppName(String rDIU_5AppName) {
		RDIU_5AppName = rDIU_5AppName;
	}

	public String getRDIU_5AppInputPortName() {
		return RDIU_5AppInputPortName;
	}

	public void setRDIU_5AppInputPortName(String rDIU_5AppInputPortName) {
		RDIU_5AppInputPortName = rDIU_5AppInputPortName;
	}

	public int getRDIU_5AppInputPortSamplePeriod() {
		return RDIU_5AppInputPortSamplePeriod;
	}

	public void setRDIU_5AppInputPortSamplePeriod(int rDIU_5AppInputPortSamplePeriod) {
		RDIU_5AppInputPortSamplePeriod = rDIU_5AppInputPortSamplePeriod;
	}

	public String getRDIU_5AppOutputPortName() {
		return RDIU_5AppOutputPortName;
	}

	public void setRDIU_5AppOutputPortName(String rDIU_5AppOutputPortName) {
		RDIU_5AppOutputPortName = rDIU_5AppOutputPortName;
	}

	public int getRDIU_5AppOutputPortRefreshPeriod() {
		return RDIU_5AppOutputPortRefreshPeriod;
	}

	public void setRDIU_5AppOutputPortRefreshPeriod(int rDIU_5AppOutputPortRefreshPeriod) {
		RDIU_5AppOutputPortRefreshPeriod = rDIU_5AppOutputPortRefreshPeriod;
	}

	public String getRIU_5LatencyCritical() {
		return RIU_5LatencyCritical;
	}

	public void setRIU_5LatencyCritical(String rIU_5LatencyCritical) {
		RIU_5LatencyCritical = rIU_5LatencyCritical;
	}

	public int getRDIU_5Latency() {
		return RDIU_5Latency;
	}

	public void setRDIU_5Latency(int rDIU_5Latency) {
		RDIU_5Latency = rDIU_5Latency;
	}

	public int getRDIU_5Jitter() {
		return RDIU_5Jitter;
	}

	public void setRDIU_5Jitter(int rDIU_5Jitter) {
		RDIU_5Jitter = rDIU_5Jitter;
	}

	public String getRDIU_6Name() {
		return RDIU_6Name;
	}

	public void setRDIU_6Name(String rDIU_6Name) {
		RDIU_6Name = rDIU_6Name;
	}

	public String getRDIU_6Type() {
		return RDIU_6Type;
	}

	public void setRDIU_6Type(String rDIU_6Type) {
		RDIU_6Type = rDIU_6Type;
	}

	public String getRDIU_6AppName() {
		return RDIU_6AppName;
	}

	public void setRDIU_6AppName(String rDIU_6AppName) {
		RDIU_6AppName = rDIU_6AppName;
	}

	public String getRDIU_6AppInputPortName() {
		return RDIU_6AppInputPortName;
	}

	public void setRDIU_6AppInputPortName(String rDIU_6AppInputPortName) {
		RDIU_6AppInputPortName = rDIU_6AppInputPortName;
	}

	public int getRDIU_6AppInputPortSamplePeriod() {
		return RDIU_6AppInputPortSamplePeriod;
	}

	public void setRDIU_6AppInputPortSamplePeriod(int rDIU_6AppInputPortSamplePeriod) {
		RDIU_6AppInputPortSamplePeriod = rDIU_6AppInputPortSamplePeriod;
	}

	public String getRDIU_6AppOutputPortName() {
		return RDIU_6AppOutputPortName;
	}

	public void setRDIU_6AppOutputPortName(String rDIU_6AppOutputPortName) {
		RDIU_6AppOutputPortName = rDIU_6AppOutputPortName;
	}

	public int getRDIU_6AppOutputPortRefreshPeriod() {
		return RDIU_6AppOutputPortRefreshPeriod;
	}

	public void setRDIU_6AppOutputPortRefreshPeriod(int rDIU_6AppOutputPortRefreshPeriod) {
		RDIU_6AppOutputPortRefreshPeriod = rDIU_6AppOutputPortRefreshPeriod;
	}

	public String getRIU_6LatencyCritical() {
		return RIU_6LatencyCritical;
	}

	public void setRIU_6LatencyCritical(String rIU_6LatencyCritical) {
		RIU_6LatencyCritical = rIU_6LatencyCritical;
	}

	public int getRDIU_6Latency() {
		return RDIU_6Latency;
	}

	public void setRDIU_6Latency(int rDIU_6Latency) {
		RDIU_6Latency = rDIU_6Latency;
	}

	public int getRDIU_6Jitter() {
		return RDIU_6Jitter;
	}

	public void setRDIU_6Jitter(int rDIU_6Jitter) {
		RDIU_6Jitter = rDIU_6Jitter;
	}

	public String getA429_CANQueueDelay() {
		return A429_CANQueueDelay;
	}

	public void setA429_CANQueueDelay(String a429_CANQueueDelay) {
		A429_CANQueueDelay = a429_CANQueueDelay;
	}

	@Override
    public String toString() {
        return "RDIUPort [key=" + key + ", subPortSamplePeriod=" + subPortSamplePeriod + ", receiveQueueLength="
                + receiveQueueLength + ", pubPortRefreshPeriod=" + pubPortRefreshPeriod + ", transmitQueueLength="
                + transmitQueueLength + ", pubParameterName=" + pubParameterName + ", pubParameterGuid="
                + pubParameterGuid + ", subParameterName=" + subParameterName + ", subParameterGuid=" + subParameterGuid
                + ", RDIU_1Name=" + RDIU_1Name + ", RDIU_1Type=" + RDIU_1Type + ", RDIU_1AppName=" + RDIU_1AppName
                + ", RDIU_1AppInputPortName=" + RDIU_1AppInputPortName + ", RDIU_1AppInputPortSamplePeriod="
                + RDIU_1AppInputPortSamplePeriod + ", RDIU_1AppOutputPortName=" + RDIU_1AppOutputPortName
                + ", RDIU_1AppOutputPortRefreshPeriod=" + RDIU_1AppOutputPortRefreshPeriod + ", RIU_1LatencyCritical="
                + RIU_1LatencyCritical + ", RDIU_1Latency=" + RDIU_1Latency + ", RDIU_1Jitter=" + RDIU_1Jitter
                + ", RDIU_2Name=" + RDIU_2Name + ", RDIU_2Type=" + RDIU_2Type + ", RDIU_2AppName=" + RDIU_2AppName
                + ", RDIU_2AppInputPortName=" + RDIU_2AppInputPortName + ", RDIU_2AppInputPortSamplePeriod="
                + RDIU_2AppInputPortSamplePeriod + ", RDIU_2AppOutputPortName=" + RDIU_2AppOutputPortName
                + ", RDIU_2AppOutputPortRefreshPeriod=" + RDIU_2AppOutputPortRefreshPeriod + ", RIU_2LatencyCritical="
                + RIU_2LatencyCritical + ", RDIU_2Latency=" + RDIU_2Latency + ", RDIU_2Jitter=" + RDIU_2Jitter
                + ", RDIU_3Name=" + RDIU_3Name + ", RDIU_3Type=" + RDIU_3Type + ", RDIU_3AppName=" + RDIU_3AppName
                + ", RDIU_3AppInputPortName=" + RDIU_3AppInputPortName + ", RDIU_3AppInputPortSamplePeriod="
                + RDIU_3AppInputPortSamplePeriod + ", RDIU_3AppOutputPortName=" + RDIU_3AppOutputPortName
                + ", RDIU_3AppOutputPortRefreshPeriod=" + RDIU_3AppOutputPortRefreshPeriod + ", RIU_3LatencyCritical="
                + RIU_3LatencyCritical + ", RDIU_3Latency=" + RDIU_3Latency + ", RDIU_3Jitter=" + RDIU_3Jitter
                + ", RDIU_4Name=" + RDIU_4Name + ", RDIU_4Type=" + RDIU_4Type + ", RDIU_4AppName=" + RDIU_4AppName
                + ", RDIU_4AppInputPortName=" + RDIU_4AppInputPortName + ", RDIU_4AppInputPortSamplePeriod="
                + RDIU_4AppInputPortSamplePeriod + ", RDIU_4AppOutputPortName=" + RDIU_4AppOutputPortName
                + ", RDIU_4AppOutputPortRefreshPeriod=" + RDIU_4AppOutputPortRefreshPeriod + ", RIU_4LatencyCritical="
                + RIU_4LatencyCritical + ", RDIU_4Latency=" + RDIU_4Latency + ", RDIU_4Jitter=" + RDIU_4Jitter
                + ", RDIU_5Name=" + RDIU_5Name + ", RDIU_5Type=" + RDIU_5Type + ", RDIU_5AppName=" + RDIU_5AppName
                + ", RDIU_5AppInputPortName=" + RDIU_5AppInputPortName + ", RDIU_5AppInputPortSamplePeriod="
                + RDIU_5AppInputPortSamplePeriod + ", RDIU_5AppOutputPortName=" + RDIU_5AppOutputPortName
                + ", RDIU_5AppOutputPortRefreshPeriod=" + RDIU_5AppOutputPortRefreshPeriod + ", RIU_5LatencyCritical="
                + RIU_5LatencyCritical + ", RDIU_5Latency=" + RDIU_5Latency + ", RDIU_5Jitter=" + RDIU_5Jitter
                + ", RDIU_6Name=" + RDIU_6Name + ", RDIU_6Type=" + RDIU_6Type + ", RDIU_6AppName=" + RDIU_6AppName
                + ", RDIU_6AppInputPortName=" + RDIU_6AppInputPortName + ", RDIU_6AppInputPortSamplePeriod="
                + RDIU_6AppInputPortSamplePeriod + ", RDIU_6AppOutputPortName=" + RDIU_6AppOutputPortName
                + ", RDIU_6AppOutputPortRefreshPeriod=" + RDIU_6AppOutputPortRefreshPeriod + ", RIU_6LatencyCritical="
                + RIU_6LatencyCritical + ", RDIU_6Latency=" + RDIU_6Latency + ", RDIU_6Jitter=" + RDIU_6Jitter
                + ", A429_CANQueueDelay=" + A429_CANQueueDelay + "]\n";
    }

}
