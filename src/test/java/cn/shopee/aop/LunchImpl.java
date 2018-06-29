package cn.shopee.aop;

/**
 * Created by liujian.lj on 2018/6/28.
 */
public class LunchImpl implements Lunch {

    @Override
    public void eat(String menu) {
        System.out.println("eat£º " + menu);
    }
}
