package edu.nwpu.edap.edapplugin.library;

import java.util.ArrayList;
import java.util.List;

public class GlobalInfoLibrary {

	private static List<String> infoMsgs;
	
	private static List<String> warnMsgs;
	
	private static List<String> errorMsgs;
	
	/**
	 * 添加info消息
	 * @param msg
	 */
	public static void addInfoMsg(String msg) {
		if(infoMsgs==null) {
			infoMsgs = new ArrayList<String>();
		}
		infoMsgs.add(msg);
	}
	
	/**
	 * 添加warn消息
	 * @param msg
	 */
	public static void addWarnMsg(String msg) {
		if(warnMsgs==null) {
			warnMsgs = new ArrayList<String>();
		}
		warnMsgs.add(msg);
	}
	
	/**
	 * 添加error消息
	 * @param msg
	 */
	public static void addErrorMsg(String msg) {
		if(errorMsgs==null) {
			errorMsgs = new ArrayList<String>();
		}
		errorMsgs.add(msg);
	}
	
	/**
	 * 获取info消息数量
	 * @return
	 */
	public static int getInfoMsgNum() {
		if(infoMsgs==null) {
			return 0;
		}
		return infoMsgs.size();
	}
	
	/**
	 * 获取warn消息数量
	 * @return
	 */
	public static int getWarnMsgNum() {
		if(warnMsgs==null) {
			return 0;
		}
		return warnMsgs.size();
	}
	
	/**
	 * 获取error消息数量
	 * @return
	 */
	public static int getErrorMsgNum() {
		if(errorMsgs==null) {
			return 0;
		}
		return errorMsgs.size();
	}
}
