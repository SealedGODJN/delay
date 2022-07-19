package edu.nwpu.edap.edapplugin.ui;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.nwpu.edap.edapplugin.handlers.ConstructSceneHandler;
import edu.nwpu.edap.edapplugin.handlers.ProgressBarHandler;
import edu.nwpu.edap.edapplugin.handlers.UIControlHandler;
import edu.nwpu.edap.edapplugin.report.GenerateReport;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * show the top menu of the plugin
 * @author zhang yifu
 * @version 1.0
 * @date 2021.12.22
 */
public class TopMenuView {

	private Node node;
	private UIControlHandler mainView;
	
	public Button startBtn;
	public Button analysisBtn;
	public Button suspendBtn;
	public Button exportBtn;
	
	public Node getRoot() {
		return node;
	}
	
	public TopMenuView() {
		execute();
	}
	
	public void setMainPane(UIControlHandler uiControlHandler) {
		this.mainView = uiControlHandler;
	}
	
	private void execute() {
		ToolBar toolBar = new ToolBar();
		toolBar.setPrefHeight(40);
		
		startBtn = new Button("导入ICD数据");
		analysisBtn = new Button("分析ICD数据");
		suspendBtn = new Button("终止时延分析");
		exportBtn = new Button("导出分析报告");
		
		toolBar.getItems().addAll(startBtn,analysisBtn,suspendBtn,exportBtn);

		node = toolBar;
		
		addListeners();
	}
	
	private void addListeners() {
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("开始进行时延分析");
				try {
					loadDataTransmissionView();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ConstructSceneHandler constructSceneHandler = new ConstructSceneHandler();
				constructSceneHandler.execute();
			}
		});
		
		analysisBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("生成延迟分析报告");
				GenerateReport generateReport = new GenerateReport();
			}
		});
		
		suspendBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("结束时延分析");
				loadUploadFileView();
				
				ProgressBarHandler.part.setVisible(false);
			}
		});
		
		exportBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(extFilter);

				Stage s = new Stage();
				File file = fileChooser.showSaveDialog(s);

				if (file == null) {
					System.out.println("文件保存操作取消");
					return;
				} else if (file.exists()) {// 文件已存在，则删除覆盖文件
					file.delete();
				}

				String text = "导出文件内容示例";
				byte b[] = text.getBytes();
				String outputFileName = file.getPath();
				FileOutputStream out;
				try {
					out = new FileOutputStream(outputFileName);
					out.write(b);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				String exportFilePath = file.getAbsolutePath();
				System.out.println("导出文件的路径" + exportFilePath);

			}
		});
		
	}
	
	private void loadDataTransmissionView() throws IOException {
		mainView.showDataTransmissionView();
	}
	
	private void loadUploadFileView() {
		mainView.showUploadFileView(mainView);
	}

	public Button getStartBtn() {
		return startBtn;
	}

	public void setStartBtn(Button startBtn) {
		this.startBtn = startBtn;
	}
	
	
	
	
}
