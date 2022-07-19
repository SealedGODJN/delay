package edu.nwpu.edap.edapplugin.ui.myTreeStrc;

import java.util.ArrayList;
import java.util.List;

import edu.nwpu.edap.edapplugin.ui.Anchor;

public class AnchorNode {

	private Anchor data;

	private ArrayList<AnchorNode> childList;
	
	private int level;
	
	
	public AnchorNode() {
		this.data = null;
		this.childList = new ArrayList<AnchorNode>();
		this.level = 1;
	}

	public AnchorNode(Anchor data) {
		this.data = data;
		this.childList = new ArrayList<AnchorNode>();
	}

	public AnchorNode(Anchor data, ArrayList<AnchorNode> childList) {
		this.data = data;
		this.childList = childList;
	}

	public Anchor getData() {
		return data;
	}

	public void setData(Anchor Data) {
		this.data = Data;
	}

	public List<AnchorNode> getChildList() {
		return childList;
	}

	public void setChildList(ArrayList<AnchorNode> childList) {
		this.childList = childList;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String toString(int level,boolean isFirst) {
		String result = "";
		for(int i=0;i<level;i++) {
			if(isFirst) {
				result += "│   ";
			}else {
				result += "    ";
			}
		}
		
		if(isFirst) {
			result += "├── ";
		} else {
			result += "└── ";
		}
		if (this.getData() != null) {
			if(this.getData().getPortNode() != null) {
				result += this.getData().getPortNode().getName() +"\n";
			} else {
				result += "sw port" + this.getData().getPortSwNode() + "\n";
			}
		}
		if (this.getChildList() == null || this.getChildList().size() == 0) {
			result += "";
		} else {
			int childNumber = this.getChildList().size();
			level++;
//			System.out.println(level);
			for (int i = 0; i < childNumber; i++) {
				AnchorNode child = this.getChildList().get(i);
				result += child.toString(level,i==0);
			}
		}
		
		return result;
		
	}
	
	

}
