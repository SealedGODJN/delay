package edap.nwpu.edap.edapplugin.bean.adn;

import edap.nwpu.edap.edapplugin.bean.device.BaseDeviceAndSwitchNode;
import edap.nwpu.edap.edapplugin.bean.hardware.Hardware;

public class SwitchNode extends BaseDeviceAndSwitchNode {
    public int subPort;
    public int pubPort;
    public Hardware hardaware;
}
