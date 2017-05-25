package com.ifmvo.matthew.custom.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ifmvo.matthew.utils.ImgLoadHelper;
import com.ifmvo.matthew.R;
import com.ifmvo.matthew.utils.print.logger.Logger;

import java.util.ArrayList;


public class ADViewPagerAdapter extends PagerAdapter {
    private ArrayList<BannerBean> ad_list;
    private Context context;

    public ADViewPagerAdapter(ArrayList<BannerBean> ad_list, Context context) {
        this.context = context;
        this.ad_list = ad_list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ad_list != null ? ad_list.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == (View) arg1;
    }

    private int mChildCount = 0;

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
//        if (mChildCount > 0) {
//            mChildCount--;
        return POSITION_NONE;
//        }
//        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        // System.out.println("destroyItem="+position);
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        // TODO Auto-generated method stub
        // final Advertisement ad = ad_list.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.adimg, null);
        ImageView img = (ImageView) view.findViewById(R.id.image);
        img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Logger.d("点击了Banner");
//                String urlStr = ad_list.get(position).getLink();
//                if (StringUtil.isEmpty(urlStr))
//                    return;
//                if (!urlStr.contains("http://")) {
//                    urlStr = "http://" + urlStr;
//                }
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse(urlStr);
//                intent.setData(content_url);
//                context.startActivity(intent);
            }
        });


//        img.setImageResource(ad_list.get(position).imgRes);
        ImgLoadHelper.loadImg(ad_list.get(position).img, img);
//        Logger.d(ad_list.get(position).img);


        container.addView(view);
        return view;
    }
}
