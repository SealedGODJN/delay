package edap.nwpu.edap.edapplugin.bean.device;

import com.nwpu.delay.Message.hardware.Hardaware;

public class BaseDeviceAndSwitchNode {
    /**
     * 根据这个type判断是哪一种应用：A653Application、HostedFunction、RemoteGateway、ARS、ACS
     */
    public String type;
    public Hardaware hardaware;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Hardaware getHardaware() {
        return hardaware;
    }

    public void setHardaware(Hardaware hardaware) {
        this.hardaware = hardaware;
    }
}
