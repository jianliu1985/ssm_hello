package cn.shopee.qa;

/**
 * Created by liujian.lj on 2017/12/7.
 */
/**
 * 一个正常的钟实现
 */
public class NormalClock extends Clock {

    @Override
    void checkState(Integer i) {
        return;
    }

    @Override
    void delay() throws InterruptedException {
        Thread.sleep(1000L);
    }

    public static void main(String args[]) {
        System.out.println("Hello World!");
        NormalClock nc = new NormalClock();
        try {
            nc.loopReport();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}