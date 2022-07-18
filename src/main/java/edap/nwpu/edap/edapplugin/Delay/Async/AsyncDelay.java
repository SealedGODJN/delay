package edap.nwpu.edap.edapplugin.Delay.Async;

import com.nwpu.delay.Message.*;
import edap.nwpu.edap.edapplugin.bean.device.EndAppNode;
import edap.nwpu.edap.edapplugin.bean.device.RIUAppNode;
import edap.nwpu.edap.edapplugin.bean.port.A664QueuingPortNode;
import edap.nwpu.edap.edapplugin.bean.port.CANPortNode;
import edap.nwpu.edap.edapplugin.bean.port.PortNode;
import edap.nwpu.edap.edapplugin.bean.RPMessage;

public class AsyncDelay {
    /**
     * 发送端的最终发送端的刷新周期值
     * 或者
     * 刷新周期值*queueLength
     */
    public static double PPub = 0;
    public static double PPubA664;

    /**
     * 接收端的最终发送端的刷新周期值
     * 或者
     * 刷新周期值*queueLength
     */
    public static double PSub = 0;
    public static double PSubA664;
    public static double PCAN;
    public static double AsyncDelay;


    /**
     * 计算发送端的PPub
     * @param pubEndApp
     * @return
     */
    public static double calculatePPub(EndAppNode pubEndApp) {
        // 发送端的发送端口
        PortNode pubPort = pubEndApp.getPort();

        // 1t-1t-1t
        if(pubPort.getType().equals("A653SamplingPort") || pubPort.getType().equals("HFSamplingPort")){
            PPub = Double.parseDouble(pubEndApp.getRefreshPeriod().toString());
        }
        // 1t-1t-2
        else if(pubPort.getType().equals("A653QueuingPort") || pubPort.getType().equals("HFQueuingPort")){
            if (pubPort instanceof A664QueuingPortNode) {
                // 为了获取queueLength属性
                A664QueuingPortNode temp = (A664QueuingPortNode) pubPort;
                int queueLength = temp.getQueueLength();
                PPub = Double.parseDouble(pubEndApp.getRefreshPeriod().toString()) * queueLength;
            }
        }
        return PPub;
    }

    /**
     * 先计算PSub，再计算异步延迟
     * @param subEndApp
     * @return
     */
    public static double calculatePSub(EndAppNode subEndApp) {
        // 接收端的接受端口
        PortNode subPort = subEndApp.getPort();
        // 1t-1t-1t-1t
        if(subPort.getType().equals("A653SamplingPort") ||subPort.getType().equals("A653QueuingPort")
                ||subPort.getType().equals("HFSamplingPort") ||subPort.getType().equals("HFQueuingPort") ){
            PSub = subEndApp.getSamplePeriod();
        }
        // 1t-1t-1t-2-1t
        if(PPub >= PSub){
            AsyncDelay = PSub;
            return PSub;
        }
        // 1t-1t-1t-2-2t
        else if(PPub < PSub){
            // 1t-1t-1t-2-2t-1t
            if(subPort.getType().equals("A653SamplingPort") || subPort.getType().equals("HFSamplingPort")){
                AsyncDelay =PPub;
                return PPub;
            }
            // 1t-1t-1t-2-2t-1f/2t
            else if(subPort.getType().equals("A653QueuingPort") || subPort.getType().equals("HFQueuingPort")){
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
    public static double calculateSubCAN(EndAppNode pubEndApp, RIUAppNode subRDIU) {
        double PPubA664Arg1 = calculatePPub(pubEndApp) , PPubA664Arg2 = 0;
        double PPubDataArg1 = calculatePPub(pubEndApp), PPubDataArg2 = 0;

        PortNode subRDIUPubPort = subRDIU.getPubPort();
        CANPortNode subRDIUCANPort = (CANPortNode) subRDIUPubPort;

        if (subRDIUCANPort.getCanMessageProtocolType().equals("parametric")
                || subRDIUCANPort.getCanMessageProtocolType().equals("UNDEFINED")) {
            PPubDataArg2 = Double.parseDouble(pubEndApp.getRefreshPeriod().toString());
            PPubA664Arg2 = subRDIU.getSamplePeriod();
        } else if (subRDIUCANPort.getCanMessageProtocolType().equals("OMS")
                || subRDIUCANPort.getCanMessageProtocolType().equals("ODLF")) {
            // CANBlockTransferPeriod
            PPubDataArg2 = Double.parseDouble(subRDIUCANPort.getCanBiteRate());
            PPubA664Arg2 = PPubDataArg2;
        }
        double PPubData = Math.max(PPubDataArg1, PPubDataArg2);
        double PPubA664 = Math.max(PPubA664Arg1, PPubA664Arg2);

        double PSubData = subRDIU.getSamplePeriod();

        double TasyncData = Math.min(PPubData, PSubData);
        double TasyncA664 = Math.min(PPubA664, PSubA664);

        AsyncDelay = TasyncData + TasyncA664;
        return AsyncDelay;
    }

    /**
     * 计算异步延迟
     *
     * RPMessage isLocal 判断消息是否过网通信
     * 例如：消息从一个RDIU端口到另一个RDIU端口
     *
     * @param rpMessage 一条rp消息
     * @return 根据发送端和接收端，计算对应的异步延迟
     */
    public static double calculateAsyncDelay(RPMessage rpMessage) {

        // 不过网通信
        if(rpMessage.isLocal()) {
            /**
             * 2022.7.15 后面再处理
             */
            return 0;
        }

        // 找发送端和接收端
        // 判断rpMessage pubEndApp是A653还是LRU

        // 发送端
        EndAppNode pubEndApp = rpMessage.getPubEndApp();
        PortNode pubPort = pubEndApp.getPort();
        RIUAppNode pubRDIU = rpMessage.getPubRDIU();
        // 接收端
        EndAppNode subEndApp = rpMessage.getSubEndApp();
        PortNode subPort = subEndApp.getPort();
        RIUAppNode subRDIU =  rpMessage.getSubRDIU();

        // 发送端是A653
        // 1
        if (pubEndApp.getType().equals("A653Application")) {

            // 判断接收端是谁？是A653 or LRU
            String subEndAppType = subEndApp.getType();
            // 1t-1
            switch (subEndAppType) {
                // 接收端是A653
                //1t-1t
                case "ApplicationComponent":
                    // 计算发送端的PPub
                    PPub = calculatePPub(pubEndApp);
                    // 直接计算得出最终的异步延迟
                    AsyncDelay = calculatePSub(subEndApp);
                    return AsyncDelay;

                // 接收端不是A653
                // 1t-2
                default:
                    // 判断RDIU的接收端口是否是CANPortNode

                    // RDIU的接收端口是CANPort
                    // TODO:要通过RDIU的发送端口来判断？还是，用接收端的接收端口来判断端口的类型？
                    PortNode subport = subRDIU.getSubPort();

                    // 场景4:A653->CAN
                    // 1t-2-1
                    if (subport.getType().equals("CANPort")) {
                        AsyncDelay = calculateSubCAN(pubEndApp, subRDIU);
                        return AsyncDelay;
                    }
                    // 1t-2-2
                    // 场景5:A653->A429
                    else if(subport.getType().equals("A429Port"))  {
                        AsyncDelay = subRDIU.getSamplePeriod();
                        return AsyncDelay;
                    }
                    // 1t-2-3
                    // 场景3:A653->AnalogPort
                    else if(subport.getType().equals("AnalogPort")){
                        // 计算发送端的PPub
                        PPub = calculatePPub(pubEndApp);
                        // 直接计算得出最终的异步延迟
                        AsyncDelay = calculatePSub(subEndApp);
                        return AsyncDelay;
                    }
                    // 1t-2-4
                    // 场景2:A653->A664
                    else if(subport.getType().equals("HFSamplingPort") || subport.getType().equals("HFQueuingPort")){
                        // 计算发送端的PPub
                        PPub = calculatePPub(pubEndApp);
                        // 直接计算得出最终的异步延迟
                        AsyncDelay = calculatePSub(subEndApp);
                        return AsyncDelay;
                    } else {
                        // 其他情况
                        return 0;
                    }
            }
        }
        // 发送端不是A653，是LRU或者RDIU
        else if (pubPort.getType().equals("CANPort")) {
            double arguments1,arguments2,arguments3;
            double Tasyncdate,TasyncA664;
            if (subPort.getType().equals("CANPort")) {                                   // CAN to CAN
                arguments1 = Double.parseDouble(pubEndApp.getRefreshPeriod().toString());
                arguments2 = PPubA664 = Double.parseDouble(pubRDIU.getRefreshPeriod().toString());
                arguments3 = Double.parseDouble(subRDIU.getRefreshPeriod().toString());
                PPub = Math.max(Math.max(arguments1,arguments2),arguments3);
                PSub = subEndApp.getSamplePeriod();
                Tasyncdate = Math.min(PPub,PSub);
                PSubA664 = subRDIU.getSamplePeriod();
                TasyncA664 = Math.min(PPubA664,PSubA664);
                AsyncDelay = Tasyncdate + TasyncA664;
                return AsyncDelay;
            } else if(subPort.getType().equals("A653QueuingPort")) {                  // CAN to A653应用
                AsyncDelay = subEndApp.getSamplePeriod();
                return AsyncDelay;
            } else if(subPort.getType().equals("A653SamplingPort")){                   // CAN to A653应用
                PPub = Double.parseDouble(pubRDIU.getRefreshPeriod().toString());
                PSub = subEndApp.getSamplePeriod();
                PCAN = Double.parseDouble(pubEndApp.getRefreshPeriod().toString());
                AsyncDelay = Math.max(Math.min(PPub,PSub),Math.min(PCAN,PSub));
                return AsyncDelay;
            } else if(subPort.getType().equals("HFQueuingPort")){                       // CAN to A664
                AsyncDelay = subEndApp.getSamplePeriod();
                return AsyncDelay;
            } else if(subPort.getType().equals("HFSamplingPort")){                      // CAN to A664
                PPub = Double.parseDouble(pubRDIU.getRefreshPeriod().toString());
                PSub = subEndApp.getSamplePeriod();
                PCAN = Double.parseDouble(pubEndApp.getRefreshPeriod().toString());
                AsyncDelay = Math.max(Math.min(PPub,PSub),Math.min(PCAN,PSub));
                return AsyncDelay;
            } else if(subPort.getType().equals("AnalogPort")){                         // CAN to AnalogPort
                PPub = Double.parseDouble(pubRDIU.getRefreshPeriod().toString());
                PSub = subEndApp.getSamplePeriod();
                PCAN = Double.parseDouble(pubEndApp.getRefreshPeriod().toString());
                AsyncDelay = Math.max(Math.min(PPub,PSub),Math.min(PCAN,PSub));
                return AsyncDelay;
            } else if(subPort.getType().equals("A429Port")){                            // CAN to A429
                AsyncDelay =subEndApp.getSamplePeriod();
                return AsyncDelay;
            } else {
                // 其他情况
                return 0;
            }
        }
        else if (pubPort.getType().equals("A429Port")) {
            if(subPort.getType().equals("A429Port")){                                                             //A429 to A429
                AsyncDelay = subRDIU.getSamplePeriod();
                return AsyncDelay;
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
                    PPub = Double.parseDouble(pubEndApp.getRefreshPeriod().toString()) * queueLength;
                    AsyncDelay = calculatePSub(subEndApp);
                    return AsyncDelay;
                } else {
                    System.out.println("类型转换错误:PortNode->A664QueuingPortNode");
                }

            }else if(subEndApp.getType().equals("A653Application")){                                          //A429 to A653应用
                if (pubRDIU.getPubPort() instanceof A664QueuingPortNode) {
                    // 为了获取queueLength属性
                    A664QueuingPortNode temp = (A664QueuingPortNode) pubPort;
                    int queueLength = temp.getQueueLength();
                    PPub = Double.parseDouble(pubEndApp.getRefreshPeriod().toString())* queueLength;
                    AsyncDelay = calculatePSub(subEndApp);
                    return AsyncDelay;
                } else {
                    System.out.println("类型转换错误:PortNode->A664QueuingPortNode");
                }
            }else if(subPort.getType().equals("CANPort")){                                         //A429 to CANPort
                //TODO
            } else {
                // 其他情况
                return 0;
            }

        }
        else if (pubPort.getType().equals("AnalogPort")){
            if(subPort.getType().equals("AnalogPort")){                       //Analog to AnalogPort
                PPub = Double.parseDouble(pubRDIU.getRefreshPeriod().toString());
                PSub = subRDIU.getSamplePeriod();
                AsyncDelay = Math.min(PPub,PSub);
                return AsyncDelay;
            }else if(subPort.getType().equals("A429Port")){                  //Analog to A429
                //TODO
            }else if(subEndApp.getType().equals("A653Application")){          //Analog to A653应用
                PPub = Double.parseDouble(pubRDIU.getRefreshPeriod().toString());
                AsyncDelay = calculatePSub(subEndApp);
                return AsyncDelay;
            }else if(subPort.getType().equals("CANPort")){                  //Analog to CANPort
                double agruments1 = PPubA664 =  Double.parseDouble(pubRDIU.getRefreshPeriod().toString());
                double  agruments2 = Double.parseDouble(subRDIU.getRefreshPeriod().toString());
                PPub = Math.max(agruments1,agruments2);
                PSub = subEndApp.getSamplePeriod();
                double Tasyncdata = Math.min(PPub,PSub);
                PSubA664 = subRDIU.getSamplePeriod();
                double TasyncA664 = Math.min(PPubA664,PSubA664);
                AsyncDelay = Tasyncdata + TasyncA664;
                return AsyncDelay;
            }else if(subPort.getType().equals("HFSamplingPort") || subPort.getType().equals("HFQueuingPort")){ //Analog to A664
                PPub =Double.parseDouble(pubRDIU.getRefreshPeriod().toString());
                AsyncDelay = calculatePSub(subEndApp);
                return AsyncDelay;
            }

        }
        else if (pubPort.getType().equals("HFSamplingPort") || pubPort.getType().equals("HFQueuingPort")) {
            // 场景6:A664->A653
            if (subPort.getType().equals("A653Application")) {
                // 计算发送端的PPub
                PPub = calculatePPub(pubEndApp);
                // 直接计算得出最终的异步延迟
                AsyncDelay = calculatePSub(subEndApp);
                return AsyncDelay;
            }
            // 场景7:A664->A664
            else if (subPort.getType().equals("HFSamplingPort") || subPort.getType().equals("HFQueuingPort")) {
                // 计算发送端的PPub
                PPub = calculatePPub(pubEndApp);
                // 直接计算得出最终的异步延迟
                AsyncDelay = calculatePSub(subEndApp);
                return AsyncDelay;
            }
            // 场景8:A664->Analog
            else if (subPort.getType().equals("AnalogPort")) {
                // 计算发送端的PPub
                PPub = calculatePPub(pubEndApp);
                // 直接计算得出最终的异步延迟
                AsyncDelay = calculatePSub(subEndApp);
                return AsyncDelay;
            }
            // 场景9:A664->CAN
            else if(subPort.getType().equals("CANPort")){
                AsyncDelay = calculateSubCAN(pubEndApp, subRDIU);
                return AsyncDelay;
            }
            // 场景10:A664->A429
            else if(subPort.getType().equals("A429Port")){
                PSub = subRDIU.getSamplePeriod();
                AsyncDelay = PSub;
                return AsyncDelay;
            } else {
                // 其他情况
                return 0;
            }
        }
        return AsyncDelay;
    }
}
