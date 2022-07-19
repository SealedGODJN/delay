package edu.nwpu.edap.edapplugin.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.careri.as.businessmodel.model.RPType;
import com.careri.as.workbench.api.datamodel.logicmodel.*;
import com.careri.as.workbench.api.icd.ICDDataService;
import com.careri.as.workbench.api.icd.impl.ICDDataServiceImpl;

import edu.nwpu.edap.edapplugin.Service.ICDStructureService;
import edu.nwpu.edap.edapplugin.Service.Impl.ICDStructureServiceImpl;
import edu.nwpu.edap.edapplugin.bean.InfoTableData;
import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.device.BaseDeviceAndSwitchNode;
import edu.nwpu.edap.edapplugin.library.RPMessageLibrary;
import edu.nwpu.edap.edapplugin.test.LoadICDDataTest;
import edu.nwpu.edap.edapplugin.ui.myTreeStrc.AnchorNode;
import edu.nwpu.edap.edapplugin.ui.myTreeStrc.BubbleNode;

/**
 * 延迟分析页面视图类
 * 
 * @author Wren
 * @date 2022年2月25日
 *
 */
public class DelayAnalysisPage extends SplitPane {

	private TreeView<ICDHierarchy> treeView = new TreeView<>(); // 消息目录树
	private TreeItem<ICDHierarchy> rootItem; // 目录树根节点
	TreeItem<ICDObject> rootRight = new TreeItem<ICDObject>();
	TreeView<ICDObject> treeRight = new TreeView<ICDObject>(rootRight);

	private BubbleArea bArea = new BubbleArea();

	private LoadICDDataTest loadICDDataTest;

	private VBox msgTreeView = new VBox(); // 左侧消息树
	private TableView<InfoTableData> infoTable = new TableView<InfoTableData>(); // 右侧节点属性信息表

	ArrayList<RPMessage> msgs = new ArrayList<RPMessage>(); // 待分析的RP消息列表

	public Bubble selectedBubble;

	//折叠面板
	private
	private TitledPane titledPane1 = new TitledPane();
	private TitledPane titledPane2 = new TitledPane();

	/**
	 * 构造函数
	 * 
	 * @throws IOException
	 */
	public DelayAnalysisPage() throws IOException {

		this.setPrefHeight(850);
		this.setPrefWidth(1450);

		this.msgTreeView = generateTreeViewArea(); // 生成左侧消息树
		this.infoTable = generateInfoTable(null); // 生成右侧节点属性信息表
		this.titledPane1 = new TitledPane();

		VBox paintbox = new VBox(10);
		TextArea delayArea = new TextArea();
		paintbox.getChildren.addAll(new PaintArea(),delayArea);

		this.getItems().add(msgTreeView);
//		this.getItems().add(new PaintArea());
		this.getItems().add(paintbox);
//		this.getItems().add(infoTable);

		this.setDividerPositions(0.35, 0.99);
		SplitPane.setResizableWithParent(this.infoTable, false);
		SplitPane.setResizableWithParent(this.msgTreeView, false);

	}

	/**
	 * 生成RP消息路径
	 * 
	 * @param msgs
	 * @throws IOException
	 */
	public String generateRPPath(ArrayList<RPMessage> msgs, BubbleArea bArea) throws IOException {

		int initX = -100;
		int initY = 200;
		
		for (RPMessage msg : msgs) {
			List<BaseDeviceAndSwitchNode> nodes = msg.getAllDeviceNodes();

			for (BaseDeviceAndSwitchNode node : nodes) {
				bArea.addBubble(this, initX, initY, node);
			}
		}
		
		AnchorNode aTree = bArea.getATree();
		BubbleNode bTree = bArea.getBTree();
		
		bTree.setNodeLevel(0);
		
		System.out.println(aTree.toString(0, true));
		System.out.println(bTree.toString(0, true));
		
		
		alignBubbles(bTree);
		
		bArea.addLine(aTree);
		
		return "";
	}
	
	public void  alignBubbles(BubbleNode bTree) {
		
		int maxl = bTree.getMaxLevel();
		System.out.println("maxL: "+maxl);
		double bubble_height = 0;
		int initX = 20;
		int initY = 200;
		double gap = 40;
		
		for(int i=0;i<=maxl;i++) {
			ArrayList<Bubble> bubbles = bTree.getSpeciLevelNode(i);
			int size = bubbles.size();
			
			
			for(int j=0;j<size;j++) {
				bubble_height = bubbles.get(j).getCoverRectangle().getHeight();
				gap = gap + bubble_height;
				
				bubbles.get(j).setLayoutX(initX + i*130);
				
				if(size % 2 == 0) { 
					initY += gap/2 + (j-size/2)*gap;
				} else { 
					initY += (j-size/2)*gap;
				}
				bubbles.get(j).setLayoutY(initY);
				
				initY = 200;
				gap = 40;
			}
		}
		
	}

	/**
	 * 生成左侧消息树
	 * 
	 * @return
	 */
	public VBox generateTreeViewArea() {

		SplitPane treeAreaPane = new SplitPane();
		loadICDDataTest = LoadICDDataTest.getInstance();

		ICDStructureService icdStructureService = new ICDStructureServiceImpl();

		rootItem = icdStructureService.getICDHierarchyStructure();
		treeView = new TreeView<ICDHierarchy>(rootItem);

		treeRight.setVisible(false);// 初始设置不可见+

		treeView.setPrefHeight(2560);
		treeRight.setPrefHeight(2560);

		treeAreaPane.getItems().addAll(treeView, treeRight);

		treeView.setMaxHeight(10000);

		addListeners(this, icdStructureService, bArea);

		VBox vBox = new VBox(10);
		HBox hBox = new HBox(5);
		TextField searchInput = new TextField("请输入消息GUID");
		Button searchBtn = new Button("搜索");

		hBox.getChildren().addAll(searchInput, searchBtn);
		vBox.getChildren().addAll(hBox, treeAreaPane);

		vBox.setPadding(new Insets(10));
		HBox.setHgrow(searchInput, Priority.ALWAYS);
		VBox.setVgrow(treeAreaPane, Priority.ALWAYS);
		searchInput.setMaxWidth(200);

		return vBox;
	}

	/**
	 * 生成右侧节点各区段延迟属性表
	 *
	 *2022年7月14日
	 *
	 */
	@SuppressWarnings("unchecked")
	public TableView<InfoTableData> generateDelayable(RPMessage rpMsg) {

		TableView<InfoTableData> delayTable = new TableView<InfoTableData>();

		delayTable.getColumns().clear();
		delayTable.setPrefWidth(400);

		TableColumn<InfoTableData, String> delayCol = new TableColumn<>("区段延迟属性");
		delayCol.setPrefWidth(120);
		TableColumn<InfoTableData, String> valueCol = new TableColumn<>("取值");
		valueCol.setPrefWidth(280);

		res.getColumns().addAll(delayCol, valueCol);
		res.setEditable(true);

		if (rpMsg != null) {

			ArrayList<InfoTableData> infoList = new ArrayList<InfoTableData>();
			infoList.add(new InfoTableData("Name", rpMsg.getPubRDIU()));
			infoList.add(new InfoTableData("Guid", rpMsg.getPubGuid()));

			ObservableList<InfoTableData> dataList = FXCollections.observableArrayList(infoList);

			propertyCol.setCellValueFactory(new PropertyValueFactory<>("proper"));
			valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

			propertyCol.setCellFactory(TextFieldTableCell.forTableColumn());// 给需要编辑的列设置属性
			valueCol.setCellFactory(TextFieldTableCell.forTableColumn());

			res.setItems(dataList);
		}

		return res;
	}

	/**
	 * 生成DP中包含的RP消息列表
	 * 
	 * @param msgs
	 * @return
	 */
	public TableView<InfoTableData> generateRPListTable(ArrayList<RPMessage> msgs) {

		ArrayList<InfoTableData> infoList = new ArrayList<InfoTableData>();

		for (int i = 0; i < msgs.size(); i++) {
			infoList.add(new InfoTableData("RP Msg" + (i + 1), msgs.get(i).getPubGuid()));
		}

		ObservableList<InfoTableData> dataList = FXCollections.observableArrayList(infoList);

		TableColumn<InfoTableData, String> proCol = new TableColumn<>("消息序号");
		TableColumn<InfoTableData, String> valCol = new TableColumn<>("消息ID");

		proCol.setCellValueFactory(new PropertyValueFactory<>("property"));
		valCol.setCellValueFactory(new PropertyValueFactory<>("value"));

		proCol.setCellFactory(TextFieldTableCell.forTableColumn());// 给需要编辑的列设置属性
		valCol.setCellFactory(TextFieldTableCell.forTableColumn());

		infoTable.setItems(dataList);

		return infoTable;

	}

	/**
	 * TreeView的监听器
	 * 
	 * @param delayAnalysisPage
	 * @param vBox
	 */
	private void addListeners(DelayAnalysisPage delayAnalysisPage, ICDStructureService icdStructureService,
			BubbleArea bArea) {

		treeView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Node node = event.getPickResult().getIntersectedNode();

				try {
					if (node instanceof Text || node instanceof TreeCell) {

						ICDHierarchy icdHierarchy = (ICDHierarchy) treeView.getSelectionModel().getSelectedItem()
								.getValue();

						if (icdHierarchy.getChildren().isEmpty()) {
							ICDDataService icdDataService = new ICDDataServiceImpl();
							ICDObject icdObject = icdDataService.retrieveICDObjectByFile(icdHierarchy);

							treeRight.setRoot(icdStructureService.getICDObjectStructure(icdObject));
							treeRight.setVisible(true);
						}
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
				}

			}
		});

		treeRight.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Node node = event.getPickResult().getIntersectedNode();

				if (node instanceof Text || node instanceof TreeCell) {
					ICDObject icdObject = (ICDObject) treeRight.getSelectionModel().getSelectedItem().getValue();
					if (icdObject.getType().equals("RP")) {
						if (icdObject.getChildren().size() != 0) {
							RPType rp = loadICDDataTest.getRPByGuid(icdObject.getGuid());
							RPMessage rpMessage = RPMessageLibrary.getRpMessageByRPGuid(rp.getGuid());
							
							System.out.println(rpMessage.toString());

							ArrayList<RPMessage> rpMsgs = new ArrayList<RPMessage>();
							rpMsgs.add(rpMessage);
							delayAnalysisPage.getItems().clear();
							delayAnalysisPage.getItems().add(msgTreeView);

							if (rpMessage == null) {
								System.err.println("出错啦，没找到！！");
								delayAnalysisPage.getItems().add(new PaintArea("出错啦，没找到！！"));
							} else if (rpMessage.isError()) {
								System.err.println(rpMessage.getErrorMsg());
								delayAnalysisPage.getItems().add(new PaintArea(rpMessage.getErrorMsg()));
								delayAnalysisPage.setDividerPositions(0.35, 0.85);
							} else {
								System.out.println(rpMessage.toString());
								delayAnalysisPage.getItems().clear();
								delayAnalysisPage.getItems().add(msgTreeView);
								try {
									delayAnalysisPage.getItems().add(new PaintArea(delayAnalysisPage, rpMsgs));
								} catch (IOException e) {
									e.printStackTrace();
								}
								delayAnalysisPage.getItems().add(infoTable);
								delayAnalysisPage.setDividerPositions(0.35, 0.85);
							}

						}
					} else if (icdObject.getType().equals("DP") || icdObject.getType().equals("A429Word")) {
						String guid = icdObject.getGuid();
						List<RPMessage> rpMessages = RPMessageLibrary.getRpMessagesByDPGuid(guid);

						if (rpMessages == null) {
							System.err.println("该DP/A429Word为空");

						} else {
							System.out.println("该DP/A429Word");
							ArrayList<RPMessage> rpMsgs = new ArrayList<RPMessage>();
							delayAnalysisPage.getItems().clear();
							delayAnalysisPage.getItems().add(msgTreeView);

							String errS = "";

							for (RPMessage rpMessage : rpMessages) {
								if (rpMessage == null) {
									System.err.println("出错啦，没找到！！");
								} else if (rpMessage.isError()) {
									System.err.println(rpMessage.getErrorMsg());
									errS += rpMessage.getErrorMsg() + "\n";
									// 目前还没有将错误信息返回到前端
								} else {
									rpMsgs.add(rpMessage);
									System.out.println(rpMessage.toString());
								}
							}

							if (rpMsgs.isEmpty()) {
								delayAnalysisPage.getItems().add(new PaintArea(errS));
								delayAnalysisPage.setDividerPositions(0.35, 0.85);
							} else {
								try {
									delayAnalysisPage.getItems().add(new PaintArea(delayAnalysisPage, rpMsgs));
								} catch (IOException e) {
									e.printStackTrace();
								}
								delayAnalysisPage.getItems().add(infoTable);
								delayAnalysisPage.setDividerPositions(0.35, 0.85);
							}
						}
					}
				}
			}

		});

	}

	public TableView<InfoTableData> getInfoTable() {
		return infoTable;
	}

	public void setInfoTable(TableView<InfoTableData> infoTable) {
		this.infoTable = infoTable;
	}

}
