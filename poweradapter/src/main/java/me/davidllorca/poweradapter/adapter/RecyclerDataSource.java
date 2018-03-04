package me.davidllorca.poweradapter.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.davidllorca.poweradapter.item.ItemRenderer;
import me.davidllorca.poweradapter.item.RecyclerItem;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public class RecyclerDataSource {

    private final Map<String, ItemRenderer<? extends RecyclerItem>> renderers;
    @SuppressLint("UseSparseArrays")
    private final Map<Integer, String> viewTypeToRendererKeyMap = new HashMap<>();
    private final List<RecyclerItem> data = new ArrayList<>();

    private WeakReference<RecyclerView.Adapter> adapterReference = new WeakReference<RecyclerView
            .Adapter>(null);

    public RecyclerDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
        this.renderers = renderers;
        for (Map.Entry<String, ItemRenderer<? extends RecyclerItem>> entry : renderers.entrySet()) {
            viewTypeToRendererKeyMap.put(entry.getValue().layoutRes(), entry.getKey());
        }
    }

    ItemRenderer<RecyclerItem> rendererForType(int viewType) {
        //noinspection unchecked
        return (ItemRenderer<RecyclerItem>) renderers.get(viewTypeToRendererKeyMap.get(viewType));
    }

    public void setData(List<? extends RecyclerItem> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RecyclerDiffCallback(data,
                newData));
        data.clear();
        data.addAll(newData);
        RecyclerView.Adapter adapter = adapterReference.get();
        if (adapter != null) {
            diffResult.dispatchUpdatesTo(adapter);
        }
    }

    @LayoutRes
    int viewResourceForPosition(int position) {
        return renderers.get(data.get(position).redenrKey()).layoutRes();
    }

    int getCount() {
        return data.size();
    }

    RecyclerItem getItem(int position) {
        return data.get(position);
    }

    void attachToAdapter(RecyclerView.Adapter adapter) {
        adapterReference = new WeakReference<>(adapter);
    }
}
