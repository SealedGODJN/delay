package edu.nwpu.edap.edapplugin.bean.external.sceneConfig;

import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.device.GPMAppNode;
import edu.nwpu.edap.edapplugin.utils.CommonUtil;
import org.apache.commons.collections4.map.HashedMap;

import java.util.ArrayList;
import java.util.Map;

/**
 * 数据传输场景配置文件的Scene标签类
 * @author zhang yifu
 *
 */
public class Scenario {

    /**
     * 场景id
     */
    private int id;

    /**
     * 场景中包含节点个数
     */
    private int num;

    /**
     * 节点List
     */
    private ArrayList<DeviceNode> deviceNodes;
    
    private Map<String, DeviceNode> deviceNodeMapByPos;

    public Scenario(int id, int num, ArrayList<DeviceNode> deviceNodes) {
        this.id = id;
        this.num = num;
        this.deviceNodes = deviceNodes;
    }
    
    /**
     * 场景和RP消息是否匹配
     * @param rpMessage
     * @return
     */
    public boolean match(RPMessage rpMessage) {
    	if(rpMessage==null) {
    		return false;
    	}
    	//检验发送端口
    	String pubPortType = CommonUtil.transferPortType2DeviceNodeType(rpMessage.getPubEndApp().getPort().getType());
    	String pubEndDeviceNodeType = deviceNodeMapByPos.get("PubEndSystem").getType();
    	if(!pubPortType.equals(pubEndDeviceNodeType)) {
    		return false;
    	}
    	//检验接收端
    	String subPortType = CommonUtil.transferPortType2DeviceNodeType(rpMessage.getSubEndApp().getPort().getType());
    	String subEndDeviceNodeType = deviceNodeMapByPos.get("SubEndSystem").getType();
    	if(!subPortType.equals(subEndDeviceNodeType)) {
    		return false;
    	}
    	if(rpMessage.isLocal()) {
    		if(rpMessage.getPubEndApp().getType().equals("HostedFunction")&&deviceNodeMapByPos.get("MidRDIU")==null) {
    			return false;
    		}else if(rpMessage.getPubEndApp().getType().equals("A653ApplicationComponent")&&num!=2) {
    			return false;
    		}
    	}else {
    		//检验发送端RDIU
        	if((rpMessage.getPubRDIU()==null&&deviceNodeMapByPos.get("PubRDIU")!=null)||
        			(rpMessage.getPubRDIU()!=null&&deviceNodeMapByPos.get("PubRDIU")==null)) {
        		return false;
        	}
        	//检验接收端RDIU
        	if((rpMessage.getSubRDIU()==null&&deviceNodeMapByPos.get("SubRDIU")!=null)||
        			(rpMessage.getSubRDIU()!=null&&deviceNodeMapByPos.get("SubRDIU")==null)) {
        		return false;
        	}
        	//检验ADN网络
        	if(deviceNodeMapByPos.get("VL")==null) {
        		return false;
        	}
    	}
    	return true;
    }
    
    /**
     * 为该场景下的RPMessage添加各个节点的预设上限延迟和上限抖动
     * @param rpMessage
     */
    public void setThreshold(RPMessage rpMessage) {
    	//设置发送端App
    	if(CommonUtil.isGPMApp(rpMessage.getPubEndApp().getType())) {
        	((GPMAppNode)rpMessage.getPubEndApp()).setLatencyThreshold(deviceNodeMapByPos.get("PubEndSystem").getLatencyThreshold());
        	((GPMAppNode)rpMessage.getPubEndApp()).setJitterThreshold(deviceNodeMapByPos.get("PubEndSystem").getJitterThreshold());
        	((GPMAppNode)rpMessage.getPubEndApp()).setCount(deviceNodeMapByPos.get("PubEndSystem").isCount());
    	}

    	//设置接收端App
    	if(CommonUtil.isGPMApp(rpMessage.getSubEndApp().getType())) {
        	((GPMAppNode)rpMessage.getSubEndApp()).setLatencyThreshold(deviceNodeMapByPos.get("SubEndSystem").getLatencyThreshold());
        	((GPMAppNode)rpMessage.getSubEndApp()).setJitterThreshold(deviceNodeMapByPos.get("SubEndSystem").getJitterThreshold());
        	((GPMAppNode)rpMessage.getSubEndApp()).setCount(deviceNodeMapByPos.get("SubEndSystem").isCount());
    	}
    	//设置RDIU和ADN
    	if(rpMessage.isLocal()) {
    		//设置Local情况
    		if(rpMessage.getPubEndApp().getType().equals("HostedFunction")) {
    			rpMessage.getPubRDIU().setLatencyThreshold(deviceNodeMapByPos.get("MidRDIU").getLatencyThreshold());
        		rpMessage.getPubRDIU().setJitterThreshold(deviceNodeMapByPos.get("MidRDIU").getJitterThreshold());
        		rpMessage.setSubRDIU(rpMessage.getPubRDIU());
    		}
    	}else {
    		//设置发送端RDIU
    		if(rpMessage.getPubRDIU()!=null) {
    			rpMessage.getPubRDIU().setLatencyThreshold(deviceNodeMapByPos.get("PubRDIU").getLatencyThreshold());
    			rpMessage.getPubRDIU().setJitterThreshold(deviceNodeMapByPos.get("PubRDIU").getJitterThreshold());
    			rpMessage.getPubRDIU().setCount(deviceNodeMapByPos.get("PubRDIU").isCount());
    		}
    		//设置接收端RDIU
    		if(rpMessage.getSubRDIU()!=null) {
    			rpMessage.getSubRDIU().setLatencyThreshold(deviceNodeMapByPos.get("SubRDIU").getLatencyThreshold());
    			rpMessage.getSubRDIU().setJitterThreshold(deviceNodeMapByPos.get("SubRDIU").getJitterThreshold());
    			rpMessage.getSubRDIU().setCount(deviceNodeMapByPos.get("SubRDIU").isCount());
    		}
    		//设置ADN网络
    		rpMessage.getAdnPart().setLatencyThreshold(deviceNodeMapByPos.get("VL").getLatencyThreshold());
    		rpMessage.getAdnPart().setJitterThreshold(deviceNodeMapByPos.get("VL").getJitterThreshold());
    		rpMessage.getAdnPart().setCount(deviceNodeMapByPos.get("VL").isCount());
    	}
    }
    
    /**
     * 将List<DeviceNode>映射为Map
     */
    public void mapDeviceNode2Map() {
    	deviceNodeMapByPos = new HashedMap<String, DeviceNode>();
    	for(DeviceNode deviceNode:deviceNodes) {
    		deviceNodeMapByPos.put(deviceNode.getPos(), deviceNode);
    	}
    }
    
    /**
     * 检查该场景是否符合条件
     * @return
     */
    public boolean check() {
    	if(deviceNodeMapByPos.get("PubEndSystem")==null||deviceNodeMapByPos.get("SubEndSystem")==null) {
    		return false;
    	}
    	DeviceNode pubEndSystem = deviceNodeMapByPos.get("PubEndSystem");
    	DeviceNode subEndSystem = deviceNodeMapByPos.get("SubEndSystem");
    	if(!pubEndSystem.getType().equals("A653")&&(pubEndSystem.getJitterThreshold()>0||pubEndSystem.getLatencyThreshold()>0)) {
    		return false;
    	}
    	if(!subEndSystem.getType().equals("A653")&&(subEndSystem.getJitterThreshold()>0||subEndSystem.getLatencyThreshold()>0)) {
    		return false;
    	}
    	if(deviceNodeMapByPos.get("VL")!=null&&deviceNodeMapByPos.get("MidRDIU")!=null) {
    		return false;
    	}
    	return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<DeviceNode> getNodes() {
        return deviceNodes;
    }

    public void setNodes(ArrayList<DeviceNode> deviceNodes) {
        this.deviceNodes = deviceNodes;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "id=" + id +
                ", num=" + num +
                ", nodes=" + deviceNodes +
                '}';
    }
}
