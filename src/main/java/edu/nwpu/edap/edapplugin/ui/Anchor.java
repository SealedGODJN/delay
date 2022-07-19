package edu.nwpu.edap.edapplugin.ui;

import java.util.ArrayList;

import edu.nwpu.edap.edapplugin.bean.InfoTableData;
import edu.nwpu.edap.edapplugin.bean.hardware.Hardware;
import edu.nwpu.edap.edapplugin.bean.port.PortNode;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * 锚点视图类：用来表示端口
 * 
 * @author Wren
 * @date 2022年2月25日
 *
 */
public class Anchor extends Rectangle {

	private DelayAnalysisPage root;
	private BubbleArea bubbleArea;
	private Bubble parent; // 父节点
	private Anchor preAnchor = null;
	private Anchor nextAnchor = null;
	private String direct = "";

	/**
	 * 工具属性
	 */
	public SimpleDoubleProperty helpX = new SimpleDoubleProperty(); // 记录并更新左上角X值
	public SimpleDoubleProperty helpY = new SimpleDoubleProperty(); // 记录并更新左上角Y值

	private int order = 0; // 在父节点中一侧结点列表中的位置
	private boolean isSelected; // 标记是否被选中

	/**
	 * 数据属性
	 */
	private int portSwNode = -1; // 交换机端口数据
	private Hardware hardware = null; // 交换机硬件
	private PortNode portNode = null; // 其他硬件端口数据

	/**
	 * 无参构造函数
	 */
	public Anchor() {

	}

	/**
	 * 构造函数 - 交换机端口
	 * 
	 * @param portSwNode
	 * @param root
	 * @param bubbleArea
	 * @param centerX
	 * @param centerY
	 * @param width
	 * @param height
	 */
	public Anchor(int portSwNode, DelayAnalysisPage root, BubbleArea bubbleArea, double centerX, double centerY,
			double width, double height, Hardware hardware) {

		super(width, height);
		
		this.hardware = hardware;

		this.portSwNode = portSwNode;
		this.root = root;
		this.bubbleArea = bubbleArea;
		this.setLayoutY(centerY);
		this.setLayoutX(centerX);

		addListeners();
	}

	/**
	 * 构造函数 - 其他硬件端口
	 * 
	 * @param portNode
	 * @param root
	 * @param bubbleArea
	 * @param centerX
	 * @param centerY
	 * @param width
	 * @param height
	 */
	public Anchor(PortNode portNode, DelayAnalysisPage root, BubbleArea bubbleArea, double centerX, double centerY,
			double width, double height) {

		super(width, height);

		this.portNode = portNode;
		this.root = root;
		this.bubbleArea = bubbleArea;
		this.setLayoutY(centerY);
		this.setLayoutX(centerX);

		addListeners();
	}

	/**
	 * 监听器
	 */
	public void addListeners() {

		// 鼠标按压
		this.setOnMousePressed(e -> {
			this.setStroke(Color.RED);
			this.setStrokeWidth(2.5);
			this.setFill(Color.YELLOW);
			this.toFront();

		});

		// 鼠标释放
		this.setOnMouseReleased(t -> {
			this.setFill(Color.BLACK);
			this.setStrokeWidth(0);
		});

		// 鼠标点击
		this.setOnMouseClicked(mouseEvent -> {

			if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {

				// 单击---------------------
				if (mouseEvent.getClickCount() == 1) {

					/**
					 * 更新选中节点视图
					 */
					Node s = this.bubbleArea.getSelectedNode(); // 得到当前被选中的节点

					if (s == null) {
						// 当前没有节点被选中
						this.setSelected(true);
						this.bubbleArea.setSelectedNode(this);
					} else {
						// 设置被点击的节点为选中状态
						if (this.bubbleArea.getSelectedNodeType().equals("Bubble")) {
							((Bubble) s).setSelected(false);
							this.bubbleArea.toggleSelected((Bubble) s, "Bubble");
						} else if (this.bubbleArea.getSelectedNodeType().equals("Anchor")) {
							((Anchor) s).setSelected(false);
							this.bubbleArea.toggleSelected((Anchor) s, "Anchor");
						}

						this.setSelected(true);
						this.bubbleArea.setSelectedNode(this);
					}

					this.bubbleArea.setSelectedNodeType("Anchor");
					this.bubbleArea.toggleSelected(this, "Anchor");

					/**
					 * 更新选中节点数据
					 */
					this.root.setDividerPosition(2, 0.85);

					ArrayList<InfoTableData> infoList = new ArrayList<InfoTableData>();
					if (portNode != null) {
						infoList.add(new InfoTableData("type", portNode.getType()));
						infoList.add(new InfoTableData("guid", portNode.getGuid()));
						//还能再添加一些其他属性
					}
					if (portSwNode > 0) {
						infoList.add(new InfoTableData("sw_portId", Integer.toString(portSwNode)));
					}

					TableView<InfoTableData> tV = this.root.getInfoTable();
					TableColumn proCol = tV.getColumns().get(0);
					TableColumn valCol = tV.getColumns().get(1);

					proCol.setCellValueFactory(new PropertyValueFactory<>("property"));
					valCol.setCellValueFactory(new PropertyValueFactory<>("value"));

					proCol.setCellFactory(TextFieldTableCell.forTableColumn());// 给需要编辑的列设置属性
					valCol.setCellFactory(TextFieldTableCell.forTableColumn());

					ObservableList<InfoTableData> dataList = FXCollections.observableArrayList(infoList);
					this.root.getInfoTable().setItems(dataList);

				}

				// 双击------------------
				if (mouseEvent.getClickCount() == 2) {
					this.bubbleArea.toggleSelected(this, "Anchor");
				}

			}
		});
	}
	
	public Bubble getMyParent() {
		return this.parent;
	}

	public void setParent(Bubble parent) {
		this.parent = parent;
	}


	public double getHelpX() {
		return helpX.get();
	}

	public SimpleDoubleProperty helpXProperty() {
		return helpX;
	}

	public double getHelpY() {
		return helpY.get();
	}

	public SimpleDoubleProperty helpYProperty() {
		return helpY;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public boolean equals(Anchor anotherAnchor) {
		if(this.getPortNode() != null && anotherAnchor.getPortNode()!= null) {
			return this.getPortNode().getGuid().equals(anotherAnchor.getPortNode().getGuid());
		} else if (this.getPortSwNode()> 0 && anotherAnchor.getPortSwNode()>0) {
			return this.getPortSwNode() == anotherAnchor.getPortSwNode() 
					&& this.hardware.getGuid().equals(anotherAnchor.hardware.getGuid());
		} else {
			return false;
		}
	}

	public boolean equalsByParent(Object obj) {
		if (obj instanceof Anchor) {
			Anchor anchor = (Anchor) obj;
			return this.parent == anchor.parent;
		}

		return false;
	}

	public int getPortSwNode() {
		return portSwNode;
	}

	public void setPortSwNode(int portSwNode) {
		this.portSwNode = portSwNode;
	}

	public PortNode getPortNode() {
		return portNode;
	}

	public void setPortNode(PortNode portNode) {
		this.portNode = portNode;
	}

	public Anchor getPreAnchor() {
		return preAnchor;
	}

	public void setPreAnchor(Anchor preAnchor) {
		this.preAnchor = preAnchor;
	}

	public Anchor getNextAnchor() {
		return nextAnchor;
	}

	public void setNextAnchor(Anchor nextAnchor) {
		this.nextAnchor = nextAnchor;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	

}
