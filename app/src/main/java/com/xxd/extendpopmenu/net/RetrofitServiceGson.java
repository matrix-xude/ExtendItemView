package com.xxd.extendpopmenu.net;

import com.xxd.extendpopmenu.entity.BackHotal;
import com.xxd.extendpopmenu.entity.BackHotalBase;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by xxd on 2017/8/1.
 * <p>
 * Retrofit支撑的方法接口，调用此接口中的方法即为直接请求该接口
 * 此接口中所有网络请求都是用Gson解析的接口（如果需要用xml，或者自定义数据格式解析接口数据，请添加其他类）
 **/

public interface RetrofitServiceGson {

    /**
     * 酒店搜索提示
     *
     * @param searchContent 搜索的内容
     * @param num           需要提示的行数，即条数
     * @return
     */
    @POST("hotelbooking/reserve/serch/serchHint")
    @FormUrlEncoded
    Call<BackHotalBase> hotalSearchHint(@Field("serchInput") String searchContent, @Query("resultNumLimit") int num);

    /**
     * 分类检索某个单独筛选条件下的酒店
     * @param code
     * @param name
     * @param currentPage
     * @param pageCount
     * @return
     */
    @POST("hotelbooking/reserve/serch/serchCategory")
    @FormUrlEncoded
    Call<BackHotalBase> hotalClassifySearch(@Query("paramCode") String code, @Field("paramName") String name,
                                     @Field("pageNo") String currentPage, @Field("pageCount") String pageCount);

}
