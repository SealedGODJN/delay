package edap.nwpu.edap.edapplugin;

import edap.nwpu.edap.edapplugin.Delay.Async.AsyncDelay;
import edap.nwpu.edap.edapplugin.bean.device.EndAppNode;
import edap.nwpu.edap.edapplugin.bean.device.RIUAppNode;
import edap.nwpu.edap.edapplugin.bean.port.A653QueuingPortNode;
import edap.nwpu.edap.edapplugin.bean.port.A653SamplingPortNode;
import edap.nwpu.edap.edapplugin.bean.port.A664QueuingPortNode;
import edap.nwpu.edap.edapplugin.bean.port.PortNode;
import edap.nwpu.edap.edapplugin.bean.RPMessage;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TestAsyncDelay {

    /**
     * 1t-1t-1t-2-1t
     * 场景：
     * A653->A653
     * A653SamplingPort A653SamplingPort
     */
    @Test
    public void testCalculateAsyncDelay1t_1t_1t_2_1t() {
        // 新建rp消息
        RPMessage rpMessage = new RPMessage();

        RIUAppNode pubRDIU = new RIUAppNode();
        rpMessage.setPubRDIU(pubRDIU);

        RIUAppNode subRDIU = new RIUAppNode();
        rpMessage.setSubRDIU(subRDIU);

        // 设置发送端终端
        EndAppNode pubEndApp = new EndAppNode();
        // 设置发送端终端为A653Application
        pubEndApp.setType("A653Application");
        //设置发送端的端口
        PortNode pubPort = new A653SamplingPortNode("A653SamplingPort", "", "");
        pubEndApp.setPort(pubPort);
//        pubPort.setType("A653SamplingPort");
        double refreshPeriodPub = 10.0;
        pubRDIU.setRefreshPeriod(BigDecimal.valueOf(refreshPeriodPub));
        rpMessage.setPubEndApp(pubEndApp);

        // 设置接收端终端
        EndAppNode subEndApp = new EndAppNode();
        // 设置接收端终端为A653Application
        subEndApp.setType("ApplicationComponent");

        //设置接收端的端口
        PortNode subPort = new A653SamplingPortNode("A653SamplingPort","1","1");
        subEndApp.setPort(subPort);
        subPort.setType("A653SamplingPort");
        // PPub == PSub
        int samplePeriodSub = 10;
        subEndApp.setSamplePeriod(samplePeriodSub);

        rpMessage.setSubEndApp(subEndApp);

        double delay = AsyncDelay.calculateAsyncDelay(rpMessage);

        double expect = 10.0;

        assertEquals(expect, delay, 0.01);
    }

    /**
     *1t-1t-1t-2-2t
     *
    */
    @Test
    public void testCalculateAsyncDelay1t_1t_1t_2_2t() {
        // 新建rp消息
        RPMessage rpMessage = new RPMessage();

        RIUAppNode pubRDIU = new RIUAppNode();
        rpMessage.setPubRDIU(pubRDIU);

        RIUAppNode subRDIU = new RIUAppNode();
        rpMessage.setSubRDIU(subRDIU);

        // 设置发送端终端
        EndAppNode pubEndApp = new EndAppNode();
        // 设置发送端终端为A653Application
        pubEndApp.setType("A653Application");
        //设置发送端的端口
        PortNode pubPort = new A653SamplingPortNode("A653SamplingPort", "1", "1");
        pubEndApp.setPort(pubPort);
        pubPort.setType("A653SamplingPort");
        double refreshPeriodPub = 5.0;
        pubEndApp.setRefreshPeriod(BigDecimal.valueOf(refreshPeriodPub));
        rpMessage.setPubEndApp(pubEndApp);

        // 设置接收端终端
        EndAppNode subEndApp = new EndAppNode();
        // 设置接收端终端为A653Application
        subEndApp.setType("ApplicationComponent");

        // 设置接收端的端口
        PortNode subPort = new A653SamplingPortNode("A653SamplingPort", "1", "1");
        subEndApp.setPort(subPort);
        subPort.setType("A653SamplingPort");
        // PPub < PSub
        int samplePeriodSub = 10;
        subEndApp.setSamplePeriod(samplePeriodSub);

        rpMessage.setSubEndApp(subEndApp);

        double delay = AsyncDelay.calculateAsyncDelay(rpMessage);

        double expect = 5.0;

        assertEquals(expect, delay, 0.01);
    }
    /**
     * 1t-1t-1t-2-1t
     * 场景：
     * A653->A653
     * A653QueuingPort A653SamplingPort
    */
    @Test
    public void testCalculateAsyncDelay1t_1t_2() {
        // 新建rp消息
        RPMessage rpMessage = new RPMessage();

        RIUAppNode pubRDIU = new RIUAppNode();
        rpMessage.setPubRDIU(pubRDIU);

        RIUAppNode subRDIU = new RIUAppNode();
        rpMessage.setSubRDIU(subRDIU);
        // 获取queueLength
        A664QueuingPortNode a664QueuingPortNode = new A664QueuingPortNode("A653QueuingPort", "1", "1");
        a664QueuingPortNode.setQueueLength(3);

        // 设置发送端终端
        EndAppNode pubEndApp = new EndAppNode();
        // 设置发送端终端为A653Application
        pubEndApp.setType("A653Application");
        //设置发送端的端口
        PortNode pubPort = new A653QueuingPortNode("A653QueuingPort", "1", "1");
        pubEndApp.setPort(pubPort);
        pubPort.setType("A653QueuingPort");
        double refreshPeriodPub = 5.0;
        pubEndApp.setRefreshPeriod(BigDecimal.valueOf(refreshPeriodPub));
        rpMessage.setPubEndApp(pubEndApp);

        // 设置接收端终端
        EndAppNode subEndApp = new EndAppNode();
        // 设置接收端终端为A653Application
        subEndApp.setType("ApplicationComponent");

        // 设置接收端的端口
        PortNode subPort = new A653SamplingPortNode("A653SamplingPort", "1", "1");
        subEndApp.setPort(subPort);
        subPort.setType("A653SamplingPort");
        // PPub == PSub
        int samplePeriodSub = 8;
        subEndApp.setSamplePeriod(samplePeriodSub);

        rpMessage.setSubEndApp(subEndApp);

        double delay = AsyncDelay.calculateAsyncDelay(rpMessage);

        double expect = 8.0;

        assertEquals(expect, delay, 0.01);
    }

}
