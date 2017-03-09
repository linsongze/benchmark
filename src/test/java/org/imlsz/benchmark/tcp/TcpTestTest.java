package org.imlsz.benchmark.tcp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Created by lsz on 2017/3/10.
 */
class TcpTestTest {
    @BeforeAll
    public void before() throws Exception {
        EchoServer.main(null);
    }

    @Test
    void start() throws InterruptedException {
            byte[] data = "Hello".getBytes();
            TcpTest tcpTest = new TcpTest(1,data,"localhost",8007);
            tcpTest.start();
    }

}