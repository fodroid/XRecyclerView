package me.shihao.xrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

            }

            @Override
            public void onLoadMore() {

            }
        });

    }
}
