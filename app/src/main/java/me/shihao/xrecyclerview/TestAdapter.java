package me.shihao.xrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.shihao.library.RecyclerAdapter;

/**
 * Created by shihao <1406504841@qq.com> on 2017/2/13.16:51
 */

public class TestAdapter extends RecyclerAdapter<String, TestAdapter.RecViewHolder> {

    public TestAdapter(Context context) {
        super(context);
    }

    public TestAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_test, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecViewHolder holder, final int position) {
        holder.textView.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecItemClick() != null)
                    getRecItemClick().onItemClick(position, data.get(position), holder);
            }
        });
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public RecViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
