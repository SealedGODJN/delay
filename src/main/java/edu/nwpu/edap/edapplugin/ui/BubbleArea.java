package edu.nwpu.edap.edapplugin.ui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.adn.ACSSwitchNode;
import edu.nwpu.edap.edapplugin.bean.adn.ARSSwitchNode;
import edu.nwpu.edap.edapplugin.bean.device.BaseDeviceAndSwitchNode;
import edu.nwpu.edap.edapplugin.bean.device.GPMAppNode;
import edu.nwpu.edap.edapplugin.bean.device.LRUAppNode;
import edu.nwpu.edap.edapplugin.bean.device.RIUAppNode;
import edu.nwpu.edap.edapplugin.bean.hardware.Hardware;
import edu.nwpu.edap.edapplugin.bean.port.PortNode;
import edu.nwpu.edap.edapplugin.ui.myTreeStrc.AnchorNode;
import edu.nwpu.edap.edapplugin.ui.myTreeStrc.BubbleNode;

/**
 * 结点区域：结点的容器类
 *
 * @author Wren
 * @date 2022年2月25日
 *
 */

public class BubbleArea extends Pane {

	private ArrayList<Bubble> bubbleArrayList = new ArrayList<Bubble>();
	private ArrayList<Anchor> anchorArrayList = new ArrayList<Anchor>();
	private ArrayList<Connector> lines = new ArrayList<Connector>();
	private ArrayList<Connector> AllLine = new ArrayList<Connector>();
	/**
	 * @return the allLine
	 */
	public ArrayList<Connector> getAllLine() {
		return AllLine;
	}

	private Node selectedNode;
	private String selectedNodeType;
	private DelayAnalysisPage root;

	private int ANCHOR_WIDTH = 10;
	private int ANCHOR_HEIGHT = 10;
	private int initSpace = 15;
	private int midSpace = 15;

	private AnchorNode aTree = null;
	private BubbleNode bTree = null;
	private AnchorNode aPNode = null;
	private BubbleNode bPNode = null;

	/**
	 * 无参构造函数
	 */
	public BubbleArea() {

	}

	/**
	 * 有参构造函数
	 * @return
	 * @throws IOException
	 */
	public AnchorNode addBubble(DelayAnalysisPage root, double x, double y, BaseDeviceAndSwitchNode node)
			throws IOException {

		this.root = root;

		Hardware h = node.getHardware();
		Bubble bubble = null;

		boolean flag = true;

		// 判断当前结点是否已绘制
		if (!bubbleArrayList.isEmpty()) {
			for (int i = 0; i < bubbleArrayList.size(); i++) {
				Bubble b = bubbleArrayList.get(i);
				if (b.getHardware().getGuid().equals(h.getGuid())) {
					bubble = b;
					flag = false;
					bPNode = getBubbleTreeNode(bTree, bubble);
					break;
				}
			}
		}
		if (flag || bubbleArrayList.isEmpty()) {
			bubble = new Bubble(this, h, root, x, y, h.getType(), h.getName());
			this.bubbleArrayList.add(bubble);

			if (bTree == null) { // 如果树的根节点为空
				bTree = new BubbleNode(bubble);
				bPNode = bTree;
			} else {
				BubbleNode temp = new BubbleNode(bubble);
				bPNode.getChildList().add(temp);
				bPNode = temp;
			}
		}
		/**
		 * 对设备结点进行向下转型
		 */
		PortNode port;
		PortNode subPort;
		PortNode pubPort;
		int subPortSW;
		int pubPortSW;

		switch (h.getType()) {
			case "LRU":
				LRUAppNode nodeLRU = (LRUAppNode) node;
				port = nodeLRU.getPort();
				addAnchor(bubble, nodeLRU.getDirect(), -1, port, null);
				break;

			case "RIU":
				RIUAppNode nodeRIU = (RIUAppNode) node;
				subPort = nodeRIU.getSubPort();
				pubPort = nodeRIU.getPubPort();
				addAnchor(bubble, "sub", -1, subPort, null);
				addAnchor(bubble, "pub", -1, pubPort, null);
				break;

			case "ARS":
				ARSSwitchNode nodeARS = (ARSSwitchNode) node;
				subPortSW = nodeARS.getSubPort();
				pubPortSW = nodeARS.getPubPort();
				addAnchor(bubble, "sub", subPortSW, null, nodeARS.getHardware());
				addAnchor(bubble, "pub", pubPortSW, null, nodeARS.getHardware());
				break;

			case "ACS":
				ACSSwitchNode nodeACS = (ACSSwitchNode) node;
				subPortSW = nodeACS.getSubPort();
				pubPortSW = nodeACS.getPubPort();
				addAnchor(bubble, "sub", subPortSW, null, nodeACS.getHardware());
				addAnchor(bubble, "pub", pubPortSW, null, nodeACS.getHardware());
				break;

			case "GPM":
				GPMAppNode nodeGPM = (GPMAppNode) node;
				port = nodeGPM.getPort();
				port = nodeGPM.getPort();
				addAnchor(bubble, nodeGPM.getDirect(), -1, port, null);
				break;

			default:
				break;
		}

		return this.aTree;

	}

	/**
	 * 新建锚点视图 -- 代码可以删减
	 *
	 * @param parent
	 * @param direct
	 * @param swPort
	 * @param nSWPort
	 */
	private void addAnchor(Bubble parent, String direct, int swPort, PortNode nSWPort, Hardware hardware) {

		Anchor anchor = null;

		boolean flag = true;

		// 左端口
		if (direct.equals("sub")) {

			if (nSWPort != null) {// 其他硬件端口
				anchor = new Anchor(nSWPort, root, this, 0, 0, ANCHOR_WIDTH, ANCHOR_HEIGHT);
			} else { // 交换机硬件端口
				anchor = new Anchor(swPort, root, this, 0, 0, ANCHOR_WIDTH, ANCHOR_HEIGHT, hardware);
			}

			anchor.setDirect(direct);
			if (anchorArrayList.isEmpty()) {
				updateParent(direct, parent, anchor);
				flag = false;
			} else {
				for (int j = 0; j < anchorArrayList.size(); j++) {
					Anchor a = anchorArrayList.get(j);
					if (a.equals(anchor)) {
						anchor = a;
						flag = false;
						aPNode = getTreeNode(aTree, anchor);
						if (aPNode == null) {
							System.out.println("交换机端口：" + anchor.getPortSwNode());
							System.out.println("非交换机端口：" + anchor.getPortNode());
						}
						break;
					}
				}
			}

			if (flag) {
				updateParent(direct, parent, anchor);
			}
		}

		// 右端口
		if (direct.equals("pub")) {

			double bubble_width = parent.getCoverRectangle().getWidth();

			if (nSWPort != null) {
				anchor = new Anchor(nSWPort, root, this, bubble_width + ANCHOR_WIDTH, 0, ANCHOR_WIDTH, ANCHOR_HEIGHT);
			} else {
				anchor = new Anchor(swPort, root, this, bubble_width + ANCHOR_WIDTH, 0, ANCHOR_WIDTH, ANCHOR_HEIGHT,
						hardware);
			}

			anchor.setDirect(direct);

			if (anchorArrayList.isEmpty()) {
				updateParent(direct, parent, anchor);
				flag = false;
			} else {
				for (int j = 0; j < anchorArrayList.size(); j++) {
					Anchor a = anchorArrayList.get(j);
					if (a.equals(anchor)) {
						anchor = a;
						flag = false;
						aPNode = getTreeNode(aTree, anchor);
						if (aPNode == null) {
							System.out.println("交换机端口：" + anchor.getPortSwNode());
							System.out.println("非交换机端口：" + anchor.getPortNode());
						}
						break;
					}
				}
			}

			if (flag) {
				updateParent(direct, parent, anchor);
			}
		}

	}

	public void updateParent(String direct, Bubble parent, Anchor anchor) {

		if (direct.equals("sub")) {
			int num = parent.getLeftPortNum(); // 得到当前结点的左锚点个数
			num++;
			double newH = 30 + 10 * num + 5 * (num - 1); // 根据锚点个数，计算当前结点应设的高度
			anchor.setOrder(num - 1);

			parent.setPrefHeight(newH);
			parent.getCoverRectangle().setHeight(newH);
			parent.getTitleLabel().setPrefHeight(newH);
			parent.getLeftAnchors().add(anchor);
			parent.setLeftPortNum(num);

			for (int i = 0; i < num; i++) {
				parent.getLeftAnchors().get(i).setLayoutY(initSpace + i * midSpace);
			}

		} else if (direct.equals("pub")) {
			int num = parent.getRightPortNum(); // 得到当前结点的右锚点个数
			num++;
			double newH = 30 + 10 * num + 5 * (num - 1); // 根据锚点个数，计算当前结点应设的高度

			anchor.setOrder(num - 1);

			parent.setPrefHeight(newH);
			parent.getCoverRectangle().setHeight(newH);
			parent.getTitleLabel().setPrefHeight(newH);
			parent.getRightAnchors().add(anchor);
			parent.setRightPortNum(num);

			for (int i = 0; i < num; i++) {
				anchor.setLayoutY(initSpace + i * midSpace);
			}
		}

		parent.getTitleLabel().setLayoutY(0);

		parent.getAnchorArrayList().add(anchor);

		anchor.setParent(parent);

		if (aTree == null) { // 如果树的根节点为空
			aTree = new AnchorNode(anchor);
			aPNode = aTree;
		} else {
			AnchorNode temp = new AnchorNode(anchor);
			aPNode.getChildList().add(temp);
			aPNode = temp;
		}

		anchorArrayList.add(anchor);
	}

	/**
	 *
	 * @param treeRoot
	 * @param target
	 * @return
	 */
	public AnchorNode getTreeNode(AnchorNode treeRoot, Anchor target) {

		if (treeRoot.getData().equals(target))
			return treeRoot;
		if (treeRoot.getChildList() == null || treeRoot.getChildList().size() == 0) {
			return null;
		} else {
			int childNumber = treeRoot.getChildList().size();
			for (int i = 0; i < childNumber; i++) {
				AnchorNode child = treeRoot.getChildList().get(i);
				AnchorNode resultNode = getTreeNode(child, target);
				if (resultNode != null) {
					return resultNode;
				}
			}
			return null;
		}

	}

	public BubbleNode getBubbleTreeNode(BubbleNode treeRoot, Bubble target) {

		if (treeRoot.getData().equals(target))
			return treeRoot;
		if (treeRoot.getChildList() == null || treeRoot.getChildList().size() == 0) {
			return null;
		} else {
			int childNumber = treeRoot.getChildList().size();
			for (int i = 0; i < childNumber; i++) {
				BubbleNode child = treeRoot.getChildList().get(i);
				BubbleNode resultNode = getBubbleTreeNode(child, target);
				if (resultNode != null) {
					return resultNode;
				}
			}
			return null;
		}

	}

	/**
	 * 绑定锚点到父节点的工具方法
	 *
	 * @param parent
	 * @param anchor
	 * @param direct
	 */
	private void bindAnchor(Bubble parent, Anchor anchor, String direct) {

		int initSpace = 15;

		int midSpace = 15;

		double bubble_width = parent.getCoverRectangle().getWidth();

		if (direct.equals("left")) {

			anchor.helpX.bind(parent.layoutXProperty());
			anchor.helpY.bind(parent.layoutYProperty().add(initSpace + anchor.getOrder() * midSpace));

		} else if (direct.equals("right")) {

			anchor.helpX.bind(parent.layoutXProperty().add(bubble_width).add(ANCHOR_WIDTH * 2));
			anchor.helpY.bind(parent.layoutYProperty().add(initSpace + anchor.getOrder() * midSpace));

		} else {
			System.out.println("锚点方向错误！");
		}
	}

	/**
	 * 绘制传输线
	 * 能够递归的绘制
	 *
	 * AnchorNode tree 是树形结构，其属性data是树的根节点的值，也是anchor树的根节点（代表最初发送端的发送端口）
	 *
	 * @param tree 传输线的端点列表
	 */
	public void addLine(AnchorNode tree) {

		Anchor startAnchor = null;
		Anchor endAnchor;

		if (tree.getData() != null) {
			if (tree.getData().getDirect().equals("pub")) {
				startAnchor = tree.getData();
			}
		}
		if (tree.getChildList() == null || tree.getChildList().size() == 0) {
			return;
		} else {
			int childNumber = tree.getChildList().size();
			for (int i = 0; i < childNumber; i++) {
				AnchorNode child = tree.getChildList().get(i);

				endAnchor = child.getData();
				if (startAnchor != null) {
					paintLine(startAnchor, endAnchor);
				}

				addLine(child);
			}
		}

		return;

	}

	/**添加rp消息
	 *
	 */
	public void addLine(AnchorNode tree, ArrayList<RPMessage> msgs) {

		Anchor startAnchor = null;
		Anchor endAnchor;

		if (tree.getData() != null) {
			if (tree.getData().getDirect().equals("pub")) {
				startAnchor = tree.getData();
			}
		}
		if (tree.getChildList() == null || tree.getChildList().size() == 0) {
			return;
		} else {
			int childNumber = tree.getChildList().size();
			for (int i = 0; i < childNumber; i++) {
				AnchorNode child = tree.getChildList().get(i);

				endAnchor = child.getData();
				if (startAnchor != null) {
					paintLine(startAnchor, endAnchor, msgs); //rp消息
				}

				addLine(child);
			}
		}

		return;
	}

	/**
	 * 绘制传输线
	 * @param startAnchor
	 * @param endAnchor
	 */
	public void paintLine(Anchor startAnchor, Anchor endAnchor) {
		Bubble sParent = startAnchor.getMyParent();
		Bubble eParent = endAnchor.getMyParent();

		Connector line = null;
		boolean flag = true;

		if (!lines.isEmpty()) { // 如果全局连接线集合不为空

			for (int j = 0; j < lines.size(); j++) {
				Connector l = lines.get(j);
				if (l.getStartAnchor().equals(startAnchor) && l.getEndAnchor().equals(endAnchor)) {
					line = l;
					flag = false;
					break; // 找到了返回
				}
			}

		}
		if (lines.isEmpty() || flag) {

			if (!sParent.getChildren().contains(startAnchor)) {
				sParent.getChildren().add(startAnchor);
			}

			if (!eParent.getChildren().contains(endAnchor)) {
				eParent.getChildren().add(endAnchor);
			}

			bindAnchor(sParent, startAnchor, "right");
			bindAnchor(eParent, endAnchor, "left");

			line = new Connector(startAnchor, endAnchor);
//			double textX = (line.endXProperty().get() + line.startXProperty().get()) / 2;
//			double textY = (line.endYProperty().get() + line.startYProperty().get()) / 2;
//			Text text = new Text(textX, textY, "delay");

			Group group = new Group();

			if (sParent.equals(eParent)) {

				sParent.getLeftAnchors().remove(endAnchor);
				sParent.setLeftPortNum(sParent.getLeftPortNum() - 1);
				sParent.getRightAnchors().add(endAnchor);
				sParent.setRightPortNum(sParent.getRightPortNum() + 1);

				int rightNum = sParent.getRightAnchors().size();
				double newH = 30 + (rightNum - 1) * 5 + rightNum * 10;

				sParent.getCoverRectangle().setHeight(newH);
				sParent.getTitleLabel().setPrefHeight(newH);

				endAnchor.setOrder(endAnchor.getOrder() + rightNum - 1);

				endAnchor.setLayoutX(startAnchor.getLayoutX());
				endAnchor.setLayoutY(startAnchor.getLayoutY() + (endAnchor.getOrder() - startAnchor.getOrder()) * 15);

				endAnchor.helpX
						.bind(sParent.layoutXProperty().add(sParent.getCoverRectangle().getWidth()).add(ANCHOR_WIDTH));
				endAnchor.helpY.bind(sParent.layoutYProperty().add(initSpace + endAnchor.getOrder() * midSpace));

				group.getChildren().addAll(sParent, line);
//				group.getChildren().addAll(sParent, line, text);
			} else {
				group.getChildren().addAll(sParent, eParent, line);
//				group.getChildren().addAll(sParent, eParent, line, text);
			}

			line.setStartAnchor(startAnchor);
			line.setEndAnchor(endAnchor);
			line.toBack();
			AllLine.add(line); // 添加所有的line
			this.getChildren().add(group);

		}
	}

	/**
	 * 绘制传输线+高亮传输线
	 * @param startAnchor
	 * @param endAnchor
	 */
	public void paintLine(Anchor startAnchor, Anchor endAnchor, ArrayList<RPMessage> msgs) {
		Bubble sParent = startAnchor.getMyParent();
		Bubble eParent = endAnchor.getMyParent();

		Connector line = null;
		boolean flag = true;

		if (!lines.isEmpty()) { // 如果全局连接线集合不为空

			for (int j = 0; j < lines.size(); j++) {
				Connector l = lines.get(j);
				if (l.getStartAnchor().equals(startAnchor) && l.getEndAnchor().equals(endAnchor)) {
					line = l;
					flag = false;
					break; // 找到了返回
				}
			}

		}
		if (lines.isEmpty() || flag) {

			if (!sParent.getChildren().contains(startAnchor)) {
				sParent.getChildren().add(startAnchor);
			}

			if (!eParent.getChildren().contains(endAnchor)) {
				eParent.getChildren().add(endAnchor);
			}

			bindAnchor(sParent, startAnchor, "right");
			bindAnchor(eParent, endAnchor, "left");

			// 分析rpMessage
			line = new Connector(startAnchor, endAnchor, msgs); //毛修改
//			line = new Connector(startAnchor, endAnchor);

			Group group = new Group();

			if (sParent.equals(eParent)) {

				sParent.getLeftAnchors().remove(endAnchor);
				sParent.setLeftPortNum(sParent.getLeftPortNum() - 1);
				sParent.getRightAnchors().add(endAnchor);
				sParent.setRightPortNum(sParent.getRightPortNum() + 1);

				int rightNum = sParent.getRightAnchors().size();
				double newH = 30 + (rightNum - 1) * 5 + rightNum * 10;

				sParent.getCoverRectangle().setHeight(newH);
				sParent.getTitleLabel().setPrefHeight(newH);

				endAnchor.setOrder(endAnchor.getOrder() + rightNum - 1);

				endAnchor.setLayoutX(startAnchor.getLayoutX());
				endAnchor.setLayoutY(startAnchor.getLayoutY() + (endAnchor.getOrder() - startAnchor.getOrder()) * 15);

				endAnchor.helpX
						.bind(sParent.layoutXProperty().add(sParent.getCoverRectangle().getWidth()).add(ANCHOR_WIDTH));
				endAnchor.helpY.bind(sParent.layoutYProperty().add(initSpace + endAnchor.getOrder() * midSpace));

				group.getChildren().addAll(sParent, line);
			} else {
				group.getChildren().addAll(sParent, eParent, line);
			}

			line.setStartAnchor(startAnchor);
			line.setEndAnchor(endAnchor);
			line.toBack();
			AllLine.add(line); // 把新建的line添加到AllLine集合，在高亮的时候，就可以从AllLine中获取所有的line

			this.getChildren().add(group);

		}
	}

	/**
	 * 切换选中节点并更改样式
	 *
	 * @param node
	 * @param type
	 */
	public void toggleSelected(Node node, String type) {
		if (type.equals("Bubble")) {
			if (((Bubble) node).isSelected()) {
				DropShadow dropShadow = new DropShadow();
				dropShadow.setColor(Color.DARKGRAY);
				dropShadow.setOffsetX(3);// 水平方向，0则向左右两侧，正则向右，负则向左
				dropShadow.setOffsetY(3);
				dropShadow.setSpread(0.1);
				dropShadow.setRadius(10);
				dropShadow.setWidth(5);

				((Bubble) node).coverRectangle.setEffect(dropShadow);
				((Bubble) node).coverRectangle.setStrokeWidth(3);
			} else {
				((Bubble) node).coverRectangle.setEffect(null);
				((Bubble) node).coverRectangle.setStrokeWidth(2);
			}
		} else {
			if (((Anchor) node).isSelected()) {
				((Anchor) node).setStroke(Color.RED);
				((Anchor) node).setStrokeWidth(2.5);
				((Anchor) node).setFill(Color.YELLOW);
				((Anchor) node).toFront();
			} else {
				((Anchor) node).setFill(Color.BLACK);
				((Anchor) node).setStrokeWidth(0);
			}
		}

	}

	public ArrayList<Bubble> getBubbleArrayList() {
		return bubbleArrayList;
	}

	public Node getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(Node selectedNode) {
		this.selectedNode = selectedNode;
	}

	public String getSelectedNodeType() {
		return selectedNodeType;
	}

	public void setSelectedNodeType(String selectedNodeType) {
		this.selectedNodeType = selectedNodeType;
	}

	public BubbleNode getBTree() {
		return bTree;
	}

	public void setBTree(BubbleNode bTree) {
		this.bTree = bTree;
	}

	public AnchorNode getATree() {
		return aTree;
	}

	public void setATree(AnchorNode aTree) {
		this.aTree = aTree;
	}

}
