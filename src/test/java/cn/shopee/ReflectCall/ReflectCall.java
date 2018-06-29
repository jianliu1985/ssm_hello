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


        /* ��JVM�У����ȷ��һ������ʵ������ȫ�����𣿲��ǣ��������������ȫ����
         * Thread.currentThread().getContextClassLoader()
         * ���ظ��̵߳�����������������������������������̵߳Ĵ��������ṩ��Ϊ�˴��������ڸ��߳�ʱ���������Դ��
         * ���������loader�ǵ�ǰ�̵߳����������������
         * Class.forName(String className) �������࣬����ִ�����ʼ��
         * ClassLoader.loadClass(String className)�����������࣬��ִ�����ʼ��
         */
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader:      "+loader);
        System.out.println("parent loader:       "+loader.getParent());
        //�游ClassLoader�Ǹ���װ��������Ϊ��Java���޷�������ľ�������Խ�����null
        System.out.println("grandparent loader:  "+loader.getParent(). getParent());
        /**
         * ��ͨ����װ������ȡCar�����
         * 1.��ȡ��ǰ�̵߳�ClassLoader
         * 2.ͨ��ָ����ȫ�޶��ࡰcn.shopee.ReflectCall.Car��װ��Car���Ӧ�ķ���ʵ��
         */
        Class class_car = loader.loadClass("cn.shopee.ReflectCall.Car");

        /**
         * �ڻ�ȡ���Ĭ�Ϲ���������ͨ����ʵ����Car
         * 1.ͨ��Car�ķ���������ȡCar�Ĺ��캯������cons
         * 2.ͨ�����캯�������newInstrance()����ʵ����Car������Ч����ͬ��new Car()
         */
        Constructor cons = class_car.getDeclaredConstructor((Class[])null);
        Car car = (Car)cons.newInstance();

        /**
         * �ۻ�ȡ��ĳ�Ա����������˽�г�Ա�������Գ�Ա������ֵ
         */
        Field ownerFld = class_car.getDeclaredField("owner");
        //ȡ��Java���Է��ʼ���Է���private����
        ownerFld.setAccessible(true);
        ownerFld.set(car,"С��");


        /**
         * ��ͨ�����䷽����������
         * ͨ��Car�ķ���������getMethod��String methodName,Class paramClass����ȡ���Ե�Setter��������
         * ��һ��������Ŀ��Class�ķ��������ڶ��������Ƿ�����εĶ������͡�
         */
        Method setBrand = class_car.getMethod("setBrand",String.class);
        /**
         * ͨ��invoke��Object obj,Object param����������Ŀ����ķ�����
         * �÷����ĵ�һ�������ǲ�����Ŀ�������ʵ�����ڶ���������Ŀ�귽������Ρ�
         */
        setBrand.invoke(car,"����CA72");

        Method setColor = class_car.getMethod("setColor", String.class);
        setColor.invoke(car, "��ɫ");
        Method setMaxSpeed = class_car.getMethod("setMaxSpeed",int.class);
        setMaxSpeed.invoke(car, 200);

        /**
         * �ݻ�ȡ���˽�з�������������
         */
        Method whoHasItMtd = class_car.getDeclaredMethod("whoHasIt",(Class[])null);
        //ȡ��Java���Է��ʼ���Է���private����
        whoHasItMtd.setAccessible(true);
        whoHasItMtd.invoke(car,(Object[])null);

        return car;
    }

    public static void main(String[] args) throws Throwable {
        Car car = initByDefaultConst();
        car.introduce();
    }
}

