# XRecyclerView
Material Design风格，支持下拉刷新及加载更多的RecyclerView

<img src="https://github.com/fodroid/XRecyclerView/blob/master/capture/4744071.gif" width="480" height="800" />
# Function 功能特点

* 官方Material Design风格。
* 支持自动刷新、下来刷新、自动加载更多。
* 支持添加多个Header和Footer。
* 封装了RecyclerAdapter，使用更方便。

# Usage 使用
`gradle`

Add it in your root build.gradle at the end of repositories:

    allprojects {
	    repositories {
		    ...
		    maven { url 'https://www.jitpack.io' }
        }
    }

Add the dependency

    dependencies {
	        compile 'com.github.fodroid:XRecyclerView:v1.0'
	}

`maven`

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://www.jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
        <groupId>com.github.fodroid</groupId>
        <artifactId>XRecyclerView</artifactId>
        <version>v1.0</version>
    </dependency>

####详细的使用方法可以查看`app`文件夹中<br>
#####1.在xml中引入XRecyclerView<br>
    <me.shihao.library.XRecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
#####2.在java代码中设置<br>
    xRecyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
    adapter = new TestAdapter(this);
    xRecyclerView.verticalLayoutManager().setAdpter(adapter);
    //xRecyclerView.horizontalLayoutManager().setAdapter(adapter);
#####3.设置刷新监听<br>
    xRecyclerView.setOnRefreshListener(new XRecyclerView.OnRefreshListener() {
        @Override
        public void onRefresh() {
            //刷新
        }

        @Override
        public void onLoadMore() {
            //加载更多
        }
    });
#####4.数据获取完成后通知界面`必须调用`<br>
    xRecyclerView.refreshComlete();
####其它常用方法
* 自动刷新 `xRecyclerView.autoRefresh()`。
* 添加Header、Footer `xRecyclerView.addHeaderView(View view)` `xRecyclerView.addFooterView(View view)`。
* 数据加载后没有更多数据了，可以设置 `xRecyclerView.loadMoreNoData()`。再次滑动到底部时可以显示相应的文字，且不会再加载数据。
* 如果继承了`RecyclerAdapter`，对数据的增删可以调用`adapter.addElement` `adapter.removeElement`。

# 相关文章

[如何简单的为Recycleview实现刷新和加载更多](http://www.jianshu.com/p/923889dafff5)

### 如果你觉得有用，请不吝star

# 鸣谢：
#####ProgressBar使用了DreaminginCodeZH的开源库，大家可以去详细了解。（[MaterialProgressBar传送门](https://github.com/DreaminginCodeZH/MaterialProgressBar) ）
