package edu.nwpu.edap.edapplugin.handlers;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

/**
 * Control the view of the plug-in, establish the scene part of the IMA plateform
 * @author zhang yifu
 * @version 1.0
 * @date 2021.12.15
 */
public class OpenViewHandler {
	
	private static final String PART_ID = "edu.nwpu.edap.edapplugin";
	
	public void execute(EPartService partService, MApplication application, EModelService modelService) {
		
		System.out.println("The plugin is openning the whole scene...");
		MPart part = partService.findPart(PART_ID);
		if(part == null) {
			System.out.println("the view has shown");
			MPartStack stack = (MPartStack)modelService.find("workbench.app.partstack.center", application);
			part = MBasicFactory.INSTANCE.createPart();
			part.setElementId(PART_ID);
			part.setContributionURI("bundleclass://edu.nwpu.edap.edapplugin/edu.nwpu.edap.edapplugin.handlers.UIControlHandler");
			part.setCloseable(true);
			stack.getChildren().add(part);
			part.setLabel("End-to-end Delay Analysis");
			part.setIconURI("platform:/plugin/edu.nwpu.edap.edapplugin/icons/Sample.png");
			stack.setSelectedElement(part);
		}else {
			System.out.println("This part has existed!");
			partService.showPart(PART_ID, PartState.ACTIVATE);//如果已经创建，则根据PART_ID显示PART
		}
	}
	
}
