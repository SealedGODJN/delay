package com.nwpu.delay.Message;

import com.nwpu.delay.Message.port.PortNode;

public class EndAppNode extends BaseDeviceNode{
    public String ata;
    public PortNode port;
    public String portDef;


    public String getAta() {
        return ata;
    }

    public void setAta(String ata) {
        this.ata = ata;
    }

    public PortNode getPort() {
        return port;
    }

    public void setPort(PortNode port) {
        this.port = port;
    }

    public String getPortDef() {
        return portDef;
    }

    public void setPortDef(String portDef) {
        this.portDef = portDef;
    }
}
