package edu.nwpu.edap.edapplugin.library;

import edu.nwpu.edap.edapplugin.bean.gpm.RxVLs;
import edu.nwpu.edap.edapplugin.bean.gpm.TxVL;
import edu.nwpu.edap.edapplugin.bean.gpm.TxVLs;
import org.apache.commons.collections4.map.HashedMap;

import java.util.Map;

public class EndSystemDALibrary {

	private static Map<String, RxVLs> rxVLsMapByGPMName;
	
	private static Map<String, TxVLs> txVLsMapByGPMName;
	
	private static Map<String,Map<Integer, TxVL>> txVLMapByGPMNameAndID;

	/**
	 * 根据GPMName获得RxVLs
	 * @param name
	 * @return
	 */
	public static RxVLs getRxVLsByGPMName(String name) {
		if(name==null) {
			return null;
		}
		return rxVLsMapByGPMName.get(name);
	}
	
	/**
	 * 根据GPMName获得TxVLs
	 * @param name
	 * @return
	 */
	public static TxVLs getTxVLsByGPMName(String name) {
		if(name==null) {
			return null;
		}
		return txVLsMapByGPMName.get(name);
	}
	
	/**
	 * 根据GPMName和TxVL的ID获得对应的TxVL
	 * @param name
	 * @param id
	 * @return
	 */
	public static TxVL getTxVLByGPMNameAndID(String name,int id) {
		if(name==null) {
			return null;
		}
		Map<Integer,TxVL> map = txVLMapByGPMNameAndID.get(name);
		if(map==null) {
			return null;
		}
		return map.get(id);
	}
	
	/**
	 * 添加RxVLs相关内容
	 * @param name
	 * @param rxVLs
	 */
	public static void addRxVLs(String name,RxVLs rxVLs) {
		if(name==null||rxVLs==null) {
			return;
		}
		if(rxVLsMapByGPMName==null) {
			rxVLsMapByGPMName = new HashedMap<String, RxVLs>();
		}
		rxVLsMapByGPMName.put(name, rxVLs);
	}
	
	/**
	 * 添加TxVLs相关内容
	 * @param name
	 * @param txVLs
	 */
	public static void addTxVLs(String name,TxVLs txVLs) {
		if(name==null||txVLs==null) {
			return;
		}
		if(txVLMapByGPMNameAndID==null) {
			txVLMapByGPMNameAndID = new HashedMap<String, Map<Integer,TxVL>>();
		}
		if(txVLsMapByGPMName==null) {
			txVLsMapByGPMName = new HashedMap<String, TxVLs>();
		}
		txVLsMapByGPMName.put(name, txVLs);
		Map<Integer, TxVL> map = new HashedMap<Integer, TxVL>();
		for(TxVL txVL:txVLs.getTxVLs()) {
			map.put(txVL.getID(), txVL);
		}
		txVLMapByGPMNameAndID.put(name, map);
	}
	
}
