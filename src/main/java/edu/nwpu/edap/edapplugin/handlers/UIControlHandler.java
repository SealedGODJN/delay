package edu.nwpu.edap.edapplugin.handlers;

import com.careri.as.workbench.api.exception.WorkbenchException;
import edu.nwpu.edap.edapplugin.log.OutputLog;
import edu.nwpu.edap.edapplugin.test.TestView;
import edu.nwpu.edap.edapplugin.ui.DelayAnalysisPage;
import edu.nwpu.edap.edapplugin.ui.TopMenuView;
import edu.nwpu.edap.edapplugin.ui.UploadFileView;
import javafx.scene.layout.BorderPane;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;

/**
 * control the view change 
 * @author zhang yifu
 * @version 1.0
 * @date 2021.12.22
 */
public class UIControlHandler {
	
	private BorderPane pane;
	private OutputLog outputLog;
	private TopMenuView topMenuView;

	@Inject
	public UIControlHandler() {
		outputLog = new OutputLog();
	}
	
	@PostConstruct
	public void postConstruct(BorderPane mainPane) throws IOException {
		
		pane = mainPane;
		
		
		showTopMenuView();
		showUploadFileView(this);
		
		outputLog.outputLogHead();
		
//		ConstructSceneHandler constructSceneHandler = new ConstructSceneHandler();
//		constructSceneHandler.execute();
	}
	
	/**
	 * show the menu of the plug-in
	 */
	public void showTopMenuView() {
		topMenuView = new TopMenuView();
		topMenuView.setMainPane(this);
		pane.setTop(topMenuView.getRoot());
	}
	
	/**
	 * show the upload page
	 */
	public void showUploadFileView(UIControlHandler mainView) {
		pane.setCenter(new UploadFileView(mainView));
	}
	
	/**
	 * show the data transmission page
	 * @throws IOException 
	 */
	public void showDataTransmissionView() throws IOException, WorkbenchException {
//		DataTransmissionView dataTransmissionView = new DataTransmissionView();
//		dataTransmissionView.setMainPane(this);
//		pane.setCenter(dataTransmissionView.getRoot());
		
		pane.setCenter(new DelayAnalysisPage());
	}
	
	public void showTestView() {
		TestView testView = new TestView();
		testView.setMainPane(this);
		pane.setCenter(testView.getRoot());
	}

	public BorderPane getPane() {
		return pane;
	}

	public OutputLog getOutputLog() {
		return outputLog;
	}

	public TopMenuView getTopMenuView() {
		return this.topMenuView;
	}
	
	
}
