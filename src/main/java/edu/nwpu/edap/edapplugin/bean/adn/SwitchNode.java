package edu.nwpu.edap.edapplugin.bean.adn;

import edu.nwpu.edap.edapplugin.bean.device.BaseDeviceAndSwitchNode;
import edu.nwpu.edap.edapplugin.bean.hardware.Hardware;

public class SwitchNode extends BaseDeviceAndSwitchNode {

    //交换机接收端端口
    protected int subPort;

    //交换机发送端端口
    protected int pubPort;

    public int getSubPort() {
        return subPort;
    }

    public void setSubPort(int subPort) {
        this.subPort = subPort;
    }

    public int getPubPort() {
        return pubPort;
    }

    public void setPubPort(int pubPort) {
        this.pubPort = pubPort;
    }
}
