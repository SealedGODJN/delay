package edu.nwpu.edap.edapplugin.Delay;

import com.careri.as.businessmodel.model.A653ApplicationComponentType;
import com.careri.as.businessmodel.model.RPType;
import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.device.EndAppNode;
import edu.nwpu.edap.edapplugin.bean.port.A653QueuingPortNode;
import edu.nwpu.edap.edapplugin.bean.port.PortNode;
import edu.nwpu.edap.edapplugin.library.RPMessageLibrary;
import edu.nwpu.edap.edapplugin.test.LoadICDDataTest;

import java.util.ArrayList;
import java.util.List;

public class PCIQueuingDelay {
    private EndAppNode pubEndApp;

    /**
     * 计算PCI发送端排队延迟
     * @param rpMessage
     * @return
     */
    public double calculatePCIQueuingDelay(RPMessage rpMessage) {
        double PCIQueuingDelay = 0;
        //发送端类型为A653
        if(rpMessage.getPubEndApp().equals("A653Application")){
            PortNode pubPort = rpMessage.getPubEndApp().getPort();
            //发送端端口类型
            if (pubPort.getType().equals("A653QueuingPort")) {
                // 需要端口队列长度
                int length = ((A653QueuingPortNode) pubPort).getQueueLength();
//                    double portRate = pubPort.getRate(); //端口发送速率也不知道有没有

                if (length == 0) {
                    System.out.println("参数错误！");
                    return 0;
                }
                //通过端口Guid获取经过该端口的所有消息
                LoadICDDataTest instance = LoadICDDataTest.getInstance();
                // 发送端端口Guid
                String pubPortGuid = pubPort.getGuid();
                A653ApplicationComponentType a653pub =
                        (A653ApplicationComponentType) instance.getSoftwareInstanceByPortGuid(pubPortGuid);
                if (a653pub instanceof A653ApplicationComponentType) {
                    List<RPType> a653rpMessage = a653pub.getRP();//通过A653ApplicationComponentType中的方法获取List<RPType>

                    ArrayList<RPMessage> allA653RP = new ArrayList<>();//用来装所有的RP消息
                    //遍历list集合找到对应的所有RP消息
                    for (RPType A653PubRP : a653rpMessage) {
                        RPMessage rp = RPMessageLibrary.getRpMessageByRPGuid(A653PubRP.getGuidDef());
                        allA653RP.add(rp);
                    }

                    //遍历RP消息计算排队时间
                    for (RPMessage rp : allA653RP){
                        PCIQueuingDelay = 0;
                    }
                }
            } else {
                System.out.println("==========该端口是A653SamplingPort==========");
            }
        }else{

        }
        return PCIQueuingDelay;
    }
}
