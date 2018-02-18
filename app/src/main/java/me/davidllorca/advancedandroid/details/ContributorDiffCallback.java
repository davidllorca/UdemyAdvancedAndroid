package me.davidllorca.advancedandroid.details;

import android.support.v7.util.DiffUtil;

import java.util.List;

import me.davidllorca.advancedandroid.model.Contributor;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 18/02/18.
 */

public class ContributorDiffCallback extends DiffUtil.Callback {

    private final List<Contributor> oldList;
    private final List<Contributor> newList;

    public ContributorDiffCallback(List<Contributor> oldList, List<Contributor> newList) {
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

    // Hashcode are the same
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id() == newList.get(newItemPosition).id();
    }

    // Equals are the same
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
