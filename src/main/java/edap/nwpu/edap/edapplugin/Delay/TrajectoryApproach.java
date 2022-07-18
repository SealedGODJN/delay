//package com.nwpu.delay.Delay;
//
//import com.nwpu.delay.Message.*;
//import com.nwpu.delay.Message.port.PortNode;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//
///**
// * 用来计算分布式系统中端到端响应时间的确定性上界
// * 用来计算网络化分布式系统中端到端通信的确定性上界
// *
// * 考虑偶发的数据流
// * 但并不假设相应的到达曲线
// *
// * 网络演算法
// * 网络演算是一种“整体性”（holistic）方法，每个节点上的最坏情况场景，考虑了流在前一个节点上造成的最大可能的抖动（抖动=最大延迟-最小延迟）
// * 用包络代替确切的到达曲线和服务曲线（缺点）
// *
// * 轨迹法
// * 最坏情况的分析
// * 研究数据包在它的“轨迹”（各个节点所处的忙时间段）上经历的最坏场景
// * 任意排队规则和到达规则
// */
//public class TrajectoryApproach {
//    /**
//     * 简易版轨迹法
//     * 对每个节点的消息集合进行等价类划分
//     */
//    public List<Double> calculateQueueLength(RPMessage cur, List<RPMessage> all) {
//        // 1. 找出cur中的所有端口
//        List<BaseDeviceAndSwitchNode> curPassAllNode = cur.getAllDeviceNodes();
//        List<RPMessage> passPubApp = new ArrayList<>();
//        List<RPMessage> passPubRIU = new ArrayList<>();
//        List<RPMessage> passPubSwitch = new ArrayList<>();
//
//        List<RPMessage> passSubApp = new ArrayList<>();
//        List<RPMessage> passSubRIU = new ArrayList<>();
//        List<RPMessage> passSubSwitch = new ArrayList<>();
//
//        // 方法一；将cur经过的所有节点都单独拿出来比较
//        for (BaseDeviceAndSwitchNode node : curPassAllNode) {
//            switch (node.getType()) {
//                case "LRUAppNode":
//                    LRUAppNode temp = (LRUAppNode) node;
//                    if (cur.getPubEndApp().equals(temp)) {
//                        // TODO:既然已经分好了类，在judged函数里面，就不要再浪费时间去分类了
//                        passPubApp = judgePassPubLRUNode(temp, all);
//                    } else {
//                        passSubApp = judgePassSubLRUNode(temp, all);
//                    }
//                    break;
//                case "GPMAppNode":
//                    GPMAppNode temp = (GPMAppNode) node;
//                    if (cur.getPubEndApp().equals(temp)) {
//                        passPubApp = judgePassPubGPMNode(temp, all);
//                    } else {
//                        passSubApp = judgePassSubGPMNode(temp, all);
//                    }
//                    break;
//                case "RIUAppNode":
//                    RIUAppNode temp = (RIUAppNode) node;
//                    if (cur.getPubRDIU().equals(temp)) {
//                        passPubApp = judgePassPubRIUNode(temp, all);
//                    } else {
//                        passSubApp = judgePassSubRIUNode(temp, all);
//                    }
//                    break;
//                case "SwitchNode":
//                    SwitchNode temp = (SwitchNode) node;
//                    judgePassSwitchNode(temp, all, passPubSwitch, passSubSwitch);
//            }
//        }
//
//        // TODO:方法二，直接比较两个消息的发送端，接收端
//
//        // 依次计算消息在每个节点可能产生的排队长度
//        // 如果消息的发送端相同，则他们属于同一个消息冲突集（同一消息冲突集内的消息不产生排队）
//        // 不同消息冲突集的消息之间产生排队
//
//        // TODO:方法一：记录经过同一个发送端口的所有消息
//        // key为端口 value为消息集合（拥有同一个发送端端口的消息）
////        HashMap<PortNode, List<RPMessage>> theSamePubApp = new HashMap<>();
////        for (RPMessage rpMessage : all) {
////            PortNode port = rpMessage.pubEndApp.getPort();
////            if (theSamePubApp.get(port) == null) {
////                List<RPMessage> temp = new ArrayList<>();
////                temp.add(rpMessage);
////                theSamePubApp.putIfAbsent(rpMessage.pubEndApp.getPort(), temp);
////            } else {
////                List<RPMessage> temp = theSamePubApp.get(port);
////                temp.add(rpMessage);
////                // 应该是不需要执行replace操作，因为temp已经add rpMessage，这个temp是对应HashMap中存储的ArrayList
//////                theSamePubApp.replace(port, temp);
////            }
////        }
//
//        // TODO:方法二：记录经过同一个发送端口所有消息中长度最大的那个消息
//        // key为端口 value为长度MessageSize最大的消息（拥有同一个发送端端口的消息）
//        HashMap<PortNode, RPMessage> theSamePubApp = new HashMap<>();
//        for (RPMessage rpMessage : all) {
//            PortNode port = rpMessage.getPubEndApp().getPort();
//            if (theSamePubApp.get(port) == null) {
//                theSamePubApp.putIfAbsent(rpMessage.pubEndApp.getPort(), rpMessage);
//            } else {
//                RPMessage temp = theSamePubApp.get(port);
//                // 比较两个消息的MessageSize大小
//                if(temp.getMessageSize() < rpMessage.getMessageSize()) {
//                    theSamePubApp.replace(port, rpMessage);
//                }
//            }
//        }
//
//        // 设立一个临时变量
//        HashMap<PortNode, RPMessage> theSamePubRDIU = new HashMap<>();
//        for (RPMessage rpMessage : all) {
//            // RDIU还有一个subPort
//            // 但是在PubApp中，消息已经排过队了，消息是有序到达subPort的？
//            // 严格来讲，有可能会有其他的终端到subPort
//            // 在此处，只考虑在RDIU的PubPort进行排队
//            PortNode port = rpMessage.getPubRDIU().getPubPort();
//            if (theSamePubApp.get(port) == null) {
//                theSamePubApp.putIfAbsent(rpMessage.pubEndApp.getPort(), rpMessage);
//            } else {
//                RPMessage temp = theSamePubApp.get(port);
//                // 比较两个消息的MessageSize大小
//                if(temp.getMessageSize() < rpMessage.getMessageSize()) {
//                    theSamePubApp.replace(port, rpMessage);
//                }
//            }
//        }
//
//        // 计算QueueLength
//        double pubAppQueueLength = 0;
//        double pubRDIUQueueLength = 0;
//        double pubSwitchQueueLength = 0;
//
//        double subAppQueueLength = 0;
//        double subRDIUQueueLength = 0;
//        double subSwitchQueueLength = 0;
//
//        // 根据上面的HashMap来计算
//        // 在计算QueueLength的时候，应该记录哪些消息参与了排队
//        // 使用hadQueueMessage记录已经排过队的消息
//        HashSet<RPMessage> hadQueueMessage = new HashSet<>();
//
//        for(RPMessage rpMessage : theSamePubApp.values()) {
//            pubAppQueueLength += rpMessage.getMessageSize();
//            hadQueueMessage.add(rpMessage);
//        }
//
//        for(RPMessage rpMessage : theSamePubRDIU.values()) {
//            if (!hadQueueMessage.contains(rpMessage)) {
//                pubRDIUQueueLength += rpMessage.getMessageSize();
//                hadQueueMessage.add(rpMessage);
//            }
//        }
//
//        List<Double> allQueueLength = new ArrayList<>();
//        allQueueLength.add(pubAppQueueLength);
//        allQueueLength.add(pubRDIUQueueLength);
//        allQueueLength.add(pubSwitchQueueLength);
//
//        allQueueLength.add(subAppQueueLength);
//        allQueueLength.add(subRDIUQueueLength);
//        allQueueLength.add(subSwitchQueueLength);
//        return allQueueLength;
//    }
//}
