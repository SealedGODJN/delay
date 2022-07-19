package edu.nwpu.edap.edapplugin.Service;

import edu.nwpu.edap.edapplugin.bean.RPMessage;

public interface ConstructSceneService {
		
	//获取消息传输路径和端口基本信息
	public RPMessage seekPathByPort(String pubPortType,String subPortType,String rpGuid,String rpPortGuid,String rpPubRef,String rpDataflowRef,String rpGuidDef);

	//获取VirtualLink和硬件信息
	public RPMessage getHardwareInfo(RPMessage rpMessage);
	
	//获取交换机信息
	public void getSwitchesInfo(RPMessage rpMessage);
	
}
