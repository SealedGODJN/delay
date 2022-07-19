package edu.nwpu.edap.edapplugin.bean.device;

import edu.nwpu.edap.edapplugin.bean.port.PortNode;

public class RIUAppNode extends BaseDeviceNode{
    public String riuType;
    public boolean count;
    public PortNode pubPort;
    public PortNode subPort;
    public double latency;
    public double jitter;
    public double latencyThreshold;
    public double jitterThreshold;

    public String getRiuType() {
        return riuType;
    }

    public void setRiuType(String riuType) {
        this.riuType = riuType;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public PortNode getPubPort() {
        return pubPort;
    }

    public void setPubPort(PortNode pubPort) {
        this.pubPort = pubPort;
    }

    public PortNode getSubPort() {
        return subPort;
    }

    public void setSubPort(PortNode subPort) {
        this.subPort = subPort;
    }

    public double getLatency() {
        return latency;
    }

    public void setLatency(double latency) {
        this.latency = latency;
    }

    public double getJitter() {
        return jitter;
    }

    public void setJitter(double jitter) {
        this.jitter = jitter;
    }

    public double getLatencyThreshold() {
        return latencyThreshold;
    }

    public void setLatencyThreshold(double latencyThreshold) {
        this.latencyThreshold = latencyThreshold;
    }

    public double getJitterThreshold() {
        return jitterThreshold;
    }

    public void setJitterThreshold(double jitterThreshold) {
        this.jitterThreshold = jitterThreshold;
    }
}
