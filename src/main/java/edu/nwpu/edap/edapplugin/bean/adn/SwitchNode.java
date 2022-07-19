package edu.nwpu.edap.edapplugin.bean.adn;

import edu.nwpu.edap.edapplugin.bean.device.BaseDeviceAndSwitchNode;
import edu.nwpu.edap.edapplugin.bean.hardware.Hardware;

public class SwitchNode extends BaseDeviceAndSwitchNode {
    public int subPort;
    public int pubPort;
    public Hardware hardware;
}
