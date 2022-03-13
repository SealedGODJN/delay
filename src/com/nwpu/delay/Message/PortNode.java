package com.nwpu.delay.Message;

public class PortNode {
    public String type;
    public String name;
    public String guid;
    /**
     * 刷新周期
     */
    public double refreshPeriod;
    /**
     * 采样周期
     */
    public double samplePeriod;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public double getRefreshPeriod() {
        return refreshPeriod;
    }

    public void setRefreshPeriod(double refreshPeriod) {
        this.refreshPeriod = refreshPeriod;
    }

    public double getSamplePeriod() {
        return samplePeriod;
    }

    public void setSamplePeriod(double samplePeriod) {
        this.samplePeriod = samplePeriod;
    }
}
