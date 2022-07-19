package edu.nwpu.edap.edapplugin.bean;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class BottomPartValue {

    public static BottomPartValue uniqueInstance;

    private EPartService partService;
    private MApplication application;
    private EModelService modelService;

    public static BottomPartValue getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new BottomPartValue();
        }
        return uniqueInstance;
    }

    public void setValue(EPartService partService, MApplication application, EModelService modelService) {
        this.partService = partService;
        this.application = application;
        this.modelService = modelService;
    }

    public EPartService getPartService() {
        return partService;
    }

    public void setPartService(EPartService partService) {
        this.partService = partService;
    }

    public MApplication getApplication() {
        return application;
    }

    public void setApplication(MApplication application) {
        this.application = application;
    }

    public EModelService getModelService() {
        return modelService;
    }

    public void setModelService(EModelService modelService) {
        this.modelService = modelService;
    }

}
