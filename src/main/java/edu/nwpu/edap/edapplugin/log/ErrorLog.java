package edu.nwpu.edap.edapplugin.log;

/**
 * 记录错误和警告个数
 * @author zhang yifu
 * @version 1.0
 * @date 2021.12.30
 */
public class ErrorLog {

	private static ErrorLog uniqueErrorLog;
	
	private int errorNum = 0;
	private int warnNum = 0;
	
	public static ErrorLog getInstance() {
		if(uniqueErrorLog == null) {
			uniqueErrorLog = new ErrorLog();
		}
		return uniqueErrorLog;
	}

	public int getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}

	public int getWarnNum() {
		return warnNum;
	}

	public void setWarnNum(int warnNum) {
		this.warnNum = warnNum;
	}

	@Override
	public String toString() {
		return "ErrorLog [errorNum=" + errorNum + ", warnNum=" + warnNum + "]";
	}
}
