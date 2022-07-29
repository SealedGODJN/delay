package edu.nwpu.edap.edapplugin.ui;

import java.awt.TextField;
import java.util.ArrayList;

import edu.nwpu.edap.edapplugin.bean.InfoTableData;
import edu.nwpu.edap.edapplugin.bean.RPMessage;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import jdk.internal.org.objectweb.asm.Label;
import sun.net.idn.Punycode;


/**
 * 传输线视图类
 *
 * @author Wren
 * @date 2022年2月25日
 *
 */
public class Connector extends Line {


	private final static int ANCHOR_WIDTH = 10;

	private final static int ANCHOR_HEIGHT = 10;

	private Anchor startAnchor;
	private Anchor endAnchor;

	/**
	 * 2022.6.30
	 * 新建参数msgs【用于：高亮传输线+延迟显示】
	 */
	ArrayList<RPMessage> msgs = new ArrayList<RPMessage>(); //rpMessage消息序列


	/**
	 * 构造函数
	 *
	 * @param startAnchor 起始锚点
	 * @param endAnchor 终止锚点
	 */
	public Connector(Anchor startAnchor, Anchor endAnchor){
		/**
		 * 2022.7.13 高亮传输线
		 */
		this.startAnchor = startAnchor;
		this.endAnchor = endAnchor;

		this.setStroke(Color.BLACK);
		this.setStrokeWidth(2);

		this.startXProperty().bind(startAnchor.helpXProperty().subtract(ANCHOR_WIDTH/2));
		this.startYProperty().bind(startAnchor.helpYProperty().add(ANCHOR_HEIGHT/2));

		this.endXProperty().bind(endAnchor.helpXProperty().add(ANCHOR_WIDTH/2));
		this.endYProperty().bind(endAnchor.helpYProperty().add(ANCHOR_HEIGHT/2));
	}

	/**
	 * 高亮传输线
	 *
	 * @param startAnchor
	 * @param endAnchor
	 * @param msgs
	 */
	public Connector(Anchor startAnchor, Anchor endAnchor, ArrayList<RPMessage> msgs){
		/**
		 * 2022.7.13 高亮传输线
		 */
		this.startAnchor = startAnchor;
		this.endAnchor = endAnchor;

		this.msgs = msgs; // 正在分析的rp消息

		this.setStroke(Color.BLACK);
		this.setStrokeWidth(2.5);

		this.startXProperty().bind(startAnchor.helpXProperty().subtract(ANCHOR_WIDTH/2));
		this.startYProperty().bind(startAnchor.helpYProperty().add(ANCHOR_HEIGHT/2));

		this.endXProperty().bind(endAnchor.helpXProperty().add(ANCHOR_WIDTH/2));
		this.endYProperty().bind(endAnchor.helpYProperty().add(ANCHOR_HEIGHT/2));

		addListeners();
	}

	/*
	 * 监听器
	 * 点击传输线显示线上rp消息属性
	 */
	public void addListeners() {
		// 鼠标按压
		this.setOnMousePressed(e -> {
//    		TextField delayField = new TextField("Latency");
//    		this.setStroke(Color.GREEN);
//    		this.setStrokeWidth(3.5);

		});

		// 鼠标释放
		this.setOnMouseReleased(t -> {

//    	    this.setStroke(Color.BLACK);
//    		this.setStrokeWidth(2.5);
		});
	}

	public Anchor getStartAnchor() {
		return startAnchor;
	}

	public void setStartAnchor(Anchor startAnchor) {
		this.startAnchor = startAnchor;
	}

	public Anchor getEndAnchor() {
		return endAnchor;
	}

	public void setEndAnchor(Anchor endAnchor) {
		this.endAnchor = endAnchor;
	}

	@Override
	public String toString() {
		return "Connector [startAnchor=" + startAnchor.getPortNode().getName() + ", endAnchor=" + endAnchor.getPortNode().getName() + "]";
	}
}
