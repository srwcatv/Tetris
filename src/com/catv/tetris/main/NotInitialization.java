package com.catv.tetris.main;

class SSClass {
    static {
        System.out.println("SSClass");
    }
}
class SupperClass extends SSClass {
    static {
        System.out.println("SupperClass init!");
    }

    public static int value = 123;

    public SupperClass(){
        System.out.println("init SupperClass");
    }

    public void print(){
        System.out.println("test");
    }
}
class SubClass extends SupperClass{
    static {
        System.out.println("SubClass init");
    }

    static int a;

    public SubClass(){
        System.out.println("init SubClass");
    }
}
class ConstClass{
    static {
        System.out.println("ConstClass init!");
    }
    public static final String HELLOWORLD = "hello world";

    public static void print(){
        System.out.println("静态方法");
    }
}
public class NotInitialization{
    public static void main(String [] args){
        System.out.println(ConstClass.HELLOWORLD);
    }
}
