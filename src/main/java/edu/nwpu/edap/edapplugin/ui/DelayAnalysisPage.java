package edu.nwpu.edap.edapplugin.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.careri.as.businessmodel.model.RPType;
import com.careri.as.workbench.api.datamodel.logicmodel.*;
import com.careri.as.workbench.api.exception.WorkbenchException;
import com.careri.as.workbench.api.icd.ICDDataService;
import com.careri.as.workbench.api.icd.impl.ICDDataServiceImpl;
import com.sun.xml.internal.ws.api.pipe.Tube;

import edu.nwpu.edap.edapplugin.Service.ICDStructureService;
import edu.nwpu.edap.edapplugin.Service.Impl.ICDStructureServiceImpl;
import edu.nwpu.edap.edapplugin.bean.InfoTableData;
import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.adn.SwitchNode;
import edu.nwpu.edap.edapplugin.bean.device.BaseDeviceAndSwitchNode;
import edu.nwpu.edap.edapplugin.bean.device.RIUAppNode;
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

	private SplitPane treeAreaPane; //消息树列显示区域
	private SplitPane msgsViewPane; //拓扑图区域重新布局
	private PaintArea msgsViewPane_PaintArea; //拓扑图区域的上半部分
	private LightArea msgsViewPane_LightArea; //拓扑图区域的下半部分

	private TreeItem<ICDHierarchy> rootItem; // 目录树根节点
	private TreeView<ICDHierarchy> treeView = new TreeView<>(); // 消息目录树

	TreeItem<ICDObject> rootRight = new TreeItem<ICDObject>();
	TreeView<ICDObject> treeRight = new TreeView<ICDObject>(rootRight);

	TreeItem<String> rpItem = new TreeItem<String>();  //第三层rp消息树
	TreeView<String> rpView = new TreeView<String>(rpItem); //DP消息对应的rp消息


	private BubbleArea bArea = new BubbleArea();

	private LoadICDDataTest loadICDDataTest;

	private VBox msgTreeView = new VBox(); // 左侧消息树
	private TableView<InfoTableData> infoTable = new TableView<InfoTableData>(); // 右侧节点属性信息表
	/**
	 * 2022.7.8
	 * review
	 */
	private TableView<InfoTableData> delayTable = new TableView<InfoTableData>(); //显示延迟属性表
	private VBox inforBox = new VBox(10); //添加属性信息显示框

	private String DPGUID = "";
	//	private ArrayList<RPMessage> msgs = new ArrayList<RPMessage>(); // 待分析的RP消息列表
	private List<Connector> highlightLine = new ArrayList<Connector>(); // 已经高亮的线条

	public Bubble selectedBubble;

	/**
	 * 构造函数
	 *
	 * @throws IOException
	 * @throws WorkbenchException
	 */
	public DelayAnalysisPage() throws IOException, WorkbenchException {

		this.setPrefHeight(850);
		this.setPrefWidth(1450);

		this.msgTreeView = generateTreeViewArea(); // 生成左侧消息树
		this.infoTable = generateInfoTable(null); // 生成右侧节点属性信息表
		this.delayTable = generateDelayTable(null); //生成右侧延迟信息表
		/**
		 * 2022.7.8
		 * review
		 */
		this.msgsViewPane = generateMsgsViewArea(); //拓扑图显示区域

		this.getItems().add(msgTreeView);
		this.getItems().add(msgsViewPane);//添加拓扑图显示区域
//		this.getItems().add(new PaintArea());
//		this.getItems().add(infoTable);
		this.setDividerPositions(0.4f);   //在导入数据时不需要显示右侧属性表
//		SplitPane.setResizableWithParent(this.infoTable, false);
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
		BubbleNode bTree = bArea.getBTree(); // 用于在控制台显示树形的数据传输路径结构

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
	 * 拓扑图显示区域布局
	 *2022年7月8号
	 */
	public SplitPane generateMsgsViewArea() {

		SplitPane msgsViewPane = new SplitPane();
		msgsViewPane.setOrientation(Orientation.VERTICAL);
		msgsViewPane_PaintArea = new PaintArea();
		msgsViewPane_LightArea = new LightArea();
		msgsViewPane.getItems().addAll(msgsViewPane_PaintArea, msgsViewPane_LightArea);
		msgsViewPane.setDividerPositions(0.99);
		return msgsViewPane;
	}

	/**
	 *
	 * @param text 显示的错误信息
	 * @return
	 */
	public SplitPane generateMsgsViewArea(String text) {

		SplitPane msgsViewPane = new SplitPane();
		msgsViewPane.setOrientation(Orientation.VERTICAL);
		msgsViewPane_PaintArea = new PaintArea(text);
		msgsViewPane_LightArea = new LightArea();
		msgsViewPane.getItems().addAll(msgsViewPane_PaintArea, msgsViewPane_LightArea);

		return msgsViewPane;
	}

	/**
	 * 显示DP时，下面的框LightArea不可见
	 * @param delayAnalysisPage
	 * @param rpMsgs
	 * @return
	 * @throws IOException
	 */
	public SplitPane generateMsgsViewArea(DelayAnalysisPage delayAnalysisPage, ArrayList<RPMessage> rpMsgs) throws IOException {
		SplitPane msgsViewPane = new SplitPane();
		msgsViewPane.setOrientation(Orientation.VERTICAL);
		msgsViewPane_PaintArea = new PaintArea(delayAnalysisPage, rpMsgs);
//		msgsViewPane_LightArea = new LightArea();
		msgsViewPane.getItems().addAll(msgsViewPane_PaintArea);
//		msgsViewPane.setDividerPositions(0.8f);
		return msgsViewPane;
	}

	public SplitPane generateMsgsViewArea(DelayAnalysisPage delayAnalysisPage, ArrayList<RPMessage> rpMsgs, PaintArea msgsViewPane_PaintArea) throws IOException {
		SplitPane msgsViewPane = new SplitPane();
		msgsViewPane.setOrientation(Orientation.VERTICAL);
		msgsViewPane_LightArea = new LightArea(delayAnalysisPage, rpMsgs);
		msgsViewPane.getItems().addAll(msgsViewPane_PaintArea, msgsViewPane_LightArea);
		msgsViewPane.setDividerPositions(0.8f);
		return msgsViewPane;
	}

	/**
	 * 生成左侧消息树
	 *
	 * @return
	 * @throws WorkbenchException
	 */
	public VBox generateTreeViewArea() throws WorkbenchException {

//		SplitPane treeAreaPane = new SplitPane();
		this.treeAreaPane = new SplitPane();
		loadICDDataTest = LoadICDDataTest.getInstance();

		ICDStructureService icdStructureService = new ICDStructureServiceImpl();

		rootItem = icdStructureService.getICDHierarchyStructure();
		treeView = new TreeView<ICDHierarchy>(rootItem);


		treeRight.setVisible(false);// 初始设置不可见+
		rpView.setVisible(false);

		treeView.setPrefHeight(2560);
		treeRight.setPrefHeight(2560);
		rpView.setPrefHeight(2560);

		treeAreaPane.getItems().addAll(treeView, treeRight,rpView);//添加rp消息显示树
		treeAreaPane.setDividerPositions(0.5f,0.99f);//将显示区域分三分，起初第三列不可见


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
	 * 生成右侧延迟属性表
	 *
	 */
	public TableView<InfoTableData> generateDelayTable(RPMessage rpMsg) {

		TableView<InfoTableData> delay = new TableView<InfoTableData>();
		TableColumn<InfoTableData, String> delayCol = new TableColumn<>("各区段延迟属性");
		delayCol.setPrefWidth(120);
		TableColumn<InfoTableData,String> valueCol = new TableColumn<>("延迟值");
		valueCol.setPrefWidth(120);

		delay.getColumns().addAll(delayCol, valueCol);
		delay.setEditable(true);

		return delay;
	}


	/**
	 * 生成右侧节点属性信息表
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TableView<InfoTableData> generateInfoTable(RPMessage rpMsg) {

		TableView<InfoTableData> res = new TableView<InfoTableData>();

		res.getColumns().clear();
		res.setPrefWidth(400);

		TableColumn<InfoTableData, String> propertyCol = new TableColumn<>("属性");
		propertyCol.setPrefWidth(120);
		TableColumn<InfoTableData, String> valueCol = new TableColumn<>("取值");
		valueCol.setPrefWidth(120);

		res.getColumns().addAll(propertyCol, valueCol);
		res.setEditable(true);

		if (rpMsg != null) {

			ArrayList<InfoTableData> infoList = new ArrayList<InfoTableData>();
			infoList.add(new InfoTableData("subguid", rpMsg.getRpGuid()));
			infoList.add(new InfoTableData("pubguid", rpMsg.getPubGuid()));

			ObservableList<InfoTableData> dataList = FXCollections.observableArrayList(infoList);

			propertyCol.setCellValueFactory(new PropertyValueFactory<>("property")); //确定数据导入的列
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
				if (inforBox.getChildren().size() == 0) {
					inforBox.getChildren().addAll(infoTable,delayTable);
				} else {
					inforBox.getChildren().clear();
					inforBox.getChildren().addAll(infoTable,delayTable);
				}

				if (node instanceof Text || node instanceof TreeCell) {
					ICDObject icdObject = (ICDObject) treeRight.getSelectionModel().getSelectedItem().getValue();

					if (icdObject.getType().equals("RP")) {
						if (icdObject.getChildren().size() != 0) {
							RPType rp = loadICDDataTest.getRPByGuid(icdObject.getGuid());
							RPMessage rpMessage = RPMessageLibrary.getRpMessageByRPGuid(rp.getGuid());
//
							ArrayList<RPMessage> rpMsgs = new ArrayList<RPMessage>();
							rpMsgs.add(rpMessage);
							delayAnalysisPage.getItems().clear();
							delayAnalysisPage.getItems().add(msgTreeView);

							rpView.setVisible(false); //RP不包含对应rp消息树
							treeAreaPane.setDividerPositions(0.5f,0.99f); // 设置分割面板的比例

							if (rpMessage == null) {
								System.err.println("出错啦，没找到！！");
								delayAnalysisPage.getItems().add(new PaintArea("出错啦，没找到！！"));
							} else if (rpMessage.isError()) {
								System.err.println(rpMessage.getErrorMsg());
								delayAnalysisPage.getItems().add(new PaintArea(rpMessage.getErrorMsg()));
								delayAnalysisPage.setDividerPositions(0.35, 0.85);
							} else {
								System.out.println("===================" + rpMessage.getA653ScheduleDelay() + "===================");
								System.out.println("-------------------" + rpMessage.getAsyncDelay() + "-------------------");
								System.out.println("==============="+rpMessage.getPubEndApp().getType() + "===============");

								System.out.println(rpMessage.toString());
								delayAnalysisPage.getItems().clear();
								delayAnalysisPage.getItems().add(msgTreeView);
								try {
									delayAnalysisPage.getItems().add(new PaintArea(delayAnalysisPage, rpMsgs));
								} catch (IOException e) {
									e.printStackTrace();
								}

								delayAnalysisPage.getItems().addAll(inforBox);
								delayAnalysisPage.setDividerPositions(0.35, 0.85);

							}

						}
					} else if (icdObject.getType().equals("DP") || icdObject.getType().equals("A429Word")) {
						DPGUID = icdObject.getGuid();
						List<RPMessage> rpMessages = RPMessageLibrary.getRpMessagesByDPGuid(DPGUID);
						if (rpMessages == null) {
							System.err.println("该DP/A429Word为空");

						} else {
							System.out.println("该DP/A429Word");
							ArrayList<RPMessage> rpMsgs = new ArrayList<RPMessage>();
							delayAnalysisPage.getItems().clear();
							delayAnalysisPage.getItems().add(msgTreeView);
							String errS = "";

							rpItem = new TreeItem<String>(DPGUID); // 将DP消息的Guid作为根目录
							rpItem.setExpanded(true); //设置目录展开

							for (RPMessage rpMessage : rpMessages) {
								if (rpMessage == null) {
									System.err.println("出错啦，没找到！！");
								} else if (rpMessage.isError()) {
									System.err.println(rpMessage.getErrorMsg());
									errS += rpMessage.getErrorMsg() + "\n";
									// 目前还没有将错误信息返回到前端（在505行）
								} else {
									System.out.println("==============="+rpMessage.getPubEndApp().getType() + "===============");
									rpMsgs.add(rpMessage);

									String rpGuid = rpMessage.getRpGuid();
									TreeItem<String> childrpItem = new TreeItem<String>(rpGuid); //获取rp消息分支
									rpItem.getChildren().add(childrpItem);
								}
							}

							treeAreaPane.setDividerPositions(0.33f,0.66f); //将显示区域三等分，显示rp消息树
							rpView.setRoot(rpItem); //显示rp消息树
							rpView.setVisible(true);

							if (rpMsgs.isEmpty()) {
								// 将错误信息返回到前端
								msgsViewPane = generateMsgsViewArea(errS);

								delayAnalysisPage.getItems().addAll(msgsViewPane, inforBox);
								delayAnalysisPage.setDividerPositions(0.35, 0.85);
							} else {
								try {
									msgsViewPane = generateMsgsViewArea(delayAnalysisPage, rpMsgs);
								} catch (IOException e) {
									e.printStackTrace();
								}

								delayAnalysisPage.getItems().addAll(msgsViewPane, inforBox);
								delayAnalysisPage.setDividerPositions(0.35, 0.85);
							}
						}
					}
				}
			}

		});

		/**
		 * 添加rp消息树监听事件
		 */
		rpView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {

				Node node = event.getPickResult().getIntersectedNode();

				if (node instanceof Text || node instanceof TreeCell) {
					String rpGuid = (String) rpView.getSelectionModel().getSelectedItem().getValue();
					if (rpGuid.equals(DPGUID)) {
						return;
					}
					try {
						RPMessage rpMessage = RPMessageLibrary.getRpMessageByRPGuid(rpGuid);
						System.out.println("==============="+rpMessage.getPubEndApp().getType() + "===============");
						ArrayList<RPMessage> rpMsgs = new ArrayList<RPMessage>();
						rpMsgs.add(rpMessage);
						// 清空页面
						delayAnalysisPage.getItems().clear();

						/**
						 * 2022.7.13 高亮线条
						 */
						highlightRPMessageLine(rpMessage);

						msgsViewPane = generateMsgsViewArea(delayAnalysisPage, rpMsgs, msgsViewPane_PaintArea);

						delayAnalysisPage.getItems().addAll(msgTreeView, msgsViewPane, inforBox);
						delayAnalysisPage.setDividerPositions(0.35, 0.85);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	/**
	 * 高亮RPMessage传输路径上的所有线条
	 * @param rpMessage 在左侧目录树第三列选中的rpMessage
	 */
	protected void highlightRPMessageLine(RPMessage rpMessage) {
		// 将上一次的线条变成黑色
		for (Connector line : highlightLine) {
			line.setStroke(Color.BLACK);
		}
		// 清空上一次的集合
		highlightLine.clear();

		List<Pair<String, String>> allPair = new ArrayList<>();
		if (rpMessage.getPubEndApp() == null) {
			System.out.println("===========该消息发送端为空===========");
			return;
		}
		// 发送端：发送端的发送端口 接收端：发送端RDIU的接收端口
		Pair<String, String> firstPair = new Pair<>(rpMessage.getPubEndApp().getPort().getGuid(),
				rpMessage.getPubRDIU().getSubPort().getGuid());
		allPair.add(firstPair);

		if (rpMessage.getSubEndApp() == null) {
			System.out.println("===========该消息接送端为空===========");
			return;
		}
		// 发送端：接收端RDIU的发送端口 接收端：接收端的接收端口
		Pair<String, String> fourPair = new Pair<>(rpMessage.getSubRDIU().getPubPort().getGuid(),
				rpMessage.getSubEndApp().getPort().getGuid());
		allPair.add(fourPair);

		// 网络拓扑图上的所有传输线
		List<Connector> AllLine = msgsViewPane_PaintArea.getBubbleArea().getAllLine();

		List<Connector> rdiuToSwitch = new ArrayList<Connector>();
		List<Connector> switchToRDIU = new ArrayList<Connector>();
		List<Connector> switchLines = new ArrayList<Connector>();
		for(Pair<String, String> pair : allPair) {
			boolean startIsSwitch = false;
			boolean endIsSwitch = false;
			for (Connector line : AllLine) {
				// 交换机的端口对应的anchor的portNode为空，需要注意
				// 交换机端口对应的anchor的portSwNode不为-1
				Anchor startAnchor = line.getStartAnchor();
				String startPortGuid = "";
				if (startAnchor.getPortNode() == null) {
					// 是交换机上的端口
					startIsSwitch = true;
				} else {
					// 是其他硬件上的端口
					startPortGuid = startAnchor.getPortNode().getGuid();
				}
				Anchor endAnchor = line.getEndAnchor();
				String endPortGuid = "";
				if (endAnchor.getPortNode() == null) {
					// 是交换机上的端口
					endIsSwitch = true;
				} else {
					// 是其他硬件上的端口
					endPortGuid = endAnchor.getPortNode().getGuid();
				}

				if (startPortGuid.equals(pair.getKey()) && endPortGuid.equals(pair.getValue())) {
					// 改变这条线的颜色
					line.setStroke(Color.RED);
					line.setStrokeWidth(3.5);
					highlightLine.add(line);
				}

				if (!startIsSwitch && endIsSwitch) {
					rdiuToSwitch.add(line); // 发送端是RDIU，接收端是Switch
				}
				if (startIsSwitch && !endIsSwitch) {
					switchToRDIU.add(line); // 发送端是Switch，接收端是RDIU
				}
				if (startIsSwitch && endIsSwitch) {
					switchLines.add(line); // 发送端和接收端都是switch
				}
			}
		}

		List<SwitchNode> allSwitch;
		// 处理和交换机有关的line
		if (rpMessage.getAdnPart() != null) {
			allSwitch = rpMessage.getAdnPart().getSwitchNodes();
			List<Pair<Integer, Integer>> switchPairList = new ArrayList<Pair<Integer,Integer>>();

			// 发送端：发送端RDIU的发送端口 接收端：第一跳交换机的接收端口
			Pair<String, Integer> rdiuToSwitchPair = new Pair<>(rpMessage.getPubRDIU().getPubPort().getGuid(),
					allSwitch.get(0).getSubPort());

			for (Connector line : rdiuToSwitch) {
				// 交换机的端口对应的anchor的portNode为空，需要注意
				// 交换机端口对应的anchor的portSwNode不为-1
				Anchor startAnchor = line.getStartAnchor();
				// 是其他硬件上的端口
				String startPortGuid = startAnchor.getPortNode().getGuid();

				Anchor endAnchor = line.getEndAnchor();
				int switchPort = endAnchor.getPortSwNode(); // 第一跳交换机的接收端口
				if (startPortGuid.equals(rdiuToSwitchPair.getKey())
						&& switchPort == rdiuToSwitchPair.getValue()) {
					// 改变这条线的颜色
					line.setStroke(Color.RED);
					line.setStrokeWidth(3.5);
					highlightLine.add(line);
				}
			}

			for (int i = 0; i < allSwitch.size() - 1; i++) {
				Pair<Integer, Integer> switchPair = new Pair<>(allSwitch.get(i).getPubPort(),
						allSwitch.get(i + 1).getSubPort());
				switchPairList.add(switchPair);
			}

			for (Pair<Integer, Integer> pair : switchPairList) {
				for (Connector line : switchLines) {
					Anchor startAnchor = line.getStartAnchor();
					int startSwitchPort = startAnchor.getPortSwNode(); // 最后一跳交换机的发送端口

					Anchor endAnchor = line.getEndAnchor();
					int endSwitchPort = endAnchor.getPortSwNode(); // 第一跳交换机的接收端口
					if (startSwitchPort == pair.getKey()
							&& endSwitchPort == pair.getValue()) {
						// 改变这条线的颜色
						line.setStroke(Color.RED);
						line.setStrokeWidth(3.5);
						highlightLine.add(line);
					}
				}
			}

			// 发送端：最后一跳交换机的发送端口 接收端：接收端RDIU的接收端口
			Pair<Integer, String> switchToRDIUPair = new Pair<>(
					allSwitch.get(allSwitch.size() - 1).getPubPort(),
					rpMessage.getSubRDIU().getSubPort().getGuid());
			for (Connector line : switchToRDIU) {
				// 交换机的端口对应的anchor的portNode为空，需要注意
				// 交换机端口对应的anchor的portSwNode不为-1
				Anchor endAnchor = line.getEndAnchor();
				// 是其他硬件上的端口
				String endPortGuid = endAnchor.getPortNode().getGuid();

				Anchor startAnchor = line.getStartAnchor();
				int switchPort = startAnchor.getPortSwNode(); // 最后一跳交换机的发送端口
				if (endPortGuid.equals(switchToRDIUPair.getValue())
						&& switchPort == switchToRDIUPair.getKey()) {
					// 改变这条线的颜色
					line.setStroke(Color.RED);
					line.setStrokeWidth(3.5);
					highlightLine.add(line);
				}
			}
		}
	}

	public TableView<InfoTableData> getInfoTable() {
		return infoTable;
	}

	public void setInfoTable(TableView<InfoTableData> infoTable) {
		this.infoTable = infoTable;
	}

}
