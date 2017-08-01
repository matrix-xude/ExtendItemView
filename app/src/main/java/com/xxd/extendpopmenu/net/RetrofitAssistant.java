package com.xxd.extendpopmenu.net;

import com.xxd.extendpopmenu.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xxd on 2017/8/1.
 */

public class RetrofitAssistant {

//    private static RetrofitAssistant retrofitAssistant;
//
//    private RetrofitAssistant() {
//    }
//
//    public synchronized static RetrofitAssistant getInstance() {
//        if (retrofitAssistant == null) {
//            synchronized (RetrofitAssistant.class) {
//                if (retrofitAssistant == null) {
//                    retrofitAssistant = new RetrofitAssistant();
//                }
//            }
//        }
//        return retrofitAssistant;
//    }

    /**
     * 获取支持Gson解析的serviece
     * @return
     */
    public static RetrofitServiceGson getGsonService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RetrofitServiceGson service = retrofit.create(RetrofitServiceGson.class);
        return service;
    }
}
