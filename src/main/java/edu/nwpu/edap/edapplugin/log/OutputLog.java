package edu.nwpu.edap.edapplugin.log;

import com.careri.as.icd.WorkbenchContext;
import com.careri.as.workbench.api.misc.LogService;
import com.careri.as.workbench.api.misc.impl.LogServiceImpl;

public class OutputLog {

	private LogService logService;
	private WorkbenchContext workbenchContext;
	
	public OutputLog() {
		this.logService = new LogServiceImpl("EDAP");
	}
	
	/**
	 * 文件头项目运行环境部分
	 */
	public void outputLogHead() {
		this.logService.info("End-to-end Eelay Analysis plug-in is starting");
		this.logService.info("系统名称为："+getOSName()+"  |  系统版本为："+getOSVersion());
		this.logService.info("IMA模型数据平台版本为："+getWorkbenchVersion() + "  |  端到端延迟分析插件版本为："+getPluginVersion());
	}
	
	
	public void doWork() {
		this.logService.info("Start my task");//普通消息
		
		this.logService.warn("Something is wrong, but not critical");
		
		this.logService.error("Critical error, not able to continue");
		
		this.logService.debug("Debug message");
	}
	
	/**
	 * 获取当前操作系统名称
	 * @return name
	 */
	private String getOSName() {
		return System.getProperty("os.name");
	}
	
	/**
	 * 获取当前操作系统版本
	 * @return version
	 */
	private String getOSVersion() {
		return System.getProperty("os.version");
	}
	
	/**
	 * 获取当前插件版本
	 * @return version
	 */
	private String getPluginVersion() {
		return "1.0.0";
	}
	
	/**
	 * 获取当前Workbench平台版本
	 * @return version
	 */
	private String getWorkbenchVersion() {
		workbenchContext = WorkbenchContext.getInstance();
		return workbenchContext.getVersion();
	}
}
