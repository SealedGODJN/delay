package edu.nwpu.edap.edapplugin.Service;

import edu.nwpu.edap.edapplugin.bean.RPMessage;

/**
 * 获取ICD模型数据，接口信息，类别信息
 * @author zhang yifu
 * @version 1.0
 * @date 2021.1.20
 */
public interface LoadICDDataService {
	
	/**
	 * 匹配导入的RDIU区段接口文件
	 * @param rpMessage
	 */
	public void matchExternalRDIUSectionInterface(RPMessage rpMessage);
	
	/**
	 * 匹配外部导入的交换机相关信息
	 * @param rpMessage
	 */
	public void matchExternalSwitchConfig(RPMessage rpMessage);
	
	/**
	 * 匹配外部导入的GPM相关信息
	 * @param rpMessage
	 */
	public void matchExternalGPMAnalysis(RPMessage rpMessage);
	
	/**
	 * 匹配外部导入的数据传输场景配置文件相关信息
	 * @param rpMessage
	 * @return
	 */
	public void matchExternalSceneConfig(RPMessage rpMessage);
}
