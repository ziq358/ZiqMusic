package com.example;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by john on 24/02/2017.
 */

public class networkRequestAnnotation {


    @Documented
    @Target(METHOD)
    @Retention(RUNTIME)
    public @interface ReqType {

        /**
         * 请求方式枚举
         *
         */
        enum ReqTypeEnum{ GET,POST,DELETE,PUT};

        /**
         * 请求方式
         * @return
         */
        ReqTypeEnum reqType() default ReqTypeEnum.POST;
    }

    @Documented
    @Target(METHOD)
    @Retention(RUNTIME)
    public @interface ReqUrl {
        String reqUrl() default "";
    }

    @Documented
    @Target(PARAMETER)
    @Retention(RUNTIME)
    public @interface ReqParam {
        String value() default "";
    }

    public interface IReqApi {

        @ReqType(reqType = ReqType.ReqTypeEnum.POST)//声明采用post请求
        @ReqUrl(reqUrl = "www.xxx.com/openApi/login")//请求Url地址
        String login(@ReqParam("userId") String userId, @ReqParam("pwd") String pwd);//参数用户名 密码

    }

    static class NetWorkProxy{
        public <T> T create(final Class<T> service) {
            return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                    new InvocationHandler() {

                        @Override
                        public Object invoke(Object proxy, Method method, Object... args)
                                throws Throwable {// Annotation[]  methodAnnotations = method.getAnnotations();//拿到函数注解数组

                            System.out.println("reqParam---args-> length = " + args.length);

                            ReqType reqType = method.getAnnotation(ReqType.class);
                            System.out.println("IReqApi---reqType->" + (reqType.reqType() == ReqType.ReqTypeEnum.POST ? "POST" : "OTHER"));
                            ReqUrl reqUrl = method.getAnnotation(ReqUrl.class);
                            System.out.println("IReqApi---reqUrl->" + reqUrl.reqUrl());
                            Type[] parameterTypes = method.getGenericParameterTypes();
                            System.out.println("IReqApi---parameterTypes->" + parameterTypes.toString());

                            Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();//拿到参数注解
                            for (int i = 0; i < parameterAnnotationsArray.length; i++) {
                                Annotation[] annotations = parameterAnnotationsArray[i];
                                if (annotations != null) {
                                    ReqParam reqParam = (ReqParam) annotations[0];
                                    System.out.println("reqParam---reqParam->" + reqParam.value() + "==" + args[i]);
                                }
                            }
                            //下面就可以执行相应的网络请求获取结果 返回结果
                            String result = "";//这里模拟一个结果

                            return result;
                        }
                    });
        }
    }



    public static void main(String[] args) {
        NetWorkProxy netWorkProxy = new NetWorkProxy();//在这里类要用static ，访问的问题
        IReqApi api = netWorkProxy.create(IReqApi.class);
        api.login("whoislcj", "123456");
    }
}
