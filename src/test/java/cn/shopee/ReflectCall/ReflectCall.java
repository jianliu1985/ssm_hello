package cn.shopee.ReflectCall;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liujian.lj on 2018/6/29.
 */
public class ReflectCall {
    public static Car initByDefaultConst() throws Throwable
    {


        /* 在JVM中，如何确定一个类型实例？答：全类名吗？不是，是类加载器加上全类名
         * Thread.currentThread().getContextClassLoader()
         * 返回该线程的上下文类加载器。这个上下文类加载器由线程的创建者所提供，为了代码运行在该线程时加载类和资源。
         * 所以这里的loader是当前线程的上下文类加载器。
         * Class.forName(String className) ：加载类，并且执行类初始化
         * ClassLoader.loadClass(String className)：仅仅加载类，不执行类初始化
         */
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader:      "+loader);
        System.out.println("parent loader:       "+loader.getParent());
        //祖父ClassLoader是根类装载器，因为在Java中无法获得它的句柄，所以仅返回null
        System.out.println("grandparent loader:  "+loader.getParent(). getParent());
        /**
         * ①通过类装载器获取Car类对象
         * 1.获取当前线程的ClassLoader
         * 2.通过指定的全限定类“cn.shopee.ReflectCall.Car”装载Car类对应的反射实例
         */
        Class class_car = loader.loadClass("cn.shopee.ReflectCall.Car");

        /**
         * ②获取类的默认构造器对象并通过它实例化Car
         * 1.通过Car的反射类对象获取Car的构造函数对象cons
         * 2.通过构造函数对象的newInstrance()方法实例化Car对象，其效果等同于new Car()
         */
        Constructor cons = class_car.getDeclaredConstructor((Class[])null);
        Car car = (Car)cons.newInstance();

        /**
         * ③获取类的成员变量（包含私有成员），并对成员变量赋值
         */
        Field ownerFld = class_car.getDeclaredField("owner");
        //取消Java语言访问检查以访问private变量
        ownerFld.setAccessible(true);
        ownerFld.set(car,"小明");


        /**
         * ④通过反射方法设置属性
         * 通过Car的反射类对象的getMethod（String methodName,Class paramClass）获取属性的Setter方法对象，
         * 第一个参数是目标Class的方法名；第二个参数是方法入参的对象类型。
         */
        Method setBrand = class_car.getMethod("setBrand",String.class);
        /**
         * 通过invoke（Object obj,Object param）方法调用目标类的方法，
         * 该方法的第一个参数是操作的目标类对象实例；第二个参数是目标方法的入参。
         */
        setBrand.invoke(car,"红旗CA72");

        Method setColor = class_car.getMethod("setColor", String.class);
        setColor.invoke(car, "黑色");
        Method setMaxSpeed = class_car.getMethod("setMaxSpeed",int.class);
        setMaxSpeed.invoke(car, 200);

        /**
         * ⑤获取类的私有方法，并调用它
         */
        Method whoHasItMtd = class_car.getDeclaredMethod("whoHasIt",(Class[])null);
        //取消Java语言访问检查以访问private方法
        whoHasItMtd.setAccessible(true);
        whoHasItMtd.invoke(car,(Object[])null);

        return car;
    }

    public static void main(String[] args) throws Throwable {
        Car car = initByDefaultConst();
        car.introduce();
    }
}

