package edu.nwpu.edap.edapplugin.bean.switchconfig;

import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.bean.adn.ADNPart;
import edu.nwpu.edap.edapplugin.bean.adn.SwitchNode;
import edu.nwpu.edap.edapplugin.bean.device.EndAppNode;
import edu.nwpu.edap.edapplugin.bean.device.RIUAppNode;
import edu.nwpu.edap.edapplugin.bean.port.A429PortNode;
import edu.nwpu.edap.edapplugin.bean.port.CANPortNode;
import edu.nwpu.edap.edapplugin.bean.port.HFSamplingPortNode;
import edu.nwpu.edap.edapplugin.bean.port.PortNode;

import java.util.ArrayList;
import java.util.List;

public class TestRPMessage{

    public RPMessage getExample(){
        RPMessage rpMessage = new RPMessage();
        rpMessage.setRpGuid("ad6f44e9f-566a-4c8eef-bf4d99b4bd92a");
        rpMessage.setPubGuid("ad7f8945a-6658-44fd-8a45-b7cac8c18d27a");

        //定义接收端端系统类
//        Hardware subEndHW = new Hardware();
//        subEndHW.setType("LRU");
//        subEndHW.setName("IASC1");
//        subEndHW.setGuid("aC47B4481C-8E50-4f9f-9D6F-8A996F7C9F87a");
//        PortNode subEsPortNode = new PortNode("A429Port","name","ac123b1a6-3dbf-43d7-afe4-ab46b7496564a");
        PortNode subLRUPortNode = new A429PortNode("A429Port","name","ac123b1a6-3dbf-43d7-afe4-ab46b7496564a");
//        EndAppNode subEsPortNode = new EndAppNode(subEndHW,subEsPortNode);
        EndAppNode subLRU = new EndAppNode();
        subLRU.setPort(subLRUPortNode);
        rpMessage.setSubEndApp(subLRU);

        //定义发送端端系统类
//        Hardware pubEndHW = new Hardware();
//        pubEndHW.setType("LRU");
//        pubEndHW.setName("AIR_COND_CPA");
//        pubEndHW.setGuid("abe4b06aa-5085-49a1-99cf-c0402a9fef32a");
        PortNode pubLRUPortNode = new CANPortNode("CANPort","name","a1c9dc707-6e9a-4dbd-9321-31674dca9716");
        EndAppNode pubEsPortNode = new EndAppNode();
        pubEsPortNode.setPort(pubLRUPortNode);
        rpMessage.setSubEndApp(pubEsPortNode);

        //定义接收端RDIU类
        RIUAppNode subRDIU = new RIUAppNode();
//        RDIUTypeHardware subRDIUHW = new RDIUTypeHardware();
//        subRDIUHW.setType("RIU");
//        subRDIUHW.setName("RDIU_08");
//        subRDIUHW.setGuid("a168D6E9D-7DB9-456a-AFB6-5c52F8DD8411a");
//        subRDIU.setHardware(subRDIUHW);

        // RDIU 的 发送端口
        PortNode subRDIUTxPort = new CANPortNode("name", "CANPort", "1");
        subRDIU.setPubPort(subRDIUTxPort);

        // RDIU 的 接收端口
        PortNode subRDIURxPort = new CANPortNode("name", "CANPort", "2");
        subRDIU.setSubPort(subRDIURxPort);

        subRDIU.setLatency(20.0);
        subRDIU.setJitter(15);
        rpMessage.setSubRDIU(subRDIU);

        //定义发送端RDIU类
        RIUAppNode pubRDIU = new RIUAppNode();
//        RDIUTypeHardware pubRDIUHW = new RDIUTypeHardware();
//        pubRDIUHW.setType("RIU");
//        pubRDIUHW.setName("RDIU_06");
//        pubRDIUHW.setGuid("a6CAFBB30-28B7-4B27-937D-E42428326523a");
//        pubRDIU.setHardware(pubRDIUHW);

        PortNode pubRDIUTxPort = new HFSamplingPortNode("name", "HFSamplingPort", "3");
//        pubRDIUTxPort.setName("name");
//        pubRDIUTxPort.setType("HFSamplingPort");
//        pubRDIUTxPort.setGuid("3");
        pubRDIU.setPubPort(pubRDIUTxPort);

        PortNode pubRDIURxPort = new CANPortNode("name", "CANPort", "4");
        pubRDIU.setSubPort(pubRDIURxPort);

        pubRDIU.setLatency(16);
        pubRDIU.setJitter(24);
        rpMessage.setSubRDIU(pubRDIU);

        //定义通过的Switch
//        SwitchTypeHardware swith1HW = new SwitchTypeHardware();
//        swith1HW.setType("ACS");
//        swith1HW.setName("ACS_RA");
//        swith1HW.setGuid("a65EB4ADF-06D0-49a4-990B-c3D5C4FAE560a");
        SwitchNode swith_1 = new SwitchNode();
        swith_1.setSubPort(12);
        swith_1.setPubPort(13);
        ADNPart adnPart = new ADNPart();
        List<SwitchNode> switchNodeList = new ArrayList<SwitchNode>();
        switchNodeList.add(swith_1);
        adnPart.setSwitchNodes(switchNodeList);
        rpMessage.setAdnPart(adnPart);

        return rpMessage;

    }


}
