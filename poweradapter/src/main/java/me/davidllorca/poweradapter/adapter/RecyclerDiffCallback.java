package me.davidllorca.poweradapter.adapter;

import android.support.v7.util.DiffUtil;

import java.util.List;

import me.davidllorca.poweradapter.item.RecyclerItem;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public class RecyclerDiffCallback extends DiffUtil.Callback {

    private final List<? extends RecyclerItem> oldList;
    private final List<? extends RecyclerItem> newList;

    RecyclerDiffCallback(List<? extends RecyclerItem> oldList, List<? extends RecyclerItem>
            newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

}
