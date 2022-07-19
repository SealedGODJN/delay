package edu.nwpu.edap.edapplugin.Delay;

import com.careri.as.businessmodel.model.A653ApplicationComponentClassType;
import com.careri.as.businessmodel.model.A653ApplicationComponentType;
import com.careri.as.icd.WorkbenchContext;
import com.careri.as.workbench.api.datamodel.logicmodel.ICDHierarchy;
import com.careri.as.workbench.api.icd.ICDDataService;
import com.careri.as.workbench.api.icd.impl.ICDDataServiceImpl;
import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.device.EndAppNode;
import edu.nwpu.edap.edapplugin.bean.device.GPMAppNode;
import edu.nwpu.edap.edapplugin.bean.port.PortNode;
import edu.nwpu.edap.edapplugin.test.LoadICDDataTest;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A653Delay {

    private A653ApplicationComponentClassType pubParition;
    private A653ApplicationComponentClassType subParition;
    private List<A653ApplicationComponentClassType> pubAllParition;
    private List<A653ApplicationComponentClassType> subAllParition;

    public double calculateA653Delay(GPMAppNode pubEndApp, GPMAppNode subEndApp, boolean isLocal) {
        double delay = 0;
        // 找发送端和接收端
        // 发送端
        PortNode pubPort = pubEndApp.getPort();
        // 接收端
        PortNode subPort = subEndApp.getPort();
        // 不过网通信
        if(isLocal) {
            Iterator<A653ApplicationComponentClassType> iterator = pubAllParition.iterator();
            while (iterator.hasNext()) {
                A653ApplicationComponentClassType temp = iterator.next();
//                if ( temp.id.equals(pubEndApp.getPubParition().getId()) ) {
//                    break;
//                }
            }
            while (iterator.hasNext()) {
                A653ApplicationComponentClassType temp = iterator.next();
//                delay += temp.duration;
//                if ( temp.id.equals(subEndApp.getSubParition().getId()) ) {
//                    break;
//                }
            }
            return delay;
        }

        /**
         * 过网通信
         *
         * 只需要计算接收端GPM的调度时间
         */
        for (A653ApplicationComponentClassType parition : subAllParition) {
//            delay += parition.duration;
        }
        delay /= subAllParition.size();

        return delay;
    }

    static List<ICDHierarchy> ATAList;

    /**
     * 根据icdDataService和当前工程目录，来获取ICD数据的root
     * @return 所有的ATA条目
     */
    public static List<ICDHierarchy> getATAList() {
        WorkbenchContext workbenchContext = WorkbenchContext.getInstance();
        String projectName = workbenchContext.getCurrentProject();
        ICDDataService icdDataService = new ICDDataServiceImpl();
        ICDHierarchy rootHierarchy = icdDataService.retrieveICDHierarchy(projectName);
        ICDHierarchy modelSystemElements = rootHierarchy.getChildren().get(0);
        modelSystemElements = modelSystemElements.getChildren().get(0);
//        ATAList = modelSystemElements.getChildren();
        return modelSystemElements.getChildren();
    }
    public static double calculateA653ScheduleDelay(RPMessage rpMessage) {
        double scheduleDelay = 0;
        ATAList = getATAList();

        EndAppNode sub = rpMessage.getSubEndApp();
        if (sub == null) {
            System.out.println("===========接收端终端不存在===========");
            return scheduleDelay;
        }

        //发送端类型为A653
        if (sub.getType().equals("A653Application")) {
            String subFullATA = sub.getAta();
            String subATA = "";
            Pattern p = Pattern.compile("\\d");
            Matcher m = p.matcher(subFullATA);
            while(m.find()) {
                subATA = m.group();
            }
            if (subATA.equals("")) {
                System.out.println("===========找不到接收端终端的ATA号===========");
                return scheduleDelay;
            }
            ICDHierarchy targetATA = null;
            for (ICDHierarchy ata : ATAList) {
                String ataName = ata.getName();
                if (ataName.contains(subATA)) {
                    targetATA = ata;
                }
            }
            if (targetATA == null) {
                System.out.println("===========找不到接收端终端的ATA对应的ATA目录===========");
                return scheduleDelay;
            }
            ICDHierarchy hostedApplication = null;
            List<ICDHierarchy> targetATAChildren = targetATA.getChildren();
            for (ICDHierarchy child : targetATAChildren) {
                String childName = child.getName();
                if (childName.equals("Hosted Applications")) {
                     hostedApplication = child;
                }
            }

            if (hostedApplication == null) {
                System.out.println("===========找不到接收端终端的ATA中的Hosted Applications目录===========");
                return scheduleDelay;
            }
            List<ICDHierarchy> hostedApplicationChildren = hostedApplication.getChildren();
            ICDHierarchy instances = null;
            for (ICDHierarchy child : hostedApplicationChildren) {
                String childName = child.getName();
                if (childName.equals("Instances")) {
                    instances = child;
                }
            }
            if (instances == null) {
                System.out.println("===========找不到接收端终端的ATA中的Hosted Applications目录中的instances===========");
                return scheduleDelay;
            }
            List<ICDHierarchy> allXML = instances.getChildren();
            for (ICDHierarchy xml : allXML) {
                ICDHierarchy application = xml.getChildren().get(0);
                String type = application.getType();
                if (type.equals("A653ApplicationComponent")) {
                    ICDHierarchy a653ApplicationComponent = application.getChildren().get(0);
                    String guid = a653ApplicationComponent.getEntity().getGuid();
                    A653ApplicationComponentClassType a653 = (A653ApplicationComponentClassType) LoadICDDataTest.getInstance().getSoftwareClassByGuid(guid);
                    int computeTime = a653.getComputeTime().intValue();
                    scheduleDelay += computeTime;
                }
            }
        }

        return scheduleDelay;
    }
}
