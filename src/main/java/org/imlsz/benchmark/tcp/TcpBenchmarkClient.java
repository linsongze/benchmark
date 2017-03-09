package org.imlsz.benchmark.tcp;

/**
 * Created by lsz on 2017/3/9.
 */
public interface TcpBenchmarkClient {
    void send(byte[] bytes);
    TcpBenchmarkClient setSummary(TcpBenchmarkSummary tcpBenchmarkSummary);
    TcpBenchmarkSummary getSummary();
    void connect() throws InterruptedException;
}
