package edu.nwpu.edap.edapplugin.Service;

import com.careri.as.workbench.api.datamodel.logicmodel.ICDHierarchy;
import com.careri.as.workbench.api.datamodel.logicmodel.ICDObject;
import javafx.scene.control.TreeItem;

/**
 * 获取ICD数据信息、结构信息和TreeItem
 * @author zhang yifu
 * @version 1.0
 * @date 2021.12.20
 */
public interface ICDStructureService {

	/**
	 * 获得workbench的工作空间
	 * @return
	 */
	public String getWorkBenchDir();
	
	
	/**
	 * 获得当前project名称
	 * @return
	 */
	public String getProjectName();
	
	/**
	 * 获取ICD目录树的结构
	 * @return
	 */
	public TreeItem<ICDHierarchy> getICDHierarchyStructure();
	
	/**
	 * 获取ICDObject结构
	 * @return
	 */
	public TreeItem<ICDObject> getICDObjectStructure(ICDObject icdObject);
}
