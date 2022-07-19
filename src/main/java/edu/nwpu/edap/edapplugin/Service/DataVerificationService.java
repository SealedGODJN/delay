package edu.nwpu.edap.edapplugin.Service;

import edu.nwpu.edap.edapplugin.bean.RPMessage;

public interface DataVerificationService {

	/**
	 * 需求-072 校验VL中的MaxFrameSize和MTU匹配，MaxFrameSize=MTU+20
	 * @param rpMessage
	 * @return
	 */
	public Boolean verifyVLMaxFrameSizeAndMTU(RPMessage rpMessage);
	
	/**
	 * 需求-073
	 * @param rpMessage
	 * @return
	 */
	public Boolean verifySubVLBuffersizeAndVLParameter(RPMessage rpMessage);
	
	/**
	 * 需求-074
	 * @param rpMessage
	 * @return
	 */
	public Boolean verifyVLBagAndVLParameter(RPMessage rpMessage);
	
	/**
	 * 需求-075
	 * @param rpMessage
	 * @return
	 */
	public Boolean verifyComPortBufferSizeAndVLParameter(RPMessage rpMessage);
	
	/**
	 * 需求-076
	 * @param rpMessage
	 * @return
	 */
	public Boolean verifyVLPathAndVLParameter(RPMessage rpMessage);
	
}
