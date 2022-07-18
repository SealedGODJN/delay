package edap.nwpu.edap.edapplugin.bean.device;

import edap.nwpu.edap.edapplugin.bean.hardware.Hardware;

public class BaseDeviceAndSwitchNode {
    /**
     * 根据这个type判断是哪一种应用：A653Application、HostedFunction、RemoteGateway、ARS、ACS
     */
    public String type;
    public Hardware hardware;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }
}
