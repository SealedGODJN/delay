package edu.nwpu.edap.edapplugin.Service.Impl;

import edu.nwpu.edap.edapplugin.Service.ProgressService;
import edu.nwpu.edap.edapplugin.bean.BottomPartValue;
import edu.nwpu.edap.edapplugin.handlers.ProgressBarHandler;
import edu.nwpu.edap.edapplugin.ui.ProgressBarView;
import javafx.scene.layout.BorderPane;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class ProgressServiceImpl implements ProgressService{
	
	private ProgressBarView progressBarView;

	@Override
	public void showProgress() {
		
		BottomPartValue value = BottomPartValue.getInstance();
		ProgressBarHandler progressBarHandler = new ProgressBarHandler();
		EPartService partService = value.getPartService();
		MApplication application = value.getApplication();
		EModelService modelService = value.getModelService();
		
		progressBarHandler.showProgressBar(partService, application, modelService);
	}
	
	@Inject
	public ProgressServiceImpl() {
		
	}
	
	@PostConstruct
	public void PostConstruct(BorderPane mainPane) {
		progressBarView = ProgressBarView.getInstance();
		progressBarView.execute();
		mainPane.setCenter(progressBarView.getRoot());
	}
	
	

	@Override
	public void setProgress(double value) {
		progressBarView = ProgressBarView.getInstance();
		progressBarView.setProgress(value);
	}
	
	@Override
	public void setMessage(String msg) {
		progressBarView = ProgressBarView.getInstance();
		progressBarView.setMessage(msg);
	}

	@Override
	public void closeProgress() {
		System.out.println("123445");
	}
	
	
}
