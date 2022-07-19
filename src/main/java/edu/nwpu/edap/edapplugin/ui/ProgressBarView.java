package edu.nwpu.edap.edapplugin.ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ProgressBarView {

	private static ProgressBarView uniqueInstance;
	
	private Node node;
	private ProgressBar progressBar;
	private Label progressLabel;
	private Label progressMsg;
	
	public static ProgressBarView getInstance() {
		if(uniqueInstance==null) {
			uniqueInstance = new ProgressBarView();
		}
		return uniqueInstance;
	}
	
	public Node getRoot() {
		return node;
	}
	
	public void execute() {
		GridPane grid = new GridPane();
//		BorderPane borderPane = new BorderPane();
		HBox hBox = new HBox();
		progressBar = new ProgressBar(0);
		progressLabel = new Label("0 %");
		progressMsg = new Label("请稍等，正在进行初始化");
		hBox.getChildren().addAll(progressBar,progressLabel);

		progressBar.setMinWidth(300);
		progressBar.setMinHeight(20);
		
		hBox.setSpacing(10);
		
		grid.setHgap(10);grid.setVgap(5);
		grid.setPadding(new Insets(40,0,0,50));
		grid.add(progressBar, 0, 0);
		grid.add(progressLabel, 1, 0);
		grid.add(progressMsg, 2, 0);
		
//		borderPane.setCenter(hBox);
		node = grid;
	}
	
	public void setProgress(double value) {
		progressBar.setProgress(value);
		progressLabel.setText(String.valueOf(Math.ceil(value*100)) +" %");
	}
	
	public void setMessage(String msg) {
		progressMsg.setText(msg);
	}
}
