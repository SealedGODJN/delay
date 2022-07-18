package edap.nwpu.edap.edapplugin.Delay;

import com.nwpu.delay.Message.*;
import edap.nwpu.edap.edapplugin.bean.device.GPMAppNode;
import edap.nwpu.edap.edapplugin.bean.port.PortNode;
import edap.nwpu.edap.edapplugin.bean.Parition;

import java.util.Iterator;
import java.util.List;

public class A653Delay {

    private Parition pubParition;
    private Parition subParition;
    private List<Parition> pubAllParition;
    private List<Parition> subAllParition;

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
                if ( temp.id.equals(pubEndApp.getPubParition().getId()) ) {
                    break;
                }
            }
            while (iterator.hasNext()) {
                A653ApplicationComponentClassType temp = iterator.next();
                delay += temp.duration;
                if ( temp.id.equals(subEndApp.getSubParition().getId()) ) {
                    break;
                }
            }
            return delay;
        }

        /**
         * 过网通信
         *
         * 只需要计算接收端GPM的调度时间
         */
        for (A653ApplicationComponentClassType parition : subAllParition) {
            delay += parition.duration;
        }
        delay /= subAllParition.size();

        return delay;
    }
}
