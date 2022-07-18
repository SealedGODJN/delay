package edap.nwpu.edap.edapplugin.bean.port;

public class CANPortNode extends NonA664TypePortNode{
    public String canBiteRate;
    /**
     * parametric/UNDEFINED/OMS/ODLF
     */
    public String canMessageProtocolType;

    public CANPortNode(String type, String name, String guid) {
        super(type, name, guid);
    }

    public String getCanBiteRate() {
        return canBiteRate;
    }

    public void setCanBiteRate(String canBiteRate) {
        this.canBiteRate = canBiteRate;
    }

    public String getCanMessageProtocolType() {
        return canMessageProtocolType;
    }

    public void setCanMessageProtocolType(String canMessageProtocolType) {
        this.canMessageProtocolType = canMessageProtocolType;
    }
}
