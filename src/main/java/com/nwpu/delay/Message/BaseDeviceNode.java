package com.nwpu.delay.Message;

import java.math.BigDecimal;

public class BaseDeviceNode extends BaseDeviceAndSwitchNode {
    public String name;
    public String guid;
    public int samplePeriod;
    public BigDecimal refreshPeriod;

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

    public int getSamplePeriod() {
        return samplePeriod;
    }

    public void setSamplePeriod(int samplePeriod) {
        this.samplePeriod = samplePeriod;
    }

    public BigDecimal getRefreshPeriod() {
        return refreshPeriod;
    }

    public void setRefreshPeriod(BigDecimal refreshPeriod) {
        this.refreshPeriod = refreshPeriod;
    }
}
