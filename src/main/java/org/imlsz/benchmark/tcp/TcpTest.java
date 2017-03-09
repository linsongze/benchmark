package org.imlsz.benchmark.tcp;

/**
 * Created by lsz on 2017/3/9.
 */
public class TcpTest {
    private int clientSize;
    private byte[] data;
    private String host;
    private int port;
    private TcpBenchmarkSummary tcpBenchmarkSummary;
    public TcpTest(int clientSize, byte[] data, String host, int port) {
        this.clientSize = clientSize;
        this.data = data;
        this.host = host;
        this.port = port;
        this.tcpBenchmarkSummary= new TcpBenchmarkSummary();
    }

    public int getClientSize() {
        return clientSize;
    }

    public TcpTest setClientSize(int clientSize) {
        this.clientSize = clientSize;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public TcpTest setData(byte[] data) {
        this.data = data;
        return this;
    }

    public String getHost() {
        return host;
    }

    public TcpTest setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public TcpTest setPort(int port) {
        this.port = port;
        return this;
    }
    public void start() throws InterruptedException {
        for (int i = 0 ; i < this.clientSize ; i++){
            final TcpBenchmarkClient client = new NettyBenchmarkClient(host,port).setSummary(TcpTest.this.tcpBenchmarkSummary);
            client.connect();
            new Thread(new Runnable() {
                public void run() {
                         while (true){
                             client.send(data);
                         }
                }
            });
        }

        while (true){
            Thread.sleep(this.tcpBenchmarkSummary.getSummaryInterval()*1000);
            this.tcpBenchmarkSummary.incrRunTime();
            TcpBenchmarkSummary.Summary summary = tcpBenchmarkSummary.calcSummary();
            summary.printSummary();
        }
    }
}
