package cn.shopee.ReflectCall;

/**
 * Created by liujian.lj on 2018/6/29.
 */
public class Car {
    private String brand;
    private String color;
    private int maxSpeed;
    //私有成员，没有对应的get和set方法；
    private String owner;

    //①默认构造函数
    public Car(){}

    //②带参构造函数
    public Car(String brand,String color,int maxSpeed){
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    //③未带参的方法
    public void introduce() {
        System.out.println("brand:"+brand+"; color:"+color+"; maxSpeed:" +maxSpeed);
    }

    //私有方法
    private void whoHasIt(){
        System.out.println("The owner of this car is:" + this.owner );
    }

    //参数的getter/Setter方法
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}