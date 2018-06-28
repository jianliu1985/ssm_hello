package cn.shopee.qa;

/**
 * Created by liujian.lj on 2017/12/7.
 */
/**
 * 一个损坏的钟实现
 */
public class  BrokenClock extends Clock {

    @Override
    void checkState(Integer i) {
        System.out.println(i.toString());
        throw new IllegalStateException();

    }



    @Override
    void delay() throws InterruptedException {

        Thread.sleep(5000L);
    }

    public static void main(String args[]) {
        System.out.println("Hello World!");
        BrokenClock nc = new BrokenClock();
        try {
            nc.loopReport();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
