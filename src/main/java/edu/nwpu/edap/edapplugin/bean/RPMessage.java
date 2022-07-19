package edu.nwpu.edap.edapplugin.bean;

import com.careri.as.businessmodel.model.RPDefType;
import edu.nwpu.edap.edapplugin.bean.adn.ADNPart;
import edu.nwpu.edap.edapplugin.bean.adn.SwitchNode;
import edu.nwpu.edap.edapplugin.bean.device.BaseDeviceAndSwitchNode;
import edu.nwpu.edap.edapplugin.bean.device.EndAppNode;
import edu.nwpu.edap.edapplugin.bean.device.RIUAppNode;

import java.util.ArrayList;
import java.util.List;

public class RPMessage {

    private double AsyncDelay;//给RP消息添加异步延迟属性

    private String rpGuid;

    private String pubGuid;

    private RPDefType rpDef;

    private EndAppNode pubEndApp;

    private EndAppNode subEndApp;

    private RIUAppNode pubRDIU;

    private RIUAppNode subRDIU;

    private int scenarioId = 0;

    private ADNPart adnPart = new ADNPart();

    private boolean isLocal = false;

    private String errorMsg = "";

    public boolean isError() {
        if(errorMsg=="") {
            return false;
        }
        return true;
    }

    public String getErrorMsg() {
        return rpGuid==null?"":("Guid为："+rpGuid+"的RP消息：")+errorMsg;
    }

    public void addErrorMsg(String errorMsg) {
        if(this.errorMsg!="") {
            errorMsg+=",";
        }
        this.errorMsg += errorMsg;
    }

    /**
     * 获取RPMessage中包含的交换机的数量
     * @return
     */
    public int getSwitchNodesSize() {
        if(adnPart==null||adnPart.getSwitchNodes()==null) {
            return 0;
        }
        return adnPart.getSwitchNodes().size();
    }

    /**
     * 按照顺序获取所有设备节点
     * @return
     */
    public List<BaseDeviceAndSwitchNode> getAllDeviceNodes(){
        if(isError()) {
            return null;
        }
        List<BaseDeviceAndSwitchNode> allDeviceNodes = new ArrayList<BaseDeviceAndSwitchNode>();
        if(pubEndApp!=null) {
            allDeviceNodes.add(pubEndApp);
        }
        if(pubRDIU!=null) {
            allDeviceNodes.add(pubRDIU);
        }
        if(!isLocal&&adnPart.getSwitchNodes()!=null) {
            for(SwitchNode switchNode:adnPart.getSwitchNodes()) {
                allDeviceNodes.add(switchNode);
            }
        }
        if(subRDIU!=null) {
            allDeviceNodes.add(subRDIU);
        }
        if(subEndApp!=null) {
            allDeviceNodes.add(subEndApp);
        }
        return allDeviceNodes;
    }

    /**
     * 判断发送端和接收端是否为同一个硬件
     * @return
     */
    public boolean isSameHardware() {
        if(isError()) {
            return false;
        }
        return pubEndApp.getHardware().getName().equals(subEndApp.getHardware().getName());
    }

    public String getRpGuid() {
        return rpGuid;
    }

    public void setRpGuid(String rpGuid) {
        this.rpGuid = rpGuid;
    }

    public String getPubGuid() {
        return pubGuid;
    }

    public void setPubGuid(String pubGuid) {
        this.pubGuid = pubGuid;
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

    public ADNPart getAdnPart() {
        return adnPart;
    }

    public void setAdnPart(ADNPart adnPart) {
        this.adnPart = adnPart;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean isLocal) {
        this.isLocal = isLocal;
    }

    public int getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(int scenarioId) {
        this.scenarioId = scenarioId;
    }

    @Override
    public String toString() {
        return "RPMessage [rpGuid=" + rpGuid + ", pubGuid=" + pubGuid + ", rpDef=" + rpDef + ", pubEndApp=" + pubEndApp
                + ", subEndApp=" + subEndApp + ", pubRDIU=" + pubRDIU + ", subRDIU=" + subRDIU + ", scenarioId="
                + scenarioId + ", adnPart=" + adnPart + ", isLocal=" + isLocal + "]";
    }
}
