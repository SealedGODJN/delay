package edu.nwpu.edap.edapplugin.Service.Impl;

import com.careri.as.businessmodel.model.VirtualLinkType;
import edu.nwpu.edap.edapplugin.Service.DataVerificationService;
import edu.nwpu.edap.edapplugin.bean.RPMessage;

public class DataVerificationServicImpl implements DataVerificationService {

	@Override
	public Boolean verifyVLMaxFrameSizeAndMTU(RPMessage rpMessage) {
		VirtualLinkType virtualLink = rpMessage.getAdnPart().getVirtualLink();
		if(virtualLink==null||virtualLink.getMaxFrameSize()!=(virtualLink.getMTU()+20)) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean verifySubVLBuffersizeAndVLParameter(RPMessage rpMessage) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean verifyVLBagAndVLParameter(RPMessage rpMessage) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean verifyComPortBufferSizeAndVLParameter(RPMessage rpMessage) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean verifyVLPathAndVLParameter(RPMessage rpMessage) {
		// TODO Auto-generated method stub
		return true;
	}

}
