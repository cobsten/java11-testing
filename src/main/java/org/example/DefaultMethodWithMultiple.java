package org.example;

public class DefaultMethodWithMultiple{
    public static void main (String[] args){
        Interface1 face1 = new Interface1() {};
        face1.method();
        Interface2 face2 = new Interface2() {};
        face2.method();
        Interface3 face3 = new Interface3() {};
        face3.method();
    }

public interface Interface1{
    default void method(){
        System.out.println("Result1");
    }
}

public interface Interface2{
    default void method(){
        System.out.println("Result2");
    }
}

public interface Interface3 extends Interface1,Interface2{

        default void method(){
            Interface1.super.method();
            Interface2.super.method();
            System.out.println("Result3");
        }
}

}

