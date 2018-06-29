package cn.shopee.aop;

/**
 * Created by liujian.lj on 2018/6/28.
 * ¾²Ì¬´úÀí
 */
public class LunchProxy implements Lunch {

    private LunchImpl lunchImpl;

    public LunchProxy(LunchImpl lunchImpl) {
        this.lunchImpl = lunchImpl;
    }

    @Override
    public void eat(String menu) {
        before();
        lunchImpl.eat(menu);
        after();
    }

    private void before() {
        System.out.println("Wash hand again£¡");
    }

    private void after() {
        System.out.println("Brush the bowl again£¡");
    }

    public static void main(String[] args) {
        Lunch lunchProxy = new LunchProxy(new LunchImpl());
        lunchProxy.eat("Kung Pao Chicken");
    }
}
