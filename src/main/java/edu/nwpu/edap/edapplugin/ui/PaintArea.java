package edu.nwpu.edap.edapplugin.ui;

import java.io.IOException;
import java.util.ArrayList;

import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.utils.ZoomHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * 绘制区域：网络拓扑图的容器类
 * 
 * @author Wren
 * @date 2022年2月25日
 *
 */
public class PaintArea extends StackPane {

	private ArrayList<RPMessage> msgs = new ArrayList<RPMessage>();

	private DelayAnalysisPage delayAnalysisPage;

	private BubbleArea bubbleArea;

	public PaintArea() {

		Label tip = new Label("正在加载ICD数据，请稍等...\n");
		tip.setStyle("-fx-font-size:20;");
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(tip);
		this.setStyle("-fx-background:#ffffff");
	}

	public PaintArea(String text) {

		Label tip = new Label(text);
		tip.setStyle("-fx-font-size:16;-fx-text-color:red;");
		tip.setPadding(new Insets(50));
		tip.setMaxWidth(1000);
		tip.setMinHeight(200);
		tip.setWrapText(true);
		tip.setAlignment(Pos.CENTER_LEFT);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(tip);
		this.setStyle("-fx-background:#ffffff;");
	}

	public PaintArea(DelayAnalysisPage delayAnalysisPage, ArrayList<RPMessage> msgs) throws IOException {

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
