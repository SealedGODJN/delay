package edu.nwpu.edap.edapplugin.Service;

/**
 * open an stage and show the progress
 * @author zhang yifu
 * @version 1.0
 * @date 2022.1.10
 */
public interface ProgressService {
	
	public void showProgress();
	
	public void setProgress(double value);
	
	public void setMessage(String msg);
	
	public void closeProgress();
}
