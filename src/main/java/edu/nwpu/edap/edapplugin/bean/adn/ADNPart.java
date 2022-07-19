package edu.nwpu.edap.edapplugin.bean.adn;

import java.util.ArrayList;
import java.util.List;

import com.careri.as.businessmodel.model.A664MessageType;
import com.careri.as.businessmodel.model.ComPortRxType;
import com.careri.as.businessmodel.model.ComPortTxType;
import com.careri.as.businessmodel.model.RGWA664MessageType;
import com.careri.as.businessmodel.model.SubVLType;
import com.careri.as.businessmodel.model.VLPathType;
import com.careri.as.businessmodel.model.VirtualLinkType;

public class ADNPart {

    private int vlID;

    private String subVLID;

    private String pathName;

    private VirtualLinkType virtualLink;

    private VLPathType vlPath;

    private SubVLType subVL;

    private ComPortTxType comPortTx;

    private ComPortRxType comPortRx;

    private A664MessageType pubA664Message;

    private RGWA664MessageType pubRgwa664Message;

    //是否参与延迟计算
    private boolean count;

    //交换机列表，按照顺序排列
    private List<SwitchNode> switchNodes;

    //与ADN接收部分相连的端口
    private String subADNPort;

    //与ADN发出部分相连的端口
    private String pubADNPort;

    //物理抖动，从交换机区段接口文件中获得
    private double physJitter;

    //物理延迟，从交换机区段接口文件中获得
    private double physLatency;

    //抖动上限
    private double jitterThreshold;

    //延迟上限
    private double latencyThreshold;

    public void addSwitch(SwitchNode switchNode) {
        if(switchNodes==null) {
            switchNodes = new ArrayList<SwitchNode>();
        }
        switchNodes.add(switchNode);
    }

    public String getActualPath() {
        if(vlPath==null) {
            return null;
        }
        return vlPath.getActualPath();
    }

    public int getVlID() {
        return vlID;
    }

    public void setVlID(int vlID) {
        this.vlID = vlID;
    }

    public String getSubVLID() {
        return subVLID;
    }

    public void setSubVLID(String subVLID) {
        this.subVLID = subVLID;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public VirtualLinkType getVirtualLink() {
        return virtualLink;
    }

    public void setVirtualLink(VirtualLinkType virtualLink) {
        this.virtualLink = virtualLink;
    }

    public VLPathType getVlPath() {
        return vlPath;
    }

    public void setVlPath(VLPathType vlPath) {
        this.vlPath = vlPath;
    }

    public SubVLType getSubVL() {
        return subVL;
    }

    public void setSubVL(SubVLType subVL) {
        this.subVL = subVL;
    }

    public ComPortTxType getComPortTx() {
        return comPortTx;
    }

    public void setComPortTx(ComPortTxType comPortTx) {
        this.comPortTx = comPortTx;
    }

    public ComPortRxType getComPortRx() {
        return comPortRx;
    }

    public void setComPortRx(ComPortRxType comPortRx) {
        this.comPortRx = comPortRx;
    }

    public A664MessageType getPubA664Message() {
        return pubA664Message;
    }

    public void setPubA664Message(A664MessageType pubA664Message) {
        this.pubA664Message = pubA664Message;
    }

    public RGWA664MessageType getPubRgwa664Message() {
        return pubRgwa664Message;
    }

    public void setPubRgwa664Message(RGWA664MessageType pubRgwa664Message) {
        this.pubRgwa664Message = pubRgwa664Message;
    }

    public List<SwitchNode> getSwitchNodes() {
        return switchNodes;
    }

    public void setSwitchNodes(List<SwitchNode> switchNodes) {
        this.switchNodes = switchNodes;
    }

    public String getSubADNPort() {
        return subADNPort;
    }

    public void setSubADNPort(String subADNPort) {
        this.subADNPort = subADNPort;
    }

    public String getPubADNPort() {
        return pubADNPort;
    }

    public void setPubADNPort(String pubADNPort) {
        this.pubADNPort = pubADNPort;
    }

    public double getPhysJitter() {
        return physJitter;
    }

    public void setPhysJitter(double physJitter) {
        this.physJitter = physJitter;
    }

    public double getPhysLatency() {
        return physLatency;
    }

    public void setPhysLatency(double physLatency) {
        this.physLatency = physLatency;
    }

    public double getJitterThreshold() {
        return jitterThreshold;
    }

    public void setJitterThreshold(double jitterThreshold) {
        this.jitterThreshold = jitterThreshold;
    }

    public double getLatencyThreshold() {
        return latencyThreshold;
    }

    public void setLatencyThreshold(double latencyThreshold) {
        this.latencyThreshold = latencyThreshold;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ADNPart [vlID=" + vlID + ", subVLID=" + subVLID + ", pathName=" + pathName + ", count=" + count
                + ", switchNodes=" + switchNodes + ", subADNPort=" + subADNPort + ", pubADNPort=" + pubADNPort
                + ", physJitter=" + physJitter + ", physLatency=" + physLatency + ", jitterThreshold=" + jitterThreshold
                + ", latencyThreshold=" + latencyThreshold + "]";
    }

}
