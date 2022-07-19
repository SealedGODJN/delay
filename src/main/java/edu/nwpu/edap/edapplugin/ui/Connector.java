package edu.nwpu.edap.edapplugin.ui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
	
	/*
	 *工具显示
	 */
	public SimpleDoubleProperty helpX = new SimpleDoubleProperty();
	public SimpleDoubleProperty heply = new SimpleDoubleProperty();

	/**
	 * 构造函数
	 * 
	 * @param startAnchor 起始锚点
	 * @param endAnchor 终止锚点
	 */
    public Connector(Anchor startAnchor, Anchor endAnchor){
    	
    	this.setStroke(Color.BLACK);
    	this.setStrokeWidth(2);
    	
    	this.startXProperty().bind(startAnchor.helpXProperty().subtract(ANCHOR_WIDTH/2));
    	this.startYProperty().bind(startAnchor.helpYProperty().add(ANCHOR_HEIGHT/2));
        
    	this.endXProperty().bind(endAnchor.helpXProperty().add(ANCHOR_WIDTH/2));
    	this.endYProperty().bind(endAnchor.helpYProperty().add(ANCHOR_HEIGHT/2));
    	
//    	addListeners();
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
	/*
	 * 监听器
	 */
	public void addListeners() {
		this.setOnMousePressed(e -> {
			this.setStroke(Color.BLUE);
			this.setStrokeWidth(2.5);

		});
		// 鼠标释放
		this.setOnMouseReleased(t -> {
			this.setFill(Color.BLACK);
			this.setStrokeWidth(0);
		});
		
	}
}
