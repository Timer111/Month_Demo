package com.dell.month_demo.presenter;

import com.dell.month_demo.bean.News;
import com.dell.month_demo.model.Model;
import com.dell.month_demo.view.Iview;

import java.util.List;

/**
 * Created by DELL on 2017/11/23.
 */

public class Presenter implements Model.OnFinish {

    Iview iview;
    Model model;

    public Presenter(Iview iview) {
        this.iview = iview;
        model = new Model();
        model.setOnFinish(this);
    }

    public void getUrl(String url){
        model.ReQuestMessage(url);
    }

    @Override
    public void onFinishListener(List<News.DataBean> list) {
        iview.ShowMessage(list);
    }
}
