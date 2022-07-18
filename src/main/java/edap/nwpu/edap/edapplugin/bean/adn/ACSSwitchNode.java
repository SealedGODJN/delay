package edap.nwpu.edap.edapplugin.bean.adn;

public class ACSSwitchNode extends SwitchNode {

    //接收端端口的速度
    protected String subPortSpeed;

    //发送端端口的速度
    protected String pubPortSpeed;

    public String getSubPortSpeed() {
        return subPortSpeed;
    }

    public void setSubPortSpeed(String subPortSpeed) {
        this.subPortSpeed = subPortSpeed;
    }

    public String getPubPortSpeed() {
        return pubPortSpeed;
    }

    public void setPubPortSpeed(String pubPortSpeed) {
        this.pubPortSpeed = pubPortSpeed;
    }

    @Override
    public String toString() {
        return "ACSSwitchNode [subPortSpeed=" + subPortSpeed + ", pubPortSpeed=" + pubPortSpeed + ", subPort=" + subPort
                + ", pubPort=" + pubPort + ", type=" + type + ", hardware=" + hardware + "]";
    }

}
