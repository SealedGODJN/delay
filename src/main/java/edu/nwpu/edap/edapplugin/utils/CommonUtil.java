package edu.nwpu.edap.edapplugin.utils;

import java.io.File;

public class CommonUtil {

	/**
	 * 根据文件路径名称获得文件名称
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		File file = new File(filePath);
		return file.getName();
	}
	
	/**
	 * 判断接口是否为A664类型，如果是则返回true
	 * @param portType
	 * @return
	 */
	public static boolean isA664TypePort(String portType) {
		if(portType==null) {
			return false;
		}
		return portType.equals("HFSamplingPort")||portType.equals("HFQueuingPort")||portType.equals("A653SamplingPort")||portType.equals("A653QueuingPort");
	}
	
	/**
	 * 判断App是否为GPM类型，如果是则返回true
	 * @param appType
	 * @return
	 */
	public static boolean isGPMApp(String appType) {
		if(appType==null) {
			return false;
		}
		return appType.equals("A653ApplicationComponent");
	}
	
	/**
	 * 将端口类型转化为数据传输场景配置文件中的设备节点类型
	 * @param portType
	 * @return
	 */
	public static String transferPortType2DeviceNodeType(String portType) {
		switch (portType) {
		case "A653SamplingPort":
			return "A653";
		case "A653QueuingPort":
			return "A653";
		case "HFSamplingPort":
			return "A664";
		case "HFQueuingPort":
			return "A664";
		case "AnalogPort":
			return "Analog";
		case "A429Port":
			return "A429";
		case "CANPort":
			return "A825";
		default:
			return "Non";
		}
	}
}
