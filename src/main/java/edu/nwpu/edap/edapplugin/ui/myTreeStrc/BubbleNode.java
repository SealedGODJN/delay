package edu.nwpu.edap.edapplugin.ui.myTreeStrc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.apache.commons.compress.archivers.ar.ArArchiveEntry;

import edu.nwpu.edap.edapplugin.ui.Bubble;

/**
 * 
 * @author Administrator
 *
 */
public class BubbleNode {

	private Bubble data;

	private ArrayList<BubbleNode> childList;

	private int level;

	/**
	 * 无参构造函数
	 */
	public BubbleNode() {
		this.data = null;
		this.level = 1;
		this.childList = new ArrayList<BubbleNode>();
	}

	/**
	 * 构造函数
	 * @param Data
	 */
	public BubbleNode(Bubble data) {
		this.data = data;
		this.childList = new ArrayList<BubbleNode>();
	}

	public BubbleNode(Bubble data, ArrayList<BubbleNode> childList) {
		this.data = data;
		this.childList = childList;
	}

	public String toString(int level, boolean isFirst) {
		String result = "";
		for (int i = 0; i < level; i++) {
			if (isFirst) {
				result += "│   ";
			} else {
				result += "    ";
			}
		}

		if (isFirst) {
			result += "├── ";
		} else {
			result += "└── ";
		}
		if (this.getData() != null) {
			result += this.getData().getHardware().getName() + "\n";
//			result += this.getLevel() + "\n";
		}
		if (this.getChildList() == null || this.getChildList().size() == 0) {
			result += "";
		} else {
			int childNumber = this.getChildList().size();
			level++;
			for (int i = 0; i < childNumber; i++) {
				BubbleNode child = this.getChildList().get(i);
				result += child.toString(level, i == 0);
			}
		}

		return result;

	}

	public BubbleNode getTreeNode(Bubble target) {

		if (this.getData().equals(target))
			return this;
		if (this.getChildList() == null || this.getChildList().size() == 0) {
			return null;
		} else {
			int childNumber = this.getChildList().size();
			for (int i = 0; i < childNumber; i++) {
				BubbleNode child = this.getChildList().get(i);
				BubbleNode resultNode = child.getTreeNode(target);
				if (resultNode != null) {
					return resultNode;
				}
			}
			return null;
		}

	}

	/**
	 * 设置节点层数
	 * 
	 * @param level
	 */
	public void setNodeLevel(int level) {
		if (this.getData() != null) {
			this.setLevel(level);
		}
		if (this.getChildList() == null || this.getChildList().size() == 0) {
			return;
		} else {
			int childNumber = this.getChildList().size();
			level++;
			for (int i = 0; i < childNumber; i++) {
				BubbleNode child = this.getChildList().get(i);
				child.setNodeLevel(level);
			}
		}
	}

	/**
	 * 获得最深层数
	 * @return
	 */
	public int getMaxLevel() {
		
		int maxL = -1;
		List<Bubble> res = new ArrayList<>();
		Stack<BubbleNode> stack = new Stack<>();
		stack.push(this);
		while (!stack.isEmpty()) {
			BubbleNode cur = stack.pop();
			if(cur.getLevel() > maxL) {
				maxL = cur.getLevel();
			}
			res.add(0, cur.getData());
			for (BubbleNode child : cur.getChildList()) {
				if (child!= null) {
					stack.push(child);
				}
			}
		}
		return maxL;
	}
	
	public ArrayList<Bubble> getSpeciLevelNode(int level) {

		Queue<BubbleNode> queue = new LinkedList<BubbleNode>();

		queue.offer(this);
		ArrayList<Bubble> res = new ArrayList<Bubble>();
		
		while (!queue.isEmpty()) {
			BubbleNode cur = queue.poll();
			if(cur.getLevel() == level) {
				res.add(cur.getData());
			}
			
			for (BubbleNode child : cur.getChildList()) {
				if (child!= null) {
					queue.offer(child);
				}
			}
		}
		
		return res;
	}
	
	public List<BubbleNode> getChildList() {
		return childList;
	}

	public void setChildList(ArrayList<BubbleNode> childList) {
		this.childList = childList;
	}
	
	public Bubble getData() {
		return data;
	}

	public void setData(Bubble data) {
		this.data = data;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
