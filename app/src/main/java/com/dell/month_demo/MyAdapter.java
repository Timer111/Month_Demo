package com.dell.month_demo;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dell.month_demo.bean.News;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/11/23.
 */

public class MyAdapter extends RecyclerView.Adapter {

    List<News.DataBean> list;
    private MyViewHolder myViewHolder;
    private OnClickLisener onClickLisener;
    private ImageLoader imageLoader;
    Context context;
    private final DisplayImageOptions options;

    public OnClickLisener getOnClickLisener() {
        return onClickLisener;
    }

    public void setOnClickLisener(OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;
    }

    public interface OnClickLisener {
        void OnDainji(View v, int position);

        void OnLong(View v, int position);
    }

    public MyAdapter(Context context,List<News.DataBean> list) {

        this.list = list;
        this.context = context;
        imageLoader = ImageLoader.getInstance();
        File file = new File(Environment.getExternalStorageDirectory(), "Bwei");
        if (!file.exists())
            file.mkdirs();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .diskCache(new UnlimitedDiskCache(file))
                .build();

        imageLoader.init(configuration);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheOnDisk(true)
                .build();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        myViewHolder = (MyViewHolder) holder;
        myViewHolder.textView.setText(list.get(position).getContent());
//        ImageLoader.getInstance().displayImage(list.get(position).getImage_url(), myViewHolder.imageView);
        getimage(list.get(position).getImage_url(), myViewHolder.imageView);

        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLisener.OnDainji(view, position);
            }
        });
        myViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClickLisener.OnLong(view, position);
                return true;
            }
        });
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLisener.OnDainji(view, position);
            }
        });
        myViewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClickLisener.OnLong(view, position);
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        private final TextView textView;
        private final ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_tv);
            imageView = itemView.findViewById(R.id.item_img);


        }
    }

    public void getimage(String path, ImageView imageView) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        ImageLoader.getInstance().displayImage(path, imageView, options);


    }


}
