package cn.shopee.aop;

/**
 * Created by liujian.lj on 2018/6/28.
 * ×î¼òµ¥µÄAOP
 */
public class LunchAop implements Lunch {

    @Override
    public void eat(String menu) {
        before();
        System.out.println("eat! " + menu);
        after();
    }

    private void before() {
        System.out.println("Wash hand£¡");
    }

    private void after() {
        System.out.println("Brush the bowl£¡");
    }

    public static void main(String[] args) {
        Lunch lunchImpl = new LunchAop();
        lunchImpl.eat("Kung Pao Chicken");
    }

}
