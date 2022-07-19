package edu.nwpu.edap.edapplugin.log;

import com.careri.as.workbench.api.misc.LogService;
import com.careri.as.workbench.api.task.BaseTask;
import com.careri.as.workbench.api.task.BaseTaskService;
import com.careri.as.workbench.api.task.PluginTask;

public class TaskLog extends BaseTask implements PluginTask{
	
	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseTaskService getTaskService() {
		// TODO Auto-generated method stub
		return null;
	}
}
