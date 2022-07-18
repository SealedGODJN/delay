package edap.nwpu.edap.edapplugin.bean.device;

import edap.nwpu.edap.edapplugin.bean.Parition;

import java.util.List;

public class GPMAppNode extends EndAppNode{
        public boolean count;
        public double latency;
        public double jitter;
        public double latencyThreshold;
        public double jitterThreshold;
        public double maxMessageLatency;
        /**
         * 发送分区
         */
        public Parition pubParition;
        /**
         * 接收分区
         */
        public Parition subParition;
        /**
         * 分区调度表
         */
        public List<Parition> allParition;

        public boolean isCount() {
                return count;
        }

        public void setCount(boolean count) {
                this.count = count;
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

        public double getMaxMessageLatency() {
                return maxMessageLatency;
        }

        public void setMaxMessageLatency(double maxMessageLatency) {
                this.maxMessageLatency = maxMessageLatency;
        }

        public Parition getPubParition() {
                return pubParition;
        }

        public void setPubParition(Parition pubParition) {
                this.pubParition = pubParition;
        }

        public Parition getSubParition() {
                return subParition;
        }

        public void setSubParition(Parition subParition) {
                this.subParition = subParition;
        }

        public List<Parition> getAllParition() {
                return allParition;
        }

        public void setAllParition(List<Parition> allParition) {
                this.allParition = allParition;
        }
}
