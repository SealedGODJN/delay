package edu.nwpu.edap.edapplugin.Service.Impl;

import com.careri.as.businessmodel.model.*;
import edu.nwpu.edap.edapplugin.Service.ConstructSceneService;
import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.adn.ACSSwitchNode;
import edu.nwpu.edap.edapplugin.bean.adn.ARSSwitchNode;
import edu.nwpu.edap.edapplugin.bean.device.GPMAppNode;
import edu.nwpu.edap.edapplugin.bean.device.LRUAppNode;
import edu.nwpu.edap.edapplugin.bean.device.RIUAppNode;
import edu.nwpu.edap.edapplugin.bean.hardware.*;
import edu.nwpu.edap.edapplugin.bean.port.*;
import edu.nwpu.edap.edapplugin.test.LoadICDDataTest;
import edu.nwpu.edap.edapplugin.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class ConstructSceneServiceImpl implements ConstructSceneService{
	
	private RPMessage rpMessage;
	
	private LoadICDDataTest loadICDDataTest = LoadICDDataTest.getInstance();	
	
//	private LogService logService = new LogServiceImpl("EDAP");
	
	@Override
	public RPMessage seekPathByPort(String pubPortType, String subPortType, String rpGuid, String rpPortGuid,
			String rpPubRef, String rpDataflowRef,String rpGuidDef) {
		rpMessage = new RPMessage();//对于每一次寻找路径，new一个新的RPMessage类
		rpMessage.setRpGuid(rpGuid);//设置rpMessage的RP的Guid
		//如果存在属性为空，则添加报错信息并返回
		if(pubPortType==null||subPortType==null||rpGuid==null||rpPortGuid==null||rpPubRef==null||rpDataflowRef==null||rpGuidDef==null) {
			rpMessage.addErrorMsg("初始传入数据中存在属性为空");
			return rpMessage;
		}
		if(rpPubRef=="N/A") {
			rpMessage.addErrorMsg("RP消息不存在或者存在多个PubRef");
			return rpMessage;
		}
		RPDefType rpDef = loadICDDataTest.getRPDefByGuid(rpGuidDef);
		if(rpDef==null) {
			rpMessage.addErrorMsg("消息获取接收端RP消息的定义类错误");
			return rpMessage;
		}
		rpMessage.setRpDef(rpDef);
		rpMessage.setPubGuid(rpPubRef);
		String pubPortGuid = loadICDDataTest.getPubPortGuidByDPGuid(rpPubRef);//根据RP消息的Pub_Ref属性获取发送端端口的Guid
		if(isNull(pubPortGuid,"pubPortGuid")) {
			return rpMessage;
		}
		/**************************Hardware************************/
		String pubPortHardwareType = loadICDDataTest.getSoftwareInstanceTypeByPortGuid(pubPortGuid);
		String subPortHardwareType = loadICDDataTest.getSoftwareInstanceTypeByPortGuid(rpPortGuid);
		
		if(pubPortHardwareType==null||subPortHardwareType==null) {
			rpMessage.addErrorMsg("发送或接收端口存在一端未找到硬件");
			return rpMessage;
		}
		
		if(isRIUApplication(pubPortHardwareType)||isRIUApplication(subPortHardwareType)) {
			rpMessage.addErrorMsg("发送或接收端口存在一端为RIUApplication");
			return rpMessage;
		}
		
		if(isSwitchApplication(pubPortHardwareType)||isSwitchApplication(subPortHardwareType)) {
			rpMessage.addErrorMsg("发送或接收端口存在一端为SwitchApplication");
			return rpMessage;
		}
		/**********************************************************/
		String pubA664PortGuid = null;//定义发送端部分的A664端口，即RDIU上的HF端口或者本地的HF/A653端口
		//获取接收端路径
		pubA664PortGuid = setSubPartPath(rpDataflowRef, subPortType, pubPortType, pubPortGuid, rpPortGuid);
		if(pubA664PortGuid==null) {
			rpMessage.addErrorMsg("该RP消息的接收部分Dataflow_Ref为空");
			return rpMessage;
		}else {
			//设置发送端的路径
			setPubPartPath(pubPortGuid, pubPortType, pubA664PortGuid);
			//检验是否VLID和PathName均存在
		}
		return rpMessage;
	}
	

	@Override
	public RPMessage getHardwareInfo(RPMessage rpMessage) {
		if(rpMessage==null||rpMessage.isError()) {
			return rpMessage;
		}
		if(rpMessage.isLocal()) {
			//如果是Local
			if(rpMessage.getAdnPart().getSubVLID()==null) {
				if(rpMessage.getPubRDIU()==null||rpMessage.getSubRDIU()==null) {
					rpMessage.addErrorMsg("通过本地RDIU交换的信息缺少RDIU信息");
				}else {
					if(rpMessage.getPubRDIU().getSubPort()==null||rpMessage.getSubRDIU().getPubPort()==null) {
						rpMessage.addErrorMsg("通过本地RDIU交换的信息缺少RDIU端口信息");
					}else {
						rpMessage.getPubRDIU().setPubPort(rpMessage.getSubRDIU().getPubPort());
						rpMessage.getPubRDIU().setRefreshPeriod(rpMessage.getSubRDIU().getRefreshPeriod());
						rpMessage.setSubRDIU(rpMessage.getPubRDIU());
					}
				}
			}
		}
		
		//获取发送端和接收端应用类型、名称、Guid、Hardware
		//接收端
		String subPortGuid = rpMessage.getSubEndApp().getPort().getGuid();
		SoftwareInstanceType subSoftwareInstance = loadICDDataTest.getSoftwareInstanceByPortGuid(subPortGuid);
		String subEndApplicationType = loadICDDataTest.getSoftwareInstanceTypeByPortGuid(subPortGuid);
		if(subSoftwareInstance==null||subEndApplicationType==null) {
			rpMessage.addErrorMsg("获取接收端端系统应用时错误");
			return rpMessage;
		}
		SoftwareClassType subSoftwareClass = loadICDDataTest.getSoftwareClassByGuid(subSoftwareInstance.getGuidDef());
		if(subSoftwareClass==null) {
			rpMessage.addErrorMsg("获取接收端端应用定义类时错误");
			return rpMessage;
		}
		rpMessage.getSubEndApp().setGuid(subSoftwareInstance.getGuid());
		rpMessage.getSubEndApp().setName(subSoftwareInstance.getName());
		rpMessage.getSubEndApp().setType(subEndApplicationType);
		rpMessage.getSubEndApp().setAta(subSoftwareClass.getATA());
		EndSystemType subEndSystem = loadICDDataTest.getEndSystemByHardwareName(removeGPMNamePosInfo(subSoftwareInstance.getHardware()));	
		if(subEndSystem==null) {
			rpMessage.addErrorMsg("获取接收端硬件信息错误");
			return rpMessage;
		}
		if(CommonUtil.isA664TypePort(rpMessage.getSubEndApp().getPort().getType())) {
			//如果是GPM
			if(isGPM(mapEndApplicationType2HardwareType(subEndApplicationType))) {
				GPMTypeHardware subEndAppHardware = new GPMTypeHardware();
				subEndAppHardware.setType(mapEndApplicationType2HardwareType(subEndApplicationType));
				subEndAppHardware.setName(removeGPMNamePosInfo(subSoftwareInstance.getHardware()));
				subEndAppHardware.setGuid(subEndSystem.getGuid());
				//设置GPM类型
				GPMClassType subGPMClass = loadICDDataTest.getGPMClassByGuid(subEndSystem.getGuidDef());
				if(subGPMClass==null) {
					rpMessage.addErrorMsg("获取接收端硬件定义类错误");
					return rpMessage;
				}
				subEndAppHardware.setESHighIntegrity(subGPMClass.getESHighIntegrity());
				subEndAppHardware.setESTechLatencyTx(subGPMClass.getESTechLatencyTx());
				subEndAppHardware.setESTechLatencyRx(subGPMClass.getESTechLatencyRx());
				subEndAppHardware.setESTransmitPolicy(subGPMClass.getESTransmitPolicy());
				rpMessage.getSubEndApp().setHardware(subEndAppHardware);
			}else {
				//如果是A664类型
				A664LRUTypeHardware subEndAppHardware = new A664LRUTypeHardware();
				subEndAppHardware.setType(mapEndApplicationType2HardwareType(subEndApplicationType));
				subEndAppHardware.setName(removeGPMNamePosInfo(subSoftwareInstance.getHardware()));
				subEndAppHardware.setGuid(subEndSystem.getGuid());
				LRUClassType subLRUClass = loadICDDataTest.getLRUClassByGuid(subEndSystem.getGuidDef());
				if(subLRUClass==null) {
					rpMessage.addErrorMsg("获取接收端硬件定义类错误");
					return rpMessage;
				}
				subEndAppHardware.setESHighIntegrity(subLRUClass.getESHighIntegrity());
				subEndAppHardware.setESTechLatencyTx(subLRUClass.getESTechLatencyTx());
				subEndAppHardware.setESTechLatencyRx(subLRUClass.getESTechLatencyRx());
				subEndAppHardware.setESTransmitPolicy(subLRUClass.getESTransmitPolicy());
				rpMessage.getSubEndApp().setHardware(subEndAppHardware);
			}
		}else {
			//如果是非A664类型的LRU
			NonA664LRUTypeHardware subEndAppHardware = new NonA664LRUTypeHardware();
			subEndAppHardware.setType(mapEndApplicationType2HardwareType(subEndApplicationType));
			subEndAppHardware.setName(removeGPMNamePosInfo(subSoftwareInstance.getHardware()));
			subEndAppHardware.setGuid(subEndSystem.getGuid());
			LRUClassType subLRUClass = loadICDDataTest.getLRUClassByGuid(subEndSystem.getGuidDef());
			if(subLRUClass==null) {
				rpMessage.addErrorMsg("获取接收端硬件定义类错误");
				return rpMessage;
			}
			if(rpMessage.getSubEndApp().getPort().getType().equals("A429Port")) {
				A429PhysPortDefType a429PhysPortDef = loadICDDataTest.getA429PhysPortDefByLRUClassAndName(subLRUClass, ((A429PortNode)rpMessage.getSubEndApp().getPort()).getPhysPortName());
				if(a429PhysPortDef==null) {
					rpMessage.addErrorMsg("消息获取接收端A429PhysPort定义类错误");
					return rpMessage;
				}
				((A429PortNode)rpMessage.getSubEndApp().getPort()).setBiteRate(a429PhysPortDef.getBitRate());
			}else if(rpMessage.getSubEndApp().getPort().getType().equals("CANPort")) {
				CANPhysPortDefType canPhysPortDef = loadICDDataTest.getCANPhysPortDefByLRUClassAndName(subLRUClass, ((CANPortNode)rpMessage.getSubEndApp().getPort()).getPhysPortName());
				if(canPhysPortDef==null) {
					rpMessage.addErrorMsg("消息获取接收端CANPhysPort定义类错误");
					return rpMessage;
				}
				((CANPortNode)rpMessage.getSubEndApp().getPort()).setCanBiteRate(canPhysPortDef.getCANBitRate());
			}
			rpMessage.getSubEndApp().setHardware(subEndAppHardware);
		}
		
		//发送端
		String pubPortGuid = rpMessage.getPubEndApp().getPort().getGuid();
		SoftwareInstanceType pubSoftwareInstance = loadICDDataTest.getSoftwareInstanceByPortGuid(pubPortGuid);
		String pubEndApplicationType = loadICDDataTest.getSoftwareInstanceTypeByPortGuid(pubPortGuid);
		if(pubSoftwareInstance==null || pubEndApplicationType==null) {
			rpMessage.addErrorMsg("获取发送端端系统应用时错误");
			return rpMessage;
		}
		SoftwareClassType pubSoftwareClass = loadICDDataTest.getSoftwareClassByGuid(pubSoftwareInstance.getGuidDef());
		if(pubSoftwareClass==null) {
			rpMessage.addErrorMsg("获取发送端端应用定义类时错误");
			return rpMessage;
		}
		rpMessage.getPubEndApp().setAta(pubSoftwareClass.getATA());
		rpMessage.getPubEndApp().setGuid(pubSoftwareInstance.getGuid());
		rpMessage.getPubEndApp().setName(pubSoftwareInstance.getName());
		rpMessage.getPubEndApp().setType(pubEndApplicationType);
		EndSystemType pubEndSystem = loadICDDataTest.getEndSystemByHardwareName(removeGPMNamePosInfo(pubSoftwareInstance.getHardware()));
		if(pubEndSystem==null) {
			System.out.println(pubSoftwareInstance.getHardware());
			rpMessage.addErrorMsg("获取发送端硬件信息错误");
			return rpMessage;
		}
		if(CommonUtil.isA664TypePort(rpMessage.getPubEndApp().getPort().getType())) {
			//如果是GPM
			if(isGPM(mapEndApplicationType2HardwareType(pubEndApplicationType))) {
				GPMTypeHardware pubEndAppHardware = new GPMTypeHardware();
				pubEndAppHardware.setType(mapEndApplicationType2HardwareType(pubEndApplicationType));
				pubEndAppHardware.setName(removeGPMNamePosInfo(pubSoftwareInstance.getHardware()));
				pubEndAppHardware.setGuid(pubEndSystem.getGuid());
				//设置GPM类型
				GPMClassType pubGPMClass = loadICDDataTest.getGPMClassByGuid(pubEndSystem.getGuidDef());
				if(pubGPMClass==null) {
					rpMessage.addErrorMsg("获取接收端硬件定义类错误");
					return rpMessage;
				}
				pubEndAppHardware.setESHighIntegrity(pubGPMClass.getESHighIntegrity());
				pubEndAppHardware.setESTechLatencyTx(pubGPMClass.getESTechLatencyTx());
				pubEndAppHardware.setESTechLatencyRx(pubGPMClass.getESTechLatencyRx());
				pubEndAppHardware.setESTransmitPolicy(pubGPMClass.getESTransmitPolicy());
				rpMessage.getPubEndApp().setHardware(pubEndAppHardware);
			}else {
				//如果是A664类型
				A664LRUTypeHardware pubEndAppHardware = new A664LRUTypeHardware();
				pubEndAppHardware.setType(mapEndApplicationType2HardwareType(pubEndApplicationType));
				pubEndAppHardware.setName(removeGPMNamePosInfo(pubSoftwareInstance.getHardware()));
				pubEndAppHardware.setGuid(pubEndSystem.getGuid());
				LRUClassType pubLRUClass = loadICDDataTest.getLRUClassByGuid(pubEndSystem.getGuidDef());
				if(pubLRUClass==null) {
					rpMessage.addErrorMsg("获取接收端硬件定义类错误");
					return rpMessage;
				}
				pubEndAppHardware.setESHighIntegrity(pubLRUClass.getESHighIntegrity());
				pubEndAppHardware.setESTechLatencyTx(pubLRUClass.getESTechLatencyTx());
				pubEndAppHardware.setESTechLatencyRx(pubLRUClass.getESTechLatencyRx());
				pubEndAppHardware.setESTransmitPolicy(pubLRUClass.getESTransmitPolicy());
				rpMessage.getPubEndApp().setHardware(pubEndAppHardware);
			}
		}else {
			//如果是非A664类型的LRU
			NonA664LRUTypeHardware pubEndAppHardware = new NonA664LRUTypeHardware();
			pubEndAppHardware.setType(mapEndApplicationType2HardwareType(pubEndApplicationType));
			pubEndAppHardware.setName(removeGPMNamePosInfo(pubSoftwareInstance.getHardware()));
			pubEndAppHardware.setGuid(pubEndSystem.getGuid());
			LRUClassType pubLRUClass = loadICDDataTest.getLRUClassByGuid(pubEndSystem.getGuidDef());
			if(pubLRUClass==null) {
				rpMessage.addErrorMsg("获取接收端硬件定义类错误");
				return rpMessage;
			}
			if(rpMessage.getPubEndApp().getPort().getType().equals("A429Port")) {
				A429PhysPortDefType a429PhysPortDef = loadICDDataTest.getA429PhysPortDefByLRUClassAndName(pubLRUClass, ((A429PortNode)rpMessage.getPubEndApp().getPort()).getPhysPortName());
				if(a429PhysPortDef==null) {
					rpMessage.addErrorMsg("消息获取接收端A429PhysPort定义类错误");
					return rpMessage;
				}
				((A429PortNode)rpMessage.getPubEndApp().getPort()).setBiteRate(a429PhysPortDef.getBitRate());
			}else if(rpMessage.getPubEndApp().getPort().getType().equals("CANPort")) {
				CANPhysPortDefType canPhysPortDef = loadICDDataTest.getCANPhysPortDefByLRUClassAndName(pubLRUClass, ((CANPortNode)rpMessage.getPubEndApp().getPort()).getPhysPortName());
				if(canPhysPortDef==null) {
					rpMessage.addErrorMsg("消息获取接收端CANPhysPort定义类错误");
					return rpMessage;
				}
				((CANPortNode)rpMessage.getPubEndApp().getPort()).setCanBiteRate(canPhysPortDef.getCANBitRate());
			}
			rpMessage.getPubEndApp().setHardware(pubEndAppHardware);
		}	
		
		//获取发送端RDIU的硬件信息
		if(rpMessage.getPubRDIU()!=null) {
			RIUAppNode pubRDIU = rpMessage.getPubRDIU();
			RemoteGatewayType pubRemoteGateway = loadICDDataTest.getRemoteGatewayByPortGuid(pubRDIU.getSubPort().getGuid());
			if(pubRemoteGateway==null) {
				rpMessage.addErrorMsg("发送端RDIU获取RemoteGateway信息错误");
				return rpMessage;
			}
			pubRDIU.setGuid(pubRemoteGateway.getGuid());
			pubRDIU.setName(pubRemoteGateway.getName());
			pubRDIU.setType("RemoteGateway");
			String pubRDIUHWName = pubRemoteGateway.getHardware();
			EndSystemType pubRDIUHW = loadICDDataTest.getEndSystemByHardwareName(pubRDIUHWName);
			RDIUTypeHardware pubHardware = new RDIUTypeHardware();
			pubHardware.setType("RIU");
			pubHardware.setName(pubRDIUHW.getName());
			pubHardware.setGuid(pubRDIUHW.getGuid());
			String pubRDIUDefGuid = pubRDIUHW.getGuidDef();
			RIUClassType pubRIUClass = loadICDDataTest.getRIUClassTypeByGuid(pubRDIUDefGuid);
			if(pubRIUClass==null) {
				rpMessage.addErrorMsg("获取RDIU发送端的Class错误");
			}
			//设置RDIU上面A429和CAN端口的比特速率
			if(rpMessage.getPubEndApp().getPort().getType().equals("A429Port")) {
				((A429PortNode)rpMessage.getPubRDIU().getSubPort()).setBiteRate(
						loadICDDataTest.getA429PhysPortDefByRIUClassAndName(
								pubRIUClass,((A429PortNode)rpMessage.getPubRDIU().getSubPort()).getPhysPortName()).getBitRate());
			}else if(rpMessage.getPubEndApp().getPort().getType().equals("CANPort")) {
				((CANPortNode)rpMessage.getPubRDIU().getSubPort()).setCanBiteRate(
						loadICDDataTest.getCANPhysPortDefByRIUClassAndName(
								pubRIUClass,((CANPortNode)rpMessage.getPubRDIU().getSubPort()).getPhysPortName()).getCANBitRate());
			}
			//设置RDIU上面的ES内容
			pubHardware.setESHighIntegrity(pubRIUClass.getESHighIntegrity());
			pubHardware.setESTechLatencyTx(pubRIUClass.getESTechLatencyTx());
			pubHardware.setESTechLatencyRx(pubRIUClass.getESTechLatencyRx());
			pubHardware.setESTransmitPolicy(pubRIUClass.getESTransmitPolicy());
			pubRDIU.setRIUType(pubRIUClass.getRIUType());
			pubRDIU.setHardware(pubHardware);
			rpMessage.setPubRDIU(pubRDIU);
		}
		
		//获取接收端RDIU的硬件信息
		if(rpMessage.getSubRDIU()!=null) {
			RIUAppNode subRDIU = rpMessage.getSubRDIU();
			RemoteGatewayType subRemoteGateway = loadICDDataTest.getRemoteGatewayByPortGuid(subRDIU.getPubPort().getGuid());
			if(subRemoteGateway==null) {
				rpMessage.addErrorMsg("接收端RDIU获取RemoteGateway信息错误");
				return rpMessage;
			}
			subRDIU.setGuid(subRemoteGateway.getGuid());
			subRDIU.setName(subRemoteGateway.getName());
			subRDIU.setType("RemoteGateway");
			String subRDIUHWName = subRemoteGateway.getHardware();
			EndSystemType subRDIUHW = loadICDDataTest.getEndSystemByHardwareName(subRDIUHWName);
			RDIUTypeHardware subHardware = new RDIUTypeHardware();
			subHardware.setType("RIU");
			subHardware.setName(subRDIUHW.getName());
			subHardware.setGuid(subRDIUHW.getGuid());
			String subRDIUDefGuid = subRDIUHW.getGuidDef();
			RIUClassType subRIUClass = loadICDDataTest.getRIUClassTypeByGuid(subRDIUDefGuid);
			if(subRIUClass==null) {
				rpMessage.addErrorMsg("获取RDIU发送端的Class错误");
			}		
			//设置RDIU上面A429和CAN端口的比特速率
			if(rpMessage.getSubEndApp().getPort().getType().equals("A429Port")) {
				((A429PortNode)rpMessage.getSubRDIU().getPubPort()).setBiteRate(
						loadICDDataTest.getA429PhysPortDefByRIUClassAndName(
								subRIUClass,((A429PortNode)rpMessage.getSubRDIU().getPubPort()).getPhysPortName()).getBitRate());
			}else if(rpMessage.getSubEndApp().getPort().getType().equals("CANPort")) {
				((CANPortNode)rpMessage.getSubRDIU().getPubPort()).setCanBiteRate(
						loadICDDataTest.getCANPhysPortDefByRIUClassAndName(
								subRIUClass,((CANPortNode)rpMessage.getSubRDIU().getPubPort()).getPhysPortName()).getCANBitRate());
			}
			//设置RDIU上面的ES相关内容
			subHardware.setESHighIntegrity(subRIUClass.getESHighIntegrity());
			subHardware.setESTechLatencyTx(subRIUClass.getESTechLatencyTx());
			subHardware.setESTechLatencyRx(subRIUClass.getESTechLatencyRx());
			subHardware.setESTransmitPolicy(subRIUClass.getESTransmitPolicy());
			subRDIU.setRIUType(subRIUClass.getRIUType());
			subRDIU.setHardware(subHardware);
			rpMessage.setSubRDIU(subRDIU);
		}
		return rpMessage;
	}

	@Override
	public void getSwitchesInfo(RPMessage rpMessage) {
		if(rpMessage==null||rpMessage.isError()||rpMessage.isLocal()) {
			return;
		}
		//VritualLink
		if(rpMessage.getAdnPart().getVlID()!=0) {
			VirtualLinkType virtualLink = loadICDDataTest.getVirtualLinkByVLID(rpMessage.getAdnPart().getVlID());
			if(virtualLink==null) {
				rpMessage.addErrorMsg("未通过VLID找到对应VirtualLink");
				return;
			}else {
				rpMessage.getAdnPart().setVirtualLink(virtualLink);
			}
		}else {
			rpMessage.addErrorMsg("RP消息中缺少VLID");
			return;
		}
		//VLPath
		if(rpMessage.getAdnPart().getPathName()!= "") {
			VLPathType vlPath = loadICDDataTest.getVLPathByNameAndID(rpMessage.getAdnPart().getVlID(), rpMessage.getAdnPart().getPathName());
			if(vlPath==null) {
				rpMessage.addErrorMsg("未通过VLID找到对应VLPath");
				return;
			}else {
				rpMessage.getAdnPart().setVlPath(vlPath);
			}
		}else {
			rpMessage.addErrorMsg("RP消息中缺少PathName");
			return;
		}
		//ComPortTx和ComPortRx
		String pubGuid = null,subGuid = null;//其中pubGuid用来获取ComPortTx,subGuid用来获取ComPortRx
		if(rpMessage.getSubRDIU()!=null) {
			subGuid = rpMessage.getSubRDIU().getSubPort().getGuid();
		}else {
			subGuid = rpMessage.getSubEndApp().getPort().getGuid();
		}
		ComPortRxType comPortRx = loadICDDataTest.getComPortRxByGuidAndVL(subGuid, rpMessage.getAdnPart().getVirtualLink());
		if(comPortRx==null) {
			rpMessage.addErrorMsg("获取ComPortRx错误");
			return;
		}else {
			rpMessage.getAdnPart().setComPortRx(comPortRx);
		}
		if(rpMessage.getPubRDIU()!=null) {
			pubGuid = rpMessage.getPubRDIU().getPubPort().getGuid();
		}else {
			pubGuid = rpMessage.getPubEndApp().getPort().getGuid();
		}
		ComPortTxType comPortTx = loadICDDataTest.getComPortTxByGuidAndVL(pubGuid, rpMessage.getAdnPart().getVirtualLink());
		if(comPortTx==null) {
			rpMessage.addErrorMsg("获取ComPortTx错误");
			return;
		}else {
			rpMessage.getAdnPart().setComPortTx(comPortTx);
		}
		//SubVL
		if(rpMessage.getAdnPart().getSubVLID()!=null) {
			SubVLType subVL = loadICDDataTest.getSubVLByIdAndVL(rpMessage.getAdnPart().getSubVLID(), rpMessage.getAdnPart().getVirtualLink());
			if(subVL==null) {
				rpMessage.addErrorMsg("获取SubVL错误");
				return;
			}else {
				rpMessage.getAdnPart().setSubVL(subVL);
			}
		}else {
			rpMessage.addErrorMsg("RP消息中缺少SubVLId");
			return;
		}
		String actualPath = rpMessage.getAdnPart().getActualPath();
		if(actualPath==null) {
			rpMessage.addErrorMsg("消息的actualPath未找到");
			return;
		}
		List<String> nodeList = new ArrayList<String>();//存放各个设备节点信息
		int lastPos = 0;
		for(int i=0;i<actualPath.length();i++) {
			if(actualPath.charAt(i)==','||actualPath.charAt(i)==';') {
				nodeList.add(actualPath.substring(lastPos,i));
				lastPos = i+1;
			}
		}
		nodeList.add(actualPath.substring(lastPos));
		//设置每一个交换机信息
		for(int i=0;i<nodeList.size();i++) {
			if(i==0) {
				rpMessage.getAdnPart().setSubADNPort(getPortInfo(nodeList.get(i)));
			}else if(i==nodeList.size()-1) {
				rpMessage.getAdnPart().setPubADNPort(getPortInfo(nodeList.get(i)));
			}else {
				//获取交换机名称
				String switchName = getHardwareInfo(nodeList.get(i));
				SwitchTypeHardware switchHardware = new SwitchTypeHardware();
				switchHardware.setName(switchName);			
				if(isACS(switchName)) {
					ACSSwitchNode newSwitch = new ACSSwitchNode();
					newSwitch.setType("ACS");
					newSwitch.setSubPort(Integer.parseInt(getPortInfo(nodeList.get(i))));
					newSwitch.setPubPort(Integer.parseInt(getPortInfo(nodeList.get(i+1))));
					switchHardware.setType("ACS");
					ACSType acs = loadICDDataTest.getACSTypeByName(switchName);
					String acsGuidDef = acs.getGuidDef();
					ACSClassType acsClass = loadICDDataTest.getACSClassTypeByGuid(acsGuidDef);
					switchHardware.setGuid(acs.getGuid());
					if(acsClass==null) {
						rpMessage.addErrorMsg("消息获取ACS定义类错误");
						return;
					}
					switchHardware.setESTechLatencyTx(acsClass.getESTechLatencyTx());
					switchHardware.setESTechLatencyRx(acsClass.getESTechLatencyRx());
					AswPhysPortDefType subAswPhysPortDef = loadICDDataTest.getAswPhysPortDefByACSClassAndPortId(acsClass, newSwitch.getSubPort());
					AswPhysPortDefType pubAswPhysPortDef = loadICDDataTest.getAswPhysPortDefByACSClassAndPortId(acsClass, newSwitch.getPubPort());
					if(subAswPhysPortDef==null||pubAswPhysPortDef==null) {
						rpMessage.addErrorMsg("交换机获取ASWPhysPortDef错误");
						return;
					}
					newSwitch.setSubPortSpeed(subAswPhysPortDef.getSpeed());
					newSwitch.setPubPortSpeed(pubAswPhysPortDef.getSpeed());
					newSwitch.setHardware(switchHardware);
					rpMessage.getAdnPart().addSwitch(newSwitch);
				}else {
					ARSSwitchNode newSwitch = new ARSSwitchNode();
					newSwitch.setType("ARS");
					newSwitch.setSubPort(Integer.parseInt(getPortInfo(nodeList.get(i))));
					newSwitch.setPubPort(Integer.parseInt(getPortInfo(nodeList.get(i+1))));
					switchHardware.setType("ARS");
					ARSType ars = loadICDDataTest.getARSTypeByName(switchName);
					String arsGuidDef = ars.getGuidDef();
					ARSClassType arsClass = loadICDDataTest.getARSClassTypeByGuid(arsGuidDef);
					switchHardware.setGuid(ars.getGuid());
					if(arsClass==null) {
						rpMessage.addErrorMsg("消息获取ARS定义类错误");
						return;
					}
					switchHardware.setESTechLatencyTx(arsClass.getESTechLatencyTx());
					switchHardware.setESTechLatencyRx(arsClass.getESTechLatencyRx());
					newSwitch.setHardware(switchHardware);
					rpMessage.getAdnPart().addSwitch(newSwitch);
				}
				i++;//因为一个交换机有两个端口，会出现两次，所以需要+1
			}
		}
		return;
	}


	/**
	 * 设置接收端部分的路径
	 * @param rpDataflowRef RPDataflow_Ref的SrcGuid
	 * @param subPortType 接收端端口类型
	 * @param pubPortType 发送端端口的类型
	 * @param pubPortGuid 发送端端口Guid
	 * @param subPortGuid 接收端端口的Guid
	 * @return 
	 */
	private String setSubPartPath(String rpDataflowRef,String subPortType,String pubPortType,String pubPortGuid,String subPortGuid) {
		String resGuid = null;//定义返回值
		if(loadICDDataTest.getLocalPortTypeByGuid(rpDataflowRef)!=null) {
			//如果RP消息的DataflowRef找到了本地的发送端端口，且发送和接收端存在一方不为A664或A653接口
			if(!(isA664Port(pubPortType)&&isA664Port(subPortType))) {
				rpMessage.addErrorMsg("接收端Port直接和发送端Port相连，且存在非A664/A653端口");
			}else {
				resGuid = setSubA664PortPath(rpDataflowRef, subPortType, pubPortGuid, subPortGuid);
			}
		}else {
			//如果RP消息的DataflowRef找到了非本地的端口
			String type = loadICDDataTest.getRGWPortTypeByGuid(rpDataflowRef);
			if(type==null) {
				rpMessage.addErrorMsg("获取DataflowRef端口错误");
				return resGuid;
			}
			if((isA664Port(subPortType)&&!isA664Port(type))||(isA664Port(type)&&!isA664Port(subPortType))) {
				//如果接收端为非664端口，而另一边为664端口；或者接收端为664端口，而另一边为非664端口，则不匹配
				rpMessage.addErrorMsg("接收端端口和RGW上端口不匹配");
			}else if(isA664Port(subPortType)) {
				resGuid = setSubA664PortPath(rpDataflowRef, subPortType, pubPortGuid, subPortGuid);
			}else {
				resGuid = setSubNonA664PortPath(rpDataflowRef, subPortType, pubPortGuid, subPortGuid);
			}
		}
		return resGuid;
	}
	
	/**
	 * 设置发送端部分的路径
	 * @param pubPortGuid
	 * @param pubPortType
	 * @param pubA664PortGuid
	 */
	private void setPubPartPath(String pubPortGuid,String pubPortType,String pubA664PortGuid) {
		if(isA664Port(pubPortType)) {
			setPubA664PortPath(pubPortGuid, pubPortType);
		}else {
			setPubNonA664PortPath(pubPortGuid, pubPortType, pubA664PortGuid);
		}
	}
	
	
	
	/**********************接收端***************************/
	/**
	 * 设置接收端为A664或A653类型端口的部分路径
	 * @param rpDataflowRef RPDataflow_Ref的SrcGuid
	 * @param subPortType 接收端端口类型
	 * @param pubPortGuid 发送端端口Guid
	 * @param subPortGuid 接收端端口的Guid
	 * @return 返回rpDataflowRef的SrcGuid
	 */
	private String setSubA664PortPath(String rpDataflowRef,String subPortType,String pubPortGuid,String subPortGuid) {
		switch (subPortType) {
		case "HFSamplingPort":
			setSubHFSamplingPortPath(subPortGuid);
			break;
		case "HFQueuingPort":
			setSubHFQueuingPortPortPath(subPortGuid);
			break;
		case "A653SamplingPort":
			setSubA653SamplingPortPath(subPortGuid);
			break;
		case "A653QueuingPort":
			setSubA653QueuingPortPortPath(subPortGuid);
			break;
		default:
			break;
		}
		return rpDataflowRef;
	}
	
	/**
	 * 设置接收端为A429或Analog或CAN类型端口的RDIU上部分路径
	 * @param rpDataflowRef RPDataflow_Ref的SrcGuid
	 * @param subPortType 接收端端口类型
	 * @param pubPortGuid 发送端端口Guid
	 * @param subPortGuid 接收端端口的Guid
	 * @return 返回rpDataflowRef的SrcGuid
	 */
	private String setSubNonA664PortPath(String rpDataflowRef,String subPortType,String pubPortGuid, String subPortGuid) {
		String resGuid = null;
		//设置接收端端系统
		LRUAppNode subLRUAppNode = new LRUAppNode();
		NonA664LogicalPortType subNonA664LogicalPort = loadICDDataTest.getNonA664LogicalPortByPortGuid(subPortGuid);
		String subPortGuidDef = subNonA664LogicalPort.getGuidDef();
		subLRUAppNode.setPortDef(subPortGuidDef);//设置端系统接口的GuidDef
		//获取端口定义类，设置SamplePeriod
		NonA664LogicalPortDefType nonA664LogicalPortDef = loadICDDataTest.getNonA664LogicalPortDefByGuid(subPortGuidDef);
		if(nonA664LogicalPortDef==null) {
			rpMessage.addErrorMsg("消息获取接收端端口定义类错误");
			return resGuid;
		}
		subLRUAppNode.setSamplePeriod(nonA664LogicalPortDef.getSamplePeriod());//设置接收端口的采样周期
		String physical = subNonA664LogicalPort.getPhysical();
		String physPortName = getPortInfo(physical);//获取端口物理名称
		switch (subPortType) {
		case "A429Port":
			A429PortNode a429PortNode = new A429PortNode(subPortType,subNonA664LogicalPort.getName(),subPortGuid);
			a429PortNode.setPhysPortName(physPortName);
			subLRUAppNode.setPort(a429PortNode);
			resGuid = setSubA429PortPath(rpDataflowRef, pubPortGuid);
			break;
		case "AnalogPort":
			AnalogPortNode analogPortNode = new AnalogPortNode(subPortType,subNonA664LogicalPort.getName(),subPortGuid);
			analogPortNode.setPhysPortName(physPortName);
			subLRUAppNode.setPort(analogPortNode);
			resGuid = setSubAnalogPortPath(rpDataflowRef, pubPortGuid);
			break;
		case "CANPort":
			CANPortNode canPortNode = new CANPortNode(subPortType,subNonA664LogicalPort.getName(),subPortGuid);
			canPortNode.setPhysPortName(physPortName);
			canPortNode.setCanMessageProtocolType(((CANPortDefType)nonA664LogicalPortDef).getCANMessageProtocolType());
			subLRUAppNode.setPort(canPortNode);
			resGuid = setSubCANPortPath(rpDataflowRef, pubPortGuid);
			break;
		default:
			break;
		}
		rpMessage.setSubEndApp(subLRUAppNode);
		subLRUAppNode.setDirect("sub");
		return resGuid;
	}
	
	/**
	 * 设置接收端RDIU的A664端口部分
	 * @param subRDIUPortGuid 接收端RDIU的A664端口Guid
	 * @param subRDIUPortType 接收端RDIU的A664端口类型
	 * @return 发送端A664消息的Guid
	 */
	private String setSubA664PortRDIUPath(String subRDIUPortGuid,String subRDIUPortType) {
		String resGuid = null;
		if(isRGWHFQueuingPort(subRDIUPortType)) {
			RGWHFQueuingPortType rgwhfQueuingPort = loadICDDataTest.getRGWHFQueuingPortByGuid(subRDIUPortGuid);
			if(rgwhfQueuingPort==null) {
				rpMessage.addErrorMsg("接收端RDIU的HFQueuing端口获取错误");
			}else {
				HFQueuingPortNode subPort = new HFQueuingPortNode("HFQueuingPort",rgwhfQueuingPort.getName(),rgwhfQueuingPort.getGuid());
				subPort.setQueueLength(rgwhfQueuingPort.getQueueLength());				
				rpMessage.getSubRDIU().setSubPort(subPort);
				rpMessage.getSubRDIU().setSamplePeriod(rgwhfQueuingPort.getSamplePeriod());
				rpMessage.getAdnPart().setPathName(rgwhfQueuingPort.getPathName());
				if(rgwhfQueuingPort.getDataflowRef().size()!=1) {
					rpMessage.addErrorMsg("接收端RDIU的A664接口DataflowRef数量不等于1");
				}else {
					resGuid = rgwhfQueuingPort.getDataflowRef().get(0).getSrcGuid();
				}
			}
		}else if(isRGWHFSamplingPort(subRDIUPortType)) {
			RGWHFSamplingPortType rgwhfSamplingPort = loadICDDataTest.getRGWHFSamplingPortByGuid(subRDIUPortGuid);
			if(rgwhfSamplingPort==null) {
				rpMessage.addErrorMsg("接收端RDIU的HFSamplingPort端口获取错误");
			}else {
				HFSamplingPortNode subPort = new HFSamplingPortNode("HFSamplingPort",rgwhfSamplingPort.getName(),rgwhfSamplingPort.getGuid());
				rpMessage.getSubRDIU().setSubPort(subPort);
				rpMessage.getSubRDIU().setSamplePeriod(rgwhfSamplingPort.getSamplePeriod());
				rpMessage.getAdnPart().setPathName(rgwhfSamplingPort.getPathName());
				if(rgwhfSamplingPort.getDataflowRef().size()!=1) {
					rpMessage.addErrorMsg("接收端RDIU的A664接口DataflowRef数量不等于1");
				}else {
					resGuid = rgwhfSamplingPort.getDataflowRef().get(0).getSrcGuid();
				}
			}
		}else {
			rpMessage.addErrorMsg("接收端RDIU的A664端口类型不符合");
		}
		return resGuid;
	}
	
	/**
	 * 设置接收端为A429端口的部分路径
	 * @param rpDataflowRef RPDataflow_Ref的SrcGuid
	 * @param pubPortGuid 发送端端口Guid
	 * @return 发送端A664消息的Guid
	 */
	private String setSubA429PortPath(String rpDataflowRef,String pubPortGuid) {
		String resGuid = null;
		String srcGuid = null;//RDIU输出端口的GatewayRef的srcGuid
		RIUAppNode subRDIU = new RIUAppNode();
		rpMessage.setSubRDIU(subRDIU);
		//通过RPDataflow_Ref获取接收端RDIU发出的A429端口
		RGWA429PortType rgwa429Port = loadICDDataTest.getRGWA429PortByGuid(rpDataflowRef);
		if(rgwa429Port==null) {
			rpMessage.addErrorMsg("接收端RDIU的A429端口获取错误");
			return resGuid;
		}
		A429PortNode pubPort = new A429PortNode("A429Port",rgwa429Port.getName(),rgwa429Port.getGuid());
		pubPort.setPhysPortName(getPortInfo(rgwa429Port.getPhysical()));
		subRDIU.setRefreshPeriod(rgwa429Port.getRefreshPeriod());
		subRDIU.setPubPort(pubPort);
		if(rgwa429Port.getGatewayRef().size()>1) {
			rpMessage.addErrorMsg("接收端RDIU的A429端口GatewayRef数量大于1，无法构建数据传输场景");
		}else if(rgwa429Port.getGatewayRef().size()== 0) {
			rpMessage.addErrorMsg("接收端RDIU的A429端口GatewayRef数量为0，无法构建数据传输场景");
		}else {
			srcGuid = rgwa429Port.getGatewayRef().get(0).getSrcGuid();
		}
		if(srcGuid==null) {
			return resGuid;
		}
		//根据srcGuid获取RDIU接收端的端口类型
		String subRDIUPortType = loadICDDataTest.getRGWPortTypeByGuid(srcGuid);
		if(subRDIUPortType==null) {
			rpMessage.addErrorMsg("接收端RDIU的A664端口获取错误");
			return resGuid;
		}
		if(isRGWA664Port(subRDIUPortType)) {
			//如果RDIU另外一个端口连到664端口，即通过ADN网络
			resGuid = setSubA664PortRDIUPath(srcGuid, subRDIUPortType);
		}else {
			//如果RDIU另外一个端口连到非664，即消息在本地RDIU转换
			resGuid = srcGuid;
		}
		return resGuid;
	}
	
	/**
	 * 设置接收端为Analog端口的部分路径
	 * @param rpDataflowRef RPDataflow_Ref的SrcGuid
	 * @param pubPortGuid 发送端端口Guid
	 * @return 发送端A664消息的Guid
	 */
	private String setSubAnalogPortPath(String rpDataflowRef,String pubPortGuid) {
		String resGuid = null;
		String srcGuid = null;//RDIU输出端口的GatewayRef的srcGuid
		RIUAppNode subRDIU = new RIUAppNode();
		rpMessage.setSubRDIU(subRDIU);
		//通过RPDataflow_Ref获取接收端RDIU发出的Analog端口
		RGWAnalogPortType rgwanalogPort = loadICDDataTest.getRGWAnalogPortByGuid(rpDataflowRef);
		if(rgwanalogPort==null) {
			rpMessage.addErrorMsg("接收端RDIU的Analog端口获取错误");
			return resGuid;
		}
		AnalogPortNode pubPort = new AnalogPortNode("AnalogPort",rgwanalogPort.getName(),rgwanalogPort.getGuid());
		pubPort.setPhysPortName(getPortInfo(rgwanalogPort.getPhysical()));
		subRDIU.setRefreshPeriod(rgwanalogPort.getRefreshPeriod());//设置刷新周期
		subRDIU.setPubPort(pubPort);
		if(rgwanalogPort.getGatewayRef().size()>1) {
			rpMessage.addErrorMsg("接收端RDIU的Analog端口GatewayRef数量大于1，无法构建数据传输场景");
		}else if(rgwanalogPort.getGatewayRef().size()== 0) {
			rpMessage.addErrorMsg("接收端RDIU的Analog端口GatewayRef数量为0，无法构建数据传输场景");
		}else {
			srcGuid = rgwanalogPort.getGatewayRef().get(0).getSrcGuid();
		}
		if(srcGuid==null) {
			return resGuid;
		}
		//根据srcGuid获取RDIU接收端的端口类型
		String subRDIUPortType = loadICDDataTest.getRGWPortTypeByGuid(srcGuid);
		if(subRDIUPortType==null) {
			rpMessage.addErrorMsg("接收端RDIU的A664端口获取错误");
			return resGuid;
		}
		if(isRGWA664Port(subRDIUPortType)) {
			//如果RDIU另外一个端口连到664端口，即通过ADN网络
			resGuid = setSubA664PortRDIUPath(srcGuid, subRDIUPortType);
		}else {
			//如果RDIU另外一个端口连到非664，即消息在本地RDIU转换
			resGuid = srcGuid;
		}
		return resGuid;
	}
	
	/**
	 * 设置接收端为CAN端口的部分路径
	 * @param rpDataflowRef RPDataflow_Ref的SrcGuid
	 * @param pubPortGuid 发送端端口Guid
	 * @return 发送端A664消息的Guid
	 */
	private String setSubCANPortPath(String rpDataflowRef,String pubPortGuid) {
		String resGuid = null;
		String srcGuid = null;//RDIU输出端口的GatewayRef的srcGuid
		RIUAppNode subRDIU = new RIUAppNode();
		rpMessage.setSubRDIU(subRDIU);
		//通过RPDataflow_Ref获取接收端RDIU发出的CAN端口
		RGWCANPortType rgwcanPort = loadICDDataTest.getRGWCANPortByGuid(rpDataflowRef);
		if(rgwcanPort==null) {
			rpMessage.addErrorMsg("接收端RDIU的CAN端口获取错误");
			return resGuid;
		}
		CANPortNode pubPort = new CANPortNode("CANPort",rgwcanPort.getName(),rgwcanPort.getGuid());
		pubPort.setPhysPortName(getPortInfo(rgwcanPort.getPhysical()));
		subRDIU.setRefreshPeriod(rgwcanPort.getRefreshPeriod());//设置刷新周期
		subRDIU.setPubPort(pubPort);
		if(rgwcanPort.getGatewayRef().size()>1) {
			rpMessage.addErrorMsg("接收端RDIU的CAN端口GatewayRef数量大于1，无法构建数据传输场景");
		}else if(rgwcanPort.getGatewayRef().size()== 0) {
			rpMessage.addErrorMsg("接收端RDIU的CAN端口GatewayRef数量为0，无法构建数据传输场景");
		}else {
			srcGuid = rgwcanPort.getGatewayRef().get(0).getSrcGuid();
		}
		if(srcGuid==null) {
			return resGuid;
		}
		//根据srcGuid获取RDIU接收端的端口类型
		String subRDIUPortType = loadICDDataTest.getRGWPortTypeByGuid(srcGuid);
		if(subRDIUPortType==null) {
			rpMessage.addErrorMsg("接收端RDIU的A664端口获取错误");
			return resGuid;
		}
		if(isRGWA664Port(subRDIUPortType)) {
			//如果RDIU另外一个端口连到664端口，即通过ADN网络
			resGuid = setSubA664PortRDIUPath(srcGuid, subRDIUPortType);
		}else {
			//如果RDIU另外一个端口连到非664，即消息在本地RDIU转换
			resGuid = srcGuid;
		}
		return resGuid;
	}
	
	/**
	 * 设置接收端为HFSamplingPort的端系统
	 * @param subPortGuid 接收端端口的Guid
	 */
	private void setSubHFSamplingPortPath(String subPortGuid) {
		HFSamplingPortType hfSamplingPort = loadICDDataTest.getHFSamplingPortByGuid(subPortGuid);
		if(hfSamplingPort==null) {
			rpMessage.addErrorMsg("接收端HFSamplingPort获取错误");
		}
		LRUAppNode subLRUAppNode = new LRUAppNode();
		HFSamplingPortNode port = new HFSamplingPortNode("HFSamplingPort",hfSamplingPort.getName(),subPortGuid);
		subLRUAppNode.setPortDef(hfSamplingPort.getGuidDef());
		HFSamplingPortDefType hfSamplingPortDef = loadICDDataTest.getHFSamplingPortDefByGuid(hfSamplingPort.getGuidDef());
		if(hfSamplingPortDef==null) {
			rpMessage.addErrorMsg("接收端端系统获取端口定义类错误");
			return;
		}
		subLRUAppNode.setSamplePeriod(hfSamplingPortDef.getSamplePeriod());
		subLRUAppNode.setPort(port);
		rpMessage.setSubEndApp(subLRUAppNode);
		subLRUAppNode.setDirect("sub");
		rpMessage.getAdnPart().setPathName(hfSamplingPort.getPathName());//设置PathName
	}
	
	/**
	 * 设置接收端为HFQueuingPort的端系统
	 * @param subPortGuid 接收端端口的Guid
	 */
	private void setSubHFQueuingPortPortPath(String subPortGuid) {
		HFQueuingPortType hfQueuingPort = loadICDDataTest.getHFQueuingPortByGuid(subPortGuid);
		if(hfQueuingPort==null) {
			rpMessage.addErrorMsg("接收端HFQueuingPort获取错误");
		}
		LRUAppNode subLRUAppNode = new LRUAppNode();
		HFQueuingPortNode port = new HFQueuingPortNode("HFQueuingPort",hfQueuingPort.getName(),subPortGuid);
		subLRUAppNode.setPortDef(hfQueuingPort.getGuidDef());
		HFQueuingPortDefType hfQueuingPortDef = loadICDDataTest.getHFQueuingPortDefByGuid(hfQueuingPort.getGuidDef());
		if(hfQueuingPortDef==null) {
			rpMessage.addErrorMsg("接收端端系统获取端口定义类错误");
			return;
		}
		port.setQueueLength(hfQueuingPortDef.getQueueLength());
		subLRUAppNode.setSamplePeriod(hfQueuingPortDef.getSamplePeriod());
		subLRUAppNode.setPort(port);
		rpMessage.setSubEndApp(subLRUAppNode);
		subLRUAppNode.setDirect("sub");
		rpMessage.getAdnPart().setPathName(hfQueuingPort.getPathName());//设置PathName
	}
	
	/**
	 * 设置接收端为A653SamplingPort的端系统
	 * @param subPortGuid 接收端端口的Guid
	 */
	private void setSubA653SamplingPortPath(String subPortGuid) {
		A653SamplingPortType a653SamplingPort = loadICDDataTest.getA653SamplingPortByGuid(subPortGuid);
		if(a653SamplingPort==null) {
			rpMessage.addErrorMsg("接收端A653SamplingPort获取错误");
		}
		GPMAppNode subGPMAppNode = new GPMAppNode();
		A653SamplingPortNode port = new A653SamplingPortNode("A653SamplingPort",a653SamplingPort.getName(),subPortGuid);
		subGPMAppNode.setPortDef(a653SamplingPort.getGuidDef());
		A653SamplingPortDefType a653SamplingPortDef = loadICDDataTest.getA653SamplingPortDefByGuid(a653SamplingPort.getGuidDef());
		if(a653SamplingPortDef==null) {
			rpMessage.addErrorMsg("接收端端系统获取端口定义类错误");
			return;
		}
		subGPMAppNode.setRefreshPeriod(a653SamplingPortDef.getRefreshPeriod());
		subGPMAppNode.setSamplePeriod(a653SamplingPortDef.getSamplePeriod());
		subGPMAppNode.setPort(port);
		rpMessage.setSubEndApp(subGPMAppNode);
		subGPMAppNode.setDirect("sub");
		rpMessage.getAdnPart().setPathName(a653SamplingPort.getPathName());//设置PathName
	}
	
	/**
	 * 设置接收端为A653QueuingPort的端系统
	 * @param subPortGuid 接收端端口的Guid
	 */
	private void setSubA653QueuingPortPortPath(String subPortGuid) {
		A653QueuingPortType a653QueuingPort = loadICDDataTest.getA653QueuingPortByGuid(subPortGuid);
		if(a653QueuingPort==null) {
			rpMessage.addErrorMsg("接收端A653QueuingPort获取错误");
		}
		GPMAppNode subGPMAppNode = new GPMAppNode();
		A653QueuingPortNode port = new A653QueuingPortNode("A653QueuingPort",a653QueuingPort.getName(),subPortGuid);
		subGPMAppNode.setPortDef(a653QueuingPort.getGuidDef());
		A653QueuingPortDefType a653QueuingPortDef = loadICDDataTest.getA653QueuingPortDefByGuid(a653QueuingPort.getGuidDef());
		if(a653QueuingPortDef==null) {
			rpMessage.addErrorMsg("接收端端系统获取端口定义类错误");
			return;
		}
		port.setQueueLength(a653QueuingPortDef.getQueueLength());
		subGPMAppNode.setRefreshPeriod(a653QueuingPortDef.getRefreshPeriod());
		subGPMAppNode.setSamplePeriod(a653QueuingPortDef.getSamplePeriod());
		subGPMAppNode.setPort(port);
		rpMessage.setSubEndApp(subGPMAppNode);	
		subGPMAppNode.setDirect("sub");
		rpMessage.getAdnPart().setPathName(a653QueuingPort.getPathName());//设置PathName
	}
	
	/**********************发送端***************************/	
	/**
	 * 设置发送端口为A664类型的路径
	 * @param pubPortGuid 发送端口Guid
	 * @param pubPortType 发送端口类型
	 */
	private void setPubA664PortPath(String pubPortGuid,String pubPortType) {
		switch (pubPortType) {
		case "HFSamplingPort":
			setPubHFSamplingPortPath(pubPortGuid);
			break;
		case "HFQueuingPort":
			setPubHFQueuingPortPortPath(pubPortGuid);
			break;
		case "A653SamplingPort":
			setPubA653SamplingPortPath(pubPortGuid);
			break;
		case "A653QueuingPort":
			setPubA653QueuingPortPortPath(pubPortGuid);
			break;
		default:
			break;
		}
	}
	
	/**
	 * 设置发送端口为非A664类型的路径
	 * @param pubPortGuid 发送端口Guid
	 * @param pubPortType 发送端口类型
	 * @param pubA664PortGuid 发送端A664消息Guid
	 */
	private void setPubNonA664PortPath(String pubPortGuid,String pubPortType,String pubA664PortGuid) {
		LRUAppNode pubLRUAppNode = new LRUAppNode();
		NonA664LogicalPortType pubNonA664LogicalPort = loadICDDataTest.getNonA664LogicalPortByPortGuid(pubPortGuid);
		String pubPortGuidDef = pubNonA664LogicalPort.getGuidDef();
		pubLRUAppNode.setPortDef(pubPortGuidDef);
		//获取端口定义类，设置refreshPeriod
		NonA664LogicalPortDefType nonA664LogicalPortDef = loadICDDataTest.getNonA664LogicalPortDefByGuid(pubPortGuidDef);
		if(nonA664LogicalPortDef==null) {
			rpMessage.addErrorMsg("消息获取接收端端口定义类错误");
			return ;
		}
		pubLRUAppNode.setRefreshPeriod(nonA664LogicalPortDef.getRefreshPeriod());
		String physical = pubNonA664LogicalPort.getPhysical();
		String physPortName = getPortInfo(physical);
		switch (pubPortType) {
		case "A429Port":
			A429PortNode a429PortNode = new A429PortNode(pubPortType,pubNonA664LogicalPort.getName(),pubPortGuid);
			pubLRUAppNode.setPort(a429PortNode);
			a429PortNode.setPhysPortName(physPortName);
			setPubA429PortPath(pubPortGuid, pubA664PortGuid);
			break;
		case "AnalogPort":
			AnalogPortNode analogPortNode = new AnalogPortNode(pubPortType,pubNonA664LogicalPort.getName(),pubPortGuid);
			pubLRUAppNode.setPort(analogPortNode);
			analogPortNode.setPhysPortName(physPortName);
			setPubAnalogPortPath(pubPortGuid, pubA664PortGuid);
			break;
		case "CANPort":
			CANPortNode canPortNode = new CANPortNode(pubPortType,pubNonA664LogicalPort.getName(),pubPortGuid);
			canPortNode.setCanMessageProtocolType(((CANPortDefType)nonA664LogicalPortDef).getCANMessageProtocolType());
			pubLRUAppNode.setPort(canPortNode);
			canPortNode.setPhysPortName(physPortName);
			setPubCANPortPath(pubPortGuid, pubA664PortGuid);
			break;
		default:
			break;
		}
		rpMessage.setPubEndApp(pubLRUAppNode);
		pubLRUAppNode.setDirect("pub");
	}
	
	/**
	 * 设置发送端RDIU发送部分为A664端口的路径
	 * @param pubA664PortGuid 发送端A664部分的Guid
	 */
	private void setPubA664PortRDIUPath(String pubA664PortGuid) {
		String type = loadICDDataTest.getRGWPortTypeByGuid(pubA664PortGuid);
		if(type==null) {
			rpMessage.addErrorMsg("发送端RDIU上获取A664端口错误");
		}else if(isRGWHFQueuingPort(type)) {
			RGWHFQueuingPortType rgwhfQueuingPort = loadICDDataTest.getRGWHFQueuingPortByGuid(pubA664PortGuid);
			if(rgwhfQueuingPort.getA664Message().size()>0) {
				//可以设置A664消息块
				rpMessage.getAdnPart().setVlID(rgwhfQueuingPort.getA664Message().get(0).getVLID());
				rpMessage.getAdnPart().setSubVLID(rgwhfQueuingPort.getA664Message().get(0).getSubVLID());
				rpMessage.getAdnPart().setPubRgwa664Message(rgwhfQueuingPort.getA664Message().get(0));
			}else {
				rpMessage.addErrorMsg("发送端未包含A664数据块");
			}
			HFQueuingPortNode pubPort = new HFQueuingPortNode("HFQueuingPort",rgwhfQueuingPort.getName(),rgwhfQueuingPort.getGuid());
			pubPort.setQueueLength(rgwhfQueuingPort.getQueueLength());
			rpMessage.getPubRDIU().setRefreshPeriod(rgwhfQueuingPort.getRefreshPeriod());
			rpMessage.getPubRDIU().setPubPort(pubPort);
		}else if(isRGWHFSamplingPort(type)) {
			RGWHFSamplingPortType rgwhfSamplingPort = loadICDDataTest.getRGWHFSamplingPortByGuid(pubA664PortGuid);
			if(rgwhfSamplingPort.getA664Message().size()>0) {
				//可以设置A664消息块
				if(rpMessage.getAdnPart()==null) {
					System.out.println(rpMessage.getRpGuid());
				}
				rpMessage.getAdnPart().setVlID(rgwhfSamplingPort.getA664Message().get(0).getVLID());
				rpMessage.getAdnPart().setSubVLID(rgwhfSamplingPort.getA664Message().get(0).getSubVLID());
				rpMessage.getAdnPart().setPubRgwa664Message(rgwhfSamplingPort.getA664Message().get(0));
			}else {
				rpMessage.addErrorMsg("发送端未包含A664数据块");
			}
			HFSamplingPortNode pubPort = new HFSamplingPortNode("HFSamplingPort",rgwhfSamplingPort.getName(),rgwhfSamplingPort.getGuid());
			rpMessage.getPubRDIU().setRefreshPeriod(rgwhfSamplingPort.getRefreshPeriod());
			rpMessage.getPubRDIU().setPubPort(pubPort);
		}else {
			rpMessage.addErrorMsg("发送端RDIU上的发送部分类型错误");
		}
	}
	
	/**
	 * 设置发送端为A429Port的端系统部分路径
	 * @param pubPortGuid 发送端端口Guid
	 * @param pubA664PortGuid 发送端A664端口Guid
	 */
	private void setPubA429PortPath(String pubPortGuid,String pubA664PortGuid) {
		//根据发送端口寻找与之连接的RDIU的A429端口
		RGWA429PortType rgwa429Port = loadICDDataTest.getA429PortByDataflowSrcGuid(pubPortGuid);
		if(rgwa429Port==null) {
			rpMessage.addErrorMsg("发送端RDIU的A429端口获取错误");
			return;
		}
		A429PortNode subPort = new A429PortNode("A429Port",rgwa429Port.getName(),rgwa429Port.getGuid());
		subPort.setPhysPortName(getPortInfo(rgwa429Port.getPhysical()));
		RIUAppNode pubRDIU = new RIUAppNode();
		pubRDIU.setSamplePeriod(rgwa429Port.getSamplePeriod());//设置采样周期
		pubRDIU.setSubPort(subPort);
		rpMessage.setPubRDIU(pubRDIU);
		if(rgwa429Port.getGuid().equals(pubA664PortGuid)) {
			//代表在本地进行RDIU转换
			rpMessage.setLocal(true);
		}else {
			//获取A664端口
			setPubA664PortRDIUPath(pubA664PortGuid);
		}
	}
	
	/**
	 * 设置发送端为AnalogPort的端系统部分路径
	 * @param pubPortGuid 发送端端口Guid
	 * @param pubA664PortGuid 发送端A664端口Guid
	 */
	private void setPubAnalogPortPath(String pubPortGuid,String pubA664PortGuid) {
		//根据发送端口寻找与之连接的RDIU的Analog端口
		RGWAnalogPortType rgwanalogPort = loadICDDataTest.getAnalogPortByDataflowSrcGuid(pubPortGuid);
		if(rgwanalogPort==null) {
			rpMessage.addErrorMsg("发送端RDIU的Analog端口获取错误");
			return;
		}
		AnalogPortNode subPort = new AnalogPortNode("AnalogPort",rgwanalogPort.getName(),rgwanalogPort.getGuid());
		subPort.setPhysPortName(getPortInfo(rgwanalogPort.getPhysical()));
		RIUAppNode pubRDIU = new RIUAppNode();
		pubRDIU.setSamplePeriod(rgwanalogPort.getSamplePeriod());//设置采样周期
		pubRDIU.setSubPort(subPort);
		rpMessage.setPubRDIU(pubRDIU);
		if(rgwanalogPort.getGuid().equals(pubA664PortGuid)) {
			//代表在本地进行RDIU转换
			rpMessage.setLocal(true);
		}else {
			//获取A664端口
			setPubA664PortRDIUPath(pubA664PortGuid);
		}
	}
	
	/**
	 * 设置发送端为CANPort的端系统部分路径
	 * @param pubPortGuid 发送端端口Guid
	 * @param pubA664PortGuid 发送端A664端口Guid
	 */
	private void setPubCANPortPath(String pubPortGuid,String pubA664PortGuid) {
		//根据发送端口寻找与之连接的RDIU的CAN端口
		RGWCANPortType rgwcanPort = loadICDDataTest.getCANPortByDataflowSrcGuid(pubPortGuid);
		if(rgwcanPort==null) {
			rpMessage.addErrorMsg("发送端RDIU的CAN端口获取错误");
			return;
		}
		CANPortNode subPort = new CANPortNode("CANPort",rgwcanPort.getName(),rgwcanPort.getGuid());
		subPort.setPhysPortName(getPortInfo(rgwcanPort.getPhysical()));
		RIUAppNode pubRDIU = new RIUAppNode();
		pubRDIU.setSamplePeriod(rgwcanPort.getSamplePeriod());//设置采样周期
		pubRDIU.setSubPort(subPort);
		rpMessage.setPubRDIU(pubRDIU);
		if(rgwcanPort.getGuid().equals(pubA664PortGuid)) {
			//代表在本地进行RDIU转换
			rpMessage.setLocal(true);
		}else {
			//获取A664端口
			setPubA664PortRDIUPath(pubA664PortGuid);
		}
	}
	
	/**
	 * 设置发送端端口为HFSampingPort的路径
	 * @param pubPortGuid 发送端端口
	 */
	private void setPubHFSamplingPortPath(String pubPortGuid) {
		HFSamplingPortType hfSamplingPort = loadICDDataTest.getHFSamplingPortByGuid(pubPortGuid);
		HFSamplingPortNode port = new HFSamplingPortNode("HFSamplingPort",hfSamplingPort.getName(),hfSamplingPort.getGuid());
		LRUAppNode pubLRUAppNode = new LRUAppNode();
		pubLRUAppNode.setPortDef(hfSamplingPort.getGuidDef());
		pubLRUAppNode.setPort(port);
		HFSamplingPortDefType hfSamplingPortDef = loadICDDataTest.getHFSamplingPortDefByGuid(hfSamplingPort.getGuidDef());
		if(hfSamplingPortDef==null) {
			rpMessage.addErrorMsg("发送端端系统获取端口定义类错误");
			return;
		}
		pubLRUAppNode.setRefreshPeriod(hfSamplingPortDef.getRefreshPeriod());
		rpMessage.setPubEndApp(pubLRUAppNode);
		pubLRUAppNode.setDirect("pub");
		if(hfSamplingPort.getA664Message().size()==0||hfSamplingPort.getA664Message().size()>1) {
			rpMessage.addErrorMsg("发送端端口的A664块数量不为1");
		}else {
			rpMessage.getAdnPart().setVlID(hfSamplingPort.getA664Message().get(0).getVLID());
			rpMessage.getAdnPart().setSubVLID(hfSamplingPort.getA664Message().get(0).getSubVLID());
			rpMessage.getAdnPart().setPubA664Message(hfSamplingPort.getA664Message().get(0));
		}
	}
	
	/**
	 * 设置发送端端口为HFQueuingPort的路径
	 * @param pubPortGuid 发送端端口
	 */
	private void setPubHFQueuingPortPortPath(String pubPortGuid) {
		HFQueuingPortType hfQueuingPort = loadICDDataTest.getHFQueuingPortByGuid(pubPortGuid);
		HFQueuingPortNode port = new HFQueuingPortNode("HFQueuingPort",hfQueuingPort.getName(),hfQueuingPort.getGuid());
		LRUAppNode pubLRUAppNode = new LRUAppNode();
		pubLRUAppNode.setPortDef(hfQueuingPort.getGuidDef());
		HFQueuingPortDefType hfQueuingPortDef = loadICDDataTest.getHFQueuingPortDefByGuid(hfQueuingPort.getGuidDef());
		if(hfQueuingPortDef==null) {
			rpMessage.addErrorMsg("发送端端系统获取端口定义类错误");
			return;
		}
		port.setQueueLength(hfQueuingPortDef.getQueueLength());
		pubLRUAppNode.setPort(port);
		pubLRUAppNode.setRefreshPeriod(hfQueuingPortDef.getRefreshPeriod());
		rpMessage.setPubEndApp(pubLRUAppNode);
		pubLRUAppNode.setDirect("pub");
		if(hfQueuingPort.getA664Message().size()==0||hfQueuingPort.getA664Message().size()>1) {
			rpMessage.addErrorMsg("发送端端口的A664块数量不为1");
		}else {
			rpMessage.getAdnPart().setVlID(hfQueuingPort.getA664Message().get(0).getVLID());
			rpMessage.getAdnPart().setSubVLID(hfQueuingPort.getA664Message().get(0).getSubVLID());
			rpMessage.getAdnPart().setPubA664Message(hfQueuingPort.getA664Message().get(0));
		}
	}
	
	/**
	 * 设置发送端端口为A653SampingPort的路径
	 * @param pubPortGuid 发送端端口
	 */
	private void setPubA653SamplingPortPath(String pubPortGuid) {
		A653SamplingPortType a653SamplingPort = loadICDDataTest.getA653SamplingPortByGuid(pubPortGuid);
		GPMAppNode pubGPMAppNode = new GPMAppNode();
		A653SamplingPortNode port = new A653SamplingPortNode("A653SamplingPort",a653SamplingPort.getName(),a653SamplingPort.getGuid());
		pubGPMAppNode.setPortDef(a653SamplingPort.getGuidDef());
		pubGPMAppNode.setPort(port);
		A653SamplingPortDefType a653SamplingPortDef = loadICDDataTest.getA653SamplingPortDefByGuid(a653SamplingPort.getGuidDef());
		if(a653SamplingPortDef==null) {
			rpMessage.addErrorMsg("发送端端系统获取端口定义类错误");
			return;
		}
		pubGPMAppNode.setRefreshPeriod(a653SamplingPortDef.getRefreshPeriod());
		pubGPMAppNode.setSamplePeriod(a653SamplingPortDef.getSamplePeriod());
		rpMessage.setPubEndApp(pubGPMAppNode);
		pubGPMAppNode.setDirect("pub");
		if(a653SamplingPort.getA664Message().size()==0||a653SamplingPort.getA664Message().size()>1) {
			rpMessage.addErrorMsg("发送端端口的A664块数量不为1");
		}else {
			rpMessage.getAdnPart().setVlID(a653SamplingPort.getA664Message().get(0).getVLID());
			rpMessage.getAdnPart().setSubVLID(a653SamplingPort.getA664Message().get(0).getSubVLID());
			if(rpMessage.getAdnPart().getSubVLID().equals("NA")) {
				rpMessage.setLocal(true);
			}
			rpMessage.getAdnPart().setPubA664Message(a653SamplingPort.getA664Message().get(0));
		}
	}
	
	/**
	 * 设置发送端端口为A653QueuingPort的路径
	 * @param pubPortGuid 发送端端口
	 */
	private void setPubA653QueuingPortPortPath(String pubPortGuid) {
		A653QueuingPortType a653QueuingPort = loadICDDataTest.getA653QueuingPortByGuid(pubPortGuid);
		GPMAppNode pubGPMAppNode = new GPMAppNode();
		A653QueuingPortNode port = new A653QueuingPortNode("A653QueuingPort",a653QueuingPort.getName(),a653QueuingPort.getGuid());
		pubGPMAppNode.setPortDef(a653QueuingPort.getGuidDef());
		A653QueuingPortDefType a653QueuingPortDef = loadICDDataTest.getA653QueuingPortDefByGuid(a653QueuingPort.getGuidDef());
		if(a653QueuingPortDef==null) {
			rpMessage.addErrorMsg("发送端端系统获取端口定义类错误");
			return;
		}
		port.setQueueLength(a653QueuingPortDef.getQueueLength());
		pubGPMAppNode.setPort(port);
		pubGPMAppNode.setRefreshPeriod(a653QueuingPortDef.getRefreshPeriod());
		pubGPMAppNode.setSamplePeriod(a653QueuingPortDef.getSamplePeriod());
		rpMessage.setPubEndApp(pubGPMAppNode);
		pubGPMAppNode.setDirect("pub");
		if(a653QueuingPort.getA664Message().size()==0||a653QueuingPort.getA664Message().size()>1) {
			rpMessage.addErrorMsg("发送端端口的A664块数量不为1");
		}else {
			rpMessage.getAdnPart().setVlID(a653QueuingPort.getA664Message().get(0).getVLID());
			rpMessage.getAdnPart().setSubVLID(a653QueuingPort.getA664Message().get(0).getSubVLID());
			if(rpMessage.getAdnPart().getSubVLID().equals("NA")) {
				rpMessage.setLocal(true);
			}
			rpMessage.getAdnPart().setPubA664Message(a653QueuingPort.getA664Message().get(0));
		}
	}
    
    /**********************其他功能函数*********************************/
    public boolean isA664Port(String portType) {
    	return isLocalA664Port(portType)||isRGWA664Port(portType);
    }
    
    public boolean isLocalA664Port(String portType) {
    	return portType.equals("HFSamplingPort")||portType.equals("HFQueuingPort")
    			||portType.equals("A653SamplingPort")||portType.equals("A653QueuingPort");
    }
    
    public boolean isRGWA664Port(String portType) {
    	return isRGWHFSamplingPort(portType)||isRGWHFQueuingPort(portType);
    }
    
    public boolean isRGWHFSamplingPort(String portType) {
    	if(portType==null) {
    		return false;
    	}
    	return portType.equals("RGWHFSamplingPort");
    }
    
    public boolean isRGWHFQueuingPort(String portType) {
    	if(portType==null) {
    		return false;
    	}
    	return portType.equals("RGWHFQueuingPort");
    }
    
    public boolean isHostedFunction(String hardwareType) {
    	if(hardwareType==null) {
    		return false;
    	}
    	return hardwareType.equals("HostedFunction");
    }
    
    public boolean isA653ApplicationComponent(String hardwareType) {
    	if(hardwareType==null) {
    		return false;
    	}
    	return hardwareType.equals("A653ApplicationComponent");
    }
    
    public boolean isRIUApplication(String hardwareType) {
    	if(hardwareType == null) {
    		return false;
    	}
    	return hardwareType.equals("RIUApplication");
    }
    
    public boolean isSwitchApplication(String hardwareType) {
    	if(hardwareType == null) {
    		return false;
    	}
    	return hardwareType.equals("SwitchApplication");
    }
    
    public boolean isRemoteGateway(String hardwareType) {
    	if(hardwareType == null) {
    		return false;
    	}
    	return hardwareType.equals("RemoteGateway");
    }
	
    public boolean isNull(Object object,String string) {
    	if(object == null) {
    		rpMessage.addErrorMsg("存在"+string+"属性为空");
    		return true;
    	}
    	return false;
    }
    
    /**
     * 去除GPM的Name中小数点左边的内容
     * @return
     */
    public String removeGPMNamePosInfo(String name) {
    	if(name==null) {
    		return null;
    	}
    	int pos = name.indexOf(".");
    	return name.substring(pos+1);
    }
    
    /**
     * 获取硬件名称
     * @param info 输入信息，如：GPM_R1.A
     * @return 返回硬件信息，如GPM_R1
     */
    public String getHardwareInfo(String info) {
    	if(info==null) {
    		return null;
    	}
    	int pos = info.indexOf(".");
    	return info.substring(0,pos);
    }
    
    /**
     * 获取端口信息
     * @param info 输入信息，如：GPM_R1.A
     * @return A
     */
    public String getPortInfo(String info) {
    	if(info==null) {
    		return null;
    	}
    	int pos = info.indexOf(".");
    	return info.substring(pos+1);
    }
    
    /**
     * 判断给定的交换机名称是否为ACS
     * @param name
     * @return
     */
    public boolean isACS(String name) {
    	if(name==null) {
    		return false;
    	}
    	int pos = name.indexOf("_");
    	if (name.substring(0,pos).equals("ACS")) {
			return true;
		}
    	return false;
    }
    
    public String mapEndApplicationType2HardwareType(String type) {
    	if(type==null) {
    		return null;
    	}else if(type.equals("A653ApplicationComponent")) {
    		return "GPM";
    	}else if(type.equals("HostedFunction")) {
    		return "LRU";
    	}else {
    		return null;
    	}
    }
    
    /**
     * 判断种类是否为GPM
     * @param type
     * @return
     */
    public boolean isGPM(String type) {
    	if(type==null) {
    		return false;
    	}
    	return type.equals("GPM");
    }
}
