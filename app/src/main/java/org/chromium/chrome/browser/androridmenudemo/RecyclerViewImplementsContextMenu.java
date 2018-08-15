package org.chromium.chrome.browser.androridmenudemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

public class RecyclerViewImplementsContextMenu extends RecyclerView {
    private AdapterView.AdapterContextMenuInfo contextMenuInfo;
    public RecyclerViewImplementsContextMenu(Context context) {
        super(context);
    }

    public RecyclerViewImplementsContextMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewImplementsContextMenu(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //重写方法一
    @Override
    public AdapterView.AdapterContextMenuInfo getContextMenuInfo() {
        return contextMenuInfo;
    }

    //重写方法二
    @Override
    public boolean showContextMenuForChild(View originalView) {
        int position = getChildAdapterPosition(originalView);
        long longId = getChildItemId(originalView);
        contextMenuInfo = new AdapterView.AdapterContextMenuInfo(originalView, position, longId);
        return super.showContextMenuForChild(originalView);
    }


}
