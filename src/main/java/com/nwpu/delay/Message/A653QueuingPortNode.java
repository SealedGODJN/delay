package com.nwpu.delay.Message;

public class A653QueuingPortNode extends A664QueuingPortNode {
    /**
     * TODO
     * 添加了messageSize属性
     * 用于计算QueueLength
     */
    public double messageSize;

    public double getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(double messageSize) {
        this.messageSize = messageSize;
    }
}
