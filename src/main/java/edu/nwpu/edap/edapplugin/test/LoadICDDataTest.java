package edu.nwpu.edap.edapplugin.test;

import com.careri.as.businessmodel.model.*;
import com.careri.as.businessmodel.service.BusinessModelService;
import com.careri.as.businessmodel.service.impl.BusinessModelServiceImpl;
import com.careri.as.workbench.api.datamodel.logicmodel.ICDObject;
import com.careri.as.workbench.api.icd.ICDDataService;
import com.careri.as.workbench.api.icd.impl.ICDDataServiceImpl;
import edu.nwpu.edap.edapplugin.Service.Impl.ProgressServiceImpl;
import edu.nwpu.edap.edapplugin.Service.ProgressService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LoadICDDataTest {
	
	private static LoadICDDataTest uniqueInstance;

	List<RPType> rpTypes;
	List<RPDefType> rpDefTypes;
	
	List<A429PortType> a429PortTypes;
	List<RGWA429PortType> rgwa429PortTypes;
	List<A429PortDefType> a429PortDefTypes;
	
	List<CANPortType> canPortTypes;
	List<RGWCANPortType> rgwcanPortTypes;
	List<CANPortDefType> canPortDefTypes;
	
	List<AnalogPortType> analogPortTypes;
	List<RGWAnalogPortType> rgwAnalogPortTypes;
	List<AnalogPortDefType> analogPortDefTypes;
	
	List<HFSamplingPortType> hfSamplingPortTypes;
	List<RGWHFSamplingPortType> rgwhfSamplingPortTypes;
	List<HFSamplingPortDefType> hfSamplingPortDefTypes;
	
	List<HFQueuingPortType> hfQueuingPortTypes;
	List<RGWHFQueuingPortType> rgwhfQueuingPortTypes;
	List<HFQueuingPortDefType> hfQueuingPortDefTypes;
	
	List<A653SamplingPortType> a653SamplingPortTypes;
	List<A653SamplingPortDefType> a653SamplingPortDefTypes;
	List<A653QueuingPortType> a653QueuingPortTypes;
	List<A653QueuingPortDefType> a653QueuingPortDefTypes;
	
	List<VirtualLinkType> virtualLinkTypes;
	
	List<HostedFunctionClassType> hostedFunctionClassTypes;
	List<SwitchApplicationClassType> switchApplicationClassTypes;
	List<RIUApplicationClassType> riuApplicationClassTypes;
	List<A653ApplicationComponentClassType> a653ApplicationComponentClassTypes;
	
	List<HostedFunctionType> hostedFunctionTypes;
	List<RemoteGatewayType> remoteGatewayTypes;
	List<SwitchApplicationType> switchApplicationTypes;
	List<RIUApplicationType> riuApplicationTypes;
	List<A653ApplicationComponentType> a653ApplicationComponentTypes;
	
	List<GPMClassType> gpmClassTypes;
	List<ARSClassType> arsClassTypes;
	List<ACSClassType> acsClassTypes;
	List<LRUClassType> lruClassTypes;
	List<RIUClassType> riuClassTypes;
	
	List<GPMType> gpmTypes;
	List<ARSType> arsTypes;
	List<ACSType> acsTypes;
	List<LRUType> lruTypes;
	List<RIUType> riuTypes;
	
	Map<String, RPType> rpMap;
	Map<String, RPDefType> rpDefMap;
	
	Map<String, A429PortType> a429Map;
	Map<String, RGWA429PortType> rgwA429Map;
	Map<String, A429PortDefType> a429DefMap;
	
	Map<String, AnalogPortType> analogMap;
	Map<String, RGWAnalogPortType> rgwAnalogMap;
	Map<String, AnalogPortDefType> analogDefMap;	
	
	Map<String, CANPortType> canMap;
	Map<String, RGWCANPortType> rgwcanMap;
	Map<String, CANPortDefType> canDefMap;
	
	Map<String, HFSamplingPortType> hfSamplingMap;
	Map<String,RGWHFSamplingPortType> rgwhfSamplingMap;
	Map<String, HFSamplingPortDefType> hfSamplingDefMap;
	
	Map<String, HFQueuingPortType> hfQueuingMap;
	Map<String, RGWHFQueuingPortType> rgwhfQueuingMap;
	Map<String, HFQueuingPortDefType> hfQueuingDefMap;
	
	Map<String, A653SamplingPortType> a653SamplingMap;
	Map<String, A653SamplingPortDefType> a653SamplingDefMap;
	
	Map<String, A653QueuingPortType> a653QueuingMap;
	Map<String, A653QueuingPortDefType> a653QueuingDefMap;
	
	Map<Integer, VirtualLinkType> virtualLinkMap;
	
	Map<String, HostedFunctionType> hfMap;
	
	Map<String, LRUClassType> lruClassMap;
	Map<String, GPMClassType> gpmClassMap;
	Map<String, ACSClassType> acsClassMap;
	Map<String, ARSClassType> arsClassMap;
	Map<String, RIUClassType> riuClassMap;
	
	Map<String, LRUType> lruMap;
	Map<String, GPMType> gpmMap;
	Map<String, ACSType> acsMap;
	Map<String, ARSType> arsMap;
	Map<String, RIUType> riuMap;
	
	Map<String,RGWA429PortType> dataa429Map;
	Map<String, RGWCANPortType> datacanMap;
	Map<String,RGWAnalogPortType> dataanalogMap;
	
	Map<String, CANPortType> dpcanMap;
	Map<String, AnalogPortType> dpanalogMap;
	Map<String, A429PortType> dpa429Map;
	Map<String, A653SamplingPortType> dpa653SamplingMap;
	Map<String, A653QueuingPortType> dpa653QueuingMap;
	Map<String, HFSamplingPortType> dphfSamplingMap;
	Map<String, HFQueuingPortType> dphfQueuingMap;
	
	Map<String, String> localPortTypeMap;
	Map<String,String> rgwPortTypeMap;
	Map<String, String> localPortTypeByRPMap;
	Map<String,String> localPortGuidByRPMap;
	Map<String, String> localPortDataflowMap;
	Map<String, NonA664LogicalPortType> localNonA664LogicalPortMapByPortGuid;
	Map<String, NonA664LogicalPortDefType> nonA664LogicalPortDefMapByGuid;
	Map<String, RGWNonA664LogicalPortType> rgwNonA664LogicalPortMapByPortGuid;
	
	//硬件、端应用相关Map
	Map<String, String> softwareInstanceTypeMap;//获取端应用类型
	Map<String, SoftwareClassType> softwareClassMap;//获取端应用定义类
	Map<String, SoftwareInstanceType> softwareInstanceMap;//获取端应用（父类）
	Map<String, RemoteGatewayType> remoteGatewayMap;
	Map<String, EndSystemType> endSystemMap;
	
	
	private LoadICDDataTest() {
		init();
//		test();
	}
	
	public static LoadICDDataTest getInstance() {
		if(uniqueInstance==null) {
			uniqueInstance = new LoadICDDataTest();
		}
		return uniqueInstance;
	}
	
	private void init(){
		try {

			ProgressService progressService = new ProgressServiceImpl();
			
			progressService.closeProgress();
			
			progressService.showProgress();
			
			MyService myService = new MyService();
			myService.start();
			
			myService.progressProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
					progressService.setProgress(arg2.doubleValue());
					
				}
			});
			
			myService.messageProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
					progressService.setMessage(arg2);	
					
				}
			});
				
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	
	private void test() {
		ICDDataService icdDataService = new ICDDataServiceImpl();
//		List<ICDObject> dpList = icdDataService.retrieveICDObjectsByType("DP");

		List<ICDObject> a429List = icdDataService.retrieveICDObjectsByType("HFSamplingPort");
		
		Set<String> set1 = new HashSet<String>();

		for(ICDObject icdObject:a429List) {
			set1.add(icdDataService.retrieveParent(icdObject).getType());
		}

		
		System.out.println(set1);
	}
	
	public List<RPType> getRPList(){
		return rpTypes;
	}
	
	public RPDefType getRPDefByGuid(String Guid) {
		return rpDefMap.get(Guid);
	}
	
	public RPType getRPByGuid(String Guid) {
		return rpMap.get(Guid);
	}
	
	public A429PortType getA429PortByGuid(String Guid) {
		return a429Map.get(Guid);
	}
	
	public RGWA429PortType getRGWA429PortByGuid(String Guid) {
		return rgwA429Map.get(Guid);
	}
	
	public A429PortDefType getA429PortDefByGuid(String Guid) {
		return a429DefMap.get(Guid);
	}
	
	public CANPortType getCANPortByGuid(String Guid) {
		return canMap.get(Guid);
	}
	
	public RGWCANPortType getRGWCANPortByGuid(String Guid) {
		return rgwcanMap.get(Guid);
	}
	
	public CANPortDefType getCANPortDefByGuid(String Guid) {
		return canDefMap.get(Guid);
	}
	
	public AnalogPortType getAnalogPortByGuid(String Guid) {
		return analogMap.get(Guid);
	}
	
	public RGWAnalogPortType getRGWAnalogPortByGuid(String Guid) {
		return rgwAnalogMap.get(Guid);
	}
	
	public AnalogPortDefType getAnalogPortDefByGuid(String Guid) {
		return analogDefMap.get(Guid);
	}
	
	public HFSamplingPortType getHFSamplingPortByGuid(String Guid) {
		return hfSamplingMap.get(Guid);
	}
	
	public RGWHFSamplingPortType getRGWHFSamplingPortByGuid(String Guid) {
		return rgwhfSamplingMap.get(Guid);
	}
	
	public HFSamplingPortDefType getHFSamplingPortDefByGuid(String Guid) {
		return hfSamplingDefMap.get(Guid);
	}
	
	public HFQueuingPortType getHFQueuingPortByGuid(String Guid) {
		return hfQueuingMap.get(Guid);
	}
	
	public RGWHFQueuingPortType getRGWHFQueuingPortByGuid(String Guid) {
		return rgwhfQueuingMap.get(Guid);
	}
	
	public HFQueuingPortDefType getHFQueuingPortDefByGuid(String Guid) {
		return hfQueuingDefMap.get(Guid);
	}
	
	public A653SamplingPortType getA653SamplingPortByGuid(String Guid) {
		return a653SamplingMap.get(Guid);
	}
	
	public A653SamplingPortDefType getA653SamplingPortDefByGuid(String Guid) {
		return a653SamplingDefMap.get(Guid);
	}
	
	public A653QueuingPortType getA653QueuingPortByGuid(String Guid) {
		return a653QueuingMap.get(Guid);
	}
	
	public A653QueuingPortDefType getA653QueuingPortDefByGuid(String Guid) {
		return a653QueuingDefMap.get(Guid);
	}
	
	public VirtualLinkType getVirtualLinkByVLID(Integer VLID) {
		return virtualLinkMap.get(VLID);
	}
	
	/**
	 * 根据VLID和PathName找到VirtualLink中对应的VLPath
	 * @param VLID
	 * @param PathName
	 * @return VLPath，若没找到则返回null
	 */
	public VLPathType getVLPathByNameAndID(Integer VLID,String PathName) {
		VirtualLinkType virtualLinkType = virtualLinkMap.get(VLID);
		for(VLPathType vl:virtualLinkType.getVLPath()) {
			if(vl.getName().equals(PathName)) {
				return vl;
			}
		}
		return null;
	}
	
	/**
	 * 根据Guid和VirtualLink返回其中对应的ComPortTx
	 * @param Guid
	 * @param virtualLink
	 * @return
	 */
	public ComPortTxType getComPortTxByGuidAndVL(String Guid,VirtualLinkType virtualLink) {
		if(Guid==null||virtualLink==null) {
			return null;
		}
		for(ComPortTxType comPortTx:virtualLink.getComPortTx()) {
			for(PortType port:comPortTx.getPortRef()) {
				if(port.getGuid().equals(Guid)) {
					return comPortTx;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据Guid和VirtualLink返回其中对应的ComPortRx
	 * @param Guid
	 * @param virtualLink
	 * @return
	 */
	public ComPortRxType getComPortRxByGuidAndVL(String Guid,VirtualLinkType virtualLink) {
		if(Guid==null||virtualLink==null) {
			return null;
		}
		for(ComPortRxType comPortRx:virtualLink.getComPortRx()) {
			for(PortType port:comPortRx.getPortRef()) {
				if(port.getGuid().equals(Guid)) {
					return comPortRx;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据id和VirtualLink返回其中对应的SubVL
	 * @param id
	 * @param virtualLink
	 * @return
	 */
	public SubVLType getSubVLByIdAndVL(String id,VirtualLinkType virtualLink) {
		if(id==null || virtualLink==null) {
			return null;
		}
		for(SubVLType subvl:virtualLink.getSubVL()) {
			if(subvl.getSubVLID().equals(id)) {
				return subvl;
			}
		}
		return null;
	}
	
	public RGWA429PortType getA429PortByDataflowSrcGuid(String SrcGuid) {
		return dataa429Map.get(SrcGuid);
	}

	public RGWCANPortType getCANPortByDataflowSrcGuid(String SrcGuid) {
		return datacanMap.get(SrcGuid);
	}
	
	public RGWAnalogPortType getAnalogPortByDataflowSrcGuid(String SrcGuid) {
		return dataanalogMap.get(SrcGuid);
	}
	
	public A429PortType getA429PortTypeByDPGuid(String Guid) {
		return dpa429Map.get(Guid);
	}
	
	public AnalogPortType getAnalogPortByDPGuid(String Guid) {
		return dpanalogMap.get(Guid);
	}
	
	public CANPortType getCANPortByDPGuid(String Guid) {
		return dpcanMap.get(Guid);
	}
	
	public A653SamplingPortType getA653SamplingPortByDPGuid(String Guid) {
		return dpa653SamplingMap.get(Guid);
	}
	
	public A653QueuingPortType getA653QueuingPortByDPGuid(String Guid) {
		return dpa653QueuingMap.get(Guid);
	}
	
	public HFSamplingPortType getHFSamplingPortByDPGuid(String Guid) {
		return dphfSamplingMap.get(Guid);
	}
	
	public HFQueuingPortType getHFQueuingPortByDPGuid(String Guid) {
		return dphfQueuingMap.get(Guid);
	}
	
	public LRUClassType getLRUClassByGuid(String Guid) {
		return lruClassMap.get(Guid);
	}
	
	public GPMClassType getGPMClassByGuid(String Guid) {
		return gpmClassMap.get(Guid);
	}
	
	public ACSClassType getACSClassTypeByGuid(String Guid) {
		return acsClassMap.get(Guid);
	}
	
	public ARSClassType getARSClassTypeByGuid(String Guid) {
		return arsClassMap.get(Guid);
	}
	
	public RIUClassType getRIUClassTypeByGuid(String Guid) {
		return riuClassMap.get(Guid);
	}
	
	public LRUType getLRUByName(String name) {
		return lruMap.get(name);
	}
	
	public GPMType getGPMTypeByName(String name) {
		return gpmMap.get(name);
	}
	
	public ACSType getACSTypeByName(String name) {
		return acsMap.get(name);
	}
	
	public ARSType getARSTypeByName(String name) {
		return arsMap.get(name);
	}
	
	public RIUType getRIUTypeByName(String name) {
		return riuMap.get(name);
	}
	
	public String getSoftwareInstanceTypeByPortGuid(String Guid) {
		return softwareInstanceTypeMap.get(Guid);
	}
	
	public SoftwareInstanceType getSoftwareInstanceByPortGuid(String Guid) {
		return softwareInstanceMap.get(Guid);
	}
	
	public RemoteGatewayType getRemoteGatewayByPortGuid(String Guid) {
		return remoteGatewayMap.get(Guid);
	}
	
	public SoftwareClassType getSoftwareClassByGuid(String Guid) {
		return softwareClassMap.get(Guid);
	}
	
	public EndSystemType getEndSystemByHardwareName(String name) {
		return endSystemMap.get(name);
	}
	
	/**
	 * 根据Guid获取对应本地Port的类型
	 * @param Guid
	 * @return
	 */
	public String getLocalPortTypeByGuid(String Guid) {
		return localPortTypeMap.get(Guid);
	}
	
	/**
	 * 根据RP的Guid获取本地Port的类型
	 * @param Guid
	 * @return
	 */
	public String getLocalPortTypeByRPGuid(String Guid) {
		return localPortTypeByRPMap.get(Guid);
	}
	
	/**
	 * 根据RP的Guid获取本地Port的Guid
	 * @param Guid
	 * @return
	 */
	public String getLocalPortGuidyRPGuid(String Guid) {
		return localPortGuidByRPMap.get(Guid);
	}
	
	/**
	 * 根据Guid获取远程网关上Port的类型
	 * @param Guid
	 * @return
	 */
	public String getRGWPortTypeByGuid(String Guid) {
		return rgwPortTypeMap.get(Guid);
	}
	
	/**
	 * 根据Port的Guid获取其Dataflow的SrcGuid
	 * @param Guid
	 * @return
	 */
	public String getLocalPortDataflowByGuid(String Guid) {
		return localPortDataflowMap.get(Guid);
	}
	
	/**
	 * 根据Port的Guid获取非664的端口信息
	 * @param Guid
	 * @return
	 */
	public NonA664LogicalPortType getNonA664LogicalPortByPortGuid(String Guid) {
		return localNonA664LogicalPortMapByPortGuid.get(Guid);
	}
	
	/**
	 * 根据Guid获取非664的端口定义类
	 * @param Guid
	 * @return
	 */
	public NonA664LogicalPortDefType getNonA664LogicalPortDefByGuid(String Guid) {
		if(Guid==null) {
			return null;
		}
		return nonA664LogicalPortDefMapByGuid.get(Guid);
	}
	
	
	/**
	 * 根据ACSClass和PortId获取AswPhysPort的定义类
	 * @param acsClass
	 * @param portId
	 * @return
	 */
	public AswPhysPortDefType getAswPhysPortDefByACSClassAndPortId(ACSClassType acsClass,int portId) {
		if(acsClass==null||portId==0) {
			return null;
		}
		for(AswPhysPortDefType aswPhysPortDef:acsClass.getAswPhysPort()) {
			if(aswPhysPortDef.getPortId()==portId) {
				return aswPhysPortDef;
			}
		}
		return null;
	}
	
	/**
	 * 根据LRUClass和A429PhysPort的Name获取A429PhysPort的定义类
	 * @param lruClass
	 * @param name
	 * @return
	 */
	public A429PhysPortDefType getA429PhysPortDefByLRUClassAndName(LRUClassType lruClass,String name) {
		if(lruClass==null||name==null) {
			return null;
		}
		for(A429PhysPortDefType a429PhysPortDef:lruClass.getA429PhysPort()) {
			if(a429PhysPortDef.getName().equals(name)) {
				return a429PhysPortDef;
			}
		}
		return null;
	}
	
	/**
	 * 根据RIUClass和A429PhysPort的Name获取A429PhysPort的定义类
	 * @param riuClass
	 * @param name
	 * @return
	 */
	public A429PhysPortDefType getA429PhysPortDefByRIUClassAndName(RIUClassType riuClass,String name) {
		if(riuClass==null||name==null) {
			return null;
		}
		for(A429PhysPortDefType a429PhysPortDef:riuClass.getA429PhysPort()) {
			if(a429PhysPortDef.getName().equals(name)) {
				return a429PhysPortDef;
			}
		}
		return null;
	}
	
	/**
	 * 根据LRUClass和CANPhysPort的Name获取CANPhysPort的定义类
	 * @param lruClass
	 * @param name
	 * @return
	 */
	public CANPhysPortDefType getCANPhysPortDefByLRUClassAndName(LRUClassType lruClass,String name) {
		if(lruClass==null||name==null) {
			return null;
		}
		for(CANPhysPortDefType canPhysPortDef:lruClass.getCANPhysPort()) {
			if(canPhysPortDef.getName().equals(name)) {
				return canPhysPortDef;
			}
		}
		return null;
	}
	
	/**
	 * 根据RIUClass和CANPhysPort的Name获取CANPhysPort的定义类
	 * @param riuClass
	 * @param name
	 * @return
	 */
	public CANPhysPortDefType getCANPhysPortDefByRIUClassAndName(RIUClassType riuClass,String name) {
		if(riuClass==null||name==null) {
			return null;
		}
		for(CANPhysPortDefType canPhysPortDef:riuClass.getCANPhysPort()) {
			if(canPhysPortDef.getName().equals(name)) {
				return canPhysPortDef;
			}
		}
		return null;
	}
	
	/**
	 * 根据Guid获取对应发送端的Port的Guid
	 * @param Guid
	 * @return
	 */
	public String getPubPortGuidByDPGuid(String Guid) {
		String type = getLocalPortTypeByGuid(Guid);
		if(type==null) {
			return null;
		}
		switch (type) {
		case "A429Port":
			return getA429PortTypeByDPGuid(Guid).getGuid();
		case "CANPort":
			return getCANPortByDPGuid(Guid).getGuid();
		case "AnalogPort":
			return getAnalogPortByDPGuid(Guid).getGuid();
		case "HFSamplingPort":
			return getHFSamplingPortByDPGuid(Guid).getGuid();
		case "HFQueuingPort":
			return getHFQueuingPortByDPGuid(Guid).getGuid();
		case "A653SamplingPort":
			return getA653SamplingPortByDPGuid(Guid).getGuid();
		case "A653QueuingPort":
			return getA653QueuingPortByDPGuid(Guid).getGuid();
		default:
			return null;
		}
	}
	
	/**
	 * 将本地的Port映射为类别
	 */
	private void mapLocalPort2Type() {
		localPortTypeMap = new HashMap<String, String>();
		localPortTypeByRPMap = new HashMap<String, String>();
		localPortGuidByRPMap = new HashMap<String, String>();
		localPortDataflowMap = new HashMap<String, String>();
		localNonA664LogicalPortMapByPortGuid = new HashMap<String, NonA664LogicalPortType>();
		
		//设置DP和A429Word到A429Port的映射
		try {
			dpa429Map = new HashMap<String, A429PortType>();
			for(A429PortType a429:a429PortTypes) {
				localPortTypeMap.put(a429.getGuid(), "A429Port");
				localNonA664LogicalPortMapByPortGuid.put(a429.getGuid(), a429);
				A429ChannelType channel = a429.getA429Channel();
				if(a429.getDataflowRef().size()==1) {
					localPortDataflowMap.put(a429.getGuid(), a429.getDataflowRef().get(0).getSrcGuid());
				}
				List<RPType> rps = a429.getRP();
				for(RPType rp:rps) {
					localPortTypeByRPMap.put(rp.getGuid(), "A429Port");
					localPortGuidByRPMap.put(rp.getGuid(), a429.getGuid());
				}
				if(channel!=null) {
					for(A429WordType a429Word:channel.getA429Word()) {
						dpa429Map.put(a429Word.getGuid(), a429);
						localPortTypeMap.put(a429Word.getGuid(), "A429Port");
						for(DPType dp:a429Word.getDP()) {
							dpa429Map.put(dp.getGuid(), a429);
							localPortTypeMap.put(dp.getGuid(), "A429Port");
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		//设置DP和A429Word到CANPort的映射
		dpcanMap = new HashMap<String, CANPortType>();
		for(CANPortType can:canPortTypes) {
			localPortTypeMap.put(can.getGuid(), "CANPort");
			localNonA664LogicalPortMapByPortGuid.put(can.getGuid(), can);
			List<RPType> rps = can.getRP();
			for(RPType rp:rps) {
				localPortTypeByRPMap.put(rp.getGuid(), "CANPort");
				localPortGuidByRPMap.put(rp.getGuid(), can.getGuid());
			}
			if(can.getDataflowRef().size()==1) {
				localPortDataflowMap.put(can.getGuid(), can.getDataflowRef().get(0).getSrcGuid());
			}
			for(CANMessageType msg:can.getCANMessage()) {
				for(A429WordType a429Word:msg.getA429Word()) {
					dpcanMap.put(a429Word.getGuid(), can);
					localPortTypeMap.put(a429Word.getGuid(), "CANPort");
					for(DPType dp:a429Word.getDP()) {
						dpcanMap.put(dp.getGuid(), can);
						localPortTypeMap.put(dp.getGuid(), "CANPort");
					}
				}
				for(DPType dp:msg.getDP()) {
					dpcanMap.put(dp.getGuid(), can);
					localPortTypeMap.put(dp.getGuid(), "CANPort");
				}
			}
		}
		
		//设置DP到AnalogPort的映射
		dpanalogMap = new HashMap<String, AnalogPortType>();
		for(AnalogPortType analog:analogPortTypes) {
			localPortTypeMap.put(analog.getGuid(), "AnalogPort");
			localNonA664LogicalPortMapByPortGuid.put(analog.getGuid(), analog);
			List<RPType> rps = analog.getRP();
			for(RPType rp:rps) {
				localPortTypeByRPMap.put(rp.getGuid(), "AnalogPort");
				localPortGuidByRPMap.put(rp.getGuid(), analog.getGuid());
			}
			if(analog.getDataflowRef().size()==1) {
				localPortDataflowMap.put(analog.getGuid(), analog.getDataflowRef().get(0).getSrcGuid());
			}
			for(AnalogDiscreteParameterType adp:analog.getAnalogDiscreteParameter()) {
				for(DPType dp:adp.getDP()) {
					dpanalogMap.put(dp.getGuid(), analog);
					localPortTypeMap.put(dp.getGuid(), "AnalogPort");
				}
			}
			for(AnalogSignalParameterType asp:analog.getAnalogSignalParameter()) {
				for(DPType dp:asp.getDP()) {
					dpanalogMap.put(dp.getGuid(), analog);
					localPortTypeMap.put(dp.getGuid(), "AnalogPort");
				}
			}
		}				
		
		dpa653SamplingMap = new HashMap<String, A653SamplingPortType>();
		for(A653SamplingPortType a653Sampling:a653SamplingPortTypes) {
			localPortTypeMap.put(a653Sampling.getGuid(), "A653SamplingPort");
			List<RPType> rps = a653Sampling.getRP();
			for(RPType rp:rps) {
				localPortTypeByRPMap.put(rp.getGuid(), "A653SamplingPort");
				localPortGuidByRPMap.put(rp.getGuid(), a653Sampling.getGuid());
			}
			if(a653Sampling.getDataflowRef().size()==1) {
				localPortDataflowMap.put(a653Sampling.getGuid(), a653Sampling.getDataflowRef().get(0).getSrcGuid());
			}
			for(A664MessageType msg:a653Sampling.getA664Message()) {
				for(DSType ds:msg.getDS()) {
					for(A429WordType a429Word:ds.getA429Word()) {
						dpa653SamplingMap.put(a429Word.getGuid(), a653Sampling);
						localPortTypeMap.put(a429Word.getGuid(), "A653SamplingPort");
						for(DPType dp:a429Word.getDP()) {
							dpa653SamplingMap.put(dp.getGuid(), a653Sampling);
							localPortTypeMap.put(dp.getGuid(), "A653SamplingPort");
						}
					}
					for(DPType dp:ds.getDP()) {
						dpa653SamplingMap.put(dp.getGuid(), a653Sampling);
						localPortTypeMap.put(dp.getGuid(), "A653SamplingPort");
					}
				}
			}
		}
		
		dpa653QueuingMap = new HashMap<String, A653QueuingPortType>();
		for(A653QueuingPortType a653Queuing:a653QueuingPortTypes) {
			localPortTypeMap.put(a653Queuing.getGuid(), "A653QueuingPort");
			List<RPType> rps = a653Queuing.getRP();
			for(RPType rp:rps) {
				localPortTypeByRPMap.put(rp.getGuid(), "A653QueuingPort");
				localPortGuidByRPMap.put(rp.getGuid(), a653Queuing.getGuid());
			}
			if(a653Queuing.getDataflowRef().size()==1) {
				localPortDataflowMap.put(a653Queuing.getGuid(), a653Queuing.getDataflowRef().get(0).getSrcGuid());
			}
			for(A664MessageType msg:a653Queuing.getA664Message()) {
				for(DSType ds:msg.getDS()) {
					for(A429WordType a429Word:ds.getA429Word()) {
						dpa653QueuingMap.put(a429Word.getGuid(), a653Queuing);
						localPortTypeMap.put(a429Word.getGuid(), "A653QueuingPort");
						for(DPType dp:a429Word.getDP()) {
							dpa653QueuingMap.put(dp.getGuid(), a653Queuing);
							localPortTypeMap.put(dp.getGuid(), "A653QueuingPort");
						}
					}
					for(DPType dp:ds.getDP()) {
						dpa653QueuingMap.put(dp.getGuid(), a653Queuing);
						localPortTypeMap.put(dp.getGuid(), "A653QueuingPort");
					}
				}
			}
		}
		
		dphfSamplingMap = new HashMap<String, HFSamplingPortType>();
		for(HFSamplingPortType hfSampling:hfSamplingPortTypes) {
			localPortTypeMap.put(hfSampling.getGuid(), "HFSamplingPort");
			List<RPType> rps = hfSampling.getRP();
			for(RPType rp:rps) {
				localPortTypeByRPMap.put(rp.getGuid(), "HFSamplingPort");
				localPortGuidByRPMap.put(rp.getGuid(), hfSampling.getGuid());
			}
			if(hfSampling.getDataflowRef().size()==1) {
				localPortDataflowMap.put(hfSampling.getGuid(), hfSampling.getDataflowRef().get(0).getSrcGuid());
			}
			for(A664MessageType msg:hfSampling.getA664Message()) {
				for(DSType ds:msg.getDS()) {
					for(A429WordType a429Word:ds.getA429Word()) {
						dphfSamplingMap.put(a429Word.getGuid(), hfSampling);
						localPortTypeMap.put(a429Word.getGuid(), "HFSamplingPort");
						for(DPType dp:a429Word.getDP()) {
							dphfSamplingMap.put(dp.getGuid(), hfSampling);
							localPortTypeMap.put(dp.getGuid(), "HFSamplingPort");
						}
					}
					for(DPType dp:ds.getDP()) {
						dphfSamplingMap.put(dp.getGuid(), hfSampling);
						localPortTypeMap.put(dp.getGuid(), "HFSamplingPort");
					}
				}
			}
		}
		
		dphfQueuingMap = new HashMap<String, HFQueuingPortType>();
		for(HFQueuingPortType hfQueuing:hfQueuingPortTypes) {
			localPortTypeMap.put(hfQueuing.getGuid(), "HFQueuingPort");
			List<RPType> rps = hfQueuing.getRP();
			for(RPType rp:rps) {
				localPortTypeByRPMap.put(rp.getGuid(), "HFQueuingPort");
				localPortGuidByRPMap.put(rp.getGuid(), hfQueuing.getGuid());
			}
			if(hfQueuing.getDataflowRef().size()==1) {
				localPortDataflowMap.put(hfQueuing.getGuid(), hfQueuing.getDataflowRef().get(0).getSrcGuid());
			}
			for(A664MessageType msg:hfQueuing.getA664Message()) {
				for(DSType ds:msg.getDS()) {
					for(A429WordType a429Word:ds.getA429Word()) {
						dphfQueuingMap.put(a429Word.getGuid(), hfQueuing);
						localPortTypeMap.put(a429Word.getGuid(), "HFQueuingPort");
						for(DPType dp:a429Word.getDP()) {
							dphfQueuingMap.put(dp.getGuid(), hfQueuing);
							localPortTypeMap.put(dp.getGuid(), "HFQueuingPort");
						}
					}
					for(DPType dp:ds.getDP()) {
						dphfQueuingMap.put(dp.getGuid(), hfQueuing);
						localPortTypeMap.put(dp.getGuid(), "HFQueuingPort");
					}
				}
			}
		}
	}
	
	/**
	 * 将RGW上的端口Guid和端口类型进行映射
	 */
	private void mapRGWPort2Type() {
		rgwPortTypeMap = new HashMap<String, String>();
		rgwNonA664LogicalPortMapByPortGuid = new HashMap<String, RGWNonA664LogicalPortType>();
		
		for(RGWA429PortType rgwa429PortType:rgwa429PortTypes) {
			rgwPortTypeMap.put(rgwa429PortType.getGuid(), "RGWA429Port");
			rgwNonA664LogicalPortMapByPortGuid.put(rgwa429PortType.getGuid(), rgwa429PortType);
		}
		
		for(RGWCANPortType rgwcanPortType:rgwcanPortTypes) {
			rgwPortTypeMap.put(rgwcanPortType.getGuid(), "RGWCANPort");
			rgwNonA664LogicalPortMapByPortGuid.put(rgwcanPortType.getGuid(), rgwcanPortType);
		}
		
		for(RGWAnalogPortType rgwAnalogPortType:rgwAnalogPortTypes) {
			rgwPortTypeMap.put(rgwAnalogPortType.getGuid(), "RGWAnalogPort");
			rgwNonA664LogicalPortMapByPortGuid.put(rgwAnalogPortType.getGuid(), rgwAnalogPortType);
		}
		
		for(RGWHFSamplingPortType rgwhfSamplingPortType:rgwhfSamplingPortTypes) {
			rgwPortTypeMap.put(rgwhfSamplingPortType.getGuid(), "RGWHFSamplingPort");
		}
		
		for(RGWHFQueuingPortType rgwhfQueuingPortType:rgwhfQueuingPortTypes) {
			rgwPortTypeMap.put(rgwhfQueuingPortType.getGuid(), "RGWHFQueuingPort");
		}
	}
	
	/**
	 * 将发送端和接收端端口上的Port映射到端设备
	 */
	private void mapPort2SoftwareInstance() {
		softwareInstanceTypeMap = new HashMap<String, String>();
		softwareInstanceMap = new HashMap<String, SoftwareInstanceType>();
		remoteGatewayMap = new HashMap<String, RemoteGatewayType>();
		softwareClassMap = new HashMap<String, SoftwareClassType>();
		
		for(HostedFunctionType hf:hostedFunctionTypes) {
			 for(A429PortType a429:hf.getA429Port()) {
				 softwareInstanceMap.put(a429.getGuid(), hf);
				 softwareInstanceTypeMap.put(a429.getGuid(), "HostedFunction");
			 }
			 for(CANPortType can:hf.getCANPort()) {
				 softwareInstanceMap.put(can.getGuid(), hf);
				 softwareInstanceTypeMap.put(can.getGuid(), "HostedFunction");
			 }
			 for(AnalogPortType analog:hf.getAnalogPort()) {
				 softwareInstanceMap.put(analog.getGuid(), hf);
				 softwareInstanceTypeMap.put(analog.getGuid(), "HostedFunction");
			 }
			 for(HFSamplingPortType hfSampling:hf.getHFSamplingPort()) {
				 softwareInstanceMap.put(hfSampling.getGuid(), hf);
				 softwareInstanceTypeMap.put(hfSampling.getGuid(), "HostedFunction");
			 }
			 for(HFQueuingPortType hfQueuing:hf.getHFQueuingPort()) {
				 softwareInstanceMap.put(hfQueuing.getGuid(), hf);
				 softwareInstanceTypeMap.put(hfQueuing.getGuid(), "HostedFunction");
			 }
		}
		
		for(RemoteGatewayType rg:remoteGatewayTypes) {
			 for(RGWA429PortType a429:rg.getA429Port()) {
				 remoteGatewayMap.put(a429.getGuid(), rg);
				 softwareInstanceTypeMap.put(a429.getGuid(), "RemoteGateway");
			 }
			 for(RGWCANPortType can:rg.getCANPort()) {
				 remoteGatewayMap.put(can.getGuid(), rg);
				 softwareInstanceTypeMap.put(can.getGuid(), "RemoteGateway");
			 }
			 for(RGWAnalogPortType analog:rg.getAnalogPort()) {
				 remoteGatewayMap.put(analog.getGuid(), rg);
				 softwareInstanceTypeMap.put(analog.getGuid(), "RemoteGateway");
			 }
			 for(RGWHFSamplingPortType hfSampling:rg.getHFSamplingPort()) {
				 remoteGatewayMap.put(hfSampling.getGuid(), rg);
				 softwareInstanceTypeMap.put(hfSampling.getGuid(), "RemoteGateway");
			 }
			 for(RGWHFQueuingPortType hfQueuing:rg.getHFQueuingPort()) {
				 remoteGatewayMap.put(hfQueuing.getGuid(), rg);
				 softwareInstanceTypeMap.put(hfQueuing.getGuid(), "RemoteGateway");
			 }
		}
		
		for(RIUApplicationType riuApp:riuApplicationTypes) {
			 for(HFSamplingPortType hfSampling:riuApp.getHFSamplingPort()) {
				 softwareInstanceMap.put(hfSampling.getGuid(), riuApp);
				 softwareInstanceTypeMap.put(hfSampling.getGuid(), "RIUApplication");
			 }
			 for(HFQueuingPortType hfQueuing:riuApp.getHFQueuingPort()) {
				 softwareInstanceMap.put(hfQueuing.getGuid(), riuApp);
				 softwareInstanceTypeMap.put(hfQueuing.getGuid(), "RIUApplication");
			 }
		}
		
		for(SwitchApplicationType switchApp:switchApplicationTypes) {
			 for(HFSamplingPortType hfSampling:switchApp.getHFSamplingPort()) {
				 softwareInstanceMap.put(hfSampling.getGuid(), switchApp);
				 softwareInstanceTypeMap.put(hfSampling.getGuid(), "SwitchApplication");
			 }
			 for(HFQueuingPortType hfQueuing:switchApp.getHFQueuingPort()) {
				 softwareInstanceMap.put(hfQueuing.getGuid(), switchApp);
				 softwareInstanceTypeMap.put(hfQueuing.getGuid(), "SwitchApplication");
			 }
		}
		
		for(A653ApplicationComponentType a653AppCom:a653ApplicationComponentTypes) {
			for(A653SamplingPortType a653Sampling:a653AppCom.getA653SamplingPort()) {
				softwareInstanceMap.put(a653Sampling.getGuid(), a653AppCom);
				softwareInstanceTypeMap.put(a653Sampling.getGuid(), "A653ApplicationComponent");
			}
			for(A653QueuingPortType a653Queuing:a653AppCom.getA653QueuingPort()) {
				softwareInstanceMap.put(a653Queuing.getGuid(), a653AppCom);
				softwareInstanceTypeMap.put(a653Queuing.getGuid(), "A653ApplicationComponent");
			}
		}
		
		for(HostedFunctionClassType hostedFunctionClass:hostedFunctionClassTypes) {
			softwareClassMap.put(hostedFunctionClass.getGuid(), hostedFunctionClass);
		}
		
		for(A653ApplicationComponentClassType a653ApplicationComponentClass:a653ApplicationComponentClassTypes) {
			softwareClassMap.put(a653ApplicationComponentClass.getGuid(), a653ApplicationComponentClass);
		}

	}

	/**
	 * 将硬件名称映射到硬件
	 */
	private void mapName2Hardware() {
		endSystemMap = new HashMap<String, EndSystemType>();
		
		for(LRUType lru:lruTypes) {
			endSystemMap.put(lru.getName(), lru);
		}
		
		for(GPMType gpm:gpmTypes) {
			endSystemMap.put(gpm.getName(), gpm);
		}
		
		for(RIUType riu:riuTypes) {
			endSystemMap.put(riu.getName(), riu);
		}
	}
	
	/**
	 * 将端口定义类一起放入Logic定义类中
	 */
	private void mapPortDef2LogicDef() {
		nonA664LogicalPortDefMapByGuid = new HashMap<String, NonA664LogicalPortDefType>();
		for(A429PortDefType a429PortDef:a429PortDefTypes) {
			nonA664LogicalPortDefMapByGuid.put(a429PortDef.getGuid(), a429PortDef);
		}
		for(CANPortDefType canPortDef:canPortDefTypes) {
			nonA664LogicalPortDefMapByGuid.put(canPortDef.getGuid(), canPortDef);
		}
		for(AnalogPortDefType analogPortDef:analogPortDefTypes) {
			nonA664LogicalPortDefMapByGuid.put(analogPortDef.getGuid(), analogPortDef);
		}
		
	}
	
	class MyService extends Service<Number>{

		@Override
		protected Task<Number> createTask() {
			
			Task<Number> task = new Task<Number>() {

				@Override
				protected Number call() throws Exception {
					int MAX_SIZE = 43, i=1;
					long startTime = System.currentTimeMillis();
					
					
					BusinessModelService service = new BusinessModelServiceImpl();		
					this.updateMessage("正在从数据库中加载数据");
					
					rpTypes = service.retrieveAllBusinessModelByType("RP", RPType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					rpDefTypes = service.retrieveAllBusinessModelByType("RP", RPDefType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					a429PortTypes = service.retrieveAllBusinessModelByType("A429Port", A429PortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					rgwa429PortTypes = service.retrieveAllBusinessModelByType("A429Port", RGWA429PortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					a429PortDefTypes = service.retrieveAllBusinessModelByType("A429Port", A429PortDefType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					hfSamplingPortTypes = service.retrieveAllBusinessModelByType("HFSamplingPort", HFSamplingPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					rgwhfSamplingPortTypes = service.retrieveAllBusinessModelByType("HFSamplingPort", RGWHFSamplingPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					hfSamplingPortDefTypes = service.retrieveAllBusinessModelByType("HFSamplingPort", HFSamplingPortDefType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					hfQueuingPortTypes = service.retrieveAllBusinessModelByType("HFQueuingPort", HFQueuingPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					rgwhfQueuingPortTypes = service.retrieveAllBusinessModelByType("HFQueuingPort", RGWHFQueuingPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					hfQueuingPortDefTypes = service.retrieveAllBusinessModelByType("HFQueuingPort", HFQueuingPortDefType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					analogPortTypes = service.retrieveAllBusinessModelByType("AnalogPort", AnalogPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					rgwAnalogPortTypes = service.retrieveAllBusinessModelByType("AnalogPort", RGWAnalogPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					analogPortDefTypes = service.retrieveAllBusinessModelByType("AnalogPort", AnalogPortDefType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					a653SamplingPortTypes = service.retrieveAllBusinessModelByType("A653SamplingPort", A653SamplingPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					a653SamplingPortDefTypes = service.retrieveAllBusinessModelByType("A653SamplingPort", A653SamplingPortDefType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					a653QueuingPortTypes = service.retrieveAllBusinessModelByType("A653QueuingPort", A653QueuingPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					a653QueuingPortDefTypes = service.retrieveAllBusinessModelByType("A653QueuingPort", A653QueuingPortDefType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					canPortTypes = service.retrieveAllBusinessModelByType("CANPort", CANPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					rgwcanPortTypes = service.retrieveAllBusinessModelByType("CANPort", RGWCANPortType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					canPortDefTypes = service.retrieveAllBusinessModelByType("CANPort", CANPortDefType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					virtualLinkTypes = service.retrieveAllBusinessModelByType("VirtualLink", VirtualLinkType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					gpmClassTypes = service.retrieveAllBusinessModelByType("GPMClass", GPMClassType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					lruClassTypes = service.retrieveAllBusinessModelByType("LRUClass", LRUClassType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					riuClassTypes = service.retrieveAllBusinessModelByType("RIUClass", RIUClassType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					arsClassTypes = service.retrieveAllBusinessModelByType("ARSClass", ARSClassType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					acsClassTypes = service.retrieveAllBusinessModelByType("ACSClass", ACSClassType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					gpmTypes = service.retrieveAllBusinessModelByType("GPM", GPMType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					lruTypes = service.retrieveAllBusinessModelByType("LRU", LRUType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					acsTypes = service.retrieveAllBusinessModelByType("ACS", ACSType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					arsTypes = service.retrieveAllBusinessModelByType("ARS", ARSType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					riuTypes = service.retrieveAllBusinessModelByType("RIU", RIUType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					hostedFunctionTypes = service.retrieveAllBusinessModelByType("HostedFunction", HostedFunctionType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					remoteGatewayTypes = service.retrieveAllBusinessModelByType("RemoteGateway", RemoteGatewayType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					riuApplicationTypes = service.retrieveAllBusinessModelByType("RIUApplication", RIUApplicationType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					switchApplicationTypes = service.retrieveAllBusinessModelByType("SwitchApplication", SwitchApplicationType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					a653ApplicationComponentTypes = service.retrieveAllBusinessModelByType("A653ApplicationComponent", A653ApplicationComponentType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					hostedFunctionClassTypes = service.retrieveAllBusinessModelByType("HostedFunctionClass", HostedFunctionClassType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					riuApplicationClassTypes = service.retrieveAllBusinessModelByType("RIUApplicationClass", RIUApplicationClassType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					switchApplicationClassTypes = service.retrieveAllBusinessModelByType("SwitchApplicationClass", SwitchApplicationClassType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					a653ApplicationComponentClassTypes = service.retrieveAllBusinessModelByType("A653ApplicationComponentClass", A653ApplicationComponentClassType.class);
					this.updateProgress(i++, MAX_SIZE);
					
					long endTime = System.currentTimeMillis();
					System.out.println("从数据库读取至内存数据所用时间为："+(endTime-startTime));
					
					/*------------------------------------------------------*/
					long startTime1 = System.currentTimeMillis();
					
					this.updateMessage("正在进行Hash映射");
					
					rpMap = rpTypes.stream().collect(Collectors.toMap(RPType::getGuid, Function.identity(),(x1,x2)->x2));
					rpDefMap = rpDefTypes.stream().collect(Collectors.toMap(RPDefType::getGuid,  Function.identity(),(x1,x2)->x2));
					
					a429Map = a429PortTypes.stream().collect(Collectors.toMap(A429PortType::getGuid, Function.identity(),(x1,x2)->x2));
					rgwA429Map = rgwa429PortTypes.stream().collect(Collectors.toMap(RGWA429PortType::getGuid,Function.identity(),(x1,x2)->x2));//存在一个key值相同的情况。直接替换
					a429DefMap = a429PortDefTypes.stream().collect(Collectors.toMap(A429PortDefType::getGuid, Function.identity(),(x1,x2)->x2));
					
					hfSamplingMap = hfSamplingPortTypes.stream().collect(Collectors.toMap(HFSamplingPortType::getGuid, Function.identity(),(x1,x2)->x2 ));
					rgwhfSamplingMap = rgwhfSamplingPortTypes.stream().collect(Collectors.toMap(RGWHFSamplingPortType::getGuid, Function.identity(),(x1,x2)->x2 ));
					hfSamplingDefMap = hfSamplingPortDefTypes.stream().collect(Collectors.toMap(HFSamplingPortDefType::getGuid, Function.identity(),(x1,x2)->x2 ));
					
					hfQueuingMap = hfQueuingPortTypes.stream().collect(Collectors.toMap(HFQueuingPortType::getGuid, Function.identity(),(x1,x2)->x2 ));
					rgwhfQueuingMap = rgwhfQueuingPortTypes.stream().collect(Collectors.toMap(RGWHFQueuingPortType::getGuid, Function.identity(),(x1,x2)->x2 ));
					hfQueuingDefMap = hfQueuingPortDefTypes.stream().collect(Collectors.toMap(HFQueuingPortDefType::getGuid, Function.identity(),(x1,x2)->x2 ));
					
					analogMap = analogPortTypes.stream().collect(Collectors.toMap(AnalogPortType::getGuid, Function.identity(),(x1,x2)->x2));
					rgwAnalogMap = rgwAnalogPortTypes.stream().collect(Collectors.toMap(RGWAnalogPortType::getGuid, Function.identity(),(x1,x2)->x2));
					analogDefMap = analogPortDefTypes.stream().collect(Collectors.toMap(AnalogPortDefType::getGuid, Function.identity(),(x1,x2)->x2));
					
					a653SamplingMap = a653SamplingPortTypes.stream().collect(Collectors.toMap(A653SamplingPortType::getGuid,Function.identity(),(x1,x2)->x2));
					a653SamplingDefMap = a653SamplingPortDefTypes.stream().collect(Collectors.toMap(A653SamplingPortDefType::getGuid,Function.identity(),(x1,x2)->x2));
					
					a653QueuingMap = a653QueuingPortTypes.stream().collect(Collectors.toMap(A653QueuingPortType::getGuid,Function.identity(),(x1,x2)->x2));
					a653QueuingDefMap = a653QueuingPortDefTypes.stream().collect(Collectors.toMap(A653QueuingPortDefType::getGuid,Function.identity(),(x1,x2)->x2));
					
					canMap = canPortTypes.stream().collect(Collectors.toMap(CANPortType::getGuid,Function.identity(),(x1,x2)->x2));
					rgwcanMap = rgwcanPortTypes.stream().collect(Collectors.toMap(RGWCANPortType::getGuid,Function.identity(),(x1,x2)->x2));
					canDefMap = canPortDefTypes.stream().collect(Collectors.toMap(CANPortDefType::getGuid,Function.identity(),(x1,x2)->x2));
					
					virtualLinkMap = virtualLinkTypes.stream().collect(Collectors.toMap(VirtualLinkType::getID, Function.identity(),(x1,x2)->x2));
					
					lruClassMap = lruClassTypes.stream().collect(Collectors.toMap(LRUClassType::getGuid, Function.identity(),(x1,x2)->x2));
					gpmClassMap = gpmClassTypes.stream().collect(Collectors.toMap(GPMClassType::getGuid, Function.identity(),(x1,x2)->x2));
					acsClassMap = acsClassTypes.stream().collect(Collectors.toMap(ACSClassType::getGuid, Function.identity(),(x1,x2)->x2));
					arsClassMap = arsClassTypes.stream().collect(Collectors.toMap(ARSClassType::getGuid, Function.identity(),(x1,x2)->x2));
					riuClassMap = riuClassTypes.stream().collect(Collectors.toMap(RIUClassType::getGuid, Function.identity(),(x1,x2)->x2));
					
					lruMap = lruTypes.stream().collect(Collectors.toMap(LRUType::getName, Function.identity(),(x1,x2)->x2));
					gpmMap = gpmTypes.stream().collect(Collectors.toMap(GPMType::getName, Function.identity(),(x1,x2)->x2));
					acsMap = acsTypes.stream().collect(Collectors.toMap(ACSType::getName, Function.identity(),(x1,x2)->x2));
					arsMap = arsTypes.stream().collect(Collectors.toMap(ARSType::getName, Function.identity(),(x1,x2)->x2));
					riuMap = riuTypes.stream().collect(Collectors.toMap(RIUType::getName, Function.identity(),(x1,x2)->x2));
					
					this.updateProgress(i++, MAX_SIZE);
		
					/*------------------------------------------------------*/
					dataa429Map = new HashMap<String, RGWA429PortType>();
					for(RGWA429PortType a429:rgwa429PortTypes) {
						for(DataflowType dataflowType:a429.getDataflowRef()) {
							dataa429Map.put(dataflowType.getSrcGuid(), a429);
						}
					}
					
					datacanMap = new HashMap<String, RGWCANPortType>();
					for(RGWCANPortType can:rgwcanPortTypes) {
						for(DataflowType dataflowType:can.getDataflowRef()) {
							datacanMap.put(dataflowType.getSrcGuid(), can);
						}
					}
					
					dataanalogMap = new HashMap<String, RGWAnalogPortType>();
					for(RGWAnalogPortType analog:rgwAnalogPortTypes) {
						for(DataflowType dataflowType:analog.getDataflowRef()) {
							dataanalogMap.put(dataflowType.getSrcGuid(), analog);
						}
					}
					
					
					this.updateProgress(i++, MAX_SIZE);
					/*------------------------------------------------------*/
					
					mapLocalPort2Type();
					
					mapRGWPort2Type();
					
					mapPort2SoftwareInstance();
					
					mapName2Hardware();
					
					mapPortDef2LogicDef();
					
					this.updateProgress(i++, MAX_SIZE);
					this.updateMessage("初始化数据已完成！");
					
					/*------------------------------------------------------*/
					long endTime1 = System.currentTimeMillis();
					System.out.println("从List映射到Map所用时间为："+(endTime1-startTime1));
					
					return null;
				}
				
			};
			
			return task;
		}
		
		
	}
}