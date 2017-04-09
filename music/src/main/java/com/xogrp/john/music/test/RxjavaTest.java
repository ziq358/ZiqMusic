package com.xogrp.john.music.test;

import android.text.AndroidCharacter;

import com.xogrp.john.music.test.model.School;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class RxjavaTest {

    public final static String TAG = "ziq";

    public static void main(String[] args) {
        testObservable();
//        testPrintSchoolName();
    }

    public static void printLog(String tag, String msg){
        System.out.println(tag+" -- "+msg);
    }

    public static void testObservable(){
        Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                printLog(TAG, "call: Observable.OnSubscribe");
                subscriber.onNext("Hi，Weavey！");  //发送数据"Hi，Weavey！"
            }
        });
        Observer<String> receiver = new Observer<String>() {
            @Override
            public void onCompleted() {
                //数据接收完成时调用
            }

            @Override
            public void onError(Throwable e) {
                //发生错误调用
            }

            @Override
            public void onNext(String s) {
                //正常接收数据调用
                System.out.print(s);  //将接收到来自sender的问候"Hi，Weavey！"
            }
        };
        //调用了subscribe方法 ，才有回调
        sender.subscribe(receiver);
    }

    public static void testPrintSchoolName(){
        //for RxJava 1
        List<School> schoolList = School.getSchoolData();
        //from 会遍历
        Observable.from(schoolList)
                .map(new Func1<School, String>() {
                        @Override
                        public String call(School school) {
                            printLog(TAG, "call: Func1");
                            return school.getName();
                        }
                    }
                )
                .subscribe(new Action1<String>() {
                        @Override
                        public void call(String schoolName) {
                            printLog(TAG, "call: "+schoolName);
                        }
                    }
                );
    }

}
