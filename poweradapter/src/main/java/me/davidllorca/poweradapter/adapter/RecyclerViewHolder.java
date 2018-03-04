package me.davidllorca.poweradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import me.davidllorca.poweradapter.item.ItemRenderer;
import me.davidllorca.poweradapter.item.RecyclerItem;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private final ItemRenderer<RecyclerItem> renderer;

    public RecyclerViewHolder(ViewGroup parent, ItemRenderer<RecyclerItem> renderer) {
        super(renderer.createView(parent));
        this.renderer = renderer;
    }

    void bind(RecyclerItem item) {
        renderer.render(itemView, item);
    }

}
