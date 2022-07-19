package edu.nwpu.edap.edapplugin.library;

import edu.nwpu.edap.edapplugin.bean.switchconfig.*;
import org.apache.commons.collections4.map.HashedMap;

import java.util.List;
import java.util.Map;

public class SwitchConfigLibrary {

	private static String xsdVersion;
	
	private static String inputCRC32;
	
	private static String switchLoadPN;
	
	private static List<ExSwitch> switchs;
	
	private static List<ExEndSystem> endSystems;
	
	private static List<ExVL> vls;
	
	private static List<TPAccount> trafficPolicingAccounts;
	
	private static Map<String, ExVL> exVLMapByGuid;
	
	private static Map<String, ExVLPath> exVLPathMapByGuid;

	public static String getXsdVersion() {
		return xsdVersion;
	}

	public static void setXsdVersion(String xsdVersion) {
		SwitchConfigLibrary.xsdVersion = xsdVersion;
	}

	public static String getInputCRC32() {
		return inputCRC32;
	}

	public static void setInputCRC32(String inputCRC32) {
		SwitchConfigLibrary.inputCRC32 = inputCRC32;
	}

	public static String getSwitchLoadPN() {
		return switchLoadPN;
	}

	public static void setSwitchLoadPN(String switchLoadPN) {
		SwitchConfigLibrary.switchLoadPN = switchLoadPN;
	}

	public static List<ExSwitch> getSwitchs() {
		return switchs;
	}

	public static void setSwitchs(List<ExSwitch> switchs) {
		SwitchConfigLibrary.switchs = switchs;
	}

	public static List<ExEndSystem> getEndSystems() {
		return endSystems;
	}

	public static void setEndSystems(List<ExEndSystem> endSystems) {
		SwitchConfigLibrary.endSystems = endSystems;
	}

	public static List<ExVL> getVls() {
		return vls;
	}

	public static void setVls(List<ExVL> vls) {
		SwitchConfigLibrary.vls = vls;
		exVLMapByGuid = new HashedMap<String, ExVL>();
		exVLPathMapByGuid = new HashedMap<String, ExVLPath>();
		for(ExVL vl:vls) {
			exVLMapByGuid.put(vl.getGuid(), vl);
			for(DestinationEs destinationEs:vl.getDestinationEsCollection()) {
				for(ExVLPath exVLPath:destinationEs.getVLPaths()) {
					exVLPathMapByGuid.put(exVLPath.getGuid(), exVLPath);
				}
			}
		}
	}

	public static List<TPAccount> getTrafficPolicingAccounts() {
		return trafficPolicingAccounts;
	}

	public static void setTrafficPolicingAccounts(List<TPAccount> trafficPolicingAccounts) {
		SwitchConfigLibrary.trafficPolicingAccounts = trafficPolicingAccounts;
	}
	
	public static ExVL getExVLByVLGuid(String Guid) {
		if(Guid==null) {
			return null;
		}
		return exVLMapByGuid.get(Guid);
	}
	
	public static ExVLPath getExVLPathByVLPathGuid(String Guid) {
		if(Guid==null) {
			return null;
		}
		return exVLPathMapByGuid.get(Guid);
	}
	
	public static int size() {
		if(exVLPathMapByGuid==null) {
			return 0;
		}
		return exVLPathMapByGuid.size();
	}

}
