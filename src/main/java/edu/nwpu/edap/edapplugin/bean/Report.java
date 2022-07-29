package edu.nwpu.edap.edapplugin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Report {

    @JSONField(name = "Key")
    int Key = -1;

    @JSONField(name = "SubscribingHardwareName")
    String SubscribingHardwareName = "N/A";

    @JSONField(name = "SubscribingSwitchUplinkTxBW")
    double SubscribingSwitchUplinkTxBW = -1;

    @JSONField(name = "SubscribingSwitchUplinkRxBW")
    double SubscribingSwitchUplinkRxBW = -1;

    @JSONField(name = "SubscribingAppName")
    String SubscribingAppName = "N/A";

    @JSONField(name = "SubscribingAppATA")
    String SubscribingAppATA = "N/A";

    @JSONField(name = "SubscribingAppGuid")
    String SubscribingAppGuid = "N/A";

    @JSONField(name = "SubscribingPortName")
    String SubscribingPortName = "N/A";

    @JSONField(name = "SubscribingPortGuid")
    String SubscribingPortGuid = "N/A";

    @JSONField(name = "SubscribingPortPhysSpeed")
    int SubscribingPortPhysSpeed = -1;

    @JSONField(name = "SubscribingPortType")
    String SubscribingPortType = "N/A";

    @JSONField(name = "SubscribingPortSamplePeriod")
    int SubscribingPortSamplePeriod = -1;

    @JSONField(name = "SubPeriod")
    int SubPeriod = -1;

    @JSONField(name = "ReceiveQueueLength")
    int ReceiveQueueLength = -1;

    @JSONField(name = "ReceiveQueueSize")
    int ReceiveQueueSize = -1;

    @JSONField(name = "PublishingHardwareName")
    String PublishingHardwareName = "N/A";

    @JSONField(name = "PublishingSwitchUplinkTxBW")
    double PublishingSwitchUplinkTxBW = -1;

    @JSONField(name = "PublishingSwitchUplinkRxBW")
    double PublishingSwitchUplinkRxBW = -1;

    @JSONField(name = "PublishingAppName")
    String PublishingAppName = "N/A";

    @JSONField(name = "PublishingAppGuid")
    String PublishingAppGuid = "N/A";

    @JSONField(name = "PublishingAppATA")
    String PublishingAppATA = "N/A";

    @JSONField(name = "PublishingPortName")
    String PublishingPortName = "N/A";

    @JSONField(name = "PublishingPortGuid")
    String PublishingPortGuid = "N/A";

    @JSONField(name = "PublishingPortPhysSpeed")
    double PublishingPortPhysSpeed = -1;

    @JSONField(name = "PublishingPortType")
    String PublishingPortType = "N/A";

    @JSONField(name = "PublishingPortRefreshPeriod")
    int PublishingPortRefreshPeriod = -1;

    @JSONField(name = "PubPeriod")
    int PubPeriod = -1;

    @JSONField(name = "TransmitQueueLength")
    int TransmitQueueLength = -1;

    @JSONField(name = "TransmitQueueSize")
    int TransmitQueueSize = -1;

    @JSONField(name = "VirtualLinkName")
    String VirtualLinkName = "N/A";

    @JSONField(name = "VirtualLinkBandwidthConsumed")
    double VirtualLinkBandwidthConsumed = -1;

    @JSONField(name = "SubVLID")
    String SubVLID = "N/A";

    @JSONField(name = "SubVLBufferSize")
    double SubVLBufferSize = -1;

    @JSONField(name = "PublishedParameterName")
    String PublishedParameterName = "N/A";

    @JSONField(name = "PublishedParameterGuid")
    String PublishedParameterGuid = "N/A";

    @JSONField(name = "PublishedParameterProtocolType")
    String PublishedParameterProtocolType = "N/A";

    @JSONField(name = "PublishedParameterSize")
    double PublishedParameterSize = -1;

    @JSONField(name = "SubscribedParameterName")
    String SubscribedParameterName = "N/A";

    @JSONField(name = "SubscribedParameterGuid")
    String SubscribedParameterGuid = "N/A";

    @JSONField(name = "KeepAliveMessageSize")
    double KeepAliveMessageSize = -1;

    @JSONField(name = "KeepAliveRefreshPeriod")
    double KeepAliveRefreshPeriod = -1;

    @JSONField(name = "PublishingA664MessageInstanceName")
    String PublishingA664MessageInstanceName = "N/A";

    @JSONField(name = "PublishingA664MessageFragments")
    double PublishingA664MessageFragments = 0.0f;

    @JSONField(name = "PublishedA664MessageSize")
    double PublishedA664MessageSize = 0.0f;

    @JSONField(name = "PublishedA664MessageDataProtocolType")
    String PublishedA664MessageDataProtocolType = "N/A";

    @JSONField(name = "PublishedA664MessagePortType")
    String PublishedA664MessagePortType = "N/A";

    @JSONField(name = "PublishedA664MessageGuid")
    String PublishedA664MessageGuid = "N/A";

    @JSONField(name = "MsgVLGroup")
    String MsgVLGroup = "N/A";

    @JSONField(name = "BWSharedGroup")
    String BWSharedGroup = "N/A";

    @JSONField(name = "VLAccountGroup")
    String VLAccountGroup = "N/A";

    @JSONField(name = "MaxFrameSize")
    double MaxFrameSize = -1;

    @JSONField(name = "MTU")
    double MTU = -1;

    @JSONField(name = "BAG")
    double BAG = -1;

    @JSONField(name = "VLNetworks")
    String VLNetworks = "N/A";

    @JSONField(name = "VLPriority")
    int VLPriority = -1;

    @JSONField(name = "RIU_1Name")
    String RIU_1Name = "N/A";

    @JSONField(name = "RIU_1Type")
    String RIU_1Type = "N/A";

    @JSONField(name = "RIU_1AppName")
    String RIU_1AppName = "N/A";

    @JSONField(name = "RIU_1AppInputPortName")
    String RIU_1AppInputPortName = "N/A";

    @JSONField(name = "RIU_1AppInputPortSamplePeriod")
    double RIU_1AppInputPortSamplePeriod = -1;

    @JSONField(name = "RIU_1AppOutputPortName")
    String RIU_1AppOutputPortName = "N/A";

    @JSONField(name = "RIU_1AppOutputPortRefreshPeriod")
    double RIU_1AppOutputPortRefreshPeriod = -1;

    @JSONField(name = "RIU_1LatencyCritical")
    double RIU_1LatencyCritical = -1;

    @JSONField(name = "RIU_1Latency")
    double RIU_1Latency = -1;

    @JSONField(name = "RIU_1Jitter")
    double RIU_1Jitter = -1;

    @JSONField(name = "RIU_2Name")
    String RIU_2Name = "N/A";

    @JSONField(name = "RIU_2Type")
    String RIU_2Type = "N/A";

    @JSONField(name = "RIU_2AppName")
    String RIU_2AppName = "N/A";

    @JSONField(name = "RIU_2AppInputPortName")
    String RIU_2AppInputPortName = "N/A";

    @JSONField(name = "RIU_2AppInputPortSamplePeriod")
    double RIU_2AppInputPortSamplePeriod = -1;

    @JSONField(name = "RIU_2AppOutputPortName")
    String RIU_2AppOutputPortName = "N/A";

    @JSONField(name = "RIU_2AppOutputPortRefreshPeriod")
    double RIU_2AppOutputPortRefreshPeriod = -1;

    @JSONField(name = "RIU_2LatencyCritical")
    double RIU_2LatencyCritical = -1;

    @JSONField(name = "RIU_2Latency")
    double RIU_2Latency = -1;

    @JSONField(name = "RIU_2Jitter")
    double RIU_2Jitter = -1;

    @JSONField(name = "RIU_3Name")
    String RIU_3Name = "N/A";

    @JSONField(name = "RIU_3Type")
    String RIU_3Type = "N/A";

    @JSONField(name = "RIU_3AppName")
    String RIU_3AppName = "N/A";

    @JSONField(name = "RIU_3AppInputPortName")
    String RIU_3AppInputPortName = "N/A";

    @JSONField(name = "RIU_3AppInputPortSamplePeriod")
    double RIU_3AppInputPortSamplePeriod = -1;

    @JSONField(name = "RIU_3AppOutputPortName")
    String RIU_3AppOutputPortName = "N/A";

    @JSONField(name = "RIU_3AppOutputPortRefreshPeriod")
    double RIU_3AppOutputPortRefreshPeriod = -1;

    @JSONField(name = "RIU_3LatencyCritical")
    double RIU_3LatencyCritical = -1;

    @JSONField(name = "RIU_3Latency")
    double RIU_3Latency = -1;

    @JSONField(name = "RIU_3Jitter")
    double RIU_3Jitter = -1;

    @JSONField(name = "RIU_4Name")
    String RIU_4Name = "N/A";

    @JSONField(name = "RIU_4Type")
    String RIU_4Type = "N/A";

    @JSONField(name = "RIU_4AppName")
    String RIU_4AppName = "N/A";

    @JSONField(name = "RIU_4AppInputPortName")
    String RIU_4AppInputPortName = "N/A";

    @JSONField(name = "RIU_4AppInputPortSamplePeriod")
    double RIU_4AppInputPortSamplePeriod = -1;

    @JSONField(name = "RIU_4AppOutputPortName")
    String RIU_4AppOutputPortName = "N/A";

    @JSONField(name = "RIU_4AppOutputPortRefreshPeriod")
    double RIU_4AppOutputPortRefreshPeriod = -1;

    @JSONField(name = "RIU_4LatencyCritical")
    double RIU_4LatencyCritical = -1;

    @JSONField(name = "RIU_4Latency")
    double RIU_4Latency = -1;

    @JSONField(name = "RIU_4Jitter")
    double RIU_4Jitter = -1;

    @JSONField(name = "RIU_5Name")
    String RIU_5Name = "N/A";

    @JSONField(name = "RIU_5Type")
    String RIU_5Type = "N/A";

    @JSONField(name = "RIU_5AppName")
    String RIU_5AppName = "N/A";

    @JSONField(name = "RIU_5AppInputPortName")
    String RIU_5AppInputPortName = "N/A";

    @JSONField(name = "RIU_5AppInputPortSamplePeriod")
    double RIU_5AppInputPortSamplePeriod = -1;

    @JSONField(name = "RIU_5AppOutputPortName")
    String RIU_5AppOutputPortName = "N/A";

    @JSONField(name = "RIU_5AppOutputPortRefreshPeriod")
    double RIU_5AppOutputPortRefreshPeriod = 0.0f;

    @JSONField(name = "RIU_5LatencyCritical")
    double RIU_5LatencyCritical = -1;

    @JSONField(name = "RIU_5Latency")
    double RIU_5Latency = -1;

    @JSONField(name = "RIU_5Jitter")
    double RIU_5Jitter = -1;

    @JSONField(name = "RIU_6Name")
    String RIU_6Name = "N/A";

    @JSONField(name = "RIU_6Type")
    String RIU_6Type = "N/A";

    @JSONField(name = "RIU_6AppName")
    String RIU_6AppName = "N/A";

    @JSONField(name = "RIU_6AppInputPortName")
    String RIU_6AppInputPortName = "N/A";

    @JSONField(name = "RIU_6AppInputPortSamplePeriod")
    double RIU_6AppInputPortSamplePeriod = -1;

    @JSONField(name = "RIU_6AppOutputPortName")
    String RIU_6AppOutputPortName = "N/A";

    @JSONField(name = "RIU_6AppOutputPortRefreshPeriod")
    double RIU_6AppOutputPortRefreshPeriod = -1;

    @JSONField(name = "RIU_6LatencyCritical")
    double RIU_6LatencyCritical = -1;

    @JSONField(name = "RIU_6Latency")
    double RIU_6Latency = -1;

    @JSONField(name = "RIU_6Jitter")
    double RIU_6Jitter = -1;

    @JSONField(name = "EmbeddedFunctionExists")
    boolean EmbeddedFunctionExists = false;

    @JSONField(name = "SubscribingLatency")
    double SubscribingLatency = -1;

    @JSONField(name = "LatencyBudget")
    double LatencyBudget = -1;

    @JSONField(name = "SystemLatency")
    double SystemLatency = -1;

    @JSONField(name = "TTPLatency")
    double TTPLatency = -1;

    @JSONField(name = "TTOLatency")
    double TTOLatency = -1;

    @JSONField(name = "Jitter")
    double Jitter = -1;

    @JSONField(name = "AESJitter")
    double AESJitter = -1;

    @JSONField(name = "PhysicalNetworkJitter")
    double PhysicalNetworkJitter = -1;

    @JSONField(name = "A429CANQueueDelay")
    double A429CANQueueDelay = -1;

    @JSONField(name = "A664Latency")
    double A664Latency = -1;

    @JSONField(name = "A664PhysicalLatency")
    double A664PhysicalLatency = -1;

    @JSONField(name = "PhysicalLatency")
    double PhysicalLatency = -1;

    @JSONField(name = "BAGLatency")
    double BAGLatency = -1;

    @JSONField(name = "AsyncLatency")
    double AsyncLatency = -1;

    @JSONField(name = "InCompliance")
    String InCompliance = "N/A";

    @JSONField (name = "NonCompliance")
    String NonCompliance = "N/A";


    public double getSubscribingSwitchUplinkTxBW() {
        return SubscribingSwitchUplinkTxBW;
    }

    public void setSubscribingSwitchUplinkTxBW(double subscribingSwitchUplinkTxBW) {
        SubscribingSwitchUplinkTxBW = subscribingSwitchUplinkTxBW;
    }

    public double getSubscribingSwitchUplinkRxBW() {
        return SubscribingSwitchUplinkRxBW;
    }

    public void setSubscribingSwitchUplinkRxBW(double subscribingSwitchUplinkRxBW) {
        SubscribingSwitchUplinkRxBW = subscribingSwitchUplinkRxBW;
    }

    public String getPublishingAppATA() {
        return PublishingAppATA;
    }

    public void setPublishingAppATA(String publishingAppATA) {
        PublishingAppATA = publishingAppATA;
    }

    public String getPublishingPortName() {
        return PublishingPortName;
    }

    public void setPublishingPortName(String publishingPortName) {
        PublishingPortName = publishingPortName;
    }

    public String getPublishingPortGuid() {
        return PublishingPortGuid;
    }

    public void setPublishingPortGuid(String publishingPortGuid) {
        PublishingPortGuid = publishingPortGuid;
    }

    public double getPublishingPortPhysSpeed() {
        return PublishingPortPhysSpeed;
    }

    public void setPublishingPortPhysSpeed(double publishingPortPhysSpeed) {
        PublishingPortPhysSpeed = publishingPortPhysSpeed;
    }

    public String getPublishingPortType() {
        return PublishingPortType;
    }

    public void setPublishingPortType(String publishingPortType) {
        PublishingPortType = publishingPortType;
    }

    public int getPublishingPortRefreshPeriod() {
        return PublishingPortRefreshPeriod;
    }

    public void setPublishingPortRefreshPeriod(int publishingPortRefreshPeriod) {
        PublishingPortRefreshPeriod = publishingPortRefreshPeriod;
    }

    public int getPubPeriod() {
        return PubPeriod;
    }

    public void setPubPeriod(int pubPeriod) {
        PubPeriod = pubPeriod;
    }

    public int getTransmitQueueLength() {
        return TransmitQueueLength;
    }

    public void setTransmitQueueLength(int transmitQueueLength) {
        TransmitQueueLength = transmitQueueLength;
    }

    public int getTransmitQueueSize() {
        return TransmitQueueSize;
    }

    public void setTransmitQueueSize(int transmitQueueSize) {
        TransmitQueueSize = transmitQueueSize;
    }

    public String getVirtualLinkName() {
        return VirtualLinkName;
    }

    public void setVirtualLinkName(String virtualLinkName) {
        VirtualLinkName = virtualLinkName;
    }

    public double getVirtualLinkBandwidthConsumed() {
        return VirtualLinkBandwidthConsumed;
    }

    public void setVirtualLinkBandwidthConsumed(double virtualLinkBandwidthConsumed) {
        VirtualLinkBandwidthConsumed = virtualLinkBandwidthConsumed;
    }

    public String getSubVLID() {
        return SubVLID;
    }

    public void setSubVLID(String subVLID) {
        SubVLID = subVLID;
    }

    public double getSubVLBufferSize() {
        return SubVLBufferSize;
    }

    public void setSubVLBufferSize(double subVLBufferSize) {
        SubVLBufferSize = subVLBufferSize;
    }

    public String getPublishedParameterName() {
        return PublishedParameterName;
    }

    public void setPublishedParameterName(String publishedParameterName) {
        PublishedParameterName = publishedParameterName;
    }

    public String getPublishedParameterGuid() {
        return PublishedParameterGuid;
    }

    public void setPublishedParameterGuid(String publishedParameterGuid) {
        PublishedParameterGuid = publishedParameterGuid;
    }

    public String getPublishedParameterProtocolType() {
        return PublishedParameterProtocolType;
    }

    public void setPublishedParameterProtocolType(String publishedParameterProtocolType) {
        PublishedParameterProtocolType = publishedParameterProtocolType;
    }

    public double getPublishedParameterSize() {
        return PublishedParameterSize;
    }

    public void setPublishedParameterSize(double publishedParameterSize) {
        PublishedParameterSize = publishedParameterSize;
    }

    public String getSubscribedParameterName() {
        return SubscribedParameterName;
    }

    public void setSubscribedParameterName(String subscribedParameterName) {
        SubscribedParameterName = subscribedParameterName;
    }

    public String getSubscribedParameterGuid() {
        return SubscribedParameterGuid;
    }

    public void setSubscribedParameterGuid(String subscribedParameterGuid) {
        SubscribedParameterGuid = subscribedParameterGuid;
    }

    public double getKeepAliveMessageSize() {
        return KeepAliveMessageSize;
    }

    public void setKeepAliveMessageSize(double keepAliveMessageSize) {
        KeepAliveMessageSize = keepAliveMessageSize;
    }

    public double getKeepAliveRefreshPeriod() {
        return KeepAliveRefreshPeriod;
    }

    public void setKeepAliveRefreshPeriod(double keepAliveRefreshPeriod) {
        KeepAliveRefreshPeriod = keepAliveRefreshPeriod;
    }

    public String getPublishingA664MessageInstanceName() {
        return PublishingA664MessageInstanceName;
    }

    public void setPublishingA664MessageInstanceName(String publishingA664MessageInstanceName) {
        PublishingA664MessageInstanceName = publishingA664MessageInstanceName;
    }

    public double getPublishingA664MessageFragments() {
        return PublishingA664MessageFragments;
    }

    public void setPublishingA664MessageFragments(double publishingA664MessageFragments) {
        PublishingA664MessageFragments = publishingA664MessageFragments;
    }

    public double getPublishedA664MessageSize() {
        return PublishedA664MessageSize;
    }

    public void setPublishedA664MessageSize(double publishedA664MessageSize) {
        PublishedA664MessageSize = publishedA664MessageSize;
    }

    public String getPublishedA664MessageDataProtocolType() {
        return PublishedA664MessageDataProtocolType;
    }

    public void setPublishedA664MessageDataProtocolType(String publishedA664MessageDataProtocolType) {
        PublishedA664MessageDataProtocolType = publishedA664MessageDataProtocolType;
    }

    public String getPublishedA664MessagePortType() {
        return PublishedA664MessagePortType;
    }

    public void setPublishedA664MessagePortType(String publishedA664MessagePortType) {
        PublishedA664MessagePortType = publishedA664MessagePortType;
    }

    public String getPublishedA664MessageGuid() {
        return PublishedA664MessageGuid;
    }

    public void setPublishedA664MessageGuid(String publishedA664MessageGuid) {
        PublishedA664MessageGuid = publishedA664MessageGuid;
    }

    public String getMsgVLGroup() {
        return MsgVLGroup;
    }

    public void setMsgVLGroup(String msgVLGroup) {
        MsgVLGroup = msgVLGroup;
    }

    public String getBWSharedGroup() {
        return BWSharedGroup;
    }

    public void setBWSharedGroup(String bWSharedGroup) {
        BWSharedGroup = bWSharedGroup;
    }

    public String getVLAccountGroup() {
        return VLAccountGroup;
    }

    public void setVLAccountGroup(String vLAccountGroup) {
        VLAccountGroup = vLAccountGroup;
    }

    public double getMaxFrameSize() {
        return MaxFrameSize;
    }

    public void setMaxFrameSize(double maxFrameSize) {
        MaxFrameSize = maxFrameSize;
    }

    public double getMTU() {
        return MTU;
    }

    public void setMTU(double mTU) {
        MTU = mTU;
    }

    public double getBAG() {
        return BAG;
    }

    public void setBAG(double bAG) {
        BAG = bAG;
    }

    public String getVLNetworks() {
        return VLNetworks;
    }

    public void setVLNetworks(String vLNetworks) {
        VLNetworks = vLNetworks;
    }

    public int getVLPriority() {
        return VLPriority;
    }

    public void setVLPriority(int vLPriority) {
        VLPriority = vLPriority;
    }

    public String getRIU_1Name() {
        return RIU_1Name;
    }

    public void setRIU_1Name(String rIU_1Name) {
        RIU_1Name = rIU_1Name;
    }

    public String getRIU_1Type() {
        return RIU_1Type;
    }

    public void setRIU_1Type(String rIU_1Type) {
        RIU_1Type = rIU_1Type;
    }

    public String getRIU_1AppName() {
        return RIU_1AppName;
    }

    public void setRIU_1AppName(String rIU_1AppName) {
        RIU_1AppName = rIU_1AppName;
    }

    public String getRIU_1AppInputPortName() {
        return RIU_1AppInputPortName;
    }

    public void setRIU_1AppInputPortName(String rIU_1AppInputPortName) {
        RIU_1AppInputPortName = rIU_1AppInputPortName;
    }

    public double getRIU_1AppInputPortSamplePeriod() {
        return RIU_1AppInputPortSamplePeriod;
    }

    public void setRIU_1AppInputPortSamplePeriod(double rIU_1AppInputPortSamplePeriod) {
        RIU_1AppInputPortSamplePeriod = rIU_1AppInputPortSamplePeriod;
    }

    public String getRIU_1AppOutputPortName() {
        return RIU_1AppOutputPortName;
    }

    public void setRIU_1AppOutputPortName(String rIU_1AppOutputPortName) {
        RIU_1AppOutputPortName = rIU_1AppOutputPortName;
    }

    public double getRIU_1AppOutputPortRefreshPeriod() {
        return RIU_1AppOutputPortRefreshPeriod;
    }

    public void setRIU_1AppOutputPortRefreshPeriod(double rIU_1AppOutputPortRefreshPeriod) {
        RIU_1AppOutputPortRefreshPeriod = rIU_1AppOutputPortRefreshPeriod;
    }

    public double getRIU_1LatencyCritical() {
        return RIU_1LatencyCritical;
    }

    public void setRIU_1LatencyCritical(double rIU_1LatencyCritical) {
        RIU_1LatencyCritical = rIU_1LatencyCritical;
    }

    public double getRIU_1Latency() {
        return RIU_1Latency;
    }

    public void setRIU_1Latency(double rIU_1Latency) {
        RIU_1Latency = rIU_1Latency;
    }

    public double getRIU_1Jitter() {
        return RIU_1Jitter;
    }

    public void setRIU_1Jitter(double rIU_1Jitter) {
        RIU_1Jitter = rIU_1Jitter;
    }

    public String getRIU_2Name() {
        return RIU_2Name;
    }

    public void setRIU_2Name(String rIU_2Name) {
        RIU_2Name = rIU_2Name;
    }

    public String getRIU_2Type() {
        return RIU_2Type;
    }

    public void setRIU_2Type(String rIU_2Type) {
        RIU_2Type = rIU_2Type;
    }

    public String getRIU_2AppName() {
        return RIU_2AppName;
    }

    public void setRIU_2AppName(String rIU_2AppName) {
        RIU_2AppName = rIU_2AppName;
    }

    public String getRIU_2AppInputPortName() {
        return RIU_2AppInputPortName;
    }

    public void setRIU_2AppInputPortName(String rIU_2AppInputPortName) {
        RIU_2AppInputPortName = rIU_2AppInputPortName;
    }

    public double getRIU_2AppInputPortSamplePeriod() {
        return RIU_2AppInputPortSamplePeriod;
    }

    public void setRIU_2AppInputPortSamplePeriod(double rIU_2AppInputPortSamplePeriod) {
        RIU_2AppInputPortSamplePeriod = rIU_2AppInputPortSamplePeriod;
    }

    public String getRIU_2AppOutputPortName() {
        return RIU_2AppOutputPortName;
    }

    public void setRIU_2AppOutputPortName(String rIU_2AppOutputPortName) {
        RIU_2AppOutputPortName = rIU_2AppOutputPortName;
    }

    public double getRIU_2AppOutputPortRefreshPeriod() {
        return RIU_2AppOutputPortRefreshPeriod;
    }

    public void setRIU_2AppOutputPortRefreshPeriod(double rIU_2AppOutputPortRefreshPeriod) {
        RIU_2AppOutputPortRefreshPeriod = rIU_2AppOutputPortRefreshPeriod;
    }

    public double getRIU_2LatencyCritical() {
        return RIU_2LatencyCritical;
    }

    public void setRIU_2LatencyCritical(double rIU_2LatencyCritical) {
        RIU_2LatencyCritical = rIU_2LatencyCritical;
    }

    public double getRIU_2Latency() {
        return RIU_2Latency;
    }

    public void setRIU_2Latency(double rIU_2Latency) {
        RIU_2Latency = rIU_2Latency;
    }

    public double getRIU_2Jitter() {
        return RIU_2Jitter;
    }

    public void setRIU_2Jitter(double rIU_2Jitter) {
        RIU_2Jitter = rIU_2Jitter;
    }

    public String getRIU_3Name() {
        return RIU_3Name;
    }

    public void setRIU_3Name(String rIU_3Name) {
        RIU_3Name = rIU_3Name;
    }

    public String getRIU_3Type() {
        return RIU_3Type;
    }

    public void setRIU_3Type(String rIU_3Type) {
        RIU_3Type = rIU_3Type;
    }

    public String getRIU_3AppName() {
        return RIU_3AppName;
    }

    public void setRIU_3AppName(String rIU_3AppName) {
        RIU_3AppName = rIU_3AppName;
    }

    public String getRIU_3AppInputPortName() {
        return RIU_3AppInputPortName;
    }

    public void setRIU_3AppInputPortName(String rIU_3AppInputPortName) {
        RIU_3AppInputPortName = rIU_3AppInputPortName;
    }

    public double getRIU_3AppInputPortSamplePeriod() {
        return RIU_3AppInputPortSamplePeriod;
    }

    public void setRIU_3AppInputPortSamplePeriod(double rIU_3AppInputPortSamplePeriod) {
        RIU_3AppInputPortSamplePeriod = rIU_3AppInputPortSamplePeriod;
    }

    public String getRIU_3AppOutputPortName() {
        return RIU_3AppOutputPortName;
    }

    public void setRIU_3AppOutputPortName(String rIU_3AppOutputPortName) {
        RIU_3AppOutputPortName = rIU_3AppOutputPortName;
    }

    public double getRIU_3AppOutputPortRefreshPeriod() {
        return RIU_3AppOutputPortRefreshPeriod;
    }

    public void setRIU_3AppOutputPortRefreshPeriod(double rIU_3AppOutputPortRefreshPeriod) {
        RIU_3AppOutputPortRefreshPeriod = rIU_3AppOutputPortRefreshPeriod;
    }

    public double getRIU_3LatencyCritical() {
        return RIU_3LatencyCritical;
    }

    public void setRIU_3LatencyCritical(double rIU_3LatencyCritical) {
        RIU_3LatencyCritical = rIU_3LatencyCritical;
    }

    public double getRIU_3Latency() {
        return RIU_3Latency;
    }

    public void setRIU_3Latency(double rIU_3Latency) {
        RIU_3Latency = rIU_3Latency;
    }

    public double getRIU_3Jitter() {
        return RIU_3Jitter;
    }

    public void setRIU_3Jitter(double rIU_3Jitter) {
        RIU_3Jitter = rIU_3Jitter;
    }

    public String getRIU_4Name() {
        return RIU_4Name;
    }

    public void setRIU_4Name(String rIU_4Name) {
        RIU_4Name = rIU_4Name;
    }

    public String getRIU_4Type() {
        return RIU_4Type;
    }

    public void setRIU_4Type(String rIU_4Type) {
        RIU_4Type = rIU_4Type;
    }

    public String getRIU_4AppName() {
        return RIU_4AppName;
    }

    public void setRIU_4AppName(String rIU_4AppName) {
        RIU_4AppName = rIU_4AppName;
    }

    public String getRIU_4AppInputPortName() {
        return RIU_4AppInputPortName;
    }

    public void setRIU_4AppInputPortName(String rIU_4AppInputPortName) {
        RIU_4AppInputPortName = rIU_4AppInputPortName;
    }

    public double getRIU_4AppInputPortSamplePeriod() {
        return RIU_4AppInputPortSamplePeriod;
    }

    public void setRIU_4AppInputPortSamplePeriod(double rIU_4AppInputPortSamplePeriod) {
        RIU_4AppInputPortSamplePeriod = rIU_4AppInputPortSamplePeriod;
    }

    public String getRIU_4AppOutputPortName() {
        return RIU_4AppOutputPortName;
    }

    public void setRIU_4AppOutputPortName(String rIU_4AppOutputPortName) {
        RIU_4AppOutputPortName = rIU_4AppOutputPortName;
    }

    public double getRIU_4AppOutputPortRefreshPeriod() {
        return RIU_4AppOutputPortRefreshPeriod;
    }

    public void setRIU_4AppOutputPortRefreshPeriod(double rIU_4AppOutputPortRefreshPeriod) {
        RIU_4AppOutputPortRefreshPeriod = rIU_4AppOutputPortRefreshPeriod;
    }

    public double getRIU_4LatencyCritical() {
        return RIU_4LatencyCritical;
    }

    public void setRIU_4LatencyCritical(double rIU_4LatencyCritical) {
        RIU_4LatencyCritical = rIU_4LatencyCritical;
    }

    public double getRIU_4Latency() {
        return RIU_4Latency;
    }

    public void setRIU_4Latency(double rIU_4Latency) {
        RIU_4Latency = rIU_4Latency;
    }

    public double getRIU_4Jitter() {
        return RIU_4Jitter;
    }

    public void setRIU_4Jitter(double rIU_4Jitter) {
        RIU_4Jitter = rIU_4Jitter;
    }

    public String getRIU_5Name() {
        return RIU_5Name;
    }

    public void setRIU_5Name(String rIU_5Name) {
        RIU_5Name = rIU_5Name;
    }

    public String getRIU_5Type() {
        return RIU_5Type;
    }

    public void setRIU_5Type(String rIU_5Type) {
        RIU_5Type = rIU_5Type;
    }

    public String getRIU_5AppName() {
        return RIU_5AppName;
    }

    public void setRIU_5AppName(String rIU_5AppName) {
        RIU_5AppName = rIU_5AppName;
    }

    public String getRIU_5AppInputPortName() {
        return RIU_5AppInputPortName;
    }

    public void setRIU_5AppInputPortName(String rIU_5AppInputPortName) {
        RIU_5AppInputPortName = rIU_5AppInputPortName;
    }

    public double getRIU_5AppInputPortSamplePeriod() {
        return RIU_5AppInputPortSamplePeriod;
    }

    public void setRIU_5AppInputPortSamplePeriod(double rIU_5AppInputPortSamplePeriod) {
        RIU_5AppInputPortSamplePeriod = rIU_5AppInputPortSamplePeriod;
    }

    public String getRIU_5AppOutputPortName() {
        return RIU_5AppOutputPortName;
    }

    public void setRIU_5AppOutputPortName(String rIU_5AppOutputPortName) {
        RIU_5AppOutputPortName = rIU_5AppOutputPortName;
    }

    public double getRIU_5AppOutputPortRefreshPeriod() {
        return RIU_5AppOutputPortRefreshPeriod;
    }

    public void setRIU_5AppOutputPortRefreshPeriod(double rIU_5AppOutputPortRefreshPeriod) {
        RIU_5AppOutputPortRefreshPeriod = rIU_5AppOutputPortRefreshPeriod;
    }

    public double getRIU_5LatencyCritical() {
        return RIU_5LatencyCritical;
    }

    public void setRIU_5LatencyCritical(double rIU_5LatencyCritical) {
        RIU_5LatencyCritical = rIU_5LatencyCritical;
    }

    public double getRIU_5Latency() {
        return RIU_5Latency;
    }

    public void setRIU_5Latency(double rIU_5Latency) {
        RIU_5Latency = rIU_5Latency;
    }

    public double getRIU_5Jitter() {
        return RIU_5Jitter;
    }

    public void setRIU_5Jitter(double rIU_5Jitter) {
        RIU_5Jitter = rIU_5Jitter;
    }

    public String getRIU_6Name() {
        return RIU_6Name;
    }

    public void setRIU_6Name(String rIU_6Name) {
        RIU_6Name = rIU_6Name;
    }

    public String getRIU_6Type() {
        return RIU_6Type;
    }

    public void setRIU_6Type(String rIU_6Type) {
        RIU_6Type = rIU_6Type;
    }

    public String getRIU_6AppName() {
        return RIU_6AppName;
    }

    public void setRIU_6AppName(String rIU_6AppName) {
        RIU_6AppName = rIU_6AppName;
    }

    public String getRIU_6AppInputPortName() {
        return RIU_6AppInputPortName;
    }

    public void setRIU_6AppInputPortName(String rIU_6AppInputPortName) {
        RIU_6AppInputPortName = rIU_6AppInputPortName;
    }

    public double getRIU_6AppInputPortSamplePeriod() {
        return RIU_6AppInputPortSamplePeriod;
    }

    public void setRIU_6AppInputPortSamplePeriod(double rIU_6AppInputPortSamplePeriod) {
        RIU_6AppInputPortSamplePeriod = rIU_6AppInputPortSamplePeriod;
    }

    public String getRIU_6AppOutputPortName() {
        return RIU_6AppOutputPortName;
    }

    public void setRIU_6AppOutputPortName(String rIU_6AppOutputPortName) {
        RIU_6AppOutputPortName = rIU_6AppOutputPortName;
    }

    public double getRIU_6AppOutputPortRefreshPeriod() {
        return RIU_6AppOutputPortRefreshPeriod;
    }

    public void setRIU_6AppOutputPortRefreshPeriod(double rIU_6AppOutputPortRefreshPeriod) {
        RIU_6AppOutputPortRefreshPeriod = rIU_6AppOutputPortRefreshPeriod;
    }

    public double getRIU_6LatencyCritical() {
        return RIU_6LatencyCritical;
    }

    public void setRIU_6LatencyCritical(double rIU_6LatencyCritical) {
        RIU_6LatencyCritical = rIU_6LatencyCritical;
    }

    public double getRIU_6Latency() {
        return RIU_6Latency;
    }

    public void setRIU_6Latency(double rIU_6Latency) {
        RIU_6Latency = rIU_6Latency;
    }

    public double getRIU_6Jitter() {
        return RIU_6Jitter;
    }

    public void setRIU_6Jitter(double rIU_6Jitter) {
        RIU_6Jitter = rIU_6Jitter;
    }

    public boolean isEmbeddedFunctionExists() {
        return EmbeddedFunctionExists;
    }

    public void setEmbeddedFunctionExists(boolean embeddedFunctionExists) {
        EmbeddedFunctionExists = embeddedFunctionExists;
    }

    public double getSubscribingLatency() {
        return SubscribingLatency;
    }

    public void setSubscribingLatency(double subscribingLatency) {
        SubscribingLatency = subscribingLatency;
    }

    public double getLatencyBudget() {
        return LatencyBudget;
    }

    public void setLatencyBudget(double latencyBudget) {
        LatencyBudget = latencyBudget;
    }

    public double getSystemLatency() {
        return SystemLatency;
    }

    public void setSystemLatency(double systemLatency) {
        SystemLatency = systemLatency;
    }

    public double getTTPLatency() {
        return TTPLatency;
    }

    public void setTTPLatency(double tTPLatency) {
        TTPLatency = tTPLatency;
    }

    public double getTTOLatency() {
        return TTOLatency;
    }

    public void setTTOLatency(double tTOLatency) {
        TTOLatency = tTOLatency;
    }

    public double getJitter() {
        return Jitter;
    }

    public void setJitter(double jitter) {
        Jitter = jitter;
    }

    public double getAESJitter() {
        return AESJitter;
    }

    public void setAESJitter(double aESJitter) {
        AESJitter = aESJitter;
    }

    public double getPhysicalNetworkJitter() {
        return PhysicalNetworkJitter;
    }

    public void setPhysicalNetworkJitter(double physicalNetworkJitter) {
        PhysicalNetworkJitter = physicalNetworkJitter;
    }

    public double getA429CANQueueDelay() {
        return A429CANQueueDelay;
    }

    public void setA429CANQueueDelay(double a429canQueueDelay) {
        A429CANQueueDelay = a429canQueueDelay;
    }

    public String getNonCompliance() {
        return NonCompliance;
    }

    public void setNonCompliance(String nonCompliance) {
        NonCompliance = nonCompliance;
    }

    public double getA664Latency() {
        return A664Latency;
    }

    public void setA664Latency(double a664Latency) {
        A664Latency = a664Latency;
    }

    public double getA664PhysicalLatency() {
        return A664PhysicalLatency;
    }

    public void setA664PhysicalLatency(double a664PhysicalLatency) {
        A664PhysicalLatency = a664PhysicalLatency;
    }

    public double getPhysicalLatency() {
        return PhysicalLatency;
    }

    public void setPhysicalLatency(double physicalLatency) {
        PhysicalLatency = physicalLatency;
    }

    public double getBAGLatency() {
        return BAGLatency;
    }

    public void setBAGLatency(double bAGLatency) {
        BAGLatency = bAGLatency;
    }

    public double getAsyncLatency() {
        return AsyncLatency;
    }

    public void setAsyncLatency(double asyncLatency) {
        AsyncLatency = asyncLatency;
    }

    public String getInCompliance() {
        return InCompliance;
    }

    public void setInCompliance(String inCompliance) {
        InCompliance = inCompliance;
    }

    public int getKey() {
        return Key;
    }

    public int getSubscribingPortSamplePeriod() {
        return SubscribingPortSamplePeriod;
    }

    public void setSubscribingPortSamplePeriod(int subscribingPortSamplePeriod) {
        SubscribingPortSamplePeriod = subscribingPortSamplePeriod;
    }

    public int getSubPeriod() {
        return SubPeriod;
    }

    public void setSubPeriod(int subPeriod) {
        SubPeriod = subPeriod;
    }

    public int getReceiveQueueLength() {
        return ReceiveQueueLength;
    }

    public void setReceiveQueueLength(int receiveQueueLength) {
        ReceiveQueueLength = receiveQueueLength;
    }

    public int getReceiveQueueSize() {
        return ReceiveQueueSize;
    }

    public void setReceiveQueueSize(int receiveQueueSize) {
        ReceiveQueueSize = receiveQueueSize;
    }

    public String getPublishingHardwareName() {
        return PublishingHardwareName;
    }

    public void setPublishingHardwareName(String publishingHardwareName) {
        PublishingHardwareName = publishingHardwareName;
    }

    public double getPublishingSwitchUplinkTxBW() {
        return PublishingSwitchUplinkTxBW;
    }

    public void setPublishingSwitchUplinkTxBW(double publishingSwitchUplinkTxBW) {
        PublishingSwitchUplinkTxBW = publishingSwitchUplinkTxBW;
    }

    public double getPublishingSwitchUplinkRxBW() {
        return PublishingSwitchUplinkRxBW;
    }

    public void setPublishingSwitchUplinkRxBW(double publishingSwitchUplinkRxBW) {
        PublishingSwitchUplinkRxBW = publishingSwitchUplinkRxBW;
    }

    public String getPublishingAppName() {
        return PublishingAppName;
    }

    public void setPublishingAppName(String publishingAppName) {
        PublishingAppName = publishingAppName;
    }

    public String getPublishingAppGuid() {
        return PublishingAppGuid;
    }

    public void setPublishingAppGuid(String publishingAppGuid) {
        PublishingAppGuid = publishingAppGuid;
    }

    public void setKey(int key) {
        Key = key;
    }

    public String getSubscribingHardwareName() {
        return SubscribingHardwareName;
    }

    public void setSubscribingHardwareName(String subscribingHardwareName) {
        SubscribingHardwareName = subscribingHardwareName;
    }

    public String getSubscribingAppName() {
        return SubscribingAppName;
    }

    public void setSubscribingAppName(String subscribingAppName) {
        SubscribingAppName = subscribingAppName;
    }

    public String getSubscribingAppATA() {
        return SubscribingAppATA;
    }

    public void setSubscribingAppATA(String subscribingAppATA) {
        SubscribingAppATA = subscribingAppATA;
    }

    public String getSubscribingAppGuid() {
        return SubscribingAppGuid;
    }

    public void setSubscribingAppGuid(String subscribingAppGuid) {
        SubscribingAppGuid = subscribingAppGuid;
    }

    public String getSubscribingPortName() {
        return SubscribingPortName;
    }

    public void setSubscribingPortName(String subscribingPortName) {
        SubscribingPortName = subscribingPortName;
    }

    public String getSubscribingPortGuid() {
        return SubscribingPortGuid;
    }

    public void setSubscribingPortGuid(String subscribingPortGuid) {
        SubscribingPortGuid = subscribingPortGuid;
    }

    public int getSubscribingPortPhysSpeed() {
        return SubscribingPortPhysSpeed;
    }

    public void setSubscribingPortPhysSpeed(int subscribingPortPhysSpeed) {
        SubscribingPortPhysSpeed = subscribingPortPhysSpeed;
    }

    public String getSubscribingPortType() {
        return SubscribingPortType;
    }

    public void setSubscribingPortType(String subscribingPortType) {
        SubscribingPortType = subscribingPortType;
    }
}
