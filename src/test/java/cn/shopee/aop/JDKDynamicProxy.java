package cn.shopee.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liujian.lj on 2018/6/28.
 * JDK¶¯Ì¬´úÀí
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public Object getProxy() {
        return  Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("Wash hand£¡");
    }

    private void after() {
        System.out.println("Brush the bowl£¡");
    }

    public static void main(String[] args) {
        Lunch lunch = (Lunch) new JDKDynamicProxy(new LunchImpl()).getProxy();
        lunch.eat("Kung Pao Chicken");
    }
}
