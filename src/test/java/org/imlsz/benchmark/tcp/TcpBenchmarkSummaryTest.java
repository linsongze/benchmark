package org.imlsz.benchmark.tcp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by lsz on 2017/3/9.
 */
public class TcpBenchmarkSummaryTest {
    @Test
    public void testSummary(){
        TcpBenchmarkSummary tcpBenchmarkSummary = new TcpBenchmarkSummary();
        tcpBenchmarkSummary.incrReqBytes(10);
        tcpBenchmarkSummary.incrRespBytes(20);
        tcpBenchmarkSummary.incrErrorTimes();
        tcpBenchmarkSummary.incrRunTime();
        tcpBenchmarkSummary.incrRunTime();

        TcpBenchmarkSummary.Summary summary = tcpBenchmarkSummary.calcSummary();
        summary.printSummary();

    }
}