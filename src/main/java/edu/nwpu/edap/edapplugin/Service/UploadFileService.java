package edu.nwpu.edap.edapplugin.Service;

import javafx.util.Pair;

/**
 * 上传文件服务
 * @author zhang yifu
 *
 */
public interface UploadFileService {

	/**
	 * 上传RDIU区段接口文件
	 * @param filePath
	 * @return
	 */
	public Pair<Boolean, String> uploadExternalRDIUConfig(String filePaht);
	
	/**
	 * 上传A664区段接口文件交换机部分
	 * @param filePath
	 * @return
	 */
	public Pair<Boolean, String> uploadSwitchConfig(String filePath);
	
	/**
	 * 上传A664区段接口文件GPM部分
	 * @param filePath
	 * @return
	 */
	public Pair<Boolean,String> uploadGPMReport(String filePath);
	
	/**
	 * 上传数据传输场景配置文件
	 * @param filePath
	 * @return
	 */
	public Pair<Boolean, String> uploadSceneConfig(String filePath);
}
