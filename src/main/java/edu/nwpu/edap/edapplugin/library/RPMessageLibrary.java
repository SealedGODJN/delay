package edu.nwpu.edap.edapplugin.library;

import edu.nwpu.edap.edapplugin.bean.RPMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RPMessageLibrary {
	
	private static List<RPMessage> rpMessages;
	
	private static List<RPMessage> duplicateRPMessage;
	
	private static Map<String, RPMessage> rpMessageMapByRPGuid = new HashMap<String, RPMessage>();
	
	private static Map<String, List<RPMessage>> rpMessagesMapByDPGuid = new HashMap<String, List<RPMessage>>();
	
	/**
	 * 返回RPMessage的List
	 * @return
	 */
	public static List<RPMessage> getRpMessages(){
		return rpMessages;
	}
	
	/**
	 * 往RPMessage列表中添加RPMessage
	 * @param rpMessage
	 */
	public static void addRPMessage(RPMessage rpMessage) {
		if(rpMessages==null) {
			rpMessages = new ArrayList<RPMessage>();
			return;
		}
		rpMessages.add(rpMessage);
		//放入rpMessageMapByRPGuid中
		if(rpMessageMapByRPGuid.get(rpMessage.getRpGuid())==null) {
			rpMessageMapByRPGuid.put(rpMessage.getRpGuid(), rpMessage);
		}else {
			addDuplicateMessage(rpMessage);
		}
		//放入rpMessageMapByDPGuid中
		List<RPMessage> rpMessageList = new ArrayList<RPMessage>();
		if(rpMessagesMapByDPGuid.get(rpMessage.getPubGuid())!=null) {
			rpMessageList = rpMessagesMapByDPGuid.get(rpMessage.getPubGuid());
		}
		rpMessageList.add(rpMessage);
		rpMessagesMapByDPGuid.put(rpMessage.getPubGuid(), rpMessageList);
	}
	
	/**
	 * 根据RPGuid获得对应的RPMessage
	 * @param rpGuid
	 * @return
	 */
	public static RPMessage getRpMessageByRPGuid(String rpGuid) {
		if(rpGuid==null) {
			return null;
		}
		return rpMessageMapByRPGuid.get(rpGuid);
	}
	
	/**
	 * 根据DPGuid获取对应的RPMessage类
	 * @param dpGuid
	 * @return
	 */
	public static List<RPMessage> getRpMessagesByDPGuid(String dpGuid){
		if(dpGuid==null) {
			return null;
		}
		return rpMessagesMapByDPGuid.get(dpGuid);
	}
	
	/**
	 * 返回Library中RPMessage的数量
	 * @return
	 */
	public static int size() {
		return rpMessages.size();
	}
	
	/**
	 * 返回Library中重复RPMessage的数量
	 * @return
	 */
	public static int duplicateSize() {
		return duplicateRPMessage.size();
	}
	
	/**
	 * 添加重复的RPMessage到其中
	 * @param rpMessage
	 */
	private static void addDuplicateMessage(RPMessage rpMessage) {
		if(duplicateRPMessage==null) {
			duplicateRPMessage = new ArrayList<RPMessage>();
		}
		duplicateRPMessage.add(rpMessage);
	}
}
