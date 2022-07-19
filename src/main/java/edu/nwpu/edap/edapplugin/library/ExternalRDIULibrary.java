package edu.nwpu.edap.edapplugin.library;

import edu.nwpu.edap.edapplugin.bean.external.ExternalRDIU;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExternalRDIULibrary {

	private static List<ExternalRDIU> externalRDIUs;
	
	private static Map<String, ExternalRDIU> externalRDIUMapByRPGuid;
	
	/**
	 * 查询是否存在以Guid为RPGuid的ExternalRDIU
	 * @param Guid
	 * @return
	 */
	public static boolean isExist(String Guid) {
		if(Guid==null||externalRDIUMapByRPGuid.get(Guid)==null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 根据RPGuid获取ExternalRDIU
	 * @param Guid
	 * @return
	 */
	public static ExternalRDIU getExternalRDIUByRPGuid(String Guid) {
		if(Guid==null) {
			return null;
		}
		return externalRDIUMapByRPGuid.get(Guid);
	}
	
	/**
	 * 获取所有外部RDIU的列表
	 * @return
	 */
	public static List<ExternalRDIU> getExternalRDIUs(){
		return externalRDIUs;
	}

	/**
	 * 设置ExternalRDIUs
	 * @param externalRDIUs
	 */
	public static void setExternalRDIUs(List<ExternalRDIU> externalRDIUs) {
		ExternalRDIULibrary.externalRDIUs = externalRDIUs;
		externalRDIUMapByRPGuid = externalRDIUs.stream().collect(Collectors.toMap(ExternalRDIU::getSubParameterGuid, Function.identity(),(x1,x2)->x2));
	}
	
	/**
	 * 查询Map的大小
	 * @return
	 */
	public static int size() {
		if(externalRDIUMapByRPGuid==null) {
			return 0;
		}
		return externalRDIUMapByRPGuid.size();
	}
	
}
