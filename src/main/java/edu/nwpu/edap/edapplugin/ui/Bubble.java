package edu.nwpu.edap.edapplugin.ui;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

import edu.nwpu.edap.edapplugin.controller.BubbleViewController;
import edu.nwpu.edap.edapplugin.utils.LoadData;
import edu.nwpu.edap.edapplugin.bean.InfoTableData;
import edu.nwpu.edap.edapplugin.bean.hardware.Hardware;


/**
 * 节点视图类：用来表示硬件
 * 
 * @author Wren
 * @date 2022年2月25日
 *
 */
public class Bubble extends Pane {
	
	private DelayAnalysisPage root;
	private BubbleArea bubbleArea;

	/**
	 * 视图属性
	 */
	public Rectangle coverRectangle;
	public Label titleLabel;
	
	private ArrayList<Anchor> anchorArrayList;
	private ArrayList<Anchor> leftAnchors = new ArrayList<Anchor>();
	private ArrayList<Anchor> rightAnchors = new ArrayList<Anchor>();
	
	/**
	 * 工具属性
	 */
	private int leftPortNum = 0;
	private int rightPortNum = 0;
	private boolean isSelected = false;

	/**
	 * 数据属性
	 */
	private Hardware hardware;

	/**
	 * 构造函数
	 * 
	 * @param daViewController 控制器
	 * @param x                节点的x位置
	 * @param y                节点的y位置
	 * @throws IOException
	 */

	public Bubble(BubbleArea bubbleArea, Hardware hardware, DelayAnalysisPage root, double x, double y, String nodeType,
			String nodeName) throws IOException {

		this.root = root;
		this.bubbleArea = bubbleArea;
		this.hardware = hardware;

		// 加载默认节点样式
		LoadData<BubbleViewController> loadData = new LoadData<BubbleViewController>("../resource/BubbleView.fxml",
				BubbleViewController.class);

		this.anchorArrayList = new ArrayList<>();

		// 挂载当前结点到Pane上
		this.getChildren().add(loadData.node);

		BubbleViewController bubbleViewController = (BubbleViewController) loadData.controller;

		this.coverRectangle = bubbleViewController.coverRectangle;
		this.titleLabel = bubbleViewController.titleLabel;

		// 设置当前节点位置
		this.setLayoutX(x);
		this.setLayoutY(y);

		/**
		 * 设置结点样式
		 */

		bubbleViewController.titleLabel.setText(nodeName);
		bubbleViewController.nodeTypeLabel.setText(nodeType);
		bubbleViewController.coverRectangle.setStrokeWidth(2);

		if (nodeType.equals("LRU")) {
			bubbleViewController.coverRectangle.setFill(Color.web("#dae8fc"));
			bubbleViewController.coverRectangle.setStroke(Color.web("#6c8ebf"));
		} else if (nodeType.equals("RIU")) {
			bubbleViewController.coverRectangle.setFill(Color.web("#e1d5e7"));
			bubbleViewController.coverRectangle.setStroke(Color.web("#9673a6"));
		} else if (nodeType.equals("ACS") || nodeType.equals("ARS")) {
			bubbleViewController.coverRectangle.setFill(Color.web("#fff2cc"));
			bubbleViewController.coverRectangle.setStroke(Color.web("#d6b656"));
		} else if (nodeType.equals("GPM")) {
			bubbleViewController.coverRectangle.setFill(Color.web("#d5e8d4"));
			bubbleViewController.coverRectangle.setStroke(Color.web("#82b366"));
		}
		
		addListeners(bubbleViewController);
	}

	/**
	 * 监视器
	 * @param bubbleViewController
	 */
	@SuppressWarnings("unchecked")
	private void addListeners(final BubbleViewController bubbleViewController) {

		ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();

		// 鼠标进入
		bubbleViewController.titleLabel.setOnMouseEntered(event -> {
			Bubble.this.setCursor(Cursor.HAND);
		});

		// 鼠标退出
		bubbleViewController.titleLabel.setOnMouseExited(event -> {
			Bubble.this.setCursor(Cursor.DEFAULT);
		});

		// 鼠标按压
		bubbleViewController.titleLabel.setOnMousePressed(e -> {
			mousePosition.set(new Point2D(e.getSceneX(), e.getSceneY()));

			DropShadow dropShadow = new DropShadow();
			dropShadow.setColor(Color.DARKGRAY);
			dropShadow.setOffsetX(3);// 水平方向，0则向左右两侧，正则向右，负则向左
			dropShadow.setOffsetY(3);
			dropShadow.setSpread(0.1);
			dropShadow.setRadius(10);
			dropShadow.setWidth(5);

			bubbleViewController.coverRectangle.setEffect(dropShadow);
			bubbleViewController.coverRectangle.setStrokeWidth(3);

		});
		
		// 鼠标释放
		bubbleViewController.titleLabel.setOnMouseReleased(t -> {
			Bubble.this.setCursor(Cursor.DEFAULT);
//			System.out.println("当前位置：X:" + t.getSceneX() + ", Y:" + t.getSceneY());
			bubbleViewController.coverRectangle.setEffect(null);
			bubbleViewController.coverRectangle.setStrokeWidth(2);
		});
		
		// 鼠标拖拽
		bubbleViewController.titleLabel.setOnMouseDragged(t -> {
			Bubble.this.setCursor(Cursor.MOVE);

			double deltaX = t.getSceneX() - mousePosition.get().getX();
			double deltaY = t.getSceneY() - mousePosition.get().getY();

			Bubble.this.setLayoutX(Bubble.this.getLayoutX() + deltaX);
			Bubble.this.setLayoutY(Bubble.this.getLayoutY() + deltaY);
			mousePosition.set(new Point2D(t.getSceneX(), t.getSceneY()));

		});
		
		// 鼠标点击
		bubbleViewController.titleLabel.setOnMouseClicked(mouseEvent -> {

			
			if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {

				// 单击----------------------
				if (mouseEvent.getClickCount() == 1) {
					
					/**
					 * 更新选中节点视图
					 */
					Node s = this.bubbleArea.getSelectedNode();//被选中的节点

					if (s == null) {
						this.setSelected(true);
						this.bubbleArea.setSelectedNode(this);
					} else {
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

					this.bubbleArea.setSelectedNodeType("Bubble");
					this.bubbleArea.toggleSelected(this, "Bubble");
					
					/**
					 * 更新选中节点数据
					 */
					this.root.setDividerPosition(2, 0.85);

					ArrayList<InfoTableData> infoList = new ArrayList<InfoTableData>();

					if(hardware.getType() != null) {
						infoList.add(new InfoTableData("type", hardware.getType()));
					}
					if(hardware.getName() != null) {
						infoList.add(new InfoTableData("name", hardware.getName()));
					}
					if(hardware.getGuid() != null) {
						infoList.add(new InfoTableData("guid", hardware.getGuid()));
					}

					ObservableList<InfoTableData> dataList = FXCollections.observableArrayList(infoList);

					TableView<InfoTableData> tV = this.root.getInfoTable();
					TableColumn proCol = tV.getColumns().get(0);
					TableColumn valCol = tV.getColumns().get(1);

					proCol.setCellValueFactory(new PropertyValueFactory<>("property"));
					valCol.setCellValueFactory(new PropertyValueFactory<>("value"));

					proCol.setCellFactory(TextFieldTableCell.forTableColumn());// 给需要编辑的列设置属性
					valCol.setCellFactory(TextFieldTableCell.forTableColumn());

					this.root.getInfoTable().setItems(dataList);
				}

				if (mouseEvent.getClickCount() == 2) {

					// 双击节点------------------
					this.bubbleArea.toggleSelected(this, "Bubble");

				}
			}
		});

	}

	public boolean myEquals(Bubble anotherBubble) {
		
		Hardware h1 = this.getHardware();
		Hardware h2 = anotherBubble.getHardware();
		
		return h1.getGuid().equals(h2.getGuid());
	}
	
	public ArrayList<Anchor> getAnchorArrayList() {
		return anchorArrayList;
	}

	public ArrayList<Anchor> getLeftAnchors() {
		return leftAnchors;
	}

	public void setLeftAnchors(ArrayList<Anchor> leftAnchors) {
		this.leftAnchors = leftAnchors;
	}

	public ArrayList<Anchor> getRightAnchors() {
		return rightAnchors;
	}

	public void setRightAnchors(ArrayList<Anchor> rightAnchors) {
		this.rightAnchors = rightAnchors;
	}

	public Rectangle getCoverRectangle() {
		return coverRectangle;
	}

	public void setCoverRectangle(Rectangle coverRectangle) {
		this.coverRectangle = coverRectangle;
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(Label titleLabel) {
		this.titleLabel = titleLabel;
	}

	public int getLeftPortNum() {
		return leftPortNum;
	}

	public void setLeftPortNum(int leftPortNum) {
		this.leftPortNum = leftPortNum;
	}

	public int getRightPortNum() {
		return rightPortNum;
	}

	public void setRightPortNum(int rightPortNum) {
		this.rightPortNum = rightPortNum;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Hardware getHardware() {
		return hardware;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}
	
	

}
