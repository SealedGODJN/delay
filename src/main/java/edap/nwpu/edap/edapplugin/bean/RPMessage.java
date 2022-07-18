package edap.nwpu.edap.edapplugin.bean;

import com.careri.as.businessmodel.model.RPDefType;
import edap.nwpu.edap.edapplugin.bean.adn.ADNPart;
import edap.nwpu.edap.edapplugin.bean.device.BaseDeviceAndSwitchNode;
import edap.nwpu.edap.edapplugin.bean.device.EndAppNode;
import edap.nwpu.edap.edapplugin.bean.device.RIUAppNode;

import java.util.ArrayList;
import java.util.List;

public class RPMessage {
    public String rpGuid;
    public RPDefType rpDef;
    public EndAppNode pubEndApp;
    public EndAppNode subEndApp;
    public RIUAppNode pubRDIU;
    public RIUAppNode subRDIU;
    public ADNPart andPart;
    public int scenarioId;
    public boolean isLocal;
    public String errorMsg;
    public double MessageSize;


    /**
     * TODO：方法待实现
     * @return 返回一个消息经过的所有节点
     */
    public List<BaseDeviceAndSwitchNode> getAllDeviceNodes() {
        return new ArrayList<>();
    }

    public double getMessageSize() {
        return MessageSize;
    }

    public void setMessageSize(double messageSize) {
        MessageSize = messageSize;
    }

    public String getRpGuid() {
        return rpGuid;
    }

    public void setRpGuid(String rpGuid) {
        this.rpGuid = rpGuid;
    }

    public RPDefType getRpDef() {
        return rpDef;
    }

    public void setRpDef(RPDefType rpDef) {
        this.rpDef = rpDef;
    }

    public EndAppNode getPubEndApp() {
        return pubEndApp;
    }

    public void setPubEndApp(EndAppNode pubEndApp) {
        this.pubEndApp = pubEndApp;
    }

    public EndAppNode getSubEndApp() {
        return subEndApp;
    }

    public void setSubEndApp(EndAppNode subEndApp) {
        this.subEndApp = subEndApp;
    }

    public RIUAppNode getPubRDIU() {
        return pubRDIU;
    }

    public void setPubRDIU(RIUAppNode pubRDIU) {
        this.pubRDIU = pubRDIU;
    }

    public RIUAppNode getSubRDIU() {
        return subRDIU;
    }

    public void setSubRDIU(RIUAppNode subRDIU) {
        this.subRDIU = subRDIU;
    }

    public ADNPart getAndPart() {
        return andPart;
    }

    public void setAndPart(ADNPart andPart) {
        this.andPart = andPart;
    }

    public int getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(int scenarioId) {
        this.scenarioId = scenarioId;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
