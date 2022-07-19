package edu.nwpu.edap.edapplugin.bean.port;

public class A664QueuingPortNode extends A664TypePortNode {
    public int queueLength;

    public A664QueuingPortNode(String type, String name, String guid) {
        super(type, name, guid);
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }
}
