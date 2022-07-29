package edu.nwpu.edap.edapplugin.ui;

import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.utils.ZoomHandler;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;


public class LightArea extends StackPane {

	private ArrayList<RPMessage> msgs = new ArrayList<RPMessage>();

	private DelayAnalysisPage delayAnalysisPage;

	private BubbleArea bubbleArea;

	public LightArea() {
		
	}

	public LightArea(DelayAnalysisPage delayAnalysisPage, ArrayList<RPMessage> msgs) throws IOException {

		this.delayAnalysisPage = delayAnalysisPage;
		this.msgs = msgs;

		this.bubbleArea = new BubbleArea();

		this.delayAnalysisPage.generateRPPath(this.msgs, this.bubbleArea);

		this.getChildren().addAll(this.bubbleArea);

		// 内层Group，接收绘制图形
		Pane background = new Pane();
		final Group innerGroup = new Group(bubbleArea.getChildren());
		innerGroup.getChildren().add(background);
		final Group outerGroup = new Group(innerGroup);

		// 添加滚动事件
		final ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(outerGroup);
		scrollPane.addEventFilter(ScrollEvent.ANY, new ZoomHandler(innerGroup));

		this.getChildren().add(scrollPane);

		this.setStyle("-fx-background:#ffffff");
		this.delayAnalysisPage.setDividerPositions(0.35, 0.70);

	}
}
