package edap.nwpu.edap.edapplugin.bean.port;

public class A653QueuingPortNode extends A664QueuingPortNode {
    /**
     * TODO
     * 添加了messageSize属性
     * 用于计算QueueLength
     */
    public double messageSize;

    public A653QueuingPortNode(String type, String name, String guid) {
        super(type, name, guid);
    }

    public double getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(double messageSize) {
        this.messageSize = messageSize;
    }
}
