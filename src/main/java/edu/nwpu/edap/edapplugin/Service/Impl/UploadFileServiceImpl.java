package edu.nwpu.edap.edapplugin.Service.Impl;

import edu.nwpu.edap.edapplugin.Service.UploadFileService;
import edu.nwpu.edap.edapplugin.bean.gpm.EndSystemDeterministicAnalysis;
import edu.nwpu.edap.edapplugin.bean.switchconfig.SwitchConfig;
import edu.nwpu.edap.edapplugin.library.EndSystemDALibrary;
import edu.nwpu.edap.edapplugin.library.SwitchConfigLibrary;
import edu.nwpu.edap.edapplugin.utils.CommonUtil;
import edu.nwpu.edap.edapplugin.utils.ImportXmlUtils;
import javafx.util.Pair;

public class UploadFileServiceImpl implements UploadFileService{

	@Override
	public Pair<Boolean, String> uploadExternalRDIUConfig(String filePaht) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<Boolean, String> uploadSwitchConfig(String filePath) {
		SwitchConfig switchConfig = ImportXmlUtils.convertXmlFileToObject(SwitchConfig.class, filePath);
		SwitchConfigLibrary.setSwitchs(switchConfig.getSwitches());
		SwitchConfigLibrary.setEndSystems(switchConfig.getEndSystems());
		SwitchConfigLibrary.setVls(switchConfig.getVLs());
		SwitchConfigLibrary.setTrafficPolicingAccounts(switchConfig.getTrafficPolicingAccounts());
		SwitchConfigLibrary.setXsdVersion(switchConfig.getXsdVersion());
		SwitchConfigLibrary.setInputCRC32(switchConfig.getInputCRC32());
		SwitchConfigLibrary.setSwitchLoadPN(switchConfig.getSwitchLoadPN());
		return new Pair<Boolean, String>(true, "上传SwitchConfig成功");
	}

	@Override
	public Pair<Boolean, String> uploadGPMReport(String filePath) {
		EndSystemDeterministicAnalysis endSystemDeterministicAnalysis = ImportXmlUtils.convertXmlFileToObject(EndSystemDeterministicAnalysis.class, filePath);
		String fileName = CommonUtil.getFileName(filePath);
		String gpmName = fileName.substring(0,fileName.lastIndexOf("_"));
		EndSystemDALibrary.addRxVLs(gpmName, endSystemDeterministicAnalysis.getRxVls());
		EndSystemDALibrary.addTxVLs(gpmName, endSystemDeterministicAnalysis.getTxVls());
		return new Pair<Boolean, String>(true, "上传"+fileName+"成功");
	}

	@Override
	public Pair<Boolean, String> uploadSceneConfig(String filePath) {
		ImportXmlUtils.importSceneConfigFile(filePath);
		return new Pair<Boolean, String>(true, "上传SceneConfig成功");
	}

}
