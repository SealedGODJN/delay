package com.nwpu.delay.Message;

import org.omg.CORBA.PUBLIC_MEMBER;

public class ADNPart {
    public VirtualLinkType virtualLinkType;
    public int vlID;
    public boolean count;
    public String subVLID;
    public String pathName;
    public VLPathType vlPath;
    public SubVLType subVL;
    public ComPortTxType comPortTx;
    public ComPortRxType comPortRx;
    public List<SwitchNode> switchNodeList;
    public A664MessageType pubA664Meaage;
    public RGWA664MessageType pubRGWA664Message;
    public String subADNPort;
    public String pubADNPort;
    public double jitter;
    public double latency;
    public double jitterThreshold;
    public double latencyThreshold;
}
