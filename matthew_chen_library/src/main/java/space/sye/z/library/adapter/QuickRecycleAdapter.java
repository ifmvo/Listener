package space.sye.z.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ZongfaHe on 16/3/25.
 */
public abstract class QuickRecycleAdapter<T> extends BaseQuickRecycleAdapter<T, BaseViewHolder> {
    protected final int layoutResId;

    public QuickRecycleAdapter(Context context, int layoutResId) {
        super(context);
        this.layoutResId = layoutResId;
    }

    public QuickRecycleAdapter(Context context, int layoutResId, List<T> data) {
        super(context, data);
        this.layoutResId = layoutResId;
    }

    @Override
    protected RecyclerView.ViewHolder onGetViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.get(context, parent, layoutResId);
    }
}
