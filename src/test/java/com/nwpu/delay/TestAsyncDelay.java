package com.nwpu.delay;

import com.nwpu.delay.Delay.Async.AsyncDelay;
import com.nwpu.delay.Message.*;
import com.nwpu.delay.Message.port.A653QueuingPortNode;
import com.nwpu.delay.Message.port.A653SamplingPortNode;
import com.nwpu.delay.Message.port.A664QueuingPortNode;
import com.nwpu.delay.Message.port.PortNode;
import org.junit.Test;

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
        PortNode pubPort = new A653SamplingPortNode();
        pubEndApp.setPort(pubPort);
        pubPort.setType("A653SamplingPort");
        double refreshPeriodPub = 10.0;
        pubPort.setRefreshPeriod(refreshPeriodPub);
        rpMessage.setPubEndApp(pubEndApp);

        // 设置接收端终端
        EndAppNode subEndApp = new EndAppNode();
        // 设置接收端终端为A653Application
        subEndApp.setType("ApplicationComponent");

        //设置接收端的端口
        PortNode subPort = new A653SamplingPortNode();
        subEndApp.setPort(subPort);
        subPort.setType("A653SamplingPort");
        // PPub == PSub
        double samplePeriodSub = 10.0;
        subPort.setSamplePeriod(samplePeriodSub);

        rpMessage.setSubEndApp(subEndApp);

        AsyncDelay asyncDelay = new AsyncDelay();
        double delay = asyncDelay.calculateAsyncDelay(rpMessage);

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
        PortNode pubPort = new A653SamplingPortNode();
        pubEndApp.setPort(pubPort);
        pubPort.setType("A653SamplingPort");
        double refreshPeriodPub = 5.0;
        pubPort.setRefreshPeriod(refreshPeriodPub);
        rpMessage.setPubEndApp(pubEndApp);

        // 设置接收端终端
        EndAppNode subEndApp = new EndAppNode();
        // 设置接收端终端为A653Application
        subEndApp.setType("ApplicationComponent");

        // 设置接收端的端口
        PortNode subPort = new A653SamplingPortNode();
        subEndApp.setPort(subPort);
        subPort.setType("A653SamplingPort");
        // PPub < PSub
        double samplePeriodSub = 10.0;
        subPort.setSamplePeriod(samplePeriodSub);

        rpMessage.setSubEndApp(subEndApp);

        AsyncDelay asyncDelay = new AsyncDelay();
        double delay = asyncDelay.calculateAsyncDelay(rpMessage);

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
        A664QueuingPortNode a664QueuingPortNode = new A664QueuingPortNode();
        a664QueuingPortNode.setQueueLength(3);

        // 设置发送端终端
        EndAppNode pubEndApp = new EndAppNode();
        // 设置发送端终端为A653Application
        pubEndApp.setType("A653Application");
        //设置发送端的端口
        PortNode pubPort = new A653QueuingPortNode();
        pubEndApp.setPort(pubPort);
        pubPort.setType("A653QueuingPort");
        double refreshPeriodPub = 5.0;
        pubPort.setRefreshPeriod(refreshPeriodPub);
        rpMessage.setPubEndApp(pubEndApp);

        // 设置接收端终端
        EndAppNode subEndApp = new EndAppNode();
        // 设置接收端终端为A653Application
        subEndApp.setType("ApplicationComponent");

        // 设置接收端的端口
        PortNode subPort = new A653SamplingPortNode();
        subEndApp.setPort(subPort);
        subPort.setType("A653SamplingPort");
        // PPub == PSub
        double samplePeriodSub = 8.0;
        subPort.setSamplePeriod(samplePeriodSub);

        rpMessage.setSubEndApp(subEndApp);

        AsyncDelay asyncDelay = new AsyncDelay();
        double delay = asyncDelay.calculateAsyncDelay(rpMessage);

        double expect = 8.0;

        assertEquals(expect, delay, 0.01);
    }

}
