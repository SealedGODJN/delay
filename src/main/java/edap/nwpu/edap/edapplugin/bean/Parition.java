package edap.nwpu.edap.edapplugin.bean;

import edap.nwpu.edap.edapplugin.bean.port.A664TypePortNode;

public class Parition {
    /**
     * 分区持续时间
     */
    public double duration;

    public String id;
    public String name;
    /**
     * 不确定是队列端口还是采样端口
     */
    public A664TypePortNode port;

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A664TypePortNode getPort() {
        return port;
    }

    public void setPort(A664TypePortNode port) {
        this.port = port;
    }
}
