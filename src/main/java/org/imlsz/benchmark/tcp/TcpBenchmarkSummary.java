package org.imlsz.benchmark.tcp;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lsz on 2017/3/9.
 */
public class TcpBenchmarkSummary {
    private AtomicLong reqTimes = new AtomicLong();
    private AtomicLong respTimes = new AtomicLong();
    private AtomicLong reqBytes = new AtomicLong();
    private AtomicLong respBytes = new AtomicLong();
    private AtomicLong errorTimes = new AtomicLong();
    private long lastReqTimes;
    private long lastRespTimes;
    private long lastReqBytes;
    private long lastRespBytes;
    private int summaryInterval = 1;
    private int runTime;

    public int getSummaryInterval() {
        return summaryInterval;
    }

    private void incrReqTimes(){
        reqTimes.incrementAndGet();
    }
    private void incrRespTimes(){
        respTimes.incrementAndGet();
    }
    public void incrErrorTimes(){
        errorTimes.incrementAndGet();
    }
    public void incrRunTime(){
        runTime+=summaryInterval;
    }
    public void incrReqBytes(int bytes){
        reqBytes.addAndGet(bytes);
        incrReqTimes();
    }
    public void incrRespBytes(int bytes){
        respBytes.addAndGet(bytes);
        incrRespTimes();
    }

    public TcpBenchmarkSummary setSummaryInterval(int summaryInterval) {
        this.summaryInterval = summaryInterval;
        return this;
    }

    public AtomicLong getReqTimes() {
        return reqTimes;
    }

    public AtomicLong getRespTimes() {
        return respTimes;
    }

    public AtomicLong getReqBytes() {
        return reqBytes;
    }

    public AtomicLong getRespBytes() {
        return respBytes;
    }

    public AtomicLong getErrorTimes() {
        return errorTimes;
    }

    public int getRunTime() {
        return runTime;
    }
    public Summary calcSummary(){
        Summary summary =new Summary();
        calcTotal(summary);
        calcAvg(summary);
        calcCurrent(summary);
        return summary;
    }
    private void calcTotal(Summary summary){
        summary.setTotalErrorTimes(errorTimes.get());
        summary.setTotalReqTimes(reqTimes.get());
        summary.setTotalRespTimes(respTimes.get());
        summary.setTotalReqBytes(reqBytes.get());
        summary.setTotalRespBytes(respBytes.get());
    }
    private void calcAvg(Summary summary){
        summary.setAvgReqTimes(reqTimes.get()/runTime);
        summary.setAvgRespTimes(respTimes.get()/runTime);
        summary.setAvgReqBytes(reqBytes.get()/runTime);
        summary.setAvgRespBytes(respBytes.get()/runTime);
    }
    private void calcCurrent(Summary summary){
        summary.setCurrentReqTimes(reqTimes.get()-lastReqTimes);
        lastReqTimes = reqTimes.get();
        summary.setCurrentRespTimes(respTimes.get()-lastRespTimes);
        lastRespTimes = respTimes.get();
        summary.setCurrentReqBytes(reqBytes.get()-lastReqBytes);
        lastReqBytes = reqBytes.get();
        summary.setCurrentRespBytes(respBytes.get()-lastRespBytes);
        lastRespBytes = respBytes.get();
    }
    public static class  Summary{
        private long currentReqTimes;
        private long currentRespTimes;
        private long currentReqBytes;
        private long currentRespBytes;
        private long avgReqTimes;
        private long avgRespTimes;
        private long avgReqBytes;
        private long avgRespBytes;
        private long totalReqTimes;
        private long totalRespTimes;
        private long totalReqBytes;
        private long totalRespBytes;
        private long totalErrorTimes;

        public Summary setCurrentReqTimes(long currentReqTimes) {
            this.currentReqTimes = currentReqTimes;
            return this;
        }

        public Summary setCurrentRespTimes(long currentRespTimes) {
            this.currentRespTimes = currentRespTimes;
            return this;
        }

        public Summary setCurrentReqBytes(long currentReqBytes) {
            this.currentReqBytes = currentReqBytes;
            return this;
        }

        public Summary setCurrentRespBytes(long currentRespBytes) {
            this.currentRespBytes = currentRespBytes;
            return this;
        }

        public Summary setTotalErrorTimes(long totalErrorTimes) {
            this.totalErrorTimes = totalErrorTimes;
            return this;
        }

        public Summary setAvgReqTimes(long avgReqTimes) {
            this.avgReqTimes = avgReqTimes;
            return this;
        }

        public Summary setAvgRespTimes(long avgRespTimes) {
            this.avgRespTimes = avgRespTimes;
            return this;
        }

        public Summary setAvgReqBytes(long avgReqBytes) {
            this.avgReqBytes = avgReqBytes;
            return this;
        }

        public Summary setAvgRespBytes(long avgRespBytes) {
            this.avgRespBytes = avgRespBytes;
            return this;
        }

        public Summary setTotalReqTimes(long totalReqTimes) {
            this.totalReqTimes = totalReqTimes;
            return this;
        }

        public Summary setTotalRespTimes(long totalRespTimes) {
            this.totalRespTimes = totalRespTimes;
            return this;
        }

        public Summary setTotalReqBytes(long totalReqBytes) {
            this.totalReqBytes = totalReqBytes;
            return this;
        }

        public Summary setTotalRespBytes(long totalRespBytes) {
            this.totalRespBytes = totalRespBytes;
            return this;
        }

        public long getCurrentReqTimes() {
            return currentReqTimes;
        }

        public long getCurrentRespTimes() {
            return currentRespTimes;
        }

        public long getCurrentReqBytes() {
            return currentReqBytes;
        }

        public long getCurrentRespBytes() {
            return currentRespBytes;
        }

        public long getAvgReqTimes() {
            return avgReqTimes;
        }

        public long getAvgRespTimes() {
            return avgRespTimes;
        }

        public long getAvgReqBytes() {
            return avgReqBytes;
        }

        public long getAvgRespBytes() {
            return avgRespBytes;
        }

        public long getTotalReqTimes() {
            return totalReqTimes;
        }

        public long getTotalRespTimes() {
            return totalRespTimes;
        }

        public long getTotalReqBytes() {
            return totalReqBytes;
        }

        public long getTotalRespBytes() {
            return totalRespBytes;
        }

        public long getTotalErrorTimes() {
            return totalErrorTimes;
        }

        public void printSummary(){
            System.out.println(toString());
        }

        @Override
        public String toString() {
            return "Summary{" +
                    "currentReqTimes=" + currentReqTimes +
                    "| currentRespTimes=" + currentRespTimes +
                    "| currentReqBytes=" + currentReqBytes +
                    "| currentRespBytes=" + currentRespBytes +
                    "| avgReqTimes=" + avgReqTimes +
                    "| avgRespTimes=" + avgRespTimes +
                    "| avgReqBytes=" + avgReqBytes +
                    "| avgRespBytes=" + avgRespBytes +
                    "| totalReqTimes=" + totalReqTimes +
                    "| totalRespTimes=" + totalRespTimes +
                    "| totalReqBytes=" + totalReqBytes +
                    "| totalRespBytes=" + totalRespBytes +
                    "| totalErrorTimes=" + totalErrorTimes+
                    '}';
        }
    }
}
