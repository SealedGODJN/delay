package com.nwpu.delay.Delay.Async;

import com.nwpu.delay.Message.*;

public class AsyncDelay {
    /**
     * 发送端的最终发送端的刷新周期值
     * 或者
     * 刷新周期值*queueLength
     */
    public double PPub = 0;
    public double PPubA664;

    /**
     * 接收端的最终发送端的刷新周期值
     * 或者
     * 刷新周期值*queueLength
     */
    public double PSub = 0;
    public double PSubA664;
    public double PCAN;
    public double AsyncDelay;


    /**
     * 计算发送端的PPub
     * @param pubEndApp
     * @return
     */
    public double calculatePPub(EndAppNode pubEndApp) {
        // 发送端的发送端口
        PortNode pubPort = pubEndApp.getPort();
        if(pubPort.getType().equals("A653SamplingPort") || pubPort.getType().equals("HFSamplingPort")){
            PPub = pubPort.getRefreshPeriod();
        } else if(pubPort.getType().equals("A653QueuingPort") || pubPort.getType().equals("HFQueuingPort")){
            if (pubPort instanceof A664QueuingPortNode) {
                // 为了获取queueLength属性
                A664QueuingPortNode temp = (A664QueuingPortNode) pubPort;
                int queueLength = temp.getQueueLength();
                PPub = pubPort.getRefreshPeriod() * queueLength;
            }
        }
        return PPub;
    }

    /**
     * 先计算PSub，再计算异步延迟
     * @param subEndApp
     * @return
     */
    public double calculatePSub(EndAppNode subEndApp) {
        // 接收端的接受端口
        PortNode subPort = subEndApp.getPort();
        if(subPort.getType().equals("A653SamplingPort") ||subPort.getType().equals("A653QueuingPort")
                ||subPort.getType().equals("HFSamplingPort") ||subPort.getType().equals("HFQueuingPort") ){
            PSub = subPort.getSamplePeriod();
        }
        if(PPub >= PSub){
            AsyncDelay = PSub;
            return PSub;
        }else if(PPub < PSub){
            if(subPort.getType().equals("A653SamplingPort") || subPort.getType().equals("HFSamplingPort")){
                AsyncDelay =PPub;
                return PPub;
            }else if(subPort.getType().equals("A653QueuingPort") || subPort.getType().equals("HFQueuingPort")){
                AsyncDelay = PSub;
                return PSub;
            }
        }
        return PSub;
    }

    /**
     * 计算以CAN为接收端的异步延迟
     * @param pubEndApp
     * @param subRDIU
     * @return
     */
    public double calculateSubCAN(EndAppNode pubEndApp, RIUAppNode subRDIU) {
        double PPubA664Arg1 = calculatePPub(pubEndApp) , PPubA664Arg2 = 0;
        double PPubDataArg1 = calculatePPub(pubEndApp), PPubDataArg2 = 0;

        PortNode subRDIUPubPort = subRDIU.getPubPort();
        CANPortNode subRDIUCANPort = (CANPortNode) subRDIUPubPort;

        if (subRDIUCANPort.getCanMessagePortocolType().equals("parametric")
                || subRDIUCANPort.getCanMessagePortocolType().equals("UNDEFINED")) {
            PPubDataArg2 = subRDIUPubPort.getRefreshPeriod();
            PPubA664Arg2 = subRDIUPubPort.getSamplePeriod();
        } else if (subRDIUCANPort.getCanMessagePortocolType().equals("OMS")
                || subRDIUCANPort.getCanMessagePortocolType().equals("ODLF")) {
            // CANBlockTransferPeriod
            PPubDataArg2 = Double.parseDouble(subRDIUCANPort.getCanBiteRate());
            PPubA664Arg2 = PPubDataArg2;
        }
        double PPubData = Math.max(PPubDataArg1, PPubDataArg2);
        double PPubA664 = Math.max(PPubA664Arg1, PPubA664Arg2);

        double PSubData = subRDIUCANPort.getSamplePeriod();

        double TasyncData = Math.min(PPubData, PSubData);
        double TasyncA664 = Math.min(PPubA664, PSubA664);

        AsyncDelay = TasyncData + TasyncA664;
        return AsyncDelay;
    }

    /**
     * 计算异步延迟
     * @param rpMessage 一条rp消息
     * @return 根据发送端和接收端，计算对应的异步延迟
     */
    public double calculateAsyncDelay(RPMessage rpMessage) {

        // 1. 找发送端和接收端
        // 1.1 判断rpMessage pubEndApp是A653还是LRU

        // 发送端
        EndAppNode pubEndApp = rpMessage.getPubEndApp();
        PortNode pubPort = pubEndApp.getPort();
        RIUAppNode pubRDIU = rpMessage.getPubRDIU();
        // 接收端
        EndAppNode subEndApp = rpMessage.getSubEndApp();
        PortNode subPort = subEndApp.getPort();
        RIUAppNode subRDIU =  rpMessage.getSubRDIU();

        // 1.1.1 发送端是A653
        if (pubEndApp.type.equals("A653Application")) {

            // 1.1.1.1 判断接收端是谁？是A653 or LRU
            String subEndAppType = subEndApp.getType();
            switch (subEndAppType) {
                // 1.1.1.1.1 接收端是A653
                case "ApplicationComponent":
                    // 计算发送端的PPub
                    PPub = calculatePPub(pubEndApp);
                    // 直接计算得出最终的异步延迟
                    AsyncDelay = calculatePSub(subEndApp);
                    break;

                // 1.1.1.1.2 接收端不是A653
                default:
                    // 判断RDIU的接收端口是否是CANPortNode

                    // 1.1.1.1.2.1 RDIU的接收端口是CANPort
                    // TODO:要通过RDIU的发送端口来判断？还是，用接收端的接收端口来判断端口的类型？
                    PortNode subport = subRDIU.getSubPort();

                    // 场景4:A653->CAN
                    if (subport.getType().equals("CANPort")) {
                        AsyncDelay = calculateSubCAN(pubEndApp, subRDIU);
                    }
                    // 场景5:A653->A429
                    else if(subport.getType().equals("A429Port"))  {
                        AsyncDelay = subRDIU.getSubPort().getSamplePeriod();
                    }
                    // 场景3:A653->AnalogPort
                    else if(subport.getType().equals("AnalogPort")){
                        // 计算发送端的PPub
                        PPub = calculatePPub(pubEndApp);
                        // 直接计算得出最终的异步延迟
                        AsyncDelay = calculatePSub(subEndApp);
                    }
                    // 场景2:A653->A664
                    else if(subport.getType().equals("HFSamplingPort") || subport.getType().equals("HFQueuingPort")){
                        // 计算发送端的PPub
                        PPub = calculatePPub(pubEndApp);
                        // 直接计算得出最终的异步延迟
                        AsyncDelay = calculatePSub(subEndApp);
                    }
                    break;
            }
        }
        // 1.1.2 发送端不是A653，是LRU或者RDIU
        else if (pubPort.getType().equals("CANPort")) {
            double arguments1,arguments2,arguments3;
            double Tasyncdate,TasyncA664;
            if (subPort.getType().equals("CANPort")) {                                   // CAN to CAN
                arguments1 = pubPort.getRefreshPeriod();
                arguments2 = PPubA664 = pubRDIU.getPubPort().getRefreshPeriod();
                arguments3 = subRDIU.getPubPort().getRefreshPeriod();
                PPub = Math.max(Math.max(arguments1,arguments2),arguments3);
                PSub = subPort.getSamplePeriod();
                Tasyncdate = Math.min(PPub,PSub);
                PSubA664 = subRDIU.getSubPort().getSamplePeriod();
                TasyncA664 = Math.min(PPubA664,PSubA664);
                AsyncDelay = Tasyncdate + TasyncA664;
            } else if(subPort.getType().equals("A653QueuingPort")) {                  // CAN to A653应用
                AsyncDelay =subPort.getSamplePeriod();
            } else if(subPort.getType().equals("A653SamplingPort")){                   // CAN to A653应用
                PPub = pubRDIU.getPubPort().getRefreshPeriod();
                PSub = subPort.getSamplePeriod();
                PCAN = pubPort.getRefreshPeriod();
                AsyncDelay = Math.max(Math.min(PPub,PSub),Math.min(PCAN,PSub));
            } else if(subPort.getType().equals("HFQueuingPort")){                       // CAN to A664
                AsyncDelay =subPort.getSamplePeriod();
            } else if(subPort.getType().equals("HFSamplingPort")){                      // CAN to A664
                PPub = pubRDIU.getPubPort().getRefreshPeriod();
                PSub = subPort.getSamplePeriod();
                PCAN = pubPort.getRefreshPeriod();
                AsyncDelay = Math.max(Math.min(PPub,PSub),Math.min(PCAN,PSub));
            } else if(subPort.getType().equals("AnalogPort")){                         // CAN to AnalogPort
                PPub = pubRDIU.getPubPort().getRefreshPeriod();
                PSub = subPort.getSamplePeriod();
                PCAN = pubPort.getRefreshPeriod();
                AsyncDelay = Math.max(Math.min(PPub,PSub),Math.min(PCAN,PSub));
            } else if(subPort.getType().equals("A429Port")){                            // CAN to A429
                AsyncDelay =subPort.getSamplePeriod();
            }
        }
        else if (pubPort.getType().equals("A429Port")) {
            if(subPort.getType().equals("A429Port")){                                                             //A429 to A429
                AsyncDelay = subRDIU.getSubPort().getSamplePeriod();
            }
            // excel中没有A429->Analog
//            else if(subPort.getType().equals("AnalogPort")){
//
//            }
            else if(subPort.getType().equals("HFSamplingPort") || subPort.getType().equals("HFQueuingPort")){       //A429 to A664
                if (pubRDIU.getPubPort() instanceof A664QueuingPortNode) {
                    // 为了获取queueLength属性
                    A664QueuingPortNode temp = (A664QueuingPortNode) pubPort;
                    int queueLength = temp.getQueueLength();
                    PPub = pubPort.getRefreshPeriod() * queueLength;
                    AsyncDelay = calculatePSub(subEndApp);
                } else {
                    System.out.println("类型转换错误:PortNode->A664QueuingPortNode");
                }

            }else if(subEndApp.getType().equals("A653Application")){                                          //A429 to A653应用
                if (pubRDIU.getPubPort() instanceof A664QueuingPortNode) {
                    // 为了获取queueLength属性
                    A664QueuingPortNode temp = (A664QueuingPortNode) pubPort;
                    int queueLength = temp.getQueueLength();
                    PPub = pubPort.getRefreshPeriod() * queueLength;
                    AsyncDelay = calculatePSub(subEndApp);
                } else {
                    System.out.println("类型转换错误:PortNode->A664QueuingPortNode");
                }
            }else if(subPort.getType().equals("CANPort")){                                         //A429 to CANPort
                   //TODO
            }

        }
        else if (pubPort.getType().equals("AnalogPort")){
            if(subPort.getType().equals("AnalogPort")){                       //Analog to AnalogPort
                PPub = pubRDIU.getPubPort().getRefreshPeriod();
                PSub = subRDIU.getSubPort().getSamplePeriod();
                AsyncDelay = Math.min(PPub,PSub);
            }else if(subPort.getType().equals("A429Port")){                  //Analog to A429
                    //TODO
            }else if(subEndApp.getType().equals("A653Application")){          //Analog to A653应用
                PPub = pubRDIU.getPubPort().getRefreshPeriod();
                AsyncDelay = calculatePSub(subEndApp);
            }else if(subPort.getType().equals("CANPort")){                  //Analog to CANPort
                double agruments1 = PPubA664 =  pubRDIU.getPubPort().getRefreshPeriod();
                double  agruments2 = subRDIU.getPubPort().getRefreshPeriod();
                PPub = Math.max(agruments1,agruments2);
                PSub = subPort.getSamplePeriod();
                double Tasyncdata = Math.min(PPub,PSub);
                PSubA664 = subRDIU.getSubPort().getSamplePeriod();
                double TasyncA664 = Math.min(PPubA664,PSubA664);
                AsyncDelay = Tasyncdata + TasyncA664;
            }else if(subPort.getType().equals("HFSamplingPort") || subPort.getType().equals("HFQueuingPort")){ //Analog to A664
                PPub =pubRDIU.getPubPort().getRefreshPeriod();
               AsyncDelay = calculatePSub(subEndApp);
            }

        } else if (pubPort.getType().equals("HFSamplingPort") || pubPort.getType().equals("HFQueuingPort")) {
            // 场景6:A664->A653
            if (subPort.getType().equals("A653Application")) {
                // 计算发送端的PPub
                PPub = calculatePPub(pubEndApp);
                // 直接计算得出最终的异步延迟
                AsyncDelay = calculatePSub(subEndApp);

            }
            // 场景7:A664->A664
            else if (subPort.getType().equals("HFSamplingPort") || subPort.getType().equals("HFQueuingPort")) {
                // 计算发送端的PPub
                PPub = calculatePPub(pubEndApp);
                // 直接计算得出最终的异步延迟
                AsyncDelay = calculatePSub(subEndApp);
            }
            // 场景8:A664->Analog
            else if (subPort.getType().equals("AnalogPort")) {
                // 计算发送端的PPub
                PPub = calculatePPub(pubEndApp);
                // 直接计算得出最终的异步延迟
                AsyncDelay = calculatePSub(subEndApp);
            }
            // 场景9:A664->CAN
            else if(subPort.getType().equals("CANPort")){
                AsyncDelay = calculateSubCAN(pubEndApp, subRDIU);
            }
            // 场景10:A664->A429
            else if(subPort.getType().equals("A429Port")){
                PSub = subRDIU.getPubPort().getSamplePeriod();
                AsyncDelay = PSub;
            }
        }
        return AsyncDelay;
    }
}
