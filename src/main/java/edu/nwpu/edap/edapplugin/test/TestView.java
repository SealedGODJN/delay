package edu.nwpu.edap.edapplugin.test;

import com.careri.as.businessmodel.service.BusinessModelService;
import com.careri.as.businessmodel.service.impl.BusinessModelServiceImpl;
import com.careri.as.workbench.api.misc.LogService;
import com.careri.as.workbench.api.misc.impl.LogServiceImpl;
import edu.nwpu.edap.edapplugin.bean.port.A429PortNode;
import edu.nwpu.edap.edapplugin.bean.port.PortNode;
import edu.nwpu.edap.edapplugin.handlers.UIControlHandler;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class TestView {
	
	private Node node;
	private UIControlHandler mainView;
	
	public void setMainPane(UIControlHandler uiControlHandler) {
		this.mainView = uiControlHandler;
	}
	
	public Node getRoot() {
		return node;
	}
	
	public TestView(){
		execute();
	}
	
	private void execute() {
		BorderPane pane = new BorderPane();
		
		calculate();
		
		System.out.println("测试结束");
		node = pane;
	}
	
	private void calculate() {
		try {
			BusinessModelService service = new BusinessModelServiceImpl();
			
			List<PortNode> portNodes = new ArrayList<PortNode>();
			A429PortNode a429PortNode = new A429PortNode("A429Port","HF_1","guid-3434-ds-4");
			a429PortNode.setPhysPortName("physName");
			a429PortNode.setBiteRate("100");
			portNodes.add(a429PortNode);
			PortNode portNode = portNodes.get(0);
			
			LogService service1 = new LogServiceImpl("EDAP1");
			service1.debug("");
			System.out.println(portNodes.toString());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}
