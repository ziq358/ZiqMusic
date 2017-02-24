package com.example;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class testAnnotation {

    /*
    @Documented –注解是否将包含在JavaDoc中
   @Retention –什么时候使用该注解
   @Target –注解用于什么地方
   @Inherited – 是否允许子类继承该注解
    * */

    @Inherited
    @Documented
    @Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
//    @Target({ElementType.FIELD, ElementType.METHOD})// 定义注解能用在什么地方；
    public @interface InheritedAnnotation {

        String value();

    }
    @InheritedAnnotation(value = "Parent's class ")
    static abstract class AbstractParent {

        @InheritedAnnotation(value = "Parent's abstractMethod ")
        @SuppressWarnings("unused")
        public abstract void abstractMethod();

        //没有用SuppressWarnings修饰，编译器会有提示(变灰色，有波浪线)
        public void doExtendss() {
            System.out.println(" AbstractParent doExtends ...");
        }

        @Deprecated
        @InheritedAnnotation(value = "Parent's doExtends")
        public void doExtends() {
            System.out.println(" AbstractParent doExtends ...");
        }


    }

    static class SubClassImpl extends AbstractParent{

        @Override
        public void abstractMethod() {
            doExtends();//被弃用注解修饰了
        }

    }


    public static void main(String[] args) throws SecurityException, NoSuchMethodException{
        Class<SubClassImpl> clazz=SubClassImpl.class;

        //abstractMethod
        System.out.println("abstractMethod：");
        Method method = clazz.getMethod("abstractMethod", new Class[]{});
        if(method.isAnnotationPresent(InheritedAnnotation.class)){
            InheritedAnnotation ma = method.getAnnotation(InheritedAnnotation.class);
            System.out.println("继承了");
            System.out.println("value："+ma.value());
        }else{
            System.out.println("没有继承");
        }

        System.out.println("\n--------------------------------\n");

        //doExtends
        System.out.println("doExtends：");
        Method methodOverride = clazz.getMethod("doExtends", new Class[]{});
        if(methodOverride.isAnnotationPresent(InheritedAnnotation.class)){
            InheritedAnnotation ma = methodOverride.getAnnotation(InheritedAnnotation.class);
            System.out.println("继承了");
            System.out.println("value："+ma.value());
        }else{
            System.out.println("没有继承");
        }

        System.out.println("\n--------------------------------\n");

        System.out.println("child class：");
        if(clazz.isAnnotationPresent(InheritedAnnotation.class)){
            InheritedAnnotation cla = clazz.getAnnotation(InheritedAnnotation.class);
            System.out.println("继承了：");
            System.out.println(cla.value());
        }else{
            System.out.println("没有继承");
        }
    }
}
