package edu.nwpu.edap.edapplugin.handlers;


import edu.nwpu.edap.edapplugin.Service.CalculateDelayService;
import edu.nwpu.edap.edapplugin.Service.ConstructSceneService;
import edu.nwpu.edap.edapplugin.Service.Impl.CalculateDelayServiceImpl;
import edu.nwpu.edap.edapplugin.Service.Impl.ConstructSceneServiceImpl;

public class ConstructSceneHandler {

	ConstructSceneService constructSceneService;
	CalculateDelayService calculateDelayService;
	
	public ConstructSceneHandler() {
		this.constructSceneService = new ConstructSceneServiceImpl();
		this.calculateDelayService = new CalculateDelayServiceImpl();
	}
	
	public void execute() {
		System.out.println("开始进行场景构建和延迟计算");
	}
}
