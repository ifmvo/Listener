package com.ifmvo.matthew.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.ifmvo.matthew.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;


/**
 * Created by ZongfaHe on 16/7/14.
 */
public class ImgLoadHelper {


    public static void init(Context context) {
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
//                .diskCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null)
                .threadPoolSize(4) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
//                .diskCache(new UnlimitedDiscCache(cacheDir)) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
//                .imageDownloader(new BaseImageDownloader(getContext())) // default
//                .imageDecoder(new BaseImageDecoder()) // default
                .defaultDisplayImageOptions(defOpt) // default
//                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }


    private static DisplayImageOptions defOpt = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.color.transparent)
            .showImageOnLoading(R.color.transparent)
            .cacheInMemory(true)
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
            .cacheOnDisk(true)
//            .displayer(new FadeInBitmapDisplayer(400))
            .bitmapConfig(Bitmap.Config.RGB_565).build();

    public static void loadUserHeadImg(String url, ImageView imageAware) {
        ImageLoader.getInstance().displayImage(
                url, imageAware, getBuilder().showImageOnLoading(R.mipmap.pic_head_def_bg).showImageForEmptyUri(R.mipmap.pic_head_def_bg).showImageOnFail(R.mipmap.pic_head_def_bg).build());
    }

    public static DisplayImageOptions.Builder getBuilder() {
        return new DisplayImageOptions.Builder().cloneFrom(defOpt);
    }

    public static void loadLocImg(String url, ImageView imageAware) {
        ImageLoader.getInstance().displayImage("file://" + url, imageAware);
    }

    public static void loadImg(String url, ImageView imageAware) {
        //TAG防止图片闪烁
        if (!url.equals(imageAware.getTag())) {
            imageAware.setTag(url);
            ImageLoader.getInstance().displayImage(url, imageAware, getBuilder().showImageOnLoading(R.mipmap.ic_default).showImageOnFail(R.mipmap.ic_default).build());
        }
    }

    public static void loadImg(String url, ImageView imageView, DisplayImageOptions opt) {
        ImageLoader.getInstance().displayImage(url, imageView, opt);
    }

    public static void loadImg(String url, ImageView imageView, DisplayImageOptions opt, ImageLoadingListener imageLoadingListener) {
        ImageLoader.getInstance().displayImage(url, imageView, opt, imageLoadingListener);
    }

    public static void loadImg(String url, ImageView imageView, DisplayImageOptions opt, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener progressListener) {
        ImageLoader.getInstance().displayImage(url, imageView, opt, imageLoadingListener, progressListener);
    }

    public static String getListImgType(int w) {
        return "?imageView2/2/w/" + w + "/format/webp/interlace/0/q/85";
    }
}
