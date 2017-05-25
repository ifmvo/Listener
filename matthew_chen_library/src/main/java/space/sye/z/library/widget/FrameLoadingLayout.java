package space.sye.z.library.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ifmvo.matthew.R;

import space.sye.z.library.manager.RecyclerMode;

/**
 * Created by ZongfaHe on 16/3/25.
 */
public class FrameLoadingLayout extends RefreshLoadingLayout {
    public FrameLoadingLayout(Context context, RecyclerMode mode) {
        super(context, mode);
    }

    //    TextView tvText;
    ImageView ivLoading;

    @Override
    protected void init() {
        super.init();
        View view = LayoutInflater.from(mContext).inflate(R.layout.refreshrecycle_loadinglayout_frame, this, false);
//        tvText = (TextView) view.findViewById(R.id.tvText);
        ivLoading = (ImageView) view.findViewById(R.id.ivLoading);
        addView(view);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void onLoadEndImpl() {
        findViewById(R.id.layParent).setVisibility(View.GONE);
        AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getDrawable();
        animationDrawable.stop();
//        tvText.setText(mContext.getResources().getString(R.string.refreshrecycle_loadend));
    }

    @Override
    protected void onRefreshImpl() {
        findViewById(R.id.layParent).setVisibility(VISIBLE);
        AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getDrawable();
        animationDrawable.start();
//        tvText.setText(mContext.getResources().getString(R.string.animation_list_refreshrecycle_loading));
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
//        alphaAnimation.setDuration(130);
//        findViewById(R.id.tvText).startAnimation(alphaAnimation);
    }

    @Override
    protected void onResetImpl() {
        findViewById(R.id.layParent).setVisibility(GONE);
        AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getDrawable();
        animationDrawable.stop();
//        tvText.setText(mContext.getResources().getString(R.string.refreshrecycle_complete));
    }
}
