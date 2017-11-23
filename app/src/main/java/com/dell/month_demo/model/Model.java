package com.dell.month_demo.model;

import com.dell.month_demo.api.Api;
import com.dell.month_demo.api.ApiService;
import com.dell.month_demo.bean.News;
import com.dell.month_demo.widget.media.AndroidMediaController;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DELL on 2017/11/23.
 */

public class Model implements Imodel {

    OnFinish onFinish;

    public interface  OnFinish{
        void onFinishListener(List<News.DataBean> list);
    }

    public void setOnFinish(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void ReQuestMessage(String url) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Api.HOME_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
       ApiService apiService = retrofit.create(ApiService.class);
        Observable<News> bean = apiService.getData();
        bean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(News news) {
                        List<News.DataBean> list = news.getData();
                        onFinish.onFinishListener(list);
                    }
                });
    }
}
