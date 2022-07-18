package edap.nwpu.edap.edapplugin.bean.port;

public class A653SamplingPortNode extends A664SamplingPortNode {
    /**
     * TODO
     * 添加了messageSize属性
     * 用于计算QueueLength
     */
    public double messageSize;

    public A653SamplingPortNode(String type, String name, String guid) {
        super(type, name, guid);
    }

    public double getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(double messageSize) {
        this.messageSize = messageSize;
    }
}
