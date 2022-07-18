package com.nwpu.delay.Message.port;

public class CANPortNode extends NonA664TypePortNode{
    public String canBiteRate;
    /**
     * parametric/UNDEFINED/OMS/ODLF
     */
    public String canMessagePortocolType;

    public CANPortNode(String type, String name, String guid) {
        super(type, name, guid);
    }

    public String getCanBiteRate() {
        return canBiteRate;
    }

    public void setCanBiteRate(String canBiteRate) {
        this.canBiteRate = canBiteRate;
    }

    public String getCanMessagePortocolType() {
        return canMessagePortocolType;
    }

    public void setCanMessagePortocolType(String canMessagePortocolType) {
        this.canMessagePortocolType = canMessagePortocolType;
    }
}
