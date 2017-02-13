# XRecyclerView
Material Design风格，支持下拉刷新及加载更多的RecyclerView

#功能特点 function

*官方Material Design风格。*

*支持下来刷新，自动加载更多。*

#Usage 使用
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