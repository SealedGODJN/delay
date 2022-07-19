package edu.nwpu.edap.edapplugin.Service.Impl;

import com.careri.as.icd.WorkbenchContext;
import com.careri.as.workbench.api.datamodel.logicmodel.ICDHierarchy;
import com.careri.as.workbench.api.datamodel.logicmodel.ICDObject;
import com.careri.as.workbench.api.icd.ICDDataService;
import com.careri.as.workbench.api.icd.impl.ICDDataServiceImpl;
import edu.nwpu.edap.edapplugin.Service.ICDStructureService;
import javafx.scene.control.TreeItem;

import java.util.LinkedList;
import java.util.Queue;

public class ICDStructureServiceImpl implements ICDStructureService{

	private String workBenchDir;//dir of current project working space
	private String projectName;// name of current project
	
	public ICDStructureServiceImpl() {
		WorkbenchContext workbenchContext = WorkbenchContext.getInstance();
		workBenchDir = workbenchContext.getWorkingDirectory();
		projectName = workbenchContext.getCurrentProject();
	}
	
	@Override
	public String getWorkBenchDir() {
		return workBenchDir;
	}

	@Override
	public String getProjectName() {
		return projectName;
	}

	@Override
	public TreeItem<ICDHierarchy> getICDHierarchyStructure() {
		ICDDataService icdDataService = new ICDDataServiceImpl();
		ICDHierarchy rootHierarchy = icdDataService.retrieveICDHierarchy(projectName);
		
		TreeItem<ICDHierarchy> root = new TreeItem<ICDHierarchy>(rootHierarchy);
		TreeItem<ICDHierarchy> rootItem = root;
		
		Queue<TreeItem<ICDHierarchy>> queueItems = new LinkedList<TreeItem<ICDHierarchy>>();
		Queue<ICDHierarchy> queueICDs = new LinkedList<ICDHierarchy>();
		
		queueICDs.add(rootHierarchy);
		queueItems.add(rootItem);
		
		while(!queueICDs.isEmpty()) {
			ICDHierarchy fatherHierarchy = queueICDs.remove();
			TreeItem<ICDHierarchy> fatherItem = queueItems.remove();
			
			for(ICDHierarchy childHierarchy:fatherHierarchy.getChildren()) {
				TreeItem<ICDHierarchy> childItem = new TreeItem<ICDHierarchy>(childHierarchy);
				fatherItem.getChildren().add(childItem);
				queueICDs.add(childHierarchy);
				queueItems.add(childItem);
			}
		}
		return root;
	}

	@Override
	public TreeItem<ICDObject> getICDObjectStructure(ICDObject icdObject) {
		TreeItem<ICDObject> root = new TreeItem<ICDObject>(icdObject);
		TreeItem<ICDObject> rootItem = root;
		
		Queue<TreeItem<ICDObject>> queueItems = new LinkedList<TreeItem<ICDObject>>();
		Queue<ICDObject> queueICDs = new LinkedList<ICDObject>();
		
		queueICDs.add(icdObject);
		queueItems.add(rootItem);
		
		while(!queueICDs.isEmpty()) {
			ICDObject fatherObject = queueICDs.remove();
			TreeItem<ICDObject> fatherItem = queueItems.remove();
			
			for(ICDObject childObject:fatherObject.getChildren()) {
				TreeItem<ICDObject> childItem = new TreeItem<ICDObject>(childObject);
				fatherItem.getChildren().add(childItem);
				queueICDs.add(childObject);
				queueItems.add(childItem);
			}
		}
		root.setExpanded(true);
		return root;
	}

	
}
