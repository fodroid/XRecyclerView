# XRecyclerView
Material Design风格，支持下拉刷新及加载更多的RecyclerView

# Function 功能特点

* 官方Material Design风格。
* 支持自动刷新、下来刷新、自动加载更多。

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

详细的使用方法可以查看`app`文件夹中<br>
* 在xml中引入XRecyclerView<br>
<pre><code>
    <me.shihao.library.XRecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</pre></code>

