package edu.nwpu.edap.edapplugin.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;


public class ProgressBarHandler {

	private static final String PART_ID = "edu.nwpu.edap.progress";
	public static MPart part;
	
	@Execute
	public void showProgressBar(EPartService partService, MApplication application, EModelService modelService) {
		part = partService.findPart(PART_ID);
		if(part == null) {
			MPartStack stack = (MPartStack)modelService.find("workbench.app.partstack.bottom", application);
			part = MBasicFactory.INSTANCE.createPart();
			part.setElementId(PART_ID);
			part.setContributionURI("bundleclass://edu.nwpu.edap.edapplugin/edu.nwpu.edap.edapplugin.Service.Impl.ProgressServiceImpl");
			part.setCloseable(true);
			stack.getChildren().add(part);
			part.setLabel("任务进度");
			part.setIconURI("platform:/plugin/edu.nwpu.edap.edapplugin/icons/Sample.png");
			stack.setSelectedElement(part);
		}else {
			partService.showPart(PART_ID, PartState.ACTIVATE);//如果已经创建，则根据PART_ID显示PART
		}
	}
}
