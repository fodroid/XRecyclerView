package me.shihao.xrecyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import me.shihao.library.RecyclerItemCallback;
import me.shihao.library.XRecyclerView;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView xRecyclerView;
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xRecyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        adapter = new TestAdapter(this);
        xRecyclerView.verticalLayoutManager().setAdapter(adapter);

        xRecyclerView.setOnRefreshListener(new XRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++)
                            adapter.addElement("Item " + i);
                        xRecyclerView.refreshComlete();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++)
                            adapter.addElement("Item " + i);
                        xRecyclerView.refreshComlete();
                    }
                }, 3000);
            }
        });

        xRecyclerView.autoRefresh();

        adapter.setRecItemClick(new RecyclerItemCallback<String, TestAdapter.RecViewHolder>() {
            @Override
            public void onItemClick(int position, String model, TestAdapter.RecViewHolder holder) {
                super.onItemClick(position, model, holder);
                Toast.makeText(MainActivity.this, "position " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
