package com.dell.month_demo.view;

import com.dell.month_demo.bean.News;

import java.util.List;

/**
 * Created by DELL on 2017/11/23.
 */

public interface Iview {

    void ShowMessage(List<News.DataBean> list);
}
