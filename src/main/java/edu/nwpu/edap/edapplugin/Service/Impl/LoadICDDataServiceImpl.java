package edu.nwpu.edap.edapplugin.Service.Impl;

import edu.nwpu.edap.edapplugin.Service.LoadICDDataService;
import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.device.GPMAppNode;
import edu.nwpu.edap.edapplugin.bean.external.ExternalRDIU;
import edu.nwpu.edap.edapplugin.bean.external.sceneConfig.Scenario;
import edu.nwpu.edap.edapplugin.bean.gpm.TxVL;
import edu.nwpu.edap.edapplugin.bean.switchconfig.ExVLPath;
import edu.nwpu.edap.edapplugin.library.EndSystemDALibrary;
import edu.nwpu.edap.edapplugin.library.ExternalRDIULibrary;
import edu.nwpu.edap.edapplugin.library.SceneConfigLibrary;
import edu.nwpu.edap.edapplugin.library.SwitchConfigLibrary;

public class LoadICDDataServiceImpl implements LoadICDDataService{

	static int success = 0,error=0;
	
	@Override
	public void matchExternalRDIUSectionInterface(RPMessage rpMessage) {
		try {
			if(rpMessage==null || ExternalRDIULibrary.size()==0||(rpMessage.getPubRDIU()==null&&rpMessage.getSubRDIU()==null)) {
				return;
			}
			ExternalRDIU externalRDIU = ExternalRDIULibrary.getExternalRDIUByRPGuid(rpMessage.getRpGuid());
			if(externalRDIU==null) {
				rpMessage.addErrorMsg("未在区段接口文件中找到该RP消息的传输路径");
//				System.out.println(rpMessage.getRPGuid());
//				System.out.println("success:"+success+" error:"+error++);
				return;
			}else {
//				System.out.println("success:"+success+++" error:"+error);
			}
			//如果发送端存在RDIU
			if(rpMessage.getPubRDIU()!=null) {
				rpMessage.getPubRDIU().setJitter(externalRDIU.getRDIU_1Jitter());
				rpMessage.getPubRDIU().setLatency(externalRDIU.getRDIU_1Latency());
				if(rpMessage.getSubRDIU()!=null) {
					rpMessage.getSubRDIU().setJitter(externalRDIU.getRDIU_2Jitter());
					rpMessage.getSubRDIU().setLatency(externalRDIU.getRDIU_2Latency());
				}
			}else {//如果发送端不存在RDIU
				if(rpMessage.getSubRDIU()!=null) {
					rpMessage.getSubRDIU().setJitter(externalRDIU.getRDIU_2Jitter());
					rpMessage.getSubRDIU().setLatency(externalRDIU.getRDIU_2Latency());
					rpMessage.setSubRDIU(rpMessage.getSubRDIU());
				}
			}
		} catch (Exception e) {
			System.out.println(rpMessage.getRpGuid());
			System.out.println(rpMessage.getErrorMsg());
			e.printStackTrace();
		}
		return;
	}
	
	@Override
	public void matchExternalSwitchConfig(RPMessage rpMessage) {
		if(rpMessage==null||rpMessage.isError()||SwitchConfigLibrary.size()==0||rpMessage.isLocal()) {
			return;
		}
		ExVLPath exVLPath = SwitchConfigLibrary.getExVLPathByVLPathGuid(rpMessage.getAdnPart().getVlPath().getGuid());
		if(exVLPath==null) {
			rpMessage.addErrorMsg("消息VLPath从A664区段接口文件中获取ExVLPath失败");
			return;
		}
		rpMessage.getAdnPart().setPhysLatency(exVLPath.getPhysLatency());
		rpMessage.getAdnPart().setPhysJitter(exVLPath.getPhysJitter());
		return;
	}

	@Override
	public void matchExternalGPMAnalysis(RPMessage rpMessage) {
		if(rpMessage==null||rpMessage.isError()||rpMessage.isLocal()) {
			return;
		}
		//加载发送端
		if(rpMessage.getPubEndApp().getHardware().getType().equals("GPM")) {
			TxVL pubGPMTxVL = EndSystemDALibrary.getTxVLByGPMNameAndID(rpMessage.getPubEndApp().getHardware().getName(), rpMessage.getAdnPart().getVlID());
			if(pubGPMTxVL==null) {
				rpMessage.addErrorMsg("未找到对应发送端GPM区段接口文件信息");
				return;
			}
			((GPMAppNode)rpMessage.getPubEndApp()).setMaxMessageLatency(pubGPMTxVL.getTxComPorts().get(0).getMaxMessageLatency());
		}
	}

	@Override
	public void matchExternalSceneConfig(RPMessage rpMessage) {
		if(rpMessage==null||rpMessage.isError()) {
			return;
		}
		int scenarioId = SceneConfigLibrary.matchScenario(rpMessage);
		if(scenarioId==-1) {
			rpMessage.addErrorMsg("该数据传输场景不存在于配置文件");
			return;
		}
		Scenario scenario = SceneConfigLibrary.getScenarioById(scenarioId);
		scenario.setThreshold(rpMessage);
		rpMessage.setScenarioId(scenarioId);
	}

}
