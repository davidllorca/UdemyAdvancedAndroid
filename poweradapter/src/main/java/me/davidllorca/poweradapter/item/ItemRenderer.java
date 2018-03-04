package me.davidllorca.poweradapter.item;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public interface ItemRenderer<T extends RecyclerItem> {

    @LayoutRes
    int layoutRes();

    View createView(@NonNull ViewGroup parent);

    void render(@NonNull View itemView, @NonNull T item);

}
