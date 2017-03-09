package org.imlsz.benchmark.commons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


/** 
 * 通用压力测试类 
 * Created by lsz on 14-10-24. 
 */  
public class CommonsTest {  
    private int threadNum = 1;
    private int sleepTime = 1000;
    private long lastTimes = 0;
    private int x = 0;
    private AtomicLong now = new AtomicLong();  
    private AtomicLong maxTime = new AtomicLong(0l);  
    private ExecutorService pool = null;
    private Runnable runnable;
  
    public CommonsTest(int threadNum, Runnable runnable) {
        this.runnable= runnable;
        this.threadNum = threadNum;  
        pool = Executors.newScheduledThreadPool(this.threadNum);
  
    }  
    CommonsTest(int threadNum){  
        this(threadNum,null);  
    }  
    CommonsTest(Runnable runnable){
        this(5,runnable);
    }  
  
    public static CommonsTest create(){  
        return new CommonsTest(5);  
    }  
    public CommonsTest setThreadNum(int threadNum){  
        this.threadNum = threadNum;  
        return this;  
    }  
    public CommonsTest setApply(Runnable runnable){
        this.runnable = runnable;
        return this;
    }

    private String format = "---------------------"+"当前1s内的执行数"+"/"+"总的执行数"+"/"+"平均的执行数"+"/"+"执行的时间"+"/"+"最长执行时间";
    public void start(){
        for(int i = 0 ; i<threadNum;i ++ ){  
            pool.execute(new TestThread());  
        }  
        while(true){
            try {  
                x++;  
                Thread.sleep(sleepTime);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            long nowtmp = now.get();  
            System.out.println(format);  
            System.out.println("---------------------"+(nowtmp - lastTimes)+"/"+now.intValue()+"/"+(now.intValue()/x)+"/"+x+"/"+maxTime.get()+"ms");  
            lastTimes = nowtmp;  
        }  
    }  
  
    class TestThread extends Thread{  
        public TestThread() {  
  
        }  
        @Override  
        public void run() {  
            System.out.println("start------------------"+Thread.currentThread().getName());  
            while (true){  
                try {  
                    long start = System.currentTimeMillis();
                    runnable.run();
                    long end = System.currentTimeMillis() - start;
                    if(end>maxTime.get()){  
                        maxTime.set(end);  
                    }  
                    now.incrementAndGet();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
  
            }  
        }
    }  
}  