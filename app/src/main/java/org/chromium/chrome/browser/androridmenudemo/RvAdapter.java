package org.chromium.chrome.browser.androridmenudemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvHolder> {
    private final Context context;
    private final List<String> list;

    public RvAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RvAdapter.RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        return new RvHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RvAdapter.RvHolder holder, final int position) {
        TextView text1 = holder.getText1();
        text1.setText(list.get(position));
        //添加单击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.showContextMenu();
            }
        });

        //添加长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, list.get(position), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class RvHolder extends RecyclerView.ViewHolder {

        private final TextView text1;

        public RvHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            // 在不给各个条目添加长按事件的情况下，如下代码必须添加否则ContextMenu是弹不出来的
            // itemView.setLongClickable(true);
        }

        public TextView getText1() {
            return text1;
        }
    }
}
